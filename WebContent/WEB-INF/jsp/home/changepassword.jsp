<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
     pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/user_change_password.js?v=${countVersionJs}"></script>
<div class="onecolumn">

	<div class="header">
		<span><span class="ico gray home"></span><spring:message code="whm.changepassword.header"/></span>
	</div>

	<div class="clear"></div>

	<div class="content">

		<form:form name="mainform" class="mainform" id="mainform" method="post" modelAttribute="user">
			<input class="uibutton loading" name="Submit" type="submit" value='<spring:message code="whm.common.operation.save"/>' />
			<a class="uibutton special" onClick="ResetForm()" title="Reset  Form"><spring:message code="whm.common.operation.clear"/></a>
		  <table width="100%" border="0">

				<tr>

					<td width="17%" height="49"><label><spring:message code="whm.changepassword.password"/></label>
					</td>

					<td width="12%"><input name="password" id="password" type="password" size="33"
						class="password" <c:if test="${isEdit == 'true'}">readonly="readonly" style="background-color: grey;" </c:if>>
						
					</td>

					<td width="71%"><span class="passwordinfo" id="passwordinfo"></span>
					</td>

				</tr>

				<tr>

					<td height="46"><label><spring:message code="whm.changepassword.retypepassword"/></label>
					</td>

					<td><input name="retypepassword" id="retypepassword" type="password"
						size="33" class="retypepassword">
					</td>

					<td><span class="retypepasswordinfo" id="retypepasswordinfo"></span></td>

				</tr>
			</table>
			<input name="id" type="hidden" value="<sec:authentication property="principal.id"/>"/>
		</form:form>

	</div>

</div>
