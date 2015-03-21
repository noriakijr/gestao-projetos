package modelo.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;



import util.Conexao;

public abstract class AbstractDAO {

	protected Connection connection;
		
	
	public AbstractDAO(){
		try {
			connection = Conexao.getConnection();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
