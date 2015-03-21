package modelo;

public class Habilidade extends EntidadeNegocio {
	private String nome;
	
	public Habilidade(String nome){
		this.nome = nome;
	}
	
	public Habilidade(){
		
	}
	
	public Habilidade(int id){
		this.id = id;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
