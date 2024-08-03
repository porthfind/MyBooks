<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

	<div class="container">
			
			<h1>Welcome ${name}</h1> 
			<br>
			<h3>My Books</h3>
			
			<div class="row">
			    <div class="col">
			        <div class="text-end">
			            <a href="add-book" class="btn btn-outline-success">Add a book</a>
			        </div>
			    </div>
			</div>

		
			<table class="table">
				<thead>
					<tr>
						<th>Name/Comment</th>
						<th>ISBN</th>
						<th>Start reading date</th>
						<th>End reading date</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${books}" var="book">
						<tr>
							<td id="myButton">${book.name}<br><em><span style="font-size:12px;color:grey;">${book.comment}</span></em></td>
							<td>${book.isbn}</td>
							<td>${book.startedDate}</td>
							<td>${book.endDate}</td>
							<td><a href="update-book?id=${book.id}" class="btn btn-outline-warning">Update</a></td>
							<td><a href="details-book?id=${book.id}" class="btn btn-outline-warning">Detail</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
	</div>
	

<%@ include file="common/footer.jspf"%>