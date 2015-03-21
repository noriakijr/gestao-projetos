package modelo.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.EntidadeNegocio;
import modelo.Mensagem;
import modelo.dao.IDAO;

public class MensagensDAO extends AbstractDAO implements IDAO{

	public List<EntidadeNegocio> Pesquisar(EntidadeNegocio en) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List<EntidadeNegocio> mensagens = new ArrayList<>();
		Mensagem mensagem = (Mensagem)en;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT distinct ");
			sql.append("m.mensagem_id mensagem_id, ");
			sql.append("m.texto texto, ");
			sql.append("m.data_envio data_envio, ");
			sql.append("m.remetente_id remetente_id, ");
			sql.append("m.destinatario_id destinatario_id, ");
			sql.append("m.status_mensagem status_mensagem ");
			sql.append("FROM mensagens m " );
			if(en.getId() != 0)
				sql.append("WHERE m.mensagem_id = ?");
			else if(mensagem.getEmissorID() != 0)
				sql.append("WHERE m.remetente_id = ?");
			else if(mensagem.getReceptorID() != 0)
				sql.append("WHERE m.destinatario_id = ?");
				
			
					
				
			
			//sql.append("WHERE destinatario_id = ?");
			
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			if(en.getId() != 0)
				ps.setInt(1, en.getId());
			else if(mensagem.getEmissorID() != 0)
				ps.setInt(1, mensagem.getEmissorID());
			else if(mensagem.getReceptorID() != 0)
				ps.setInt(1, mensagem.getReceptorID());
			
			ResultSet result = ps.executeQuery();
			
			while(result.next()){
				if(en.getId() == 0)
					mensagem = new Mensagem();
				mensagem.setId(result.getInt("mensagem_id"));
				mensagem.setTexto(result.getString("texto"));
				mensagem.setDataEnvio(result.getDate("data_envio"));
				mensagem.setEmissorID(result.getInt("remetente_id"));
				mensagem.setReceptorID(result.getInt("destinatario_id"));
				mensagem.setStatus(result.getBoolean("status_mensagem"));
				mensagens.add(mensagem);
			}
		} catch (SQLException e) {}
		return mensagens;
	}

	@Override
	public void Cadastrar(EntidadeNegocio en) {
		// TODO Auto-generated method stub
		Mensagem mensagem = (Mensagem)en;
		
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO mensagens(");
			sql.append("texto, ");
			sql.append("data_envio, ");
			sql.append("remetente_id, ");
			sql.append("destinatario_id, ");
			sql.append("status_mensagem) ");
			sql.append("values(?, ?, ?, ?, ?);");
			
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			
			ps.setString(1, mensagem.getTexto());
			ps.setDate(2,  new java.sql.Date(mensagem.getDataEnvio().getTime()));
			ps.setInt(3, mensagem.getEmissorID());
			ps.setInt(4, mensagem.getReceptorID());
			ps.setBoolean(5, mensagem.getStatus());
			
			ps.execute();

		} catch (SQLException e) {
		}
		
	}

	@Override
	public void Alterar(EntidadeNegocio en){
		Mensagem mensagem = (Mensagem)en;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE mensagens ");
			sql.append("SET status_mensagem = false ");
			sql.append("where mensagem_id = ?");
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ps.setInt(1, mensagem.getId());
			ps.execute();
		}catch(SQLException e){}
	}

	@Override
	public void Excluir(EntidadeNegocio en) {
		// TODO Auto-generated method stub
		Mensagem mensagem = (Mensagem)en;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE ");
			sql.append("FROM mensagens ");
			sql.append("WHERE mensagem_id = ?;");
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ps.setInt(1, mensagem.getId());
			ps.execute();
		}catch(SQLException e){}
		
	}
	

}
