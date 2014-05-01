<%@ page language="java" session="true"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Comment</title>
</head>
<body>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<body>

<a href="/eblog/Logout">logout</a>
<a href="/eblog/posts.jsp">View posts</a>


<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
	url="jdbc:mysql://localhost/news" user="root" password="justin" />

<sql:query dataSource="${snapshot}" var="result">
select * from posts order by id desc;
</sql:query>
<table align='center' width=500>
	<tr>
		<th>
		<h2>Welcome to News for all..</h2>
		</th>
	</tr>
	<tr>
		<th>your logged in as <%
			out.println(session.getAttribute("first"));
		%>
		</th>
	</tr>
	<tr>
		<td>${status }</td>
	</tr>
	<form action='/eblog/PostMgr' method="post">
	<tr>
		<td><input type="text" name="headline" placeHolder="title"></input></td>
	</tr>
	<tr>
		<td><textarea cols=30 rows=10 placeHolder="What's on your mind"
			name="post"></textarea></td>
	</tr>
	<tr>
		<td><input type='submit' value='Update'></input></td>
	</tr>
	</form>
	<tr>
		<td><c:forEach var="row" items="${result.rows}">
			<tr>
				<td>
				<h3><c:out value="${row.headline}" /></h3>
				</td>
			</tr>
			<tr>
				<td>
				<p><c:out value="${row.body}" /> By <c:out value="${row.user}" />
				On <c:out value="${row.time}" /> <a
					href='/eblog/comment.jsp?id=<c:out value="${row.id}"/>'>Comment</a>
				</p>
				</td>
				<td></td>
			</tr>
		</c:forEach>
</table>
</body>
</html>
