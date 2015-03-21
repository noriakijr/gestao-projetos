package modelo.dao;

import java.util.List;

import modelo.EntidadeNegocio;

public interface IDAO {
	public List<EntidadeNegocio> Pesquisar(EntidadeNegocio en);
	public void Cadastrar(EntidadeNegocio en);
	public void Alterar(EntidadeNegocio en);
	public void Excluir(EntidadeNegocio en);

}
