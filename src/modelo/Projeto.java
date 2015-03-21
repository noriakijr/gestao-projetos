package modelo;

import java.util.ArrayList;
import java.util.List;

public class Projeto extends EntidadeNegocio{
	private String nome;
	private String descricao;
	private String status;
	private Funcionario gerente = new Funcionario();
	private String data_inicio;
	private String data_fim;
	private List<Tarefa> tarefas = new ArrayList<>();
	
	public Projeto(){

	}
	public Projeto(int id){
		this.id = id;
	}
	public List<Tarefa> getTarefas() {
		return tarefas;
	}
	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}
	public void addTarefa(Tarefa tarefa) {
		tarefas.add(tarefa);
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Funcionario getGerente() {
		return gerente;
	}
	public void setGerente(Funcionario gerente) {
		this.gerente = gerente;
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
	
}
