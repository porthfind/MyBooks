<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
		
	<div class="container">
		
		<c:choose>
			<c:when test="${not empty book && book.id != 0}">
				<h1>Update Book</h1>
			</c:when>
			<c:otherwise>
				<h1>Add Book</h1>
			</c:otherwise>
		</c:choose>
		
		<form:form method="post" modelAttribute="book">
			
			<fieldset class="mb-3">
				<form:label path="name">Name: </form:label>
				<form:input type="text" path="name" class="col-sm-4"/><br>
				<form:errors path="name" cssClass="text-warning"/>
			</fieldset>
			
			
			<fieldset class="mb-3">
				<form:label path="isbn">ISBN: </form:label>
				<form:input type="text" path="isbn" class="col-sm-4"/><br>
				<form:errors path="isbn" cssClass="text-warning"/>
			</fieldset>
			   
			
			<fieldset class="mb-3">
				<form:label path="startedDate">Start Reading Date: </form:label>
				<form:input type="text" path="startedDate"/><br>
				<form:errors path="startedDate" cssClass="text-warning"/>
			</fieldset>
			
			<fieldset class="mb-3">
				<form:label path="endDate">End Reading Date: </form:label>
				<form:input type="text" path="endDate"/>
				<form:errors path="endDate" cssClass="text-warning"/>
			</fieldset>
			
			<fieldset class="mb-3">
				<form:label path="comment">Comment: </form:label>
				<form:input type="text" path="comment" class="col-sm-4"/>
				<form:errors path="comment" cssClass="text-warning"/>
			</fieldset>
				
			<input type="submit" class="btn btn-outline-primary" value="Save">
			<input type="button" class="btn btn-outline-primary" value="Cancel" onclick="history.back();"/>
		</form:form>
	</div>

<%@ include file="common/footer.jspf"%>

			<script type="text/javascript">
			 $('#startedDate').datepicker({
				format: 'dd-mm-yyyy'
			  });
			 $('#endDate').datepicker({
				format: 'dd-mm-yyyy'
			  });
			</script>
	