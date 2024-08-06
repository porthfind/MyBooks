<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

	<div class="container">
	
		<h1>Details Book</h1>
		<br>
		
		<div class="row">
		    <div class="col">
		        <div class="text-end">
		            <a href="delete-book?id=${book.id}" class="btn btn-outline-danger">Delete</a>
		            <a href="update-book?id=${book.id}" class="btn btn-outline-warning">Update</a>
		        </div>
		    </div>
		</div>
			
		<table>
		
				<tr>
					<td>Name:</td>
					<td>${book.name}</td>
				</tr>
				<tr>
					<td>ISBN:</td>
					<td>${book.isbn}</td>
				</tr>
				<tr>
					<td>Start reading date:</td>
					<td>${book.startedDate}</td>
				</tr>
				<tr>
					<td>End reading date:</td>
					<td>${book.endDate}</td>
				</tr>
				<tr>
					<td>Comment:</td>
					<td>${book.comment}</td>
				</tr>
		
		</table>
		<br>
		<input type="button" class="btn btn-outline-primary" value="Cancel" onclick="history.back();"/>
	
	</div>
	


<%@ include file="common/footer.jspf"%>