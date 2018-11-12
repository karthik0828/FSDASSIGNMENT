<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<body>
	<h2>Delete Book</h2>
	<form:form method="POST" action="delBook">
 Enter Book Id : <form:input type="text" path="bookId"></form:input>
		<input type="submit" value="Delete Book"></input>
	</form:form>
	<br><br>
	
	<a href="">Go back to menu</a><br>
</body>
</html>