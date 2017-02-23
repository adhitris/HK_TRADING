<%@page import="org.springframework.jdbc.core.RowCallbackHandler"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/user/user_pass_otorisasi_edit.js?v=${countVersionJs}"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/userDWR.js?v=${countVersionJs}"></script>

<div class="onecolumn" >
	<div class="header" align="right">
		<span >
			<span style="padding: 0" class="tip"> 
				<a title="Kembali"  href="javascript: history.go(-1)">
					<img src="<%=request.getContextPath()%>/assets/images/icon/color_18/directional_left.png" />
				</a> 
			</span>	
			Reset Password Otorisasi
		</span>
	</div>
	<div class="clear"></div>
                
 	<form:form id="user_new" class="user_new" name="user_new" method="post" modelAttribute="user">
		<input type="hidden" name="hiddenId" value="${user.id}"/>
		<table width="700" border="0" style="padding: 10px;">
			<tr>
            	<td width="200px"><strong>User Id</strong></td>
              	<td width="200px"><input name="id" type="text" class="id" id="id" size="33" maxlength="50" value="${user.id}" onkeyup="validateId()"; <c:if  test="${not empty user.id}"> <c:out value=" disabled='disabled'"/> </c:if> /></td>
                <td width="300px"><span id="idInfo"></span></td>
          	</tr>
          	<tr>
            	<td><strong>Password Otorisasi</strong></td>
              	<td><input name="passwordOtorisasi" type="password" class="passwordOtorisasi" id="passwordOtorisasi" size="33" maxlength="50" /></td>
                <td><span id="passwordOtorisasiInfo"></span></td>
          	</tr>
          	
            <tr>
            	<td height="39">&nbsp;</td>
				<td>
                	<input name="submit" class="uibutton icon add " type="submit" value="Simpan"/>
                	<a href="javascript: history.go(-1)" class="uibutton special">Batal</a>
                </td>
              	<td></td>
            </tr>
    	</table>
	</form:form>                            
</div>
				                      
                
	
	
	