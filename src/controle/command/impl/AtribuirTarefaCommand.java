package controle.command.impl;

import java.util.ArrayList;
import java.util.List;

import modelo.Atividade;
import modelo.Funcionario;
import modelo.Habilidade;
import modelo.Tarefa;
import controle.command.ICommand;

public class AtribuirTarefaCommand implements ICommand {
	
	private Tarefa tarefa;
	private List<Funcionario> funcionarios;
	public static int atividadesPorPessoa = 0;

	public AtribuirTarefaCommand(Tarefa tarefa, List<Funcionario> funcionarios){
		this.tarefa = tarefa;
		this.funcionarios = funcionarios;
		if(AtribuirTarefaCommand.atividadesPorPessoa == 0)
			atividadesPorPessoa++;
	}

	@Override
	public void execute() {
		
		List<Funcionario> funcionariosParaRealocar = new ArrayList<>();
		List<Funcionario> funcionariosHabilitados = new ArrayList<>();
		
		//loop para verificar as habilidades de todos os funcionarios para melhor distribuição de tarefas
		for(Funcionario funcionario:funcionarios)
			for(Habilidade habilidade:funcionario.getHabilidades())
				//verifica se a habilidade e o requisito da tarefa são compativeis e se o funcionario não atingiu a carga máxima de trabalho
				if(habilidade.getNome().equals(tarefa.getRequisito().getNome()) && funcionario.getTarefas().size() < atividadesPorPessoa){
					for (Atividade atividade : tarefa.getAtividades())
						if(atividade.isStatus())
							return;
					funcionario.addTarefa(tarefa);
					return;		//retorna indicando que atribuiu uma tarefa
				}//if
		
		//verificar se há algum funcionário sem tarefas atribuidas
		for (Funcionario funcionario : funcionarios) {
			if(funcionario.getTarefas().size() < atividadesPorPessoa){
				funcionariosParaRealocar.add(funcionario);
			}
		}
		
		//se todos os funcionarios tem tarefas atribuidas
		if(funcionariosParaRealocar.size() == 0){
			atividadesPorPessoa++;			//aumenta o numero de atividades por pessoa
			//loop para verificar as habilidades de todos os funcionarios para melhor distribuição de tarefas
			for(Funcionario funcionario:funcionarios)
				for(Habilidade habilidade:funcionario.getHabilidades())
					//verifica se a habilidade e o requisito da tarefa são compativeis e se o funcionario não atingiu a carga máxima de trabalho
					if(habilidade.getNome().equals(tarefa.getRequisito().getNome()) && funcionario.getTarefas().size() < atividadesPorPessoa){
						funcionario.addTarefa(tarefa);
						return;		//retorna indicando que atribuiu uma tarefa
					}//if
		}		
		//buscar o funcionario habilitado para a tarefa
		for (Funcionario funcionario : funcionarios) {
			for (Habilidade habilidade : funcionario.getHabilidades()) {
				//se for habilitado para realização da tarefa
				if(habilidade.getNome().equals(tarefa.getRequisito().getNome())){
					funcionariosHabilitados.add(funcionario);
				}

			}

		}

		//verificar se o funcionario sem tarefas sabe fazer o que o habilitado para a tarefa está fazendo
		for (Funcionario funcionarioHabilitado : funcionariosHabilitados) {
			for (Funcionario funcionarioParaRealocar : funcionariosParaRealocar) {
				for (Tarefa tarefaHabilitado : funcionarioHabilitado.getTarefas()) {
					for (Habilidade habilidade : funcionarioParaRealocar.getHabilidades()) {
						//verifica se o funcionario tem a habilidade de realizar a tarefa
						if(habilidade.getNome().equals(tarefaHabilitado.getRequisito().getNome())){
							for (Atividade atividade : tarefa.getAtividades()) 
								if(atividade.isStatus())
									return;
							funcionarioParaRealocar.addTarefa(tarefa);
							funcionarioHabilitado.removerTarefa(tarefa);
							funcionarioHabilitado.addTarefa(tarefa);
							return;
						}						
					}
				}
			}
		}
		
		//verificar se alguem está fazendo o que o funcionario que está sem tarefas sabe fazer
		for (Funcionario funcionarioParaRealocar : funcionariosParaRealocar) {
			for (Funcionario funcionario : funcionarios) {
				for (Tarefa tarefaParaTrocas : funcionario.getTarefas()) {
					for (Habilidade habilidade : funcionarioParaRealocar.getHabilidades()) {
						//verifica se o funcionario de troca está fazedo algo que o funcionario para realocar sabe fazer
						if(habilidade.getNome().equals(tarefaParaTrocas.getRequisito().getNome())){
							//verificar se esse funcionario sabe fazer o que o funcionario habilitado está fazendo
							for (Funcionario funcionarioHabilitado : funcionariosHabilitados) {
								for (Tarefa tarefaHabilitado : funcionarioHabilitado.getTarefas()) {
									for (Habilidade habilidadeParaTrocas : funcionario.getHabilidades()) {
										if(habilidadeParaTrocas.getNome().equals(tarefaHabilitado.getRequisito().getNome())){
											for (Atividade atividade : tarefa.getAtividades()) 
												if(atividade.isStatus())
													return;
											funcionarioParaRealocar.addTarefa(tarefaParaTrocas);
											funcionario.removerTarefa(tarefaParaTrocas);
											funcionario.addTarefa(tarefaHabilitado);
											funcionarioHabilitado.removerTarefa(tarefaHabilitado);
											funcionarioHabilitado.addTarefa(tarefa);
											return;
										}
											
									}
								}
							}
						}
					}
				}
			}
		}
		funcionariosHabilitados.get(0).addTarefa(tarefa);	//se não há mais ninguem para fazer, atribui a qualquer um habilitado
		//atividadesPorPessoa++;			//em ultimo caso habilita mais uma tarefa por funcionario
		return;
		
	}

}
