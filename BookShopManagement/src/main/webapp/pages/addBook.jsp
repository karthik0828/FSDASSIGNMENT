<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<body>
	<h2>Add Book</h2>
	<form:form method="POST" action="saveBook">
 Enter Book ID : <form:input type="text" path="bookId"></form:input>
		<br>
 Enter Book title : <form:input type="text" path="title"></form:input>
		<br> 
 Enter Book price : <form:input type="text" path="price"></form:input>
		<br> 
 Enter Book volume : <form:input type="text" path="volume"></form:input>
		<br> 
 Enter Book published Date : <form:input type="text" path="publistDt"></form:input>
		<br> 
 Enter Subject title : <form:input type="text" path="subject.subtitle"></form:input>
		<br>
		<input type="submit" value="Add Book"></input>
	</form:form>

</body>
</html>