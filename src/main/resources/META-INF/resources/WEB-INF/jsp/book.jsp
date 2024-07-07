<%@ include file="common/header.jspf"%>
		<%@ include file="common/navigation.jspf"%>
		
		<div class="container">
			
			<h1>Enter Book Details</h1>
			<form:form method="post" modelAttribute="book">
				
				<fieldset class="mb-3">
				<form:label path="name">Name: </form:label>
				<form:input type="text" path="name" required="required"/>
				<form:errors path="name" cssClass="text-warning"/>
				</fieldset>
				
				<fieldset class="mb-3">
				<form:label path="isbn">ISBN: </form:label>
				<form:input type="text" path="isbn" required="required"/>
				<form:errors path="isbn" cssClass="text-warning"/>
				</fieldset>
				
				<fieldset class="mb-3">
				<form:label path="startedDate">Started Date: </form:label>
				<form:input type="text" path="startedDate" required="required"/>
				<form:errors path="startedDate" cssClass="text-warning"/>
				</fieldset>
				
				<input type="submit" class="btn btn-success">
				
			</form:form>
		</div>
		
		<%@ include file="common/footer.jspf"%>
		
		<script type="text/javascript">
			 $('#targetDate').datepicker({
				format: 'yyyy-mm-dd'
			  });
</script>