<%@page import="modelo.Habilidade"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Login"%>
<%@page import="modelo.Funcionario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="PaginaEstilo.css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>:::::Funcion&aacute;rios::::::</title>
<script>
<!--Mensagem-->
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

<!--Mensagem-->
<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
	text-decoration: none
}

.abrir_janela_mensagem {
	color: #000;
	font: Arial, Helvetica, sans-serif
}

#janelaMensagem {
	width: 500px;
	min-height: 400px;
	position: absolute;
	left: 50%;
	top: 45%;
	background-color: #FFF;
	margin-left: -290px;
	margin-top: -160px;
	z-index: 1;
	display: none;
}

#fecharMensagem {
	width: 100px;
	height: 50px;
	position: absolute;
	top: 0px;
	right: -75px;
	color: #FFF;
	z-index: 2
}

#bgshadowMensagem {
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
</head>

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
			<form method="get" action="ProjetoServlet">
				<label>Digite o codigo do funcionario <input type="text"
					name="txtPesquisaFuncionario" id="txtPesquisaFuncionario" />
				</label> <input type="submit" name="btnPesquisarFuncionario"
					id="btnPesquisarFuncionario" value="Pesquisar" />


				<%
					//Funcionario logado = Login.getInstance();
					Funcionario funcionario = (Funcionario) request
							.getAttribute("funcionario");

					out.println("<br>Codigo: <input type=text name=txtIdFuncionario value="
							+ funcionario.getId()
							+ ">Funcionario: <input type=text name=txtNomeFuncionario value="
							+ funcionario.getNome() + "><br>");
					out.println("Cargo: <input type=text name=txtCargo value="
							+ funcionario.getCargo()
							+ "><br>"
							+ "Perfil: <input type=radio name=rdgPerfil id=rdgPerfil_0 value=Administrador ");
					if (funcionario.getTipo_acesso().equals("Administrador"))
						out.println(" checked ");
					out.println(">Administrador"
							+ "<input type=radio name=rdgPerfil id=rdgPerfil_1 value=Comum ");
					if (funcionario.getTipo_acesso().equals("Comum"))
						out.println(" checked ");
					out.println(">Comum<br>"
							+ "Login: <input type=text name=txtLogin value="
							+ funcionario.getLogin()
							+ "> Senha: <input type=text name=txtSenha value="
							+ funcionario.getSenha() + "><br>");
				%>
				<table width="100%" border="1" cellspacing="1" cellpadding="0">
					<%
				List<Habilidade> habilidades = (List<Habilidade>) request.getAttribute("listaHabilidades");
				
				if(habilidades != null){
					if(habilidades.size() > 0){
						out.println("");
						out.println("<tr>"+
						"<td>Habilidade</td>"+
						"<td>Codigo</td>"+
						"</tr>");
						
						for(Habilidade habilidade : habilidades){
							out.println("<tr>");
							out.println("<td><a href='http://localhost:8080/GestaoProjetos/ProjetoServlet?txtPesquisaHabilidade=" + habilidade.getId() 
									+ "&txtNomeHabilidade=" + habilidade.getNome() + "'>" 
									+ habilidade.getNome() + "</a></td>");
							out.println("<td>" + habilidade.getId() + "</td>");
							out.println("</tr>");
							
						}//for
					}
				}
				%>
				</table>
				<%
				if(Login.getInstance().getTipo_acesso().equals("Administrador"))
					out.println("<input type='submit' name='btnAlterarFunc' id='btnAlterarFunc' value='Alterar' />" 
								+"<input type='submit' name='btnExcluirFunc' id='btnExcluirFunc' value='Excluir' />"
								+"<input type='submit' name='btnAddHabilidade' id='btnAddHabilidade' value='Adicionar Habilidade' />");
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
