package modelo;

public class Atividade extends EntidadeNegocio {
	private String nome;
	private int qtde_horas;
	private String descricao;
	private boolean status;
	private Tarefa tarefa = new Tarefa();
	
	public Atividade(){

	}
	public Atividade(int id){
		this.id = id;
	}
	public Atividade(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQtde_horas() {
		return qtde_horas;
	}
	public void setQtde_horas(int qtde_horas) {
		this.qtde_horas = qtde_horas;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Tarefa getTarefa() {
		return tarefa;
	}
	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
	
}
