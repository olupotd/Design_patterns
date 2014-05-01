<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Comment</title>
</head>
<body>
<a href="/eblog/Logout">logout</a>
<a href="/eblog/posts.jsp">View posts</a>



<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
	url="jdbc:mysql://localhost/news" user="root" password="justin" />

<sql:query dataSource="${snapshot}" var="comments">
SELECT * from comments where post_id = <%
	out.println(request.getParameter("id"));
%>;
</sql:query>


<sql:query dataSource="${snapshot}" var="post">
SELECT * from posts where id =<jsp:scriptlet>out.println(request.getParameter("id"));</jsp:scriptlet>;
</sql:query>
<table align=center>
	<c:forEach var="col" items="${post.rows}">
		<tr>

			<td>
			<h3><c:out value="${col.headline}" /></h3>
		</tr>
		<tr>
			<td>
			<p><c:out value="${col.body}" /> By <c:out value="${col.user}" />
			on <c:out value="${col.time}" />
			<hr>
			</p>
			</td>
		</tr>
	</c:forEach>
	<tr><td><h2>Comments</h2></td></td></tr>
	<c:forEach var="row" items="${comments.rows}">

		<tr>
			<td>
			<p><c:out value="${row.comments}" /> on <c:out
				value="${row.date_posted}" /><br>
			<em> By <c:out value="${row.user}" /> </em><br />
			<hr />
			<hr />
			</td>
			</p>

			<tr>
	</c:forEach>

	<form action="http://localhost:8080/eblog/CommentMgr" method="post">
	<td><textarea cols=10 rows=5 name='comment'></textarea> <input
		type='hidden'
		value='<jsp:scriptlet>out.println(request.getParameter("id"));</jsp:scriptlet>'
		name='postid'></input> <input type=submit value=post></input>
	<hr>
	</td>
	</form>
	</tr>

	</tr>
</table>
</body>
</html>