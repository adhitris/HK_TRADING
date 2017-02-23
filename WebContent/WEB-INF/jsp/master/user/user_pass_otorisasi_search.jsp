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
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/user/user_pass_otorisasi_search.js?v=${countVersionJs}"></script>

<div class="onecolumn">
	<div class="header">
		<span>Reset Password Otorisasi</span> <span id="showContent"> 
			<a id="buttonShowContent" href="#"><img src="<%=request.getContextPath()%>/assets/images/icon/color_18/directional_up.png" /></a>
		 </span>
	</div>
	<div class="clear"></div>
	
	<div class="content" id="thecontent">

		<form id="filterForm" name="filterForm" action="" method="post">
			
			
			<div id="container">
			
					<table width="600" border="0" style="padding: 10px;">
					
					
			        	<tr>
			            	<td><strong>User Id</strong></td>
			              	<td><input name="id" type="text" class="id" id="id" size="33" maxlength="50" /></td>
			               
			          	</tr>
			    
			          	<tr>
			            	<td><strong>Nama</strong></td>
			              	<td><input name="nama" type="text" class="nama" id="nama" size="33" maxlength="50"/></td>
			               
			          	</tr>
			          	
			          	<tr>
			            	<td valign="top"><strong>Hak Akses</strong></td>
			              	<td>
			              		<input type="checkbox" id="isCreate" value=""/><strong>Create</strong><br/>
								<input type="checkbox" id="isUpdate" value=""/><strong>Update</strong><br/>
								<input type="checkbox" id="isDelete" value=""/><strong>Delete</strong><br/>
								<input type="checkbox" id="isCancel" value=""/><strong>Cancel</strong><br/>
								<input type="checkbox" id="isPrint" value=""/><strong>Print</strong><br/>
								<input type="checkbox" id="isReport" value=""/><strong>Report</strong><br/>
								<input type="checkbox" id="isSupervisor" value=""/><strong>Supervisor</strong>
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
<div id="loading" style="padding: 0px;"></div><br>
<div class="onecolumn">
	<div class="header">
	
			<span style="padding: 9" class="tip"> 
					<a title="Ubah Data"  href="#" id="updateUser">
						<img src="<%=request.getContextPath()%>/assets/images/icon/color_18/pencil.png" />
					</a> 
			</span>
		
			

	</div>
	<div class="clear"></div>
	<div class="content" id="thecontent"
		style="padding-left: 5px; padding-top: 10px;"  >
		<table id="grid" width="100%" align="right"></table>
		<div id="gridpager" style="100%"></div>
	</div>
</div>

