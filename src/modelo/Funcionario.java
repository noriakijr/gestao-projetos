package modelo;

import java.util.ArrayList;
import java.util.List;

public class Funcionario extends Pessoa{
	
	private String tipo_acesso;
	private String login;
	private String senha;
	private String cargo;
	private List<Tarefa> tarefas = new ArrayList<>();
	private List<Habilidade> habilidades = new ArrayList<>();
	
	public Funcionario(){
		
	}
	public Funcionario(int idFuncionario){			
		this.id = idFuncionario;
		
	}

	
	public String getTipo_acesso() {
		return tipo_acesso;
	}
	public void setTipo_acesso(String tipo_acesso) {
		this.tipo_acesso = tipo_acesso;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}
	public void addTarefa(Tarefa tarefa) {
		this.tarefas.add(tarefa);
	}
	public void removerTarefa(Tarefa tarefa){
		this.tarefas.remove(this.tarefas.indexOf(tarefa));
	}
	
	public List<Tarefa> getTarefas() {
		return tarefas;
	}
	public void setHabilidades(List<Habilidade> habilidades) {
		this.habilidades = habilidades;
	}
	public List<Habilidade> getHabilidades() {
		return habilidades;
	}
	public void addHabilidade(Habilidade habilidade) {
		this.habilidades.add(habilidade);
	}
	
	
	public boolean validar(){
		if((nome.equals("") || nome == null) || (idade == 0) || (email.equals("") || email == null) || 
			(tipo_acesso.equals("") || tipo_acesso == null) || (login.equals("") || login == null) ||
			(senha.equals("") || senha == null) || (cargo.equals("") || cargo == null))
					return false;		
		return true;
	}
}
