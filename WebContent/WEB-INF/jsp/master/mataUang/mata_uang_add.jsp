<%@page import="org.springframework.jdbc.core.RowCallbackHandler"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<script type="text/javascript" src="<%=request.getContextPath() %>/assets/js/mataUang/mata_uang_add.js?v=${countVersionJs}"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/mataUangDWR.js?v=${countVersionJs}"></script>

<div class="onecolumn" >
	<div class="header" align="right">
		<span >
			<span style="padding: 0" class="tip"> 
				<a title="Kembali"  href="javascript: history.go(-1)">
					<img src="<%=request.getContextPath()%>/assets/images/icon/color_18/directional_left.png" />
				</a> 
			</span>	
			<c:choose>
				<c:when test="${not empty  mataUang.id}"> 
					<c:out value="Edit Mata Uang"/>
				</c:when>
				<c:otherwise>
					<c:out value="Tambah Mata Uang"/>
				</c:otherwise>
			</c:choose>
		</span>
	</div>
	<div class="clear"></div>
                
 	<form:form id="mata_uang_new" class="mata_uang_new" name="mata_uang_new" method="post" modelAttribute="mataUang" autocomplete="off">
		<input type="hidden"  value="${mataUang.id}" id="hiddenId"/>
		<input type="hidden"  value="${mataUang.version}" id="version"/>
		<table width="800" border="0" style="padding: 10px;">
			<tr>
            	<td colspan="3">
            		<div id="response" style="font: bold;color: green;"></div>
            		<div id="loading"></div>
            	</td>
          	</tr>
        	<tr>
            	<td width="200"><strong> Mata Uang Id</strong></td>
              	<td width="180"><input class="upercase" name="id" type="text" class="id" id="id" <c:if  test="${not empty mataUang.id}"> <c:out value=" disabled='disabled'"/> </c:if>  onkeyup="validateId()"; value="${mataUang.id}" /></td>
                <td width="420"><span id="idInfo" class="idInfo"></span></td>
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
				                      
                
	
	
	