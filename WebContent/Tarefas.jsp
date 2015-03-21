<%@page import="modelo.Login"%>
<%@page import="modelo.Funcionario"%>
<%@page import="modelo.Tarefa"%>
<%@page import="modelo.Projeto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="PaginaEstilo.css" />
<script type="text/javascript">
	var idAntigo = 0;
	function getId(id) {
		if (id != idAntigo) {
			document.getElementById("idFuncionario").removeChild(
					document.getElementById("idHidden"));
		}
		idAntigo = id;
		var campo = document.getElementById("idFuncionario");
		campo.innerHTML = campo.innerHTML
				+ "<div id=idHidden><input type=hidden name=idFuncionario value='" + id + "'/></div>";
	}
</script>
</head>
<script>
	$(document).ready(function() {
		$('.abrir_janela').click(function() {
			$('#bgshadow').css({
				opacity : 0.6
			});
			$('#bgshadow').fadeIn(300);
			$('#janela').fadeIn(1000);

			return false;
		});

		$('#bgshadow, #fechar').click(function() {
			$('#bgshadow, #janela').fadeOut(300);
		});
	});
	$(document).ready(function() {
		$('.abrir_janela_mensagem').click(function() {
			$('#bgshadowMensagem').css({
				opacity : 0.6
			});
			$('#bgshadowMensagem').fadeIn(300);
			$('#janelaMensagem').fadeIn(1000);

			return false;
		});

		$('#bgshadowMensagem, #fecharMensagem').click(function() {
			$('#bgshadowMensagem, #janelaMensagem').fadeOut(300);
		});
	});
</script>
<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
	text-decoration: none
}

.abrir_janela {
	color: #000;
	font: Arial, Helvetica, sans-serif
}

#janela {
	width: 600px;
	min-height: 400px;
	position: absolute;
	left: 50%;
	top: 50%;
	background-color: #FFF;
	margin-left: -290px;
	margin-top: -160px;
	z-index: 1;
	display: none;
}

#fechar {
	width: 100px;
	height: 50px;
	position: absolute;
	top: 0px;
	right: -75px;
	color: #FFF;
	z-index: 2
}

