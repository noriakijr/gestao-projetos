package modelo.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Atividade;
import modelo.EntidadeNegocio;
import modelo.Habilidade;
import modelo.Tarefa;
import modelo.dao.IDAO;
import modelo.dao.IDAOPrazos;

public class TarefaDAO extends AbstractDAO implements IDAO, IDAOPrazos {

	@Override
	public List<EntidadeNegocio> Pesquisar(EntidadeNegocio en) {
		List<EntidadeNegocio> tarefas = new ArrayList<>();
		Tarefa tarefa = (Tarefa)en;

		try{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("t.tarefa_id tar_id, ");
			sql.append("t.nome nome_tarefa, ");
			sql.append("t.descricao descricao_tarefa, ");
			sql.append("to_char(t.data_inicio, 'yyyy-mm-dd') data_inicio_tarefa, ");
			sql.append("to_char(t.data_fim, 'yyyy-mm-dd') data_fim_tarefa, ");
			sql.append("t.status_tarefa status_tarefa, ");
			sql.append("t.prioridade prioridade, ");
			sql.append("t.pro_id pro_id, ");
			sql.append("f.nome nome_funcionario, ");
			sql.append("f.fun_id fun_id, ");
			sql.append("(SELECT nome FROM habilidades WHERE hab_id = habilidade_id) habilidade ");
			sql.append("FROM tarefas t LEFT JOIN funcionarios f ");
			sql.append("ON t.fun_id = f.fun_id ");
			if(tarefa.getId() != 0)
				sql.append("WHERE t.tarefa_id = ? ");
			else if(tarefa.getProjeto().getId() != 0)
				sql.append("WHERE t.pro_id = ? ");
			else if(tarefa.getFuncionario().getId() != 0)
				sql.append("WHERE t.fun_id = ? ");
			sql.append("ORDER BY tar_id;");

			PreparedStatement ps = connection.prepareStatement(sql.toString());

			if(tarefa.getId() != 0)
				ps.setInt(1, tarefa.getId());
			else if(tarefa.getProjeto().getId() != 0)
				ps.setInt(1, tarefa.getProjeto().getId());
			else if(tarefa.getFuncionario().getId() != 0)
				ps.setInt(1, tarefa.getFuncionario().getId());

			ResultSet result = ps.executeQuery();

			while(result.next()){
				if(en.getId() == 0)
					tarefa = new Tarefa();
				tarefa.setId(result.getInt("tar_id"));
				tarefa.setNome(result.getString("nome_tarefa"));
				tarefa.setDescricao(result.getString("descricao_tarefa"));
				tarefa.setRequisito(new Habilidade(result.getString("habilidade")));
				tarefa.setData_inicio(result.getString("data_inicio_tarefa"));
				tarefa.setData_fim(result.getString("data_fim_tarefa"));
				tarefa.setStatus(result.getString("status_tarefa"));
				tarefa.setPrioridade(result.getInt("prioridade"));
				tarefa.getFuncionario().setId(result.getInt("fun_id"));
				tarefa.getFuncionario().setNome(result.getString("nome_funcionario"));
				tarefa.getProjeto().setId(result.getInt("pro_id"));
				tarefas.add(tarefa);
			}

		} catch (SQLException e) {}
		return tarefas;
	}

	@Override
	public int PesquisarPrazo(EntidadeNegocio en) {
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("data_fim - current_date prazo ");
			sql.append("FROM tarefas ");
			sql.append("WHERE tarefa_id = ?;");

			PreparedStatement ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, en.getId());

			ResultSet result = ps.executeQuery();

