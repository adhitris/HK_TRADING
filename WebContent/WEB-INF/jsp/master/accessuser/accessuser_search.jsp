<%@page import="org.springframework.jdbc.core.RowCallbackHandler"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<link rel="stylesheet" type="text/css" media="screen"
	href="<%=request.getContextPath()%>/assets/jqgrid/css/ui.jqgrid.css" />

<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/custom/jquery.ui.combogrid-1.6.2.js?v=${countVersionJs}"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/jqgrid/grid.locale-en.js?v=${countVersionJs}"></script>
<script type="text/javascript">
	jQuery.jgrid.no_legacy_api = true;
</script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/jqgrid/jquery.jqGrid.min.js?v=${countVersionJs}"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/accessuser/accessuser_search.js?v=${countVersionJs}"></script>

<div class="onecolumn">
	<div class="header">
		<span>Akses User</span> <span id="showContent"> 
			<a id="buttonShowContent" href="#"><img src="<%=request.getContextPath()%>/assets/images/icon/color_18/directional_up.png" /></a>
		 </span>
	</div>
	<div class="clear"></div>
	
		<div id="dialog" title="Download">
			<table cellpadding="1" cellspacing="1">
				<tr>
					<td height="10px;">Tipe Report</td>
					<td> : </td>
					<td>
						<input type="radio" id="tipeReport" checked="checked" name="tipeReport"/ value="pdf">pdf
						<input type="radio" id="tipeReport" name="tipeReport" value="xls"/>exel
					</td>
				</tr>
				<tr>
					<td height="50px;">Status User</td>
					<td> : </td>
					<td>
						<input type="radio" id="statusUser" checked="checked" value="true" name="statusUser"/>Aktif
						<input type="radio" id="statusUser" value="false" name="statusUser"/>Non Aktif
					</td>
				</tr>
				
				<tr>
					<td></td>
					<td> </td>
					<td>
						<a href="#" class="uibutton loading" id="download">Download</a>
					</td>
				</tr>
				
			</table>
		</div>
	<div class="content" id="thecontent">

		<form id="filterForm" name="filterForm" action="" method="post">
			
			
			<div id="container">
			
					<table width="600" border="0" style="padding: 10px;">
			          	<tr>
			            	<td><strong>Nama</strong></td>
			              	<td><input name="firstName" type="text" class="firstName" id="firstName" size="33" maxlength="50"/></td>
			               
			          	</tr>
			          	<tr>
			            	<td><strong>Menu</strong></td>
			              	<td><input name="module" type="text" class="module" id="module" size="33" maxlength="50" onkeyup="setIdNull(event,'moduleId')"/>
			              	<input name="moduleId" type="hidden" class="moduleId" id="moduleId" size="33" maxlength="50"/></td>
			          	</tr>
			          	<tr>
			            	<td><strong>Departemen</strong></td>
			              	<td>
			              		<input type="text" id="department" size="33" onkeyup="setIdNull(event,'departmentId')" />
								<input type="hidden" id="departmentId"  />
			              	</td>
		          		</tr>
			          	<tr>
						<td height="50px"></td>
						<td>
							<input type="button" value='Cari' class="uibutton loading" onclick="gridReload()" /> 
							<input type="button" value='Reset' class="uibutton special" onclick="gridReset()" /> 
							
						</td>
						<td></td>
					</tr>
			          	
			    	</table>
				</div>
				
				
				


			<div id="clear"></div>
			
		</form>
	</div>
</div>
<div class="onecolumn">
	<div class="header">
	
			<span style="padding: 9" class="tip"> 
					<a title="Ubah Data"  href="#" id="updateUser">
						<img src="<%=request.getContextPath()%>/assets/images/icon/color_18/pencil.png" />
					</a> 
			</span>
			<span style="padding: 9;align:right;" class="tip"> 
					<a title="Copy" id="accessUserCopy"  href="#">
						<img src="<%=request.getContextPath()%>/assets/images/icon/color_18/copy.png" />
					</a> 
			</span>

	</div>
	<div class="clear"></div>
	<div class="content" id="thecontent"style="padding-left: 5px; padding-top: 10px;"  >
		<table id="grid"><tr><td/></tr></table>
    	<div id="gridpager"></div>
	</div>
</div>

<form:form id="diloagCopy"  method="post"  autocomplete="off">
		<div id="dialog5" title="Copy Hak Akses">
				<div id="container">
				<div align="center" id="loading5"></div>
					<h5 id="title5"></h5>
					<br/>
					<table>
						<tr>
							<td>Copy Dari </td>
							<td>
								<input type="text" class="userCopy" id="userCopy" size="33" maxlength="50" />
								<input type="hidden" id="idCopy" />
							</td>
						</tr>
						<tr>
							<td></td>
							<td><span class="uibutton loading" id="copyHakAkses">Copy</span></td>
						</tr>
					</table>
					
				</div>
				<br/>
		</div>	
	</form:form>

