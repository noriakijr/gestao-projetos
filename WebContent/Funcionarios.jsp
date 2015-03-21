<%@page import="modelo.Login"%>
<%@page import="modelo.Funcionario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>:::::Cadastro de Funcion&aacute;rios::::::</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="PaginaEstilo.css" />
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
<!--Mensagem-->
$(document).ready(function() {
    $('.abrir_janela_mensagem').click(function(){
	$('#bgshadowMensagem').css({opacity:0.6});
	$('#bgshadowMensagem').fadeIn(300);
	$('#janelaMensagem').fadeIn(1000);
	
	return false;
	});
	
	$('#bgshadowMensagem, #fecharMensagem').click(function(){
		$('#bgshadowMensagem, #janelaMensagem').fadeOut(300);
	});
});
</script>

<!--Mensagem-->
<style type="text/css">
*{ margin:0px; padding:0px; text-decoration:none}
.abrir_janela_mensagem{ color:#000; font:Arial, Helvetica, sans-serif}
#janelaMensagem{ width:500px; min-height:400px; position:absolute; left:50%; top:45%; background-color:#FFF;
margin-left:-290px; margin-top:-160px; z-index:1; display:none;}
#fecharMensagem{ width:100px; height:50px; position:absolute; top:0px; right:-75px; color:#FFF; z-index:2}
#bgshadowMensagem{ width:100%; min-height:1000px; background:#000; position:absolute; left:0px;
top:0px; display:none; z-index:0;}
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
  <div style="position:relative; width:140px; left:558px; "><img src="Logo FANPK.jpg" /></div>
  <div id="LOGO">
  	<font face="Arial, Helvetica, sans-serif" size="+5">SGP</font><br /><br />
    Sistema de Gerenciamento de Projetos
  </div><!--Fim LOGO-->
  <!--Inicio BANNER-->
  <div id="BANNER"><img src="Gerenciamento-de-Projetos.jpg"/></div><!--Fim BANNER-->
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
    <ul class="menu" style="background-color:#03C; border:0; width: 100px; height: 26px;"></ul>
  <nav>
      <ul class="menu">
      	<li><a href="Home.html">Home</a></li>
        <li><a href="Projetos.jsp">Projetos</a></li>
        <li><a href="Funcionarios.jsp">Funcionarios</a></li>
        <li><a href="http://localhost:8080/GestaoProjetos/ProjetoServlet?gerarRelatorio=1">Relatório</a></li>
        <li><a href="#">Contatos</a></li>
        <li><a href="Login.jsp">Sair</a></li>
      </ul>
  </nav></div><!--Fim menu-->
	<form id="form1" name="form1" method="get" action="ProjetoServlet">
		<label>Digite o codigo do funcionario <input type="text"
			name="txtPesquisaFuncionario" id="txtPesquisaFuncionario" />
		</label> <input type="submit" name="btnPesquisarFuncionario"
			id="btnPesquisarFuncionario" value="Pesquisar" />
		<table width="100%" border="1" cellspacing="1" cellpadding="0">
			<%
			List<Funcionario> funcionarios = (List<Funcionario>) request
									.getAttribute("listaFunc");
				Funcionario func = (Funcionario) request.getAttribute("funcionario");
				if(func != null){
					out.println("<tr>" + "<td>Funcionario</td>" + "<td>Codigo</td>"
							+ "<td>Cargo</td>" + "<td>Acesso</td>"
							+ "<td>Login</td>" + "<td>Senha</td>"
							+ "</tr>");
					out.println("<tr>");
					out.println("<td><a href='http://localhost:8080/GestaoProjetos/ProjetoServlet?txtPesquisaFuncionario1="
							+ func.getId()
							+ "'>"
							+ func.getNome()
							+ "</a></td>");
					out.println("<td>" + func.getId() + "</td>");
					out.println("<td>" + func.getCargo()
							+ "</td>");
					out.println("<td>" + func.getTipo_acesso() + "</td>");
					out.println("<td>" + func.getLogin() + "</td>");
					out.println("<td>" + func.getSenha()
							+ "</td>");
					out.println("</tr>");
				}
				
			//verifica se há algum projet na lista
			if (funcionarios != null)

				if (funcionarios.size() > 0) {
					out.println("<tr>" + "<td>Funcionario</td>" + "<td>Codigo</td>"
							+ "<td>Cargo</td>" + "<td>Acesso</td>"
							+ "<td>Login</td>" + "<td>Senha</td>"
							+ "</tr>");
					
					for (Funcionario funcionario : funcionarios) {
						out.println("<tr>");
						out.println("<td><a href='http://localhost:8080/GestaoProjetos/ProjetoServlet?txtPesquisaFuncionario1="
								+ funcionario.getId()
								+ "'>"
								+ funcionario.getNome()
								+ "</a></td>");
						out.println("<td>" + funcionario.getId() + "</td>");
						out.println("<td>" + funcionario.getCargo() + "</td>");
						out.println("<td>" + funcionario.getTipo_acesso() + "</td>");
						out.println("<td>" + funcionario.getLogin() + "</td>");
						out.println("<td>" + funcionario.getSenha() + "</td>");
						out.println("</tr>");
					}//for

				}//if
		%>
		</table>
		
		<%
		if(Login.getInstance().getTipo_acesso().equals("Administrador"))
			out.println("<input type='submit' name='btnNovoFuncionario' value='Novo Funcionário' />");
		%>
		
	<div id="janelaMensagem">
    <div id="fecharMensagem"><a href="#">[X]</div>
    <iframe width="500px" height="400px" frameborder="0" allowfullscreen src="MensagemBox.html">
    </iframe>
    </div>
    <div id="bgshadowMensagem"></div>
    
    <div id="janela">
    <div id="fechar"><a href="#">[X]</a></div>
    <iframe width="600px" height="550px" align="middle" scrolling="no" frameborder="0" allowfullscreen src="Funcionarios.html">
    </iframe>
    </div>
    <div id="bgshadow"></div>
  </form>
</div><!--Fim BODY-->
</div><!--Fim CENTRALIZAR-->
<!--RODAPÉ-->
<div id="RODAPE"></div>
</body>
</html>

