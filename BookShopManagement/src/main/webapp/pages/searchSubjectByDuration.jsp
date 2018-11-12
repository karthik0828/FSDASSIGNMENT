<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<body>
	<h2>Search Subject</h2>
	<form:form method="POST" action="searchSubject">
 Enter Duration in hrs : <form:input type="text" path="durationInHours"></form:input>
		<input type="submit" value="Search Subject"></input>
	</form:form>
	<br><br>
	
	<a href="">Go back to menu</a><br>
</body>
</html>