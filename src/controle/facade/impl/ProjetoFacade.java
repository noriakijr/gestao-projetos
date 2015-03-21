package controle.facade.impl;

import java.util.ArrayList;
import java.util.List;

import modelo.Atividade;
import modelo.EntidadeNegocio;
import modelo.Funcionario;
import modelo.Habilidade;
import modelo.Projeto;
import modelo.Resultado;
import modelo.Tarefa;
import modelo.dao.IDAO;
import modelo.dao.impl.AtividadeDAO;
import modelo.dao.impl.FuncionarioDAO;
import modelo.dao.impl.HabilidadeDAO;
import modelo.dao.impl.MensagensDAO;
import modelo.dao.impl.ProjetoDAO;
import modelo.dao.impl.TarefaDAO;
import controle.command.ICommand;
import controle.command.impl.AtribuirTarefaCommand;
import controle.command.impl.AtualizarStatusCommand;
import controle.command.impl.LoginCommand;
import controle.command.impl.ValidarCamposCommand;
import controle.facade.IFacade;

public class ProjetoFacade implements IFacade {

	//listas de funcionarios e tarefas
	List<Tarefa> tarefas;
	List<Funcionario> funcionarios;
	List<Habilidade> habilidades;
	int atividadesPorPessoa = 1;			//numero de atividades por pessoa

	public ProjetoFacade(){

		//inicializa as listas
		tarefas = new ArrayList<Tarefa>();
		funcionarios = new ArrayList<Funcionario>();
	}



	@SuppressWarnings("unchecked")
	public List<Funcionario> DistribuirTarefas() {
		ICommand atribuicaoTarefa;
		IDAO dao = new TarefaDAO();

		funcionarios = (List<Funcionario>)(Object)Pesquisar(new Funcionario());
		//loop para distribuir as tarefas
		for(Tarefa tarefa : (List<Tarefa>)(Object)Pesquisar(new Tarefa())){
			tarefa.setAtividades((List<Atividade>)(Object)Pesquisar(new Atividade(tarefa)));
			atribuicaoTarefa  = new AtribuirTarefaCommand(tarefa, funcionarios);
			atribuicaoTarefa.execute();	
		}
		AtribuirTarefaCommand.atividadesPorPessoa = 0;		//zera a contagem de numero de atividades por pessoa
		for (Funcionario funcionario : funcionarios) {
			for (Tarefa tarefa : funcionario.getTarefas()) {
				tarefa.setFuncionario(funcionario);
				dao.Alterar(tarefa);
			}		
		}
		return funcionarios;
	}

	//Insira as condições de busca para o objeto desejado
	public List<EntidadeNegocio> Pesquisar(EntidadeNegocio en){
		IDAO dao;
		ICommand status;
		//verifica qual é a entidade para chamar o dao correspondente
		if(en.getClass().getName().equals("modelo.Projeto")){
			dao = new ProjetoDAO();
			status = new AtualizarStatusCommand(dao.Pesquisar(new Projeto()));
			status.execute();
			return dao.Pesquisar(en);	
		}
		if(en.getClass().getName().equals("modelo.Tarefa")){
			dao = new TarefaDAO();
			status = new AtualizarStatusCommand(dao.Pesquisar(new Tarefa()));
			status.execute();
			return dao.Pesquisar(en);
		}
		if(en.getClass().getName().equals("modelo.Atividade")){
			dao = new AtividadeDAO();
			return dao.Pesquisar(en);
		}
		if(en.getClass().getName().equals("modelo.Funcionario")){
			Funcionario funcionario = (Funcionario)en;
			if(funcionario.getHabilidades().size() == 0)
				dao = new FuncionarioDAO();
			else
				dao = new HabilidadeDAO();
			return dao.Pesquisar(en);
		}
		if(en.getClass().getName().equals("modelo.Habilidade")){
			dao = new HabilidadeDAO();
			return dao.Pesquisar(en);
		}
		if(en.getClass().getName().equals("modelo.Mensagem")){
			dao = new MensagensDAO();
			return dao.Pesquisar(en);
		}
		return null;
	}

	public void Alterar(EntidadeNegocio en){
		IDAO dao;
		if(en.getClass().getName().equals("modelo.Projeto")){
			dao = new ProjetoDAO();
			dao.Alterar(en);
		}
		if(en.getClass().getName().equals("modelo.Tarefa")){
			dao = new TarefaDAO();
			dao.Alterar(en);
		}
		if(en.getClass().getName().equals("modelo.Atividade")){
			dao = new AtividadeDAO();
			dao.Alterar(en);
		}
		if(en.getClass().getName().equals("modelo.Funcionario")){
			dao = new FuncionarioDAO();
			dao.Alterar(en);
		}
		if(en.getClass().getName().equals("modelo.Habilidade")){
			dao = new HabilidadeDAO();
			dao.Alterar(en);
		}
		if(en.getClass().getName().equals("modelo.Mensagem")){
			dao = new MensagensDAO();
			dao.Alterar(en);
		}
	}

	public void Excluir(EntidadeNegocio en){
		IDAO dao;
		if(en.getClass().getName().equals("modelo.Projeto")){
			dao = new ProjetoDAO();
			dao.Excluir(en);
		}
		if(en.getClass().getName().equals("modelo.Tarefa")){
			dao = new TarefaDAO();
			dao.Excluir(en);
		}
		if(en.getClass().getName().equals("modelo.Atividade")){
			dao = new AtividadeDAO();
			dao.Excluir(en);
		}
		if(en.getClass().getName().equals("modelo.Funcionario")){
			dao = new FuncionarioDAO();
			dao.Excluir(en);
		}
		if(en.getClass().getName().equals("modelo.Habilidade")){
			dao = new HabilidadeDAO();
			dao.Excluir(en);
		}
		if(en.getClass().getName().equals("modelo.Mensagem")){
			dao = new MensagensDAO();
			dao.Excluir(en);
		}
	}

	public void Cadastrar(EntidadeNegocio en){
		IDAO dao;
		if(en.getClass().getName().equals("modelo.Projeto")){
			dao = new ProjetoDAO();
			dao.Cadastrar(en);
		}
		if(en.getClass().getName().equals("modelo.Tarefa")){
			dao = new TarefaDAO();
			dao.Cadastrar(en);
		}
		if(en.getClass().getName().equals("modelo.Atividade")){
			dao = new AtividadeDAO();
			dao.Cadastrar(en);
		}
		if(en.getClass().getName().equals("modelo.Funcionario")){
			dao = new FuncionarioDAO();
			dao.Cadastrar(en);
		}
		if(en.getClass().getName().equals("modelo.Habilidade")){
			dao = new HabilidadeDAO();
			dao.Cadastrar(en);
		}
		if(en.getClass().getName().equals("modelo.Mensagem")){
			dao = new MensagensDAO();
			dao.Cadastrar(en);
		}
	}
	public void validaCampos(EntidadeNegocio en, Resultado resultado){
		ICommand command = new ValidarCamposCommand(en, resultado);
		command.execute();
	}
	public void verificarLogin(Funcionario funcionario){
		ICommand command = new LoginCommand(funcionario);
		command.execute();
	}



}
