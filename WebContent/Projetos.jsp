<%@page import="modelo.Login"%>
<%@page import="modelo.Projeto"%>
<%@page import="java.util.List"%>
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
				<ul class="menu" style="background-color:#03C; border:0;">
    	<li style="border-right:#03C;"><img src="faceMensage.png" width="26px" height="26px"/>
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
						List<Projeto> projetos = (List<Projeto>) request
														.getAttribute("listaProjetos");
										Projeto proj = (Projeto) request.getAttribute("projeto");
										if(proj != null){
											out.println("<tr>" + "<td>Projeto</td>" + "<td>Codigo</td>"
													+ "<td>Gerente</td>" + "<td>Inicio</td>"
													+ "<td>Conclusão Prevista</td>" + "<td>Status</td>"
													+ "</tr>");
											out.println("<tr>");
											out.println("<td><a href='http://localhost:8080/GestaoProjetos/ProjetoServlet?txtPesquisaProjeto1="
													+ proj.getId()
													+ "'>"
													+ proj.getNome()
													+ "</a></td>");
											out.println("<td>" + proj.getId() + "</td>");
											out.println("<td>" + proj.getGerente().getNome()
													+ "</td>");
											out.println("<td>" + proj.getData_inicio() + "</td>");
											out.println("<td>" + proj.getData_fim() + "</td>");
											out.println("<td>" + proj.getStatus()
													+ "</td>");
											out.println("</tr>");
										}
										
									//verifica se há algum projet na lista
									if (projetos != null)

										if (projetos.size() > 0) {
											out.println("<tr>" + "<td>Projeto</td>" + "<td>Codigo</td>"
													+ "<td>Gerente</td>" + "<td>Inicio</td>"
													+ "<td>Conclusão Prevista</td>" + "<td>Status</td>"
													+ "</tr>");
											
											for (Projeto projeto : projetos) {
												out.println("<tr>");
												out.println("<td><a href='http://localhost:8080/GestaoProjetos/ProjetoServlet?txtPesquisaProjeto1="
														+ projeto.getId()
														+ "'>"
														+ projeto.getNome()
														+ "</a></td>");
												out.println("<td>" + projeto.getId() + "</td>");
												out.println("<td>" + projeto.getGerente().getNome()
														+ "</td>");
												out.println("<td>" + projeto.getData_inicio() + "</td>");
												out.println("<td>" + projeto.getData_fim() + "</td>");
												out.println("<td>" + projeto.getStatus()
														+ "</td>");
												out.println("</tr>");

											}//for

										}//if
					%>
				</table>
				
				<%
				if(logado.getTipo_acesso().equals("Administrador"))
					out.print("<input type='submit' name='btnNovoProjeto' value='Novo Projeto' />"
					+"<input type='submit' name='btnDistribuicao' value='Distribuir Tarefas' />");
				%>

				<div id="janela">
					<div id="fechar">
						<a href="#">[X]</a>
					</div>
					<iframe width="600px" height="500px" align="middle" scrolling="no"
						frameborder="0" allowfullscreen src="Cadastro de Projeto.html">
					</iframe>
				</div>
				<div id="bgshadow"></div>

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