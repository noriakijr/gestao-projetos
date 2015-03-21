<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.List"%>
<%@page import="modelo.Funcionario"%>
<%@page import="modelo.Projeto"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>:::::Cadastro de Projeto:::::</title>
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

			<form name="form" id="form" action="ProjetoServlet" method="get">
				<center>
					<br /> <br /> <br />
					<h1>Cadastro de Projeto</h1>
					<br />
					<fieldset
						style="position: relative; height: 250px; width: 400px; border-color: red;">
						<legend>Cadastro</legend>
						<table border="0" style="position: relative; color: black;">
							<tr>
								<td><label for="txtProjeto">Projeto:</label></td>
								<td><input type="text" name="txtProjeto"
									<%Projeto projeto = (Projeto) request.getAttribute("projeto");
			if (projeto != null)
				out.println("value='" + projeto.getNome() + "' ");%>
									placeholder="Nome do projeto" title="Digite o nome do projeto"></td>
							</tr>
							<tr>
								<td><label for="txtDescricaoProjeto">Descricao: </label></td>
								<td><input type="text" name="txtDescricaoProjeto"
									<%if (projeto != null)
				out.println(" value='" + projeto.getDescricao() + "' ");%>
									placeholder="Descricao do projeto"
									title="Digite a descricao do projeto."/></td>
							</tr>
							<tr>
								<td><label>Gerente: </label></td>
								<td><select name="cbxGerente" onchange="getId(this.value)">
										<%
											List<Funcionario> funcionarios = (List<Funcionario>) request
													.getAttribute("funcionarios");

											if (funcionarios != null) {
												for (Funcionario funcionario : funcionarios)
													out.println("<option value='" + funcionario.getId() + "'>"
															+ funcionario.getNome() + "</option>");
											}
										%>
								</select></td>
												<div id="idFuncionario">
					<div id=idHidden>
						<input type=hidden name=idFuncionario
							value='<%if(funcionarios != null) out.print(funcionarios.get(0).getId());%>' />
					</div>
				</div>
							</tr>
							<tr>
								<td><label for="txtDtInicio">Data de In&iacute;cio:</label></td>
								<td><input type="date" name="txtDtInicio"
									<%if (projeto != null)
				out.println("value=" + projeto.getData_inicio() + "");%>
									placeholder="Data de início"
									title="Digite a data de início do projeto. Formato: dd/mm/yyyy"></td>
							</tr>
							<tr>
								<td><label for="txtDtFinal">Data Final:</label></td>
								<td><input type="date" name="txtDtFinal"
									<%if (projeto != null)
				out.println("value=" + projeto.getData_fim() + "");%>
									placeholder="Data de término"
									title="Digite a data de término do projeto. Formato: dd/mm/yyyy"></td>
							</tr>
						</table>
						<br /> <input type="submit" name="btnCadastrarProjeto"
							value="Cadastrar"> <input
										type="submit" name="btnNovaTarefa" value="Nova Tarefa" />
				</center>
				</fieldset>
				<div id="janelaMensagem">
					<div id="fecharMensagem">
						<a href="#">[X]</a>
					</div>
					<iframe width="600px" height="400px" frameborder="0"
						allowfullscreen src="MensagemBox.html"> </iframe>
				</div>
				<div id="bgshadowMensagem"></div>
				<div id="janela">
					<div id="fechar">
						<a href="#">[X]</a>
					</div>
					<iframe width="600px" height="400px" scrolling="no" frameborder="0"
						allowfullscreen src="Cadastro de Tarefas.html"> </iframe>
				</div>
				<div id="bgshadow"></div>
			</form>
		</div>
		<!--Fim BODY-->
	</div>
	<!--Fim CENTRALIZAR-->
	<!--RODAPÉ-->
	<div id="RODAPE"></div>
</body>
</html>