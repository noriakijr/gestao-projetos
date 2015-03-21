package modelo.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Atividade;
import modelo.EntidadeNegocio;
import modelo.dao.IDAO;
import modelo.dao.IDAOPrazos;

public class AtividadeDAO extends AbstractDAO implements IDAO, IDAOPrazos {

	@Override
	public List<EntidadeNegocio> Pesquisar(EntidadeNegocio en) {
		List<EntidadeNegocio> atividades = new ArrayList<>();
		Atividade atividade = (Atividade)en;

		try{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("atividade_id atividade_id, ");
			sql.append("nome nome_atividade, ");
			sql.append("descricao descricao_atividade, ");
			sql.append("qtde_horas qtde_horas, ");
			sql.append("status_atividade status_atividade ");
			sql.append("FROM atividades ");
			if(atividade.getId() != 0)
				sql.append("WHERE atividade_id = ? ");
			else if(atividade.getTarefa().getId() != 0)
				sql.append("WHERE tarefa_id = ? ");
			sql.append("ORDER BY atividade_id;");

			PreparedStatement ps = connection.prepareStatement(sql.toString());

			if(atividade.getId() != 0)
				ps.setInt(1, atividade.getId());
			else if(atividade.getTarefa().getId() != 0)
				ps.setInt(1, atividade.getTarefa().getId());

			ResultSet result = ps.executeQuery();

			while(result.next()){
				if(en.getId() == 0)
					atividade = new Atividade();
				atividade.setId(result.getInt("atividade_id"));
				atividade.setNome(result.getString("nome_atividade"));
				atividade.setDescricao(result.getString("descricao_atividade"));
				atividade.setQtde_horas(result.getInt("qtde_horas"));
				atividade.setStatus(result.getBoolean("status_atividade"));
				atividades.add(atividade);
			}

		} catch (SQLException e) {}
		return atividades;
	}

	@Override
	public int PesquisarPrazo(EntidadeNegocio en) {
		if(en.getClass().getName().equals("modelo.Tarefa")){
			try{
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT ");
				sql.append("sum(a.qtde_horas) total_horas ");
				sql.append("FROM tarefas t, atividades a ");
				sql.append("where t.tarefa_id = ? and t.tarefa_id = a.tarefa_id and a.status_atividade = false;");

				PreparedStatement ps = connection.prepareStatement(sql.toString());

				ps.setInt(1, en.getId());

				ResultSet result = ps.executeQuery();

				while(result.next()){
					return result.getInt("total_horas");
				}

			} catch (SQLException e) {}
		}
		if(en.getClass().getName().equals("modelo.Projeto")){
			try{
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT ");
				sql.append("sum(a.qtde_horas) total_horas ");
				sql.append("FROM tarefas t, atividades a, projetos p ");
				sql.append("where p.pro_id = ? and t.pro_id = p.pro_id and a.tarefa_id = t.tarefa_id and a.status_atividade = false;");

				PreparedStatement ps = connection.prepareStatement(sql.toString());

				ps.setInt(1, en.getId());

				ResultSet result = ps.executeQuery();

				while(result.next()){
					return result.getInt("total_horas");
				}

			} catch (SQLException e) {}
		}

		return 0;
	}

	@Override
	public void Alterar(EntidadeNegocio en) {
		// TODO Auto-generated method stub
		Atividade atividade = (Atividade)en;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE ");
			sql.append("atividades ");
			sql.append("SET nome = ?, qtde_horas = ?, descricao = ?, status_atividade = ? ");
			sql.append("WHERE atividade_id = ?;");
			PreparedStatement ps = connection.prepareStatement(sql.toString());

			ps.setString(1, atividade.getNome());
			ps.setInt(2, atividade.getQtde_horas());
			ps.setString(3, atividade.getDescricao());
			ps.setBoolean(4, atividade.isStatus());
			ps.setInt(5, atividade.getId());
			ps.execute();
		}catch(SQLException e){ }
	}
	
	@Override
	public void Excluir(EntidadeNegocio en) {
		// TODO Auto-generated method stub
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE ");
			sql.append("FROM atividades ");
			sql.append("WHERE atividade_id = ?;");
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			Atividade atividade = (Atividade)en;

			ps.setInt(1, atividade.getId());
			ps.execute();
		}catch(SQLException e){ }
	}

	@Override
	public void Cadastrar(EntidadeNegocio en) {
		Atividade atividade = (Atividade)en;
			try{
				StringBuilder sql = new StringBuilder();
				sql.append("INSERT INTO ");
				sql.append("atividades ");
				sql.append("(nome, tarefa_id, qtde_horas, descricao, status_atividade) ");
				sql.append("VALUES(?, ?, ?, ?, ?);");

				PreparedStatement ps = 
						connection.prepareStatement(sql.toString());

				ps.setString(1, atividade.getNome());
				ps.setInt(2, atividade.getTarefa().getId());
				ps.setInt(3, atividade.getQtde_horas());
				ps.setString(4, atividade.getDescricao());
				ps.setBoolean(5, false);

				ps.execute();
			}catch(SQLException e){}
		}

	
	@Override
	public void AtualizarStatusPrazo(EntidadeNegocio en, String status) {
		// TODO Auto-generated method stub
		
	}

}
