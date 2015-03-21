package modelo;

import java.util.ArrayList;
import java.util.List;

public class Tarefa extends EntidadeNegocio{
	
	private String nome;
	private String descricao;
	private int prioridade;
	private String status;
	private String data_inicio;
	private String data_fim;
	private Funcionario funcionario = new Funcionario();
	private Habilidade requisito;
	private List<Atividade> atividades = new ArrayList<>();
	private Projeto projeto = new Projeto();
	

	public Tarefa(){
	}
	
	public Tarefa(Projeto projeto) {
		this.projeto = projeto;
	}
	public Tarefa(int id){
		this.id = id;
	}
	public Tarefa(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Atividade> getAtividades() {
		return atividades;
	}
	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}
	public void addAtividades(Atividade atividade) {
		atividades.add(atividade);
	}
	public Habilidade getRequisito() {
		return requisito;
	}
	public void setRequisito(Habilidade requisito) {
		this.requisito = requisito;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getPeso() {
		return prioridade;
	}
	public void setPeso(int peso) {
		this.prioridade = peso;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getData_inicio() {
		return data_inicio;
	}
	public void setData_inicio(String data_inicio) {
		this.data_inicio = data_inicio;
	}
	public String getData_fim() {
		return data_fim;
	}
	public void setData_fim(String data_fim) {
		this.data_fim = data_fim;
	}
	public int getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public Projeto getProjeto() {
		return projeto;
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	
	public boolean validar(){
		if((id == 0) ||(descricao.equals("") || descricao == null) ||  (prioridade == 0) || 
			(status.equals("") || status == null) || (data_inicio.equals("") || data_inicio == null) ||
			(data_fim.equals("") || data_fim == null))
				return false;
		
		return true;
	}
		
}
