package controle.command.impl;

import java.util.List;

import modelo.EntidadeNegocio;
import modelo.Tarefa;
import modelo.dao.IDAOPrazos;
import modelo.dao.impl.AtividadeDAO;
import modelo.dao.impl.ProjetoDAO;
import modelo.dao.impl.TarefaDAO;
import controle.command.ICommand;

public class AtualizarStatusCommand implements ICommand {

	List<EntidadeNegocio> list;
	Tarefa tarefa;
	IDAOPrazos dao;
	
	public AtualizarStatusCommand(List<EntidadeNegocio> list){
		this.list = list;
	}
	
	@Override
	public void execute() {
		int prazo;
		int prazoAtividade;
		
		for (EntidadeNegocio en : list) {
			if(en.getClass().getName().equals("modelo.Projeto"))
				dao = new ProjetoDAO();
			else
				dao = new TarefaDAO();

			IDAOPrazos daoAtividade = new AtividadeDAO();
			prazo = dao.PesquisarPrazo(en);
			prazoAtividade = daoAtividade.PesquisarPrazo(en);
			
			if(prazoAtividade == 0){
				dao.AtualizarStatusPrazo(en, "Concluido");
				continue;
			}
			if(prazo < 0){
				dao.AtualizarStatusPrazo(en, "Atrasado em " + prazoAtividade + " horas");
				continue;
			}
			if(prazo >= 0 && prazoAtividade <= 8){
				dao.AtualizarStatusPrazo(en, "Em andamento");
				continue;
			}
			if(prazo == 0 && prazoAtividade > 8){
				dao.AtualizarStatusPrazo(en, "Atrasado em " + prazoAtividade + " horas");
				continue;
			}
			if(prazo > 0 && prazoAtividade > prazo*8){
				dao.AtualizarStatusPrazo(en, "Atrasado em " + prazoAtividade + " horas");
				continue;
			}
			if(prazo > 0 && prazoAtividade < prazo*8){
				dao.AtualizarStatusPrazo(en, "Em andamento");
				continue;
			}
			
		}

	}

}
