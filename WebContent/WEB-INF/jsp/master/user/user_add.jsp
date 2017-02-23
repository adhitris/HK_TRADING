<%@page import="org.springframework.jdbc.core.RowCallbackHandler"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/custom/jquery.ui.combogrid-1.6.2.js?v=${countVersionJs}"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/user/user_add.js?v=${countVersionJs}"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/userDWR.js?v=${countVersionJs}"></script>

<div class="onecolumn" >
	<div class="header" align="right">
		<span >
			<span style="padding: 0" class="tip"> 
				<a title="Kembali"  href="javascript: history.go(-1)">
					<img src="<%=request.getContextPath()%>/assets/images/icon/color_18/directional_left.png" />
				</a> 
			</span>	
			<c:choose>
				<c:when test="${not empty user.id}"> 
					<c:out value="Edit User"/>
				</c:when>
				<c:otherwise>
					<c:out value="Tambah User"/>
				</c:otherwise>
			</c:choose>
		</span>
	</div>
	<div class="clear"></div>
                
 	<form:form id="user_new" class="user_new" name="user_new" method="post" modelAttribute="user" autocomplete="off">
		<input type="hidden" name="hiddenId" value="${user.id}"/>
		<input type="hidden" id="version" value="${user.version}"/>
		<div id="container" style="min-height: 200px;">
			<div id="left">
			<table width="600" border="0" style="padding: 10px;">
	        	<tr>
	            	<td width="200px"><strong>User Id</strong></td>
	              	<td width="200px"><input name="id" type="text" id="id" size="33" maxlength="50" value="${user.id}" onkeyup="validateId()"; <c:if  test="${not empty user.id}"> <c:out value=" disabled='disabled'"/> </c:if> /></td>
	                <td width="200px"><span id="idInfo"></span></td>
	          	</tr>
	          	
	          	<tr>
	            	<td><strong>Nama</strong></td>
	              	<td><input name="nama" type="text" class="nama" id="nama" size="33" maxlength="50" value="${user.nama}"/></td>
	                <td>
	                	<span id="firstNameInfo"></span>
	                	<input name="passwordOtorisasi" type="hidden" class="passwordOtorisasi" id="passwordOtorisasi" size="33" maxlength="50" />
	                </td>
	          	</tr>
          		<tr>
		            <td><strong>Limit Most Recently Used (MRU)</strong></td>
		            <td>
		              	<input  type="text"  id="mruLimit"  value="${mruLimit}" onkeypress="return isNumberKey(event)"  />
		            </td>
		            <td><span id="mruLimitInfo"></span></td>
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
	    </div>
	    <div id="right">
	    <br/>
	    	<table>
	    	<tr>
	            	<td><strong>Hak Akses</strong></td>
	              	<td>
	              		<c:choose>
						    <c:when test="${user.isCreate}">
							    <input type="checkbox" checked="checked" id="isCreate" value="1"/>Create 
						    </c:when>
						    <c:otherwise>
							    <input type="checkbox"  id="isCreate" value="0"/>Create
						    </c:otherwise>
						</c:choose>
	              	</td>
	                <td></td>
	          	</tr>
	          	<tr>
	            	<td align="right"></td>
	              	<td>
	              		<c:choose>
						    <c:when test="${user.isUpdate}">
							    <input type="checkbox" checked="checked" id="isUpdate" value="1"/>Update 
						    </c:when>
						    <c:otherwise>
							    <input type="checkbox"  id="isUpdate" value="0"/>Update
						    </c:otherwise>
						</c:choose>
	              	</td>
	                <td></td>
	          	</tr>
	          	<tr>
	            	<td align="right"></td>
	              	<td>
	              		<c:choose>
						    <c:when test="${user.isDelete}">
							    <input type="checkbox" checked="checked" id="isDelete" value="1"/>Delete 
						    </c:when>
						    <c:otherwise>
							    <input type="checkbox"  id="isDelete" value="0"/>Delete
						    </c:otherwise>
						</c:choose>
	              	</td>
	                <td></td>
	          	</tr>
	          	<tr>
	            	<td align="right"></td>
	              	<td>
	              		<c:choose>
						    <c:when test="${user.isCancel}">
							    <input type="checkbox" checked="checked" id="isCancel" value="1"/>Cancel 
						    </c:when>
						    <c:otherwise>
							    <input type="checkbox"  id="isCancel" value="0"/>Cancel
						    </c:otherwise>
						</c:choose>
	              	</td>
	                <td></td>
	          	</tr>
	          	<tr>
	            	<td align="right"></td>
	              	<td>
	              		<c:choose>
						    <c:when test="${user.isPrint}">
							    <input type="checkbox" checked="checked" id="isPrint" value="1"/>Print 
						    </c:when>
						    <c:otherwise>
							    <input type="checkbox"  id="isPrint" value="0"/>Print
						    </c:otherwise>
						</c:choose>
	              	</td>
	                <td></td>
	          	</tr>
	          	
	          	<tr>
	            	<td align="right"></td>
	              	<td>
	              		<c:choose>
						    <c:when test="${user.isReport}">
							    <input type="checkbox" checked="checked" id="isReport" value="1"/>Report 
						    </c:when>
						    <c:otherwise>
							    <input type="checkbox"  id="isReport" value="0"/>Report
						    </c:otherwise>
						</c:choose>
	              	</td>
	                <td></td>
	          	</tr>
	          	<tr>
	            	<td align="right"></td>
					<td>
	              		<c:choose>
						    <c:when test="${user.isConfirm}">
							    <input type="checkbox" checked="checked" id="isConfirm" value="1"/>Confirm 
						    </c:when>
						    <c:otherwise>
							    <input type="checkbox"  id="isConfirm" value="0"/>Confirm
						    </c:otherwise>
						</c:choose>
	              	</td>
	                <td></td>
	          	</tr>
	          	<tr>
	            	<td align="right"></td>
					<td>
	              		<c:choose>
						    <c:when test="${user.isUnconfirm}">
							    <input type="checkbox" checked="checked" id="isUnconfirm" value="1"/>Unconfirm 
						    </c:when>
						    <c:otherwise>
							    <input type="checkbox"  id="isUnconfirm" value="0"/>Unconfirm
						    </c:otherwise>
						</c:choose>
	              	</td>
	                <td></td>
	          	</tr>
	          	<tr>
	            	<td align="right"></td>
					<td>
	              		<c:choose>
						    <c:when test="${user.isSupervisor}">
							    <input type="checkbox" checked="checked" id="isSupervisor" value="1"/>Supervisor 
						    </c:when>
						    <c:otherwise>
							    <input type="checkbox"  id="isSupervisor" value="0"/>Supervisor
						    </c:otherwise>
						</c:choose>
	              	</td>
	                <td></td>
	          	</tr>
	          	<tr>
	            	<td align="right"></td>
					<td>
	              		<c:choose>
						    <c:when test="${user.isSuperuser}">
							    <input type="checkbox" checked="checked" id="isSuperuser" value="1"/>Superuser 
						    </c:when>
						    <c:otherwise>
							    <input type="checkbox"  id="isSuperuser" value="0"/>Superuser
						    </c:otherwise>
						</c:choose>
	              	</td>
	                <td></td>
	          	</tr>
	          	<tr>
	            	<td align="right"></td>
					<td>
	              		<c:choose>
						    <c:when test="${user.isReprint}">
							    <input type="checkbox" checked="checked" id="isReprint" value="1"/>Reprint 
						    </c:when>
						    <c:otherwise>
							    <input type="checkbox"  id="isReprint" value="0"/>Reprint
						    </c:otherwise>
						</c:choose>
	              	</td>
	                <td></td>
	          	</tr>
	          		<tr><td colspan="3"><br/></td></tr>
	    		
	    	</table>
	    	
	    </div>
	    </div>
    	
    	
	</form:form>                            
</div>
				                      
                
	
	
	