#bgshadow {
	width: 100%;
	min-height: 1000px;
	background: #000;
	position: absolute;
	left: 0px;
	top: 0px;
	display: none;
	z-index: 0;
}
</style>
<body bgcolor="#555555">
	<!--CABEÇALHO-->
	<div id="CABECALHO"></div>
	<!--Inicio CENTRALIZAR-->
	<div id="CENTRALIZAR_BODY">
		<!--Inicio BODY-->
		<div id="BODY">
			<!--Inicio LOGO-->
			<div style="position: relative; width: 140px; left: 558px;">
				<img src="Logo FANPK.jpg" />
			</div>
			<div id="LOGO">
				<font face="Arial, Helvetica, sans-serif" size="+5">SGP</font><br />
				<br /> Sistema de Gerenciamento de Projetos
			</div>
			<!--Fim LOGO-->
			<!--Inicio BANNER-->
			<div id="BANNER">
				<img src="Gerenciamento-de-Projetos.jpg" />
			</div>
			<!--Fim BANNER-->
			<!--Inicio menu-->
			<div id="MENU">
				<ul class="menu" style="background-color: #03C; border: 0;">
					<li style="border-right: #03C;"><img src="faceMensage.png"
						width="26px" height="26px" />
						<ul>
							<li><a href="http://localhost:8080/GestaoProjetos/ProjetoServlet?caixaDeEntrada=1">Entrada</a></li>
			        		<li><a href="http://localhost:8080/GestaoProjetos/ProjetoServlet?caixaDeSaida=1">Enviadas</a></li>
			                <li><a href="http://localhost:8080/GestaoProjetos/ProjetoServlet?novaMensagem=1">Nova Mensagem</a></li>
									
						</ul>
					</li>
				</ul>
				<ul class="menu"
					style="background-color: #03C; border: 0; width: 100px; height: 26px;"></ul>
				<nav>
				<ul class="menu">
					<li><a href="Home.html">Home</a></li>
					<li><a href="Projetos.jsp">Projetos</a></li>
					<li><a href="Funcionarios.jsp">Funcionarios</a></li>
					<li><a href="http://localhost:8080/GestaoProjetos/ProjetoServlet?gerarRelatorio=1">Relatório</a></li>
					<li><a href="#">Contatos</a></li>
					<li><a href="Login.jsp">Sair</a></li>
				</ul>
				</nav>
			</div>
			<!--Fim menu-->
			<form id="form1" name="form1" method="get" action="ProjetoServlet">
				<label>Digite o codigo do projeto <input type="text"
					name="txtPesquisaProjeto" id="txtPesquisaProjeto" />
				</label> <input type="submit" name="btnPesquisarProjeto"
					id="btnPesquisarProjeto" value="Pesquisar" />
				<table width="100%" border="1" cellspacing="1" cellpadding="0">

					<%
						Funcionario logado = Login.getInstance();
						Projeto projeto = (Projeto) request.getAttribute("projeto");
									List<Funcionario> funcionarios = (List<Funcionario>) request
											.getAttribute("funcionarios");

									out.println("<br>Codigo: <input type=text name=txtIdProjeto value= '"
											+ projeto.getId()
											+ "'>Projeto: <input type=text name=txtProjeto value='"
											+ projeto.getNome() + "'><br>");
									out.println("Gerente: <select name='cbxGerente' onchange='getId(this.value)'>"
											+ "<option value='"
											+ projeto.getGerente().getId()
											+ "'>"
											+ projeto.getGerente().getNome() + "</option>");
									for (Funcionario funcionario : funcionarios) {
										if (funcionario.getId() == projeto.getGerente().getId())
											continue;
										out.println("<option value='" + funcionario.getId() + "'>"
												+ funcionario.getNome() + "</option>");
									}
									out.println("</select>");

									out.println(" Descrição: <input type=text name=txtDescricaoProjeto value='"
											+ projeto.getDescricao()
											+ "'/><br>"
											+ "Inicio: <input type='date' name=txtDtInicio value='"
											+ projeto.getData_inicio()
											+ "'> Conclusao Prevista: <input type=date name=txtDtFinal value='"
											+ projeto.getData_fim() + "'><br>");

									List<Tarefa> tarefas = (List<Tarefa>) request
											.getAttribute("listaTarefas");
									//verifica se há algum projet na lista
									if (tarefas != null)
										if (tarefas.size() > 0) {
											out.println("");
											out.println("<tr>" + "<td>Tarefa</td>" + "<td>Codigo</td>"
													+ "<td>Descricao</td>" + "<td>Prioridade</td>"
													+ "<td>Funcionario</td>" + "<td>Inicio</td>"
													+ "<td>Conclusão Prevista</td>" + "<td>Status</td>"
													+ "</tr>");

											for (Tarefa tarefa : tarefas) {
												out.println("<tr>");
												out.println("<td><a href='http://localhost:8080/GestaoProjetos/ProjetoServlet?txtPesquisaTarefa="
														+ tarefa.getId()
														+ "&txtPesquisaProjeto2="
														+ projeto.getId()
														+ "'>"
														+ tarefa.getNome()
														+ "</a></td>");
												out.println("<td>" + tarefa.getId() + "</td>");
												out.println("<td>" + tarefa.getDescricao() + "</td>");
												out.println("<td>" + tarefa.getPrioridade() + "</td>");
												out.println("<td>" + tarefa.getFuncionario().getNome()
														+ "</td>");
												out.println("<td>" + tarefa.getData_inicio() + "</td>");
												out.println("<td>" + tarefa.getData_fim() + "</td>");
												out.println("<td>" + tarefa.getStatus()
														+ "</td>");
												out.println("</tr>");

											}//for

										}//if
					%>
				</table>
				<div id="idFuncionario">
					<div id=idHidden>
						<input type=hidden name=idFuncionario
							value='<%out.print("" + projeto.getGerente().getId());%>' />
					</div>
				</div>
				<%
				if(logado.getTipo_acesso().equals("Administrador")){
					out.println("<input type='submit' id='btnAlterarProjeto' name='btnAlterarProjeto' value='Alterar Projeto' />"
							+"<input type='submit' id='btnExcluirProjeto' name='btnExcluirProjeto' value='Excluir Projeto' />"
							+"<input type='submit' id='btnNovaTarefa' name='btnNovaTarefa' value='Nova Tarefa' />");
					
				}
				%>
				<div id="janelaMensagem">
					<div id="fecharMensagem">
						<a href="#">[X] 
					</div>
					<iframe width="500px" height="400px" frameborder="0"
						allowfullscreen src="MensagemBox.html"> </iframe>
				</div>
				<div id="bgshadowMensagem"></div>
			</form>
		</div>
		<!--Fim BODY-->
	</div>
	<!--Fim CENTRALIZAR-->
	<!--RODAPÉ-->
	<div id="RODAPE"></div>
</body>
</html>