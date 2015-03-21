package controle.command.impl;

import modelo.Atividade;
import modelo.EntidadeNegocio;
import modelo.Funcionario;
import modelo.Habilidade;
import modelo.Mensagem;
import modelo.Projeto;
import modelo.Resultado;
import modelo.Tarefa;
import controle.command.ICommand;


public class ValidarCamposCommand implements ICommand{
	
	private EntidadeNegocio en;
	private Resultado resultado;
	
	public ValidarCamposCommand(EntidadeNegocio en, Resultado resultado){
		this.en = en;
		this.resultado = resultado;
	}
	
	public void execute(){
		if(en.getClass().getName().equals("modelo.Funcionario")){
			Funcionario funcionario = (Funcionario)en;
			if(funcionario.getId() == 0)
				resultado.setResultado(false);
			else
				resultado.setResultado(true);
			
		}
		if(en.getClass().getName().equals("modelo.Mensagem")){
			Mensagem mensagem = (Mensagem)en;
			if(mensagem.getId() == 0)
				resultado.setResultado(false);
			else
				resultado.setResultado(true);
		}
		if(en.getClass().getName().equals("modelo.Projeto")){
			Projeto projeto = (Projeto)en;
			if(projeto.getId() == 0)
				resultado.setResultado(false);
			else
				resultado.setResultado(true);
		}
		if(en.getClass().getName().equals("modelo.Tarefa")){
			Tarefa tarefa = (Tarefa)en;
			if(tarefa.getId() == 0)
				resultado.setResultado(false);
			else
				resultado.setResultado(true);
		}
		if(en.getClass().getName().equals("modelo.Atividade")){
			Atividade atividade = (Atividade)en;
			if(atividade.getId() == 0)
				resultado.setResultado(false);
			else
				resultado.setResultado(true);
		}
		if(en.getClass().getName().equals("modelo.Habilidade")){
			Habilidade habilidade = (Habilidade)en;
			if(habilidade.getId() == 0)
				resultado.setResultado(false);
			else
				resultado.setResultado(true);
		}
	}
}
