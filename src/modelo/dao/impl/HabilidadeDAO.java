package modelo.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.EntidadeNegocio;
import modelo.Habilidade;
import modelo.dao.IDAO;

public class HabilidadeDAO extends AbstractDAO implements IDAO {

	@Override
	public List<EntidadeNegocio> Pesquisar(EntidadeNegocio en) {
		// TODO Auto-generated method stub
		List<EntidadeNegocio> habilidades = new ArrayList<>();
		Habilidade habilidade = new Habilidade();
		if(en.getClass().getName().equals("modelo.Habilidade"))
			habilidade = (Habilidade)en;
		
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("* ");
			if(en.getClass().getName().equals("modelo.Habilidade"))
				sql.append("FROM habilidades;");
			else{
				sql.append("FROM hab_fun JOIN habilidades ");
				sql.append("USING(hab_id) ");
				sql.append("WHERE fun_id = ?;");
			}
			
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			
			if(en.getId() != 0)
				ps.setInt(1, en.getId());
			
			ResultSet result = ps.executeQuery();
			
			while(result.next()){
				if(en.getId() == 0 || !en.getClass().getName().equals("modelo.Habilidade"))
					habilidade = new Habilidade();
				habilidade.setId(result.getInt("hab_id"));
				habilidade.setNome(result.getString("nome"));
				habilidades.add(habilidade);
			}
		}catch(SQLException e){}
		return habilidades;
	}

	@Override
	public void Cadastrar(EntidadeNegocio en) {
		// TODO Auto-generated method stub
		Habilidade habilidade = (Habilidade)en;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO ");
			sql.append("habilidades ");
			sql.append("(nome) ");
			sql.append("values(?);");
			
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			
			ps.setString(1, habilidade.getNome());
			
			ps.execute();
			
		}catch(SQLException e){}
	}

	@Override
	public void Alterar(EntidadeNegocio en) {
		// TODO Auto-generated method stub
		Habilidade habilidade = (Habilidade)en;
		
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE habilidades ");
			sql.append("SET nome = ? ");
			sql.append("WHERE hab_id = ?;");
		
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			
			ps.setString(1, habilidade.getNome());
			ps.setInt(2, habilidade.getId());
			
			ps.execute();
			
		}catch(SQLException e){}

	}

	@Override
	public void Excluir(EntidadeNegocio en) {
		// TODO Auto-generated method stub
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE ");
			sql.append("FROM hab_fun ");
			sql.append("WHERE hab_id = ?");
			
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			
			ps.setInt(1, en.getId());
			
			ps.execute();
			
			sql = new StringBuilder();
			
			sql.append("DELETE ");
			sql.append("FROM habilidades ");
			sql.append("WHERE hab_id = ?");
			
			ps = connection.prepareStatement(sql.toString());
			
			ps.setInt(1, en.getId());
			
			ps.execute();
			
		}catch(SQLException e){}
	}

}
