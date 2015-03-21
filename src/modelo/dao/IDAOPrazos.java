package modelo.dao;

import modelo.EntidadeNegocio;

public interface IDAOPrazos {
	public void AtualizarStatusPrazo(EntidadeNegocio en, String status);
	public int PesquisarPrazo(EntidadeNegocio en);

}
