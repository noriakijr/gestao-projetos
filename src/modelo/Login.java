package modelo;

import java.util.List;

import controle.command.ICommand;
import controle.command.impl.LoginCommand;

public class Login extends Funcionario{

	public static Login instancia;
	
	private Login(){
	}
	/*
	public void setLogin(Login login){
		instancia = login;
	}
	
	public static String getTipo_acesso() {
		return funcionarioLogado.getTipo_acesso();
	}
	public static String getLogin() {
		return funcionarioLogado.getLogin();
	}
	public static String getCargo() {
		return funcionarioLogado.getCargo();
	}
	public static List<Tarefa> getTarefas() {
		return funcionarioLogado.getTarefas();
	}
	public static List<Habilidade> getHabilidades() {
		return funcionarioLogado.getHabilidades();
	}
	
	public static int getId(){
		return funcionarioLogado.getId();
	}
		
	public static String getNome(){
		return funcionarioLogado.getNome();
	}*/

	public static Login getInstance(){
		if(instancia == null){
			instancia = new Login();	
		}

		return instancia;
	}
}
