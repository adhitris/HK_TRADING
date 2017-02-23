<%@page import="org.springframework.jdbc.core.RowCallbackHandler"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/custom/jquery.ui.combogrid-1.6.2.js?v=${countVersionJs}"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/js/module/module_add.js?v=${countVersionJs}"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/moduleDWR.js?v=${countVersionJs}"></script>

<div class="onecolumn" >
	<div class="header" align="right">
		<span >
			<span style="padding: 0" class="tip"> 
				<a title="Kembali"  href="javascript: history.go(-1)">
					<img src="<%=request.getContextPath()%>/assets/images/icon/color_18/directional_left.png" />
				</a> 
			</span>	
			<c:choose>
				<c:when test="${not empty  module.id}"> 
					<c:out value="Edit Modul"/>
				</c:when>
				<c:otherwise>
					<c:out value="Tambah Modul"/>
				</c:otherwise>
			</c:choose>
		</span>
	</div>
	<div class="clear"></div>
                
 	<form:form id="module_new" class="module_new" name="module_new" method="post" modelAttribute="module">
		<input type="hidden" id="hiddenId" value="${module.id}"/>
		<input type="hidden" id="version" value="${module.version}"/>
		<table width="800" border="0" style="padding: 10px;">
        	<tr>
            	<td width="200"><strong>Module Id</strong></td>
              	<td width="200"><input class="upercase" name="id" type="text" class="id" id="id" <c:if  test="${not empty module.id}"> <c:out value=" disabled='disabled'"/> </c:if>  onkeyup="validateId()"; value="${module.id}" /></td>
                <td width="400"><span id="idInfo" class="idInfo"></span></td>
          	</tr>
        	<tr>
            	<td><strong>Nama</strong></td>
              	<td><input name="nama" type="text" class="nama" id="name" size="33" maxlength="50" value="${module.nama}"/></td>
                <td><span id="nameinfo" class="nameinfo"></span></td>
          	</tr>
          <tr>
            	<td><strong>Status</strong></td>
              	<td>
              		<select name="status" name="status" id="status">
              			<c:choose>
						<c:when test="${module.status eq '0'}">
     
  							<option value="0" selected>parent</option>
  							<option value="1">child</option>
  						</c:when>
  						<c:when test="${module.status eq '1'}">
              
  							<option value="0">parent</option>
  							<option value="1" selected>child</option>
  						</c:when>
  						<c:otherwise>
  							<option value="0" selected>parent</option>
  							<option value="1">child</option>
  						</c:otherwise>
  						</c:choose>
					</select>
              	</td>
                <td><span id="statusinfo" class="statusinfo"></span></td>
          	</tr>
          	<tr>
            	<td><strong>Parent</strong></td>
              	<td>
              		<input type="text" class="name" id="parent" size="33" maxlength="50" value="${module.module.nama}"/>
              		<input name="moduleId" type="hidden" id="parentId" value="${module.module.id}"/>
              	</td>
                <td><span id="parentInfo" class="parentInfo"></span></td>
          	</tr>
          	<tr>
            	<td><strong>PATH</strong></td>
              	<td><input name="path" type="text" class="path" id="path" size="33" maxlength="50" value="${module.path}"/></td>
                <td><span id="pathinfo" class="pathinfo"></span></td>
          	</tr>
          	<tr>
            	<td><strong>URL</strong></td>
              	<td><input name="url" type="text" class="url" id="url" size="33" maxlength="60" value="${module.url}"/></td>
                <td><span id="urlinfo" class="urlinfo"></span></td>
          	</tr>
          		<tr>
            	<td><strong>Urutan</strong></td>
              	<td><input name="urutan" type="text" class="urutan" id="urutan" size="5" maxlength="50" value="${module.urutan}"/></td>
                <td><span id="urutaninfo" class="urutaninfo"></span></td>
          	</tr>
            <tr>
            	<td height="39">&nbsp;</td>
                <td>
                	<input name="submit" class="uibutton icon add " type="submit" value="Simpan"/>
                	<a href="javascript: history.go(-1)" class="uibutton special">Batal</a></td>
              	<td></td>
            </tr>
    	</table>
	</form:form>           
</div>
				                      
                
	
	
	