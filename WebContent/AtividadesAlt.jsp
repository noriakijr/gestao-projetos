<%@page import="modelo.Login"%>
<%@page import="modelo.Projeto"%>
<%@page import="modelo.Atividade"%>
<%@page import="modelo.Tarefa"%>
<%@page import="modelo.Funcionario" %>
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
<body bgcolor="#555555" >
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
						</ul></li>
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
			<form id="form1" name="form1" method="get" action="ProjetoServlet" >
				<label>Digite o codigo do projeto <input type="text"
					name="txtPesquisaProjeto" id="txtPesquisaProjeto" />
				</label> <input type="submit" name="btnPesquisarProjeto"
					id="btnPesquisarProjeto" value="Pesquisar" />

					<%
					Funcionario logado = Login.getInstance();
						Projeto projeto = (Projeto) request.getAttribute("projeto");
						out.println("<br>Projeto: " + projeto.getNome() + "<br>");
						out.println("Gerente: " + projeto.getGerente().getNome() + "<br>"
								+ " Inicio: " + projeto.getData_inicio()
								+ " Conclusao Prevista: " + projeto.getData_fim()
								+ "<br><br>");

						Tarefa tarefa = (Tarefa) request.getAttribute("tarefa");
						out.println("Codigo: " + tarefa.getId() + " Tarefa: "
								+ tarefa.getNome() + "<br>");
						out.println("Funcionario: " + tarefa.getFuncionario().getNome()
								+ "<br>" + "Inicio: " + tarefa.getData_inicio()
								+ " Conclusao Prevista: " + tarefa.getData_fim() + "<br>");

						Atividade atividade = (Atividade) request.getAttribute("atividade");
						out.println("<br><input type=checkbox id=cbStatusAtividade name=cbStatusAtividade ");
						if (atividade.isStatus())
							out.println("checked='checked'");
						out.println(" value=true>Status");
						out.println(" Codigo: <input type=text name=txtIdAtividade value='"
								+ atividade.getId()
								+ "'><br>"
								+ "Atividade: <input type=text name=txtNomeAtividade value='"
								+ atividade.getNome() + "'><br>");
						out.println("Descrição: <input type=text name=txtDescricao value='"
								+ atividade.getDescricao()
								+ "'> Qtde. horas: <input type=text name=txtQtdeHoras value='"
								+ atividade.getQtde_horas() + "'><br>");
					%>
				
				<%
				if(logado.getId() == tarefa.getFuncionario().getId() || logado.getTipo_acesso().equals("Administrador"))
					out.println("<input type='submit' id='btnAlterarAtividade' name='btnAlterarAtividade' value='Alterar' />" 
							+"<input type='submit' id='btnExcluirAtividade' name='btnExcluirAtividade' value='Excluir' />");
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