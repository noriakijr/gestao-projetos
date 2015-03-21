package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Conexao {
	public static Connection getConnection() 
			throws ClassNotFoundException, SQLException{
		String driver = "org.postgresql.Driver";
		String url = "jdbc:postgresql://localhost:5432/gestaoprojetos";
		String user = "postgres";
		String password = "123456";
		Connection con = null;
		Class.forName( driver );
		try {	// verifica se o banco existe		
			con = DriverManager.getConnection( url, user, password);
			
		} catch (SQLException e) {
			try {	// se o database gestaoprojetos não existe, será criado
				String dtb = "CREATE DATABASE gestaoprojetos;";
				con = DriverManager.getConnection("jdbc:postgresql://localhost:5432?user=postgres&password=123456");
				
				Statement stm = con.createStatement();
				stm.executeUpdate(dtb);
				
			} catch (SQLException e1) {	// Não foi possível criar o banco de dados
				e1.printStackTrace();
			}
			try {
				con = DriverManager.getConnection(url, user, password);
				String sql = "CREATE TABLE atividades(atividade_id     SERIAL NOT NULL , "
					    + "nome             VARCHAR (50) NOT NULL , "
					    + "tarefa_id        INTEGER NOT NULL , "
					    + "qtde_horas       INTEGER NOT NULL , "
					    + "descricao        VARCHAR (500) , "
					    + "status_atividade BOOLEAN); "
					    + "ALTER TABLE atividades ADD CONSTRAINT atividades_PK PRIMARY KEY (atividade_id); "
					    + "CREATE TABLE funcionarios (fun_id      SERIAL NOT NULL , "
					    + "nome        VARCHAR (40) NOT NULL , "
				        + "email       VARCHAR (100) NOT NULL , "
				        + "telefone    VARCHAR (20) NOT NULL , "
				        + "tipo_acesso VARCHAR (30) NOT NULL , "
				        + "login       VARCHAR (50) NOT NULL , "
				        + "senha       VARCHAR (20) NOT NULL , "
				        + "cargo       VARCHAR (30) NOT NULL); "
					    + "ALTER TABLE funcionarios ADD CONSTRAINT funcionarios_PK PRIMARY KEY (fun_id); "
					    + "CREATE TABLE hab_fun ( hab_id INTEGER NOT NULL , fun_id INTEGER NOT NULL); "
					    + "ALTER TABLE hab_fun ADD CONSTRAINT hab_fun_PK PRIMARY KEY (hab_id, fun_id); "
					    + "CREATE TABLE habilidades ( hab_id SERIAL NOT NULL , nome VARCHAR (100) NOT NULL); "
					    + "ALTER TABLE habilidades ADD CONSTRAINT habilidades_PK PRIMARY KEY (hab_id); "
					    + "CREATE TABLE mensagens(mensagem_id     SERIAL NOT NULL , "
				        + "texto           VARCHAR (500) NOT NULL , "
				        + "data_envio      DATE NOT NULL , "
				        + "remetente_id    INTEGER NOT NULL , "
				        + "destinatario_id INTEGER NOT NULL , "
				        + "status_mensagem BOOLEAN); "
					    + "ALTER TABLE mensagens ADD CONSTRAINT mensagens_PK PRIMARY KEY (mensagem_id); "
					    + "CREATE TABLE projetos (pro_id         SERIAL NOT NULL , "
				        + "nome           VARCHAR (50) NOT NULL , "
				        + "descricao      VARCHAR (500) , "
				        + "status_projeto VARCHAR (30) , "
				        + "gerente_id     INTEGER NOT NULL NOT NULL , "
				        + "data_inicio    DATE NOT NULL , "
				        + "data_fim       DATE NOT NULL); "
					    + "ALTER TABLE projetos ADD CONSTRAINT projetos_PK PRIMARY KEY (pro_id); "
					    + "CREATE TABLE tarefas (tarefa_id     SERIAL NOT NULL , "
				        + "nome          VARCHAR (50) NOT NULL , "
				        + "descricao     VARCHAR (100) , "
				        + "pro_id        INTEGER NOT NULL , "
				        + "fun_id        INTEGER , "
				        + "prioridade    INTEGER NOT NULL , "
				        + "status_tarefa VARCHAR (30) , "
				        + "data_inicio   DATE NOT NULL , "
				        + "data_fim      DATE NOT NULL , "
				        + "habilidade_id INTEGER NOT NULL); "
					    + "ALTER TABLE tarefas ADD CONSTRAINT tarefas_PK PRIMARY KEY (tarefa_id); "
					    + "ALTER TABLE atividades ADD CONSTRAINT atividades_tarefas_FK FOREIGN KEY ( tarefa_id ) "
					    + "REFERENCES tarefas ( tarefa_id ); "
					    + "ALTER TABLE hab_fun ADD CONSTRAINT hab_fun_funcionarios_FK FOREIGN KEY ( fun_id ) "
					    + "REFERENCES funcionarios ( fun_id ); "
					    + "ALTER TABLE hab_fun ADD CONSTRAINT hab_fun_habilidades_FK FOREIGN KEY ( hab_id ) "
					    + "REFERENCES habilidades ( hab_id ); "
					    + "ALTER TABLE mensagens ADD CONSTRAINT mensagens_funcionarios_FK FOREIGN KEY ( remetente_id ) "
					    + "REFERENCES funcionarios ( fun_id ); "
					    + "ALTER TABLE mensagens ADD CONSTRAINT mensagens_funcionarios_FKv2 FOREIGN KEY ( destinatario_id ) "
					    + "REFERENCES funcionarios ( fun_id ); "
					    + "ALTER TABLE projetos ADD CONSTRAINT projetos_funcionarios_FK FOREIGN KEY ( gerente_id ) "
					    + "REFERENCES funcionarios ( fun_id ); "
					    + "ALTER TABLE tarefas ADD CONSTRAINT tarefas_funcionarios_FK FOREIGN KEY ( fun_id ) "
					    + "REFERENCES funcionarios ( fun_id ); "
					    + "ALTER TABLE tarefas ADD CONSTRAINT tarefas_habilidades_FK FOREIGN KEY ( habilidade_id ) "
					    + "REFERENCES habilidades ( hab_id ); "
					    + "ALTER TABLE tarefas ADD CONSTRAINT tarefas_projetos_FK FOREIGN KEY ( pro_id ) "
					    + "REFERENCES projetos ( pro_id ); "
					    + "INSERT INTO funcionarios (fun_id, nome, email, telefone, tipo_acesso, login, senha, cargo) "
					    + "VALUES (1, 'Admin', 'admin@admin', '000', 'Administrador', 'admin', 'admin', '@@@'); ";

				Statement stm = con.createStatement();
				stm.executeUpdate(sql);
				
			} catch (SQLException e2) {	//Não foi possível criar as tabelas do banco de dados!
				e2.printStackTrace();
			}
		}
		return con;
	}
	

}
