package modelo.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.EntidadeNegocio;
import modelo.Projeto;
import modelo.Tarefa;
import modelo.dao.IDAO;
import modelo.dao.IDAOPrazos;

public class ProjetoDAO extends AbstractDAO implements IDAO, IDAOPrazos {

	@Override
	public List<EntidadeNegocio> Pesquisar(EntidadeNegocio en) {
		List<EntidadeNegocio> projetos = new ArrayList<>();
		Projeto projeto = (Projeto)en;
		
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("p.pro_id pro_id, ");
			sql.append("p.nome nome_projeto, ");
			sql.append("p.descricao descricao_projeto, ");
			sql.append("p.gerente_id gerente_id, ");
			sql.append("to_char(p.data_inicio,'yyyy-mm-dd') data_inicio_projeto, ");
			sql.append("to_char(p.data_fim,'yyyy-mm-dd') data_fim_projeto, ");
			sql.append("p.status_projeto status_projeto, ");
			sql.append("f.nome nome_gerente ");
			sql.append("FROM projetos p, funcionarios f ");
			
			if(projeto.getId() != 0)
				sql.append("WHERE p.pro_id = ? and p.gerente_id = f.fun_id ");
			else
				sql.append("WHERE p.gerente_id = f.fun_id ");
			sql.append("ORDER BY nome_projeto;");

			PreparedStatement ps = connection.prepareStatement(sql.toString());
			
			if(projeto.getId() != 0)
				ps.setInt(1, projeto.getId());
			
			ResultSet result = ps.executeQuery();
			
			while(result.next()){
				if(en.getId() == 0)
					projeto = new Projeto();
				projeto.setId(result.getInt("pro_id"));
				projeto.setNome(result.getString("nome_projeto"));
				projeto.setDescricao(result.getString("descricao_projeto"));
				projeto.getGerente().setId(result.getInt("gerente_id"));
				projeto.setData_inicio(result.getString("data_inicio_projeto"));
				projeto.setData_fim(result.getString("data_fim_projeto"));
				projeto.setStatus(result.getString("status_projeto"));
				projeto.getGerente().setNome(result.getString("nome_gerente"));
				projetos.add(projeto);
			}

		} catch (SQLException e) {}
		return projetos;
	}

	@Override
	public int PesquisarPrazo(EntidadeNegocio en) {
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("data_fim - current_date prazo ");
			sql.append("FROM projetos ");
			sql.append("WHERE pro_id = ?;");

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
			sql.append("UPDATE projetos ");
			sql.append("SET status_projeto = ? ");
			sql.append("WHERE pro_id = ?;");

			PreparedStatement ps = connection.prepareStatement(sql.toString());
			
			ps.setString(1, status);
			ps.setInt(2, en.getId());
			
			ps.execute();

		} catch (SQLException e) {}
	}

	@Override
	public void Cadastrar(EntidadeNegocio en) {
		// TODO Auto-generated method stub
		Projeto projeto = (Projeto)en;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO ");
			sql.append("projetos ");
			sql.append("(nome, descricao, status_projeto, gerente_id, data_inicio, data_fim) ");
			sql.append("values(?,?,?,?, to_date(?,'yyyy-mm-dd'), to_date(?,'yyyy-mm-dd'));");
			
			PreparedStatement ps = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, projeto.getNome());
			ps.setString(2, projeto.getDescricao());
			ps.setString(3, "");
			ps.setInt(4, projeto.getGerente().getId());
			ps.setString(5, projeto.getData_inicio());
			ps.setString(6, projeto.getData_fim());
			
			ps.execute();
			
			//Obtem os ids gerados na inserção
			ResultSet keys = ps.getGeneratedKeys();
			if (keys.next()) {
				//atribui o id ao id do estado
				projeto.setId(keys.getInt(1));
			}
			sql.setLength(0);
			
			for (Tarefa tarefa : projeto.getTarefas()) {
				IDAO dao = new TarefaDAO();
				tarefa.getProjeto().setId(projeto.getId());
				dao.Cadastrar(tarefa);
			}
			
		}catch(SQLException e){}
	}

	@Override
	public void Alterar(EntidadeNegocio en) {
		// TODO Auto-generated method stub
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE ");
			sql.append("projetos ");
			sql.append("SET nome = ?, descricao = ?, gerente_id = ?, data_inicio = to_date(?,'yyyy-mm-dd'), data_fim = to_date(?,'yyyy-mm-dd') ");
			sql.append("WHERE pro_id = ?;");
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			Projeto projeto = (Projeto)en;
			
			ps.setString(1, projeto.getNome());
			ps.setString(2, projeto.getDescricao());
			ps.setInt(3, projeto.getGerente().getId());
			ps.setString(4, projeto.getData_inicio());
			ps.setString(5, projeto.getData_fim());
			ps.setInt(6, projeto.getId());
			ps.execute();
		}catch(SQLException e){	}
	}

	@Override
	public void Excluir(EntidadeNegocio en) {
		// TODO Auto-generated method stub
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM atividades a ");
			sql.append("USING tarefas t, projetos p ");
			sql.append("WHERE a.tarefa_id = t.tarefa_id AND t.pro_id = p.pro_id AND p.pro_id = ?;");
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			Projeto projeto = (Projeto)en;
			ps.setInt(1, projeto.getId());
			ps.execute();
			StringBuilder sql2 = new StringBuilder();
			sql2.append("DELETE FROM tarefas t ");
			sql2.append("USING projetos p ");
			sql2.append("WHERE t.pro_id = p.pro_id AND p.pro_id = ?;");
			PreparedStatement ps2 = connection.prepareStatement(sql2.toString());
			ps2.setInt(1, projeto.getId());
			ps2.execute();
			StringBuilder sql3 = new StringBuilder();
			sql3.append("DELETE FROM projetos ");
			sql3.append("WHERE pro_id = ?;");
			PreparedStatement ps3 = connection.prepareStatement(sql3.toString());
			ps3.setInt(1, projeto.getId());
			ps3.execute();
		}catch(SQLException e){ }
	}

}
