<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>

<h2>Welcome to new for all</h2>
<font color=red>${error}</font> <jsp:scriptlet>if (request.getParameter("id") != null) {

				if (request.getParameter("id").equalsIgnoreCase("reg")) {</jsp:scriptlet>


<table>
	<form action="/eblog/Register" method="post">
	<tr>
		<th colspan=2>Register</th>
	</tr>
	<tr>
		<td><input type='text' name='username' size=20
			placeholder='Username'></input></td>
		<td align=right><input type='password' name='password' size=20
			placeholder='Password'></input></td>
	</tr>
	<tr>
		<td colspan=2><input type='text' size=44 name='first_n'
			placeholder='First Name'></input></td>
	</tr>
	<tr>
		<td colspan=2><input type='text' size=44 name='last_n'
			placeholder='Last Name'></input></td>
	</tr>
	<tr>
		<td colspan=2><input type='text' size=44 name='email'
			placeholder='Email Address'></input></td>
	</tr>
	<tr>
		<td><input type='reset' value='clear'></input></td>
		<td align="right"><input type=submit value='register'></input></td>
	</tr>
	<tr>
		<td colspan=2>Or just Log in <a href='/eblog/index.jsp'>here</a></td>
	</tr>
	</form>
</table>
<jsp:scriptlet>}
			} else {</jsp:scriptlet>

<table>
	<form action="/eblog/Logmein" method="post">
	<tr>
		<th colspan=2>Login</th>
	</tr>
	<tr>
		<td colspan=2><input type='hidden' value='login' name='id'></input></td>
	</tr>
	<tr>
		<td colspan=2><input type='text' name='username'
			placeholder='username'></input></td>
	</tr>
	<tr>
		<td colspan=2><input type='password' name='password'
			placeholder='Password'></input></td>
	</tr>
	<tr>
		<td><input type='reset' value='clear'></input></td>
		<td align="right"><input type=submit value='login'></input></td>
	</tr>
	<tr>
		<td colspan=2>You can register <a href='?id=reg'>here</a></td>
	</tr>
	</form>
</table>
</center>
<%
	}
%>
</body>
</html>