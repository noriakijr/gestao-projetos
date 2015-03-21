package modelo.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import modelo.EntidadeNegocio;
import modelo.Funcionario;
import modelo.Habilidade;
import modelo.Tarefa;
import modelo.dao.IDAO;

public class FuncionarioDAO extends AbstractDAO implements IDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<EntidadeNegocio> Pesquisar(EntidadeNegocio en) {
		List<EntidadeNegocio> funcionarios = new ArrayList<>();
		Funcionario funcionario = (Funcionario)en;
		IDAO daoHabilidade = new HabilidadeDAO();
		IDAO daoTarefa = new TarefaDAO();

		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("fun_id, ");
			sql.append("nome, ");
			sql.append("tipo_acesso, ");
			sql.append("cargo, ");
			sql.append("login, ");
			sql.append("senha ");
			sql.append("FROM funcionarios ");
			if(en.getId() != 0)
				sql.append("WHERE fun_id = ? ");
			else if(funcionario.getLogin() != null && funcionario.getSenha() != null)
				sql.append("WHERE login = ? AND senha = ? ");

			sql.append("ORDER BY fun_id;");

			PreparedStatement ps = connection.prepareStatement(sql.toString());
			// funcionario existe, id != 0
			if(en.getId() != 0)
				ps.setInt(1, en.getId());
			// usuario possui login?
			else if(funcionario.getLogin() != null && funcionario.getSenha() != null){
				ps.setString(1, funcionario.getLogin());
				ps.setString(2, funcionario.getSenha());
			}

			ResultSet result = ps.executeQuery();
			// buscar todos os funcionários
			while(result.next()){
				if(en.getId() == 0 && (funcionario.getLogin() == null || funcionario.getSenha() == null))
					funcionario = new Funcionario(); // cria referência do funcionario
				funcionario.setId(result.getInt("fun_id"));
				funcionario.setNome(result.getString("nome"));
				funcionario.setCargo(result.getString("cargo"));
				funcionario.setTipo_acesso(result.getString("tipo_acesso"));
				funcionario.setHabilidades((List<Habilidade>)((Object) daoHabilidade.Pesquisar(funcionario)));
				//funcionario.setTarefas((List<Tarefa>)(Object)daoTarefa.Pesquisar(new Tarefa(funcionario)));
				funcionarios.add(funcionario);
			}
		} catch (SQLException e) { }
		return funcionarios;
	}

	@Override
	public void Cadastrar(EntidadeNegocio en) {
		// TODO Auto-generated method stub
		Funcionario funcionario = (Funcionario)en;
		StringBuilder sql;
		PreparedStatement ps;
		try{
			if(funcionario.getId() == 0){
				sql = new StringBuilder();
				sql.append("INSERT INTO ");
				sql.append("funcionarios ");
				sql.append("(nome, email, tipo_acesso, login, senha, cargo, telefone) ");
				sql.append("values(?,?,?,?,?,?,?);");

				ps = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

				ps.setString(1, funcionario.getNome());
				ps.setString(2, funcionario.getEmail());
				ps.setString(7, funcionario.getTelefone());
				ps.setString(4, funcionario.getLogin());
				ps.setString(5, funcionario.getSenha());
				ps.setString(6, funcionario.getCargo());
				ps.setString(3, funcionario.getTipo_acesso());

				ps.execute();

				//Obtem os ids gerados na inserção
				ResultSet keys = ps.getGeneratedKeys();
				if (keys.next()) {
					//atribui o id ao id do estado
					funcionario.setId(keys.getInt(1));
				}
				sql.setLength(0);
			}
			for (Habilidade habilidade : funcionario.getHabilidades()) {
				sql = new StringBuilder();
				sql.append("INSERT INTO ");
				sql.append("hab_fun ");
				sql.append("(hab_id, fun_id) ");
				sql.append("values((SELECT hab_id FROM habilidades WHERE nome=?),?);");

				ps = connection.prepareStatement(sql.toString());

				ps.setString(1, habilidade.getNome());
				ps.setInt(2, funcionario.getId());

				ps.execute();
			}



			connection.close();

		}catch(SQLException e){}

	}

	@Override
	public void Alterar(EntidadeNegocio en) {
		// TODO Auto-generated method stub
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE ");
			sql.append("funcionarios ");
			sql.append("SET nome = ?, tipo_acesso = ?, login = ?, senha = ?, cargo = ? ");
			sql.append("WHERE fun_id = ?;");
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			Funcionario func = (Funcionario)en;

			ps.setString(1, func.getNome());
			ps.setString(2, func.getTipo_acesso());
			ps.setString(3, func.getLogin());
			ps.setString(4, func.getSenha());
			ps.setString(5, func.getCargo());
			ps.setInt(6, func.getId());
			ps.execute();
		}catch(SQLException e){ }
	}

	@Override
	public void Excluir(EntidadeNegocio en) {
		// TODO Auto-generated method stub
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE ");
			sql.append("FROM hab_fun ");
			sql.append("WHERE fun_id = ?;");

			PreparedStatement ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, en.getId());

			ps.execute();

			sql = new StringBuilder();
			sql.append("DELETE ");
			sql.append("FROM funcionarios ");
			sql.append("WHERE fun_id = ?;");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, en.getId());
			ps.execute();
		}catch(SQLException e){ }
	}

}
