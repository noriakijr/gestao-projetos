package controle.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Atividade;
import modelo.EntidadeNegocio;
import modelo.Funcionario;
import modelo.Habilidade;
import modelo.Login;
import modelo.Mensagem;
import modelo.Projeto;
import modelo.Resultado;
import modelo.Tarefa;

import controle.command.ICommand;
import controle.command.impl.LoginCommand;
import controle.facade.impl.ProjetoFacade;

/**
 * Servlet implementation class DistribuicaoServlet
 */
public class ProjetoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProjetoFacade fachada = new ProjetoFacade();
	Funcionario funcionario;
	Resultado resultado;
	//Funcionario logado = Login.getInstance();
	Projeto projeto;
	Tarefa tarefa;
	Habilidade habilidade;
	Atividade atividade;
	List<Tarefa> tarefas;
	List<Atividade> atividades;
	Mensagem mensagem;
	List<Mensagem> mensagens;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProjetoServlet() {
		super();
		// TODO Auto-generated constructor stub
		atividades = new ArrayList<>();
		tarefas = new ArrayList<>();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		fachada = new ProjetoFacade();

		//distribui as tarefas e exibe na tela
		if(request.getParameter("btnDistribuicao") != null){
			fachada.DistribuirTarefas();
			request.setAttribute("listaProjetos", fachada.Pesquisar(new Projeto()));
			request.getRequestDispatcher("Projetos.jsp").forward(request, response);
		}
		//pesquisa os projetos cadastrados
		if(request.getParameter("btnPesquisarProjeto") != null){
			resultado = new Resultado();
			if(!request.getParameter("txtPesquisaProjeto").equals("")){
				fachada.Pesquisar(projeto = new Projeto(Integer.parseInt(request.getParameter("txtPesquisaProjeto"))));
				fachada.validaCampos(projeto, resultado);
				if(resultado.isResultado()){
					request.setAttribute("projeto", projeto);
					request.getRequestDispatcher("Projetos.jsp").forward(request, response);
				}
			}
			else{
				request.setAttribute("listaProjetos", fachada.Pesquisar(new Projeto()));
				request.getRequestDispatcher("Projetos.jsp").forward(request, response);
			}
		}
		if(request.getParameter("txtPesquisaProjeto1") != null){
			resultado = new Resultado();
			if(!request.getParameter("txtPesquisaProjeto1").equals("")){
				fachada.Pesquisar(projeto = new Projeto(Integer.parseInt(request.getParameter("txtPesquisaProjeto1"))));
				fachada.validaCampos(projeto, resultado);
				if(resultado.isResultado()){
					request.setAttribute("projeto", projeto);
					request.setAttribute("listaTarefas", fachada.Pesquisar(new Tarefa(projeto)));
					request.setAttribute("funcionarios", fachada.Pesquisar(new Funcionario()));
					request.getRequestDispatcher("Tarefas.jsp").forward(request, response);
				}
			}

		}
		if(request.getParameter("txtPesquisaTarefa") != null){
			resultado = new Resultado();
			if(!request.getParameter("txtPesquisaTarefa").equals("")){
				fachada.Pesquisar(projeto = new Projeto(Integer.parseInt(request.getParameter("txtPesquisaProjeto2"))));
				fachada.validaCampos(projeto, resultado);
				if(resultado.isResultado()){
					request.setAttribute("projeto", projeto);
					fachada.Pesquisar(tarefa = new Tarefa(Integer.parseInt(request.getParameter("txtPesquisaTarefa"))));
					fachada.validaCampos(tarefa, resultado);
					if(resultado.isResultado()){
						request.setAttribute("tarefa", tarefa);
						request.setAttribute("listaAtividades", fachada.Pesquisar(new Atividade(tarefa)));
						request.setAttribute("listaHabilidades", fachada.Pesquisar(new Habilidade()));
						request.setAttribute("listaFuncionarios", fachada.Pesquisar(new Funcionario()));
						request.getRequestDispatcher("Atividades.jsp").forward(request, response);
					}
				}
			}

		}
		if(request.getParameter("txtPesquisaAtividade") != null){
			resultado = new Resultado();
			if(!request.getParameter("txtPesquisaAtividade").equals("")){
				fachada.Pesquisar(projeto = new Projeto(Integer.parseInt(request.getParameter("txtPesquisaProjeto3"))));
				fachada.validaCampos(projeto, resultado);
				if(resultado.isResultado()){
					request.setAttribute("projeto", projeto);
					resultado = new Resultado();
					fachada.Pesquisar(tarefa = new Tarefa(Integer.parseInt(request.getParameter("txtPesquisaTarefa2"))));
					fachada.validaCampos(tarefa, resultado);
					if(resultado.isResultado()){
						request.setAttribute("tarefa", tarefa);
						resultado = new Resultado();
						fachada.Pesquisar(atividade = new Atividade(Integer.parseInt(request.getParameter("txtPesquisaAtividade"))));
						fachada.validaCampos(atividade, resultado);
						if(resultado.isResultado()){
							request.setAttribute("atividade", atividade);
							request.getRequestDispatcher("AtividadesAlt.jsp").forward(request, response);
						}
					}
				}
			}
		}
		if(request.getParameter("btnPesquisarFuncionario") != null){
			if(!request.getParameter("txtPesquisaFuncionario").equals("")){
				resultado = new Resultado();
				fachada.Pesquisar(funcionario = new Funcionario(Integer.parseInt(request.getParameter("txtPesquisaFuncionario"))));
				fachada.validaCampos(funcionario, resultado);
				if(resultado.isResultado()){
					request.setAttribute("funcionario", funcionario);
					request.getRequestDispatcher("Funcionarios.jsp").forward(request, response);
				}
			}
			else{
				request.setAttribute("listaFunc", fachada.Pesquisar(new Funcionario()));
				request.getRequestDispatcher("Funcionarios.jsp").forward(request, response);
			}
		}
		if(request.getParameter("txtPesquisaFuncionario1") != null){
			if(!request.getParameter("txtPesquisaFuncionario1").equals("")){
				resultado = new Resultado();
				fachada.Pesquisar(funcionario = new Funcionario(Integer.parseInt(request.getParameter("txtPesquisaFuncionario1"))));
				request.setAttribute("funcionario", funcionario);
				funcionario.addHabilidade(new Habilidade());
				fachada.validaCampos(funcionario, resultado);
				//if(resultado.isResultado()){
					request.setAttribute("listaHabilidades", fachada.Pesquisar(funcionario));
					request.getRequestDispatcher("Habilidades.jsp").forward(request, response);
			//	}
				//else
				//	response.sendRedirect("Home.html");
			}
		}
		if(request.getParameter("btnAlterarProjeto") != null){
			resultado = new Resultado();
			projeto = new Projeto();
			projeto.setId(Integer.parseInt(request.getParameter("txtIdProjeto")));
			projeto.setNome(request.getParameter("txtProjeto"));
			projeto.setDescricao(request.getParameter("txtDescricaoProjeto"));
			projeto.setData_inicio(request.getParameter("txtDtInicio"));
			projeto.setData_fim(request.getParameter("txtDtFinal"));
			projeto.setGerente(new Funcionario(Integer.parseInt(request.getParameter("idFuncionario"))));
			fachada.validaCampos(projeto, resultado);
			if(resultado.isResultado()){
				fachada.Alterar(projeto);
				// (request.getParameter("txtNomeGerente"));
				request.getRequestDispatcher("Projetos.jsp").forward(request, response);
			}
		}
		if(request.getParameter("btnAlterarTarefa") != null){
			resultado = new Resultado();
			tarefa = new Tarefa();
			tarefa.setId(Integer.parseInt(request.getParameter("txtIdTarefa")));
			tarefa.setNome(request.getParameter("txtTarefa"));
			tarefa.setDescricao(request.getParameter("txtDescricaoTarefa"));
			try{
				tarefa.setPrioridade(Integer.parseInt(request.getParameter("txtPrioridade")));
			}catch(Exception e){
				response.sendRedirect("Home.html");
			}
			tarefa.setData_inicio(request.getParameter("txtDtInicio"));
			tarefa.setData_fim(request.getParameter("txtDtFinal"));
			tarefa.setRequisito(new Habilidade(request.getParameter("cbxRequisito")));
			tarefa.setFuncionario(new Funcionario(Integer.parseInt(request.getParameter("idFuncionario"))));
			fachada.validaCampos(tarefa, resultado);
			if(resultado.isResultado()){
				fachada.Alterar(tarefa);
				
				//tarefa.setRequisito(request.getParameter("cbRequisito"));
				//tarefa.setPrioridade(Integer.parseInt(request.getParameter("txtPrioridade")));
				request.getRequestDispatcher("Projetos.jsp").forward(request, response);
			}
		}
		if(request.getParameter("btnAlterarAtividade") != null){
			resultado = new Resultado();
			atividade = new Atividade();
			atividade.setId(Integer.parseInt(request.getParameter("txtIdAtividade")));
			atividade.setNome(request.getParameter("txtNomeAtividade"));
			atividade.setQtde_horas(Integer.parseInt(request.getParameter("txtQtdeHoras")));
			atividade.setDescricao(request.getParameter("txtDescricao"));
			atividade.setStatus(Boolean.parseBoolean(request.getParameter("cbStatusAtividade")));
			fachada.validaCampos(atividade, resultado);
			if(resultado.isResultado()){
				fachada.Alterar(atividade);
				//false);
	
				//atividade.setStatus(Boolean.parseBoolean(request.getParameter("txtStatusAtividade")));
				request.getRequestDispatcher("Projetos.jsp").forward(request, response);
				//response.sendRedirect("AtividadesAlt.jsp");
			}
		}
		if(request.getParameter("btnAlterarFunc") != null){
			resultado = new Resultado();
			funcionario = new Funcionario();
			if(!request.getParameter("txtIdFuncionario").equals(""))
			funcionario.setId(Integer.parseInt(request.getParameter("txtIdFuncionario")));
			funcionario.setNome(request.getParameter("txtNomeFuncionario"));
			funcionario.setCargo(request.getParameter("txtCargo"));
			funcionario.setLogin(request.getParameter("txtLogin"));
			funcionario.setSenha(request.getParameter("txtSenha"));
			funcionario.setTipo_acesso(request.getParameter("rdgPerfil"));
			fachada.validaCampos(funcionario, resultado);
			if(resultado.isResultado()){
				fachada.Alterar(funcionario);
				//funcionario.setSexo(request.getParameter("txtSexo"));
				//funcionario.setIdade(Integer.parseInt(request.getParameter("txtIdade")));
				request.getRequestDispatcher("Funcionarios.jsp").forward(request, response);
			}
			else
				response.sendRedirect("Home.html");
		}
		if(request.getParameter("btnExcluirProjeto") != null){
			fachada.Excluir(new Projeto(Integer.parseInt(request.getParameter("txtIdProjeto"))));
			request.getRequestDispatcher("Projetos.jsp").forward(request, response);
		}
		if(request.getParameter("btnExcluirTarefa") != null){
			fachada.Excluir(new Tarefa(Integer.parseInt(request.getParameter("txtIdTarefa"))));
			request.getRequestDispatcher("Projetos.jsp").forward(request, response);
		}
		if(request.getParameter("btnExcluirAtividade") != null){
			fachada.Excluir(new Atividade(Integer.parseInt(request.getParameter("txtIdAtividade"))));
			request.getRequestDispatcher("Projetos.jsp").forward(request, response);
		}
		if(request.getParameter("btnExcluirFunc") != null){
			fachada.Excluir(new Funcionario(Integer.parseInt(request.getParameter("txtIdFuncionario"))));
			request.getRequestDispatcher("Funcionarios.jsp").forward(request, response);
		}
		if(request.getParameter("btnNovoFuncionario") != null){
			request.setAttribute("habilidades", fachada.Pesquisar(new Habilidade()));
			request.getRequestDispatcher("Cadastro de Funcionarios.jsp").forward(request, response);
		}
		if(request.getParameter("btnNovaHabilidade") != null){
				response.sendRedirect("Cadastro de Habilidades.html");
		}
		if(request.getParameter("btnCadastrarHabilidade") != null){
			fachada.Cadastrar(new Habilidade(request.getParameter("txtHabilidade")));
			//request.setAttribute("funcionario", funcionario);
			request.setAttribute("habilidades", fachada.Pesquisar(new Habilidade()));
			request.getRequestDispatcher("HabilidadesAdd.jsp").forward(request, response);

		}
		if(request.getParameter("btnCadastrarFuncionario") != null){
			funcionario.setNome(request.getParameter("funcNome"));
			funcionario.setTelefone(request.getParameter("funcTelefone"));
			funcionario.setEmail(request.getParameter("funcEmail"));
			/*loop para pegar todas as habilidades
			for(int i = 0; request.getParameter("cbxHabilidades" + i) != null; i++)
				funcionario.addHabilidade(new Habilidade(request.getParameter("cbxHabilidades" + i)));
			 */
			funcionario.setCargo(request.getParameter("txtCargo"));
			funcionario.setLogin(request.getParameter("userLogin"));
			funcionario.setSenha(request.getParameter("userSenha"));
			funcionario.setTipo_acesso(request.getParameter("rdgPerfil"));
			fachada.Cadastrar(funcionario);
			response.sendRedirect("Funcionarios.jsp");
		}
		if(request.getParameter("btnNovoProjeto") != null){
			tarefas = new ArrayList<>();
			request.setAttribute("funcionarios", fachada.Pesquisar(new Funcionario()));
			request.getRequestDispatcher("Cadastro de Projeto.jsp").forward(request, response);
		}
		//dentro do if do botao de cadastro da tarefa
		if(request.getParameter("btnCadastrarTarefa") != null){
			Tarefa tarefa = new Tarefa();
			tarefa.setNome(request.getParameter("txtTarefa"));
			tarefa.setDescricao(request.getParameter("txtDescricaoTarefa"));		
			tarefa.setPrioridade(Integer.parseInt(request.getParameter("txtPrioridade")));
			tarefa.setStatus(request.getParameter("txtStatus"));
			tarefa.setData_inicio(request.getParameter("txtDtInicio"));
			tarefa.setData_fim(request.getParameter("txtDtFinal"));
			tarefa.setRequisito(new Habilidade(request.getParameter("cbxRequisitos")));
			tarefa.setAtividades(atividades);
			tarefas.add(tarefa);
			if(projeto.getId() != 0){
				tarefa.getProjeto().setId(projeto.getId());
				projeto.setTarefas(tarefas);
				fachada.Cadastrar(tarefa);
				//limpa os objetos
				projeto = null;
				tarefas.clear();
				atividades.clear();

				response.sendRedirect("Projetos.jsp");

			}
			else{
				request.setAttribute("projeto", projeto);
				request.setAttribute("funcionarios", fachada.Pesquisar(new Funcionario()));
				request.getRequestDispatcher("Cadastro de Projeto.jsp").forward(request, response);
			}
		}

		//if do botao cadastrar atividade (verifica se o botao é igual diferente de null, olhar no meu)
		if(request.getParameter("btnCadastrarAtividade") != null){
			atividade = new Atividade();
			atividade.setNome(request.getParameter("txtAtividade"));			
			atividade.setQtde_horas(Integer.parseInt(request.getParameter("txtQtdeHoras")));
			atividade.setDescricao(request.getParameter("txtDescricao"));
			atividades.add(atividade);
			if(tarefa.getId() != 0){
				atividade.getTarefa().setId(tarefa.getId());
				tarefa.setAtividades(atividades);
				fachada.Cadastrar(atividade);

				//limpa os objetos
				projeto = null;
				tarefas.clear();
				atividades.clear();

				response.sendRedirect("Projetos.jsp");
			}
			else{
				request.setAttribute("tarefa", tarefa);
				request.setAttribute("habilidades", fachada.Pesquisar(new Habilidade()));
				request.getRequestDispatcher("Cadastro de Tarefas.jsp").forward(request, response);
			}
		}
		//if do botao cadastrar atividade (verifica se o botao é igual diferente de null, olhar no meu)
		if(request.getParameter("btnNovaAtividade") != null){
			tarefa = new Tarefa();
			if(request.getParameter("txtIdTarefa") != null)
				tarefa.setId(Integer.parseInt(request.getParameter("txtIdTarefa")));
			tarefa.setNome(request.getParameter("txtTarefa"));
			tarefa.setDescricao(request.getParameter("txtDescricaoTarefa"));		
			tarefa.setPrioridade(Integer.parseInt(request.getParameter("txtPrioridade")));
			tarefa.setStatus(request.getParameter("txtStatus"));
			tarefa.setData_inicio(request.getParameter("txtDtInicio"));
			tarefa.setData_fim(request.getParameter("txtDtFinal"));
			//tarefas.add(tarefa);
			response.sendRedirect("Cadastro de Atividades.html");
		}
		if(request.getParameter("btnNovaTarefa") != null){
			atividades = new ArrayList<>();
			projeto = new Projeto();
			if(request.getParameter("txtIdProjeto") != null)
				projeto.setId(Integer.parseInt(request.getParameter("txtIdProjeto")));
			projeto.setNome(request.getParameter("txtProjeto"));
			projeto.setDescricao(request.getParameter("txtDescricaoProjeto"));
			projeto.setData_inicio(request.getParameter("txtDtInicio"));
			projeto.setData_fim(request.getParameter("txtDtFinal"));
			request.setAttribute("habilidades", fachada.Pesquisar(new Habilidade()));
			request.getRequestDispatcher("Cadastro de Tarefas.jsp").forward(request, response);
		}

		//if do botao cadastrar projeto
		if(request.getParameter("btnCadastrarProjeto") != null){
			funcionario = new Funcionario();
			projeto.setNome(request.getParameter("txtProjeto"));
			projeto.setDescricao(request.getParameter("txtDescricaoProjeto"));
			projeto.setStatus(request.getParameter("txtStatusProjeto"));						
			projeto.setData_inicio(request.getParameter("txtDtInicio"));
			projeto.setData_fim(request.getParameter("txtDtFinal"));	
			funcionario.setNome(request.getParameter("cbxGerente"));
			funcionario.setId(Integer.parseInt(request.getParameter("idFuncionario")));
			projeto.setGerente(funcionario);
			projeto.setTarefas(tarefas);
			fachada.Cadastrar(projeto);

			//limpa os objetos
			projeto = null;
			tarefas.clear();
			atividades.clear();

			//chamar metodo para gravar TUDO no banco
			//IDAO dao = new ProjetoDAO();
			//chamar metodo para cadastrar projeto dao.Cadastrar(projeto)
			//for(Tarefas tarefa : projeto.getTarefas()){   dentro do dao, um dao chama outro (cadastra o projeto e retorna o id)
			// IDAO dao =  new TarefaDAO()
			//	dao.Cadastrar(tarefa)
			//		for(Ativadade atividade : tarefa.getAtividades()){
			//			dao.Cadastrar(atividade);
			response.sendRedirect("Projetos.jsp");
		}
		if(request.getParameter("txtPesquisaHabilidade") != null){
			if(!request.getParameter("txtPesquisaHabilidade").equals("")){
				habilidade = new Habilidade();
				habilidade.setNome(request.getParameter("txtNomeHabilidade"));
				habilidade.setId(Integer.parseInt(request.getParameter("txtPesquisaHabilidade")));
				request.setAttribute("habilidade", habilidade);
				request.getRequestDispatcher("HabilidadeAlt.jsp").forward(request, response);
			}
		}
		if(request.getParameter("btnAddHabilidade") != null){
			funcionario = new Funcionario();

			if(request.getParameter("txtIdFuncionario") != null)
				funcionario.setId(Integer.parseInt(request.getParameter("txtIdFuncionario")));
			funcionario.setNome(request.getParameter("funcNome"));
			funcionario.setTelefone(request.getParameter("funcTelefone"));
			funcionario.setEmail(request.getParameter("funcEmail"));
			funcionario.setCargo(request.getParameter("txtCargo"));
			funcionario.setLogin(request.getParameter("userLogin"));
			funcionario.setSenha(request.getParameter("userSenha"));
			funcionario.setTipo_acesso(request.getParameter("rdgPerfil"));
			request.setAttribute("habilidades", fachada.Pesquisar(new Habilidade()));
			request.getRequestDispatcher("HabilidadesAdd.jsp").forward(request, response);
		}
		if(request.getParameter("btnAddTodasHabilidades") != null){
			//loop para pegar todas as habilidades
			for(int i = 0; request.getParameter("cbxHabilidades" + i) != null; i++)
				funcionario.addHabilidade(new Habilidade(request.getParameter("cbxHabilidades" + i)));
			if(funcionario.getId() != 0){
				funcionario.addHabilidade(new Habilidade(request.getParameter("txtHabilidade")));
				fachada.Cadastrar(funcionario);

				//limpar os objetos
				funcionario = null;
				response.sendRedirect("Funcionarios.jsp");
			}
			else{
				request.setAttribute("funcionario", funcionario);
				request.getRequestDispatcher("Cadastro de Funcionarios.jsp").forward(request, response);
			}
		}
		if(request.getParameter("btnAlterarHab") != null){
			habilidade = new Habilidade();
			habilidade.setNome(request.getParameter("txtNomeHabilidade"));
			habilidade.setId(Integer.parseInt(request.getParameter("txtIdHabilidade")));
			fachada.Alterar(habilidade);
			response.sendRedirect("Funcionarios.jsp");
		}
		if(request.getParameter("btnExcluirHab") != null){
			fachada.Excluir(new Habilidade(Integer.parseInt(request.getParameter("txtIdHabilidade"))));
			response.sendRedirect("Funcionarios.jsp");
		}
		
		

		if(request.getParameter("novaMensagem") != null){
			funcionario = new Funcionario();
			//logado = Login.getInstance();
			request.setAttribute("listaFuncionarios", fachada.Pesquisar(funcionario));
			request.setAttribute("remetente", Login.getInstance().getNome());
			request.getRequestDispatcher("MensagemBox.jsp").forward(request, response);
//			request.setAttribute("comboNome", funcionarioRecebe.getNome());
			
		}
		
		if(request.getParameter("btnEnviar") != null){
			Date agora = new Date();
			mensagem = new Mensagem();
			funcionario.setNome(request.getParameter("comboNome"));
			fachada.Pesquisar(funcionario);
			mensagem.setEmissorID(Login.getInstance().getId());
			mensagem.setReceptorID(Integer.parseInt(request.getParameter("comboNome")));
			mensagem.setStatus(true);
			mensagem.setDataEnvio(agora);
			mensagem.setTexto(request.getParameter("txtMensagem"));
			request.setAttribute("comboNome", funcionario.getNome());
			fachada.Cadastrar(mensagem);
			request.getRequestDispatcher("Home.html").forward(request, response);
		}
		
		if(request.getParameter("caixaDeEntrada") != null){
			mensagem = new Mensagem();
			mensagem.setReceptorID(Login.getInstance().getId());
			request.setAttribute("listaMensagensUsuario", fachada.Pesquisar(mensagem));
			request.getRequestDispatcher("CaixaEntrada.jsp").forward(request, response);
		}
		
		if(request.getParameter("caixaDeSaida") != null){
			mensagem = new Mensagem();
			mensagem.setEmissorID(Login.getInstance().getId());
			request.setAttribute("listaMensagensDestinatario", fachada.Pesquisar(mensagem));
			request.getRequestDispatcher("MensagensEnviadas.jsp").forward(request, response);
		}
		
		if(request.getParameter("mudarParaLida") != null){
			resultado = new Resultado();
			mensagem = new Mensagem();
			funcionario = new Funcionario();
			mensagem.setId(Integer.parseInt(request.getParameter("mudarParaLida")));
			fachada.validaCampos(mensagem, resultado);
			if(resultado.isResultado()){
				fachada.Alterar(mensagem);
				fachada.Pesquisar(mensagem);
				funcionario.setId(mensagem.getEmissorID());
				fachada.Pesquisar(funcionario);
				request.setAttribute("mensagem", mensagem);
				request.setAttribute("funcionario", funcionario);
				request.getRequestDispatcher("mensagem.jsp").forward(request, response);
			}
		}
		
		if(request.getParameter("btnExcluirMensagem") != null){
			resultado = new Resultado();
			mensagem = new Mensagem();
			mensagem.setId(Integer.parseInt(request.getParameter("id")));
			fachada.validaCampos(mensagem, resultado);
			if(resultado.isResultado()){
				fachada.Excluir(mensagem);
				response.sendRedirect("Home.html");
			}
		}
		
		if(request.getParameter("gerarRelatorio") != null){
			projeto = new Projeto();
			request.setAttribute("listaProjetos", fachada.Pesquisar(projeto));
			request.getRequestDispatcher("Relatorio.jsp").forward(request, response);
		}
		if(request.getParameter("buscaInformacoesProjeto") != null){
			resultado = new Resultado();
			tarefas = new ArrayList<>();
			projeto = new Projeto();
			atividades = new ArrayList<>();
			projeto.setId(Integer.parseInt(request.getParameter("buscaInformacoesProjeto")));
			fachada.validaCampos(projeto, resultado);
			if(resultado.isResultado()){
				fachada.Pesquisar(projeto);
				projeto.addTarefa(new Tarefa());
				for(Tarefa t: (List<Tarefa>)(Object)fachada.Pesquisar(new Tarefa(projeto))){
					t.addAtividades(new Atividade());
					for(Atividade a:(List<Atividade>)(Object)fachada.Pesquisar(new Atividade(t))){
						atividades.add(a);
					}
					tarefas.add(t);
				}
				request.setAttribute("listaAtividades", atividades);
				request.setAttribute("projeto", projeto);
				request.setAttribute("listaTarefas", tarefas);
				request.getRequestDispatcher("InformacoesProjeto.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnEntrar") != null){
			funcionario = new Funcionario();
			funcionario.setLogin(request.getParameter("txtUsuario"));
			funcionario.setSenha(request.getParameter("txtSenha"));
			fachada.verificarLogin(funcionario);
			if(Login.getInstance().getId() == 0){
				request.setAttribute("erro", "Usuario ou senha invalidos.");
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
			else
				response.sendRedirect("Home.html");
			funcionario = new Funcionario();
		}
	}


}
