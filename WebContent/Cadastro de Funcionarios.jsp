<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.List"%>
<%@page import="modelo.Habilidade"%>
<%@page import="modelo.Funcionario"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>:::::Cadastro de Funcion&aacute;rios::::::</title>
<link rel="stylesheet" type="text/css" href="PaginaEstilo.css" />
<script type="text/javascript">
	//Total máximo de campos que você permitirá criar em seu site:
	var totalCampos = 20;

	//Não altere os valores abaixo, pois são variáveis controle;
	var iLoop = 1;
	var iCount = 1;
	var linhaAtual;
	var i = 0;

	function AddCampos() {
		var hidden = document.getElementById("hidden");
		var habilidades = hidden.value.split(",");
		var camposTexto = document.getElementById('habilidades');

		var texto = "<div id='linha"+iCount+
		"'><select name=cbxHabilidades" + iCount + ">";
		for (i = 0; i < habilidades.length - 1; i++)
			texto += "<option>" + habilidades[i] + "</option>";

		texto += "<input type='button' value='Apagar' onClick='removeInput(\"linha"
				+ iCount + "\")'></div>";
		//Capturando a div principal, na qual os novos divs serão inseridos:
		camposTexto.innerHTML = camposTexto.innerHTML + texto;
		iCount++;
	}
	//Função que remove os campos;
	function removeInput(e) {
		var pai = document.getElementById('habilidades');
		var filho = document.getElementById(e);
		pai.removeChild(filho);
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
			<form id="form1" name="form1" method="get" action="ProjetoServlet">
				<center>
					<br />
					<h1>Cadastro de Funcion&aacute;rios</h1>
					<br />
					<fieldset
						style="position: relative; height: 390px; width: 400px; border-color: red;">
						<legend>Cadastro</legend>
						<table>
							<%
								Funcionario funcionario = (Funcionario) request
										.getAttribute("funcionario");
								List<Habilidade> habilidades = (List<Habilidade>) request
										.getAttribute("habilidades");
							%>
							<tr>
								<td>Nome: </label></td>
								<td><input name="funcNome" type="text" maxlength="40"
									<%if (funcionario != null)
				out.println("value='" + funcionario.getNome() + "' ");%>
									placeholder="Digite o nome"
									title="Digite o nome do funcion&aacute;rio" /><br /></td>
							</tr>
							<tr>
								<td>Telefone: </label></td>
								<td><input name="funcTelefone" type="text" maxlength="9"
									<%if (funcionario != null)
				out.println("value='" + funcionario.getTelefone() + "' ");%>
									placeholder="Digite o telefone"
									title="Digite o telefone do funcion&aacute;rio." /><br /></td>
							</tr>
							<tr>
								<td>E-mail: </label></td>
								<td><input name="funcEmail" type="text" maxlength="70"
									<%if (funcionario != null)
				out.println("value='" + funcionario.getEmail() + "' ");%>
									placeholder="Digite o e-mail"
									title="Digite o e-mail. Exemplo: funcionario@empresa.com.br" /><br /></td>
							</tr>
							<tr>
								<td>Cargo: </label></td>
								<td><input type="text" name="txtCargo" maxlength="30"
									<%if (funcionario != null)
				out.println("value='" + funcionario.getCargo() + "' ");%>
									placeholder="Digite o Cargo" title="Digite o cargo do usuario" /></td>
							</tr>
							<tr>
								<td>Login: </label></td>
								<td><input name="userLogin" type="text" maxlength="20"
									<%if (funcionario != null)
				out.println("value='" + funcionario.getLogin() + "' ");%>
									placeholder="Digite o login"
									title="Digite o seu nome de usu&aacute;rio." /><br /></td>
							</tr>
							<tr>
								<td>Senha: </label></td>
								<td><input name="userSenha" type="password" maxlength="12"
									<%if (funcionario != null)
				out.println("value='" + funcionario.getSenha() + "' ");%>
									placeholder="Digite a senha" title="Digite a sua senha" /><br /></td>
							</tr>
							<tr>
								<td>Perfil de Usu&aacute;rio:</label></td>
								<td></td>
							</tr>
							<tr>
								<td><label> <input type="radio" name="rdgPerfil"
										value="Administrador" id="rdgPerfil_0"
										<%if (funcionario != null) {
				if (funcionario.getTipo_acesso() != null)
					if (funcionario.getTipo_acesso().equals("Administrador"))
						out.println(" checked ");
			}%>
										title="Perfil Gestor tem acesso a todo o sistema." />
										Administrador
								</label></td>
								<td></td>
							</tr>
							<tr>
								<td><label> <input type="radio" name="rdgPerfil"
										value="Comum" id="rdgPerfil_1"
										<%if (funcionario != null) {
				if (funcionario.getTipo_acesso() != null)
					if (funcionario.getTipo_acesso().equals("Comum"))
						out.println(" checked ");
			}%>
										title="Perfil comum tem acesso limitado." /> Comum
								</label></td>
								<td></td>
							</tr>
						</table>
						<br /> <input type="submit" name="btnCadastrarFuncionario"
							id="funcCadastrar" value="Cadastrar" />
							<input type="submit" name="btnAddHabilidade" value="Adicionar habilidade" /><br />
				</center>
				</fieldset>
			</form>
		</div>
		<!--Fim BODY-->
	</div>
	<!--Fim CENTRALIZAR-->
	<!--RODAPÉ-->
	<div id="RODAPE"></div>
</body>
</html>