			while(result.next()){
				return result.getInt("prazo");
			}

		} catch (SQLException e) {}
		return 0;
	}

	@Override
	public void AtualizarStatusPrazo(EntidadeNegocio en, String status) {
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE tarefas ");
			sql.append("SET status_tarefa = ? ");
			sql.append("WHERE tarefa_id = ?;");

			PreparedStatement ps = connection.prepareStatement(sql.toString());

			ps.setString(1, status);
			ps.setInt(2, en.getId());

			ps.execute();

		} catch (SQLException e) {}

	}

	@Override
	public void Alterar(EntidadeNegocio en) {
		// TODO Auto-generated method stub
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE ");
			sql.append("tarefas ");
			sql.append("SET nome = ?, ");
			sql.append("descricao = ?, ");
			sql.append("fun_id = ?, ");
			sql.append("habilidade_id = (SELECT hab_id FROM habilidades WHERE nome = ?), ");
			sql.append("prioridade = ?, ");
			sql.append("data_inicio = to_date(?,'yyyy-mm-dd'), ");
			sql.append("data_fim = to_date(?,'yyyy-mm-dd') ");
			sql.append("WHERE tarefa_id = ?;");
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			Tarefa tarefas = (Tarefa)en;

			ps.setString(1, tarefas.getNome());
			ps.setString(2, tarefas.getDescricao());
			if(tarefas.getFuncionario().getId() != 0)
				ps.setInt(3, tarefas.getFuncionario().getId());
			else
				ps.setNull(3, 0);
			ps.setString(4, tarefas.getRequisito().getNome());
			ps.setInt(5, tarefas.getPrioridade());
			ps.setString(6, tarefas.getData_inicio());
			ps.setString(7, tarefas.getData_fim());
			ps.setInt(8, tarefas.getId());
			ps.execute();
		}catch(SQLException e){ }
	}

	@Override
	public void Excluir(EntidadeNegocio en) {

		try{
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM atividades a ");
			sql.append("USING tarefas t, projetos p ");
			sql.append("WHERE a.tarefa_id = t.tarefa_id AND t.pro_id = p.pro_id AND t.tarefa_id = ?;");
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			Tarefa tarefas = (Tarefa)en;
			ps.setInt(1, tarefas.getId());
			ps.execute();
			StringBuilder sql2 = new StringBuilder();
			sql2.append("DELETE FROM tarefas t ");
			sql2.append("USING projetos p ");
			sql2.append("WHERE t.pro_id = p.pro_id AND t.tarefa_id = ?;");
			PreparedStatement ps2 = connection.prepareStatement(sql2.toString());
			ps2.setInt(1, tarefas.getId());
			ps2.execute();


		}catch(SQLException e){ }
	}

	@Override
	public void Cadastrar(EntidadeNegocio en) {
		Tarefa tarefa = (Tarefa)en;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO ");
			sql.append("tarefas ");
			sql.append("(nome, descricao, pro_id, prioridade, status_tarefa, data_inicio, data_fim, habilidade_id) ");
			sql.append("VALUES (?,?,?,?,?, to_date(?,'yyyy-mm-dd'), to_date(?,'yyyy-mm-dd'), (SELECT hab_id FROM habilidades WHERE nome = ?));");

			PreparedStatement ps = 
					connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);


			ps.setString(1, tarefa.getNome());
			ps.setString(2, tarefa.getDescricao());
			ps.setInt(3, tarefa.getProjeto().getId());
			ps.setInt(4, tarefa.getPrioridade());
			ps.setString(5, "");
			ps.setString(6, tarefa.getData_inicio());
			ps.setString(7, tarefa.getData_fim());
			ps.setString(8, tarefa.getRequisito().getNome());


			ps.execute();

			//Obtem os ids gerados na inserção
			ResultSet keys = ps.getGeneratedKeys();
			if (keys.next()) {
				//atribui o id ao id do estado
				tarefa.setId(keys.getInt(1));
			}
			sql.setLength(0);
			
			for (Atividade atividade : tarefa.getAtividades()) {
				IDAO dao = new AtividadeDAO();
				atividade.getTarefa().setId(tarefa.getId());
				dao.Cadastrar(atividade);
			}

		}catch(SQLException e){}
	}


}
