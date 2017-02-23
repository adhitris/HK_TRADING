<%@page import="org.springframework.jdbc.core.RowCallbackHandler"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<script type="text/javascript" src="<%=request.getContextPath() %>/assets/js/unit/unit_add.js?v=${countVersionJs}"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/unitDWR.js?v=${countVersionJs}"></script>

<div class="onecolumn" >
	<div class="header" align="right">
		<span >
			<span style="padding: 0" class="tip"> 
				<a title="Kembali"  href="javascript: history.go(-1)">
					<img src="<%=request.getContextPath()%>/assets/images/icon/color_18/directional_left.png" />
				</a> 
			</span>	
			<c:choose>
				<c:when test="${not empty  unit.id}"> 
					<c:out value="Edit Unit"/>
				</c:when>
				<c:otherwise>
					<c:out value="Tambah Unit"/>
				</c:otherwise>
			</c:choose>
		</span>
	</div>
	<div class="clear"></div>
                
 	<form:form id="unit_new" class="unit_new" name="unit_new" method="post" modelAttribute="unit" autocomplete="off">
		<input type="hidden"  value="${unit.id}" id="hiddenId"/>
		<input type="hidden"  value="${unit.version}" id="version"/>
		<table width="650" border="0" style="padding: 10px;">
			<tr>
            	<td colspan="3">
            		<div id="response" style="font: bold;color: green;"></div>
            		<div id="loading"></div>
            	</td>
          	</tr>
        	<tr>
            	<td width="200"><strong> Unit Id</strong></td>
              	<td width="200"><input class="upercase" name="id" type="text" class="id" id="id" <c:if  test="${not empty unit.id}"> <c:out value=" disabled='disabled'"/> </c:if>  onkeyup="validateId()"; value="${unit.id}" size="10" maxlength="10" /></td>
                <td width="250"><span id="idInfo" class="idInfo"></span></td>
          	</tr>
          	
          	<tr>
            	<td width="200"><strong> Nama</strong></td>
              	<td width="200"><input name="nama" type="text" class="nama" id="nama" value="${unit.nama}" /></td>
                <td width="250"><span id="namaInfo" class="namaInfo"></span></td>
          	</tr>
 
  
            <tr>
            	<td>&nbsp;</td>
                <td>
                	<input name="submit" class="uibutton icon add " type="submit" value="Simpan"/>
                	<a href="javascript: history.go(-1)" class="uibutton special">Batal</a>
                </td>
              	<td></td>
            </tr>
    	</table>
	</form:form>           
</div>
				                      
                
	
	
	