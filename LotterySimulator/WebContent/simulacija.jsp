<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="loto.*"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 

<% 

String trenutnaSimulacijaId=request.getParameter("trenutnaSimulacija");
System.out.println("JSP TrenutaSimulacijaId = " + trenutnaSimulacijaId);

Map map=(Map) request.getSession().getAttribute("simulacija");

System.out.println("JSP MAP = " + map);

Loto loto=null;
IzvlacenjeDO trenutnoIzvlacenje=null;
if (map != null) { 
	loto=(Loto) map.get(trenutnaSimulacijaId);
	trenutnoIzvlacenje= loto.getTrenutnoIzvlacenje();
}

%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>

<% if (trenutnoIzvlacenje != null) { 

	List<KoloDO> kola=trenutnoIzvlacenje.getKola();


	%>
	<table>
	
	
	<tr>
	
	<td colspan="<%= loto.getTrenutnoIzvlacenje().getKola().get(0).getKombinacije().get(0).getOdigrano().size()%>"> Izvuceni brojevi: </td>
	
	</tr>
	
	
	<tr>
		
		<% 

			List<Integer> izvuceno=loto.getTrenutnoIzvlacenje().getKola().get(0).getIzvuceno();
			for (Integer broj:izvuceno) { %>
				
				<td> 
				
				<% out.print(broj.intValue()); %> &nbsp;&nbsp;&nbsp;
				
				</td>
				
		<%}%>

		
	</tr>
	
	
	

	<tr>
	
	<td colspan="<%= loto.getTrenutnoIzvlacenje().getKola().get(0).getKombinacije().get(0).getOdigrano().size()%>"> Odigrani brojevi: </td>
	
	</tr>


	<%
	
		java.util.Set<Integer> set=new java.util.HashSet<Integer>();
		set.addAll(loto.getTrenutnoIzvlacenje().getKola().get(0).getIzvuceno());
	
		for (KoloDO kolo:kola) {%>
		
		<tr>
		
		<% 
			List<Integer> odigrano=kolo.getKombinacije().get(0).getOdigrano();
			
		
			for (Integer broj:odigrano) { %>
				
				<td>
				
				
				<%
				if (set.contains(broj)) {	
				%>
				<font color="red">
				
				<%
				}
				%>
				
				
				<% out.print(broj.intValue()); %> &nbsp;&nbsp;&nbsp;
				
				
				<%
				if (set.contains(broj)) {	
				%>
				</font>
				<%
				}
				%>
				
				
				</td>
				
		<%}%>

		
		</tr>
		
	<% } %>


</table>

<% } %>

</body>
</html>