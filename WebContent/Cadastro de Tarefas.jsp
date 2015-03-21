<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.List"%>
<%@page import="modelo.Habilidade"%>
<%@page import="modelo.Tarefa"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>:::::Cadastro de Tarefas:::::</title>
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
			<form action="ProjetoServlet" method="get"
				style="font-family: Arial, Helvetica, sans-serif">
				<center>
					<br />
					<h1>Cadastro de Tarefas</h1>
					<br /> <br />
					<fieldset
						style="position: relative; height: 250px; width: 400px; border-color: red;">
						<legend>Cadastro</legend>
						<table border="0"
							style="position: relative; color: black; left: 40px;">
							<tr>
								<td><label for="txtTarefa">Tarefa: </label></td>
								<td><input type="text" name="txtTarefa"
									<%Tarefa tarefa = (Tarefa) request.getAttribute("tarefa");
			if (tarefa != null)
				out.println("value='" + tarefa.getNome() + "' ");%>
									placeholder="Nome da tarefa" title="Digite o nome da tarefa."></td>
							</tr>
							<tr>
								<td><label for="txtDescricaoTarefa">Descricao: </label></td>
								<td><input type="text" name="txtDescricaoTarefa"
									<%if (tarefa != null)
				out.println(" value='" + tarefa.getDescricao() + "' ");%>
									placeholder="Nome da tarefa"
									title="Digite a descricao da tarefa."/></td>
							</tr>
							<tr>
								<td><label for="txtDtInicio">Data de In&iacute;cio:
								</label></td>
								<td><input type="date" name="txtDtInicio"
									<%if (tarefa != null)
				out.println("value=" + tarefa.getData_inicio() + "");%>
									placeholder="Data de início"
									title="Digite a data de início da tarefa."></td>
							</tr>
							<tr>
								<td><label for="txtDtFinal">Data Final: </label></td>
								<td><input type="date" name="txtDtFinal"
									<%if (tarefa != null)
				out.println("value=" + tarefa.getData_fim() + "");%>
									placeholder="Data de término"
									title="Digite a data de término da tarefa."></td>
							</tr>
							<tr>
								<td><label for="txtRequisitos">Requisitos: </label></td>
								<td><select name="cbxRequisitos">
										<%
											List<Habilidade> habilidades = (List<Habilidade>) request
													.getAttribute("habilidades");
											if (habilidades != null) {
												for (Habilidade habilidade : habilidades) {
													out.println("<option>" + habilidade.getNome() + "</option>");
												}
											}
										%>
								</select></td>
							</tr>
							<tr>
								<td><label for="txtPrioridade">Prioridade: </label></td>
								<td><input type="text" name="txtPrioridade"
									<%if (tarefa != null)
				out.println("value=" + tarefa.getPrioridade() + "");%>
									placeholder="Prioridade da tarefa"
									title="Digite a prioridade da tarefa"></td>
							</tr>
						</table>
						<br /> <a class="fechar_janela" href="#"><input type="submit"
							name="btnCadastrarTarefa" value="Cadastrar" /></a><input
							type="submit" name="btnNovaAtividade" value="Nova Atividade" />
				</center>
				</fieldset>
				<div id="janela">
					<div id="fechar">
						<a href="#">[X]</a>
					</div>
					<iframe width="500px" height="400px" align="middle" scrolling="no"
						frameborder="0" allowfullscreen src="Cadastro de Atividades.html">
					</iframe>
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