<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="modelo.Tarefa"%>
<%@page import="modelo.Mensagem" %>
<%@page import="modelo.Funcionario"%>
<%@page import="java.util.List"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="PaginaEstilo.css" />
</head>
<script><!--Mensagem-->
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
        <li><a href="Funcionarios.jsp">Funcionários</a></li>
        <li><a href="http://localhost:8080/GestaoProjetos/ProjetoServlet?gerarRelatorio=1">Relatório</a></li>
        <li><a href="#">Contatos</a></li>
        <li><a href="Login.jsp">Sair</a></li>
      </ul>
  </nav></div><!--Fim menu-->
  <form style="font-family:Arial, Helvetica, sans-serif">
      <br />
	<table align="center" border="1" width="90%">
	<td colspan=4 align="center" fontcolor="#FF0000"><h1>Caixa de Entrada</h1></td>
	<tr>
	<td width="140"  ><h2>ID</h2></td><td><h2>Mensagem</h2></td><td><h2>Status</h2></td><td><h2>Data de Envio</h2></td>
	<%
		List<Mensagem> mensagens = (List<Mensagem>)request.getAttribute("listaMensagensUsuario");
			for(Mensagem mensagem:mensagens){
		out.println("<tr><td width=140>" + mensagem.getEmissorID() + " </td><td><a href='http://localhost:8080/GestaoProjetos/ProjetoServlet?mudarParaLida=" + mensagem.getId() + "'>" + mensagem.getTexto() + "</a></td>");
		if(mensagem.getStatus() == true)
			out.println("<td>Não Lida</td>");
		else
			out.println("<td>Lida</td>");
		out.println("<td>" + mensagem.getDataEnvio().toString() + "</td>");	
		}
	%>
	</tr>
		
	</table>
  </form>
  <div id="janelaMensagem">
  <div id="fecharMensagem"><a href="#">[X]</div>
  <iframe width="500px" height="400px" frameborder="0" allowfullscreen src="MensagemBox.jsp">
  </iframe>
  </div>
  <div id="bgshadowMensagem"></div>
</div><!--Fim BODY-->
</div><!--Fim CENTRALIZAR-->
<!--RODAPÉ-->
<div id="RODAPE"></div>
</body>
</html>