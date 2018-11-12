<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<body>
	<h2>Search Book</h2>
	<form:form method="POST" action="searchBook">
 Enter Book title : <form:input type="text" path="title"></form:input>
		<input type="submit" value="Search Book"></input>
	</form:form>
	<br><br>
	
	<a href="">Go back to menu</a><br>
</body>
</html>