<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id="pagination">
	<a href="javascript: step('1')">
		First
	</a> 
	| 
	<a href="javascript: step('${filterCriteria.prevPage}')">
		Prev
	</a>
	| 
	
	Page ${filterCriteria.page} of ${filterCriteria.totalPage} 
	
	| 
	<a href="javascript: step('${filterCriteria.nextPage}')">
		Next 
	</a>
	| 
	<a href="javascript: step('${filterCriteria.totalPage}')">
		Last 
	</a>
</div>

<input type="hidden" id="requesturl" name="requesturl" value="${filterCriteria.url}"/>