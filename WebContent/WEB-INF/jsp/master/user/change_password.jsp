<%@page import="org.springframework.jdbc.core.RowCallbackHandler"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/user/changepassword.js?v=${countVersionJs}"></script>

<div class="onecolumn" >
	<div class="header" align="right"><span ><span class="ico  gray arrow_bidirectional"></span>Change Password</span></div>
	<div class="clear"></div>
                
 	<form:form id="new_pass" class="new_pass" name="new_pass" method="post">
		<table  style="padding: 10px;">
			<tr>
            	<td width="150px"><strong>Password Lama</strong></td>
              	<td width="180px"><input name="currentpassword"  type="password" class="currentpassword" id="currentpassword" size="33" maxlength="50" /></td>
                <td width="300px"><span id="currentpasswordinfo" class="currentpasswordinfo"></span></td>
          	</tr>
          	<tr>
            	<td width="150px"><strong>Password Baru</strong></td>
              	<td width="180px"><input name="newPassword" type="password" class="newPassword" id="newPassword" size="33" maxlength="50" /></td>
                <td width="300px"><span id="newPasswordInfo" class="newPasswordInfo"></span></td>
          	</tr>
          	<tr>
            	<td><strong>Ulangi Password Baru</strong></td>
              	<td>
              		<input name="confirmNewPassword" type="password" class="confirmNewPassword" id="confirmNewPassword" size="33" maxlength="50" />
              		<input name="userId" value="${userId}" type="hidden" class="userId" id="userId" size="33" maxlength="50" />
              	</td>
                <td><span id="confirmNewPasswordInfo" class="confirmNewPasswordInfo"></span></td>
          	</tr>
          	<tr>
            	<td height="39">
					
				</td>
                <td><input name="submit" type="submit" class="uibutton icon add " value="submit"/></td>
              	<td></td>
            </tr>
    	</table>
	</form:form>                            
</div>
				                      
                
	
	
	