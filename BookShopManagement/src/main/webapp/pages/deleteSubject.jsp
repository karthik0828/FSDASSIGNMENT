<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<body>
	<h2>Delete Subject</h2>
	<form:form method="POST" action="delSubject">
 Enter Subject title : <form:input type="text" path="subtitle"></form:input>
		<input type="submit" value="Delete Subject"></input>
	</form:form>
	<br><br>
	
	<a href="">Go back to menu</a><br>
</body>
</html>