<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<a href="">Go back to menu</a><br>

<h2>Subject search results</h2>
<table border="1" width="70%" cellpadding="2">
	<tr>
		<th>Subject title</th>
		<th>Book id</th>
		<th>Book title</th>
		<th>Book price</th>
		<th>Book volume</th>
	</tr>
	<c:forEach var="book" items="${booksbysubject}">
		<tr>
			<td>${book.subject.subtitle}</td>
			<td>${book.bookId}</td>
			<td>${book.title}</td>
			<td>${book.price}</td>
			<td>${book.volume}</td>
		</tr>
	</c:forEach>
</table>