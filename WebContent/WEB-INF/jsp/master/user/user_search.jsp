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
	src="<%=request.getContextPath()%>/assets/js/user/user_search.js?v=${countVersionJs}"></script>

<div class="onecolumn">
	<div class="header">
		<span>Master User</span> <span id="showContent"> 
			<a id="buttonShowContent" href="#"><img src="<%=request.getContextPath()%>/assets/images/icon/color_18/directional_up.png" /></a>
		 </span>
	</div>
	<div class="clear"></div>
	
		<div id="dialog" title="Report User">
			<table class="littleTableInside">
				<tr>
					<td height="10px;">Tipe Report</td>
					<td> : </td>
					<td>
						<input type="radio" id="tipeReport" checked="checked" name="tipeReport"/ value="pdf">pdf
						<input type="radio" id="tipeReport" name="tipeReport" value="xls"/>exel
					</td>
				</tr>
<!-- 				<tr> -->
<!-- 					<td height="50px;">Status User</td> -->
<!-- 					<td> : </td> -->
<!-- 					<td> -->
<!-- 						<input type="radio" id="statusUser" checked="checked" value="true" name="statusUser"/>Aktif -->
<!-- 						<input type="radio" id="statusUser" value="false" name="statusUser"/>Non Aktif -->
<!-- 					</td> -->
<!-- 				</tr> -->
				
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
			            	<td><strong>User Id</strong></td>
			              	<td><input name="id" type="text" class="id" id="id" size="33" maxlength="50" /></td>
			               
			          	</tr>
			    
			          	<tr>
			            	<td><strong>Nama</strong></td>
			              	<td><input name="nama" type="text" class="nama" id="nama" size="33" maxlength="50"/></td>
			               
			          	</tr>
			          	<tr>
			            	<td><strong>Departemen</strong></td>
			              	<td>
			              		<input type="text" id="department" size="33" onkeyup="setIdNull(event,'departmentId')" />
								<input type="hidden" id="departmentId"  />
			              	</td>
		          		</tr>
			          	<tr>
							<td><strong>Active</strong>
							</td>
							<td>
								<input type="radio" id="isActive" checked="checked" value="" name="isActive" />Semua
								<input type="radio" id="isActive" value="1" name="isActive" />Aktif
								<input type="radio" id="isActive"  value="0" name="isActive" />Non Aktif</td>
							<td></td>
						</tr>
			          	<tr>
			            	<td valign="top"><strong>Hak Akses</strong></td>
			              	<td>
			              		<table>
			              			<tr>
			              				<td><input type="checkbox" id="isCreate" value=""/><strong>Create</strong></td>
			              				<td><input type="checkbox" id="isUpdate" value=""/><strong>Update</strong></td>
			              				<td><input type="checkbox" id="isDelete" value=""/><strong>Delete</strong></td>
			              			</tr>
			              			<tr>
			              				<td><input type="checkbox" id="isCancel" value=""/><strong>Cancel</strong></td>
			              				<td><input type="checkbox" id="isConfirm" value=""/><strong>Confirm</strong></td>
			              				<td><input type="checkbox" id="isUnconfirm" value=""/><strong>Unconfirm</strong></td>
			              			</tr>
			              			<tr>
			              				<td><input type="checkbox" id="isPrint" value=""/><strong>Print</strong></td>
			              				<td><input type="checkbox" id="isReport" value=""/><strong>Report</strong></td>
			              				<td><input type="checkbox" id="isSupervisor" value=""/><strong>Supervisor</strong></td>
			              			</tr>
			              			<tr>
			              				<td><input type="checkbox" id="isSuperuser" value=""/><strong>Superuser</strong></td>
			              				<td><input type="checkbox" id="isReprint" value=""/><strong>Reprint</strong></td>
			              			</tr>
			              		</table>
			              	</td>
			               
			          	</tr>
			          	<tr>
						<td height="50px"></td>
						<td>
							<input type="button" value='Cari' class="uibutton loading" onclick="gridReload()" /> 
							<input type="button" value='Reset' class="uibutton special" onclick="gridReset()" /> 
							<c:choose>
			            		<c:when test="${isReport}">
			               			<input type="button" value='Report' class="uibutton confirm" onclick="showDialogReport();" />	
			          			</c:when>
			            		<c:otherwise>
	              				</c:otherwise>
			      			</c:choose>
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
<div class="column_left" style="width: 98%">
<div class="onecolumn">
	<div class="header">
		<span style="padding: 9" class="tip"> 
					<a title="Tambah Data"  href="#" id="addUser">
						<img src="<%=request.getContextPath()%>/assets/images/icon/color_18/add.png" />
					</a> 
				</span>
			<span style="padding: 9" class="tip"> 
					<a title="Ubah Data"  href="#" id="updateUser">
						<img src="<%=request.getContextPath()%>/assets/images/icon/color_18/pencil.png" />
					</a> 
			</span>
			<span style="padding: 9;align:right;" class="tip"> 
					<a title="Hapus Data"  href="#" id="deleteUser">
						<img src="<%=request.getContextPath()%>/assets/images/icon/color_18/trash_can.png" />
					</a> 
			</span>
			<span style="padding: 9;align:right;" class="tip"> 
					<a title="Aktif/Non Aktif" id="editIsActive" href="#">
						<img src="<%=request.getContextPath()%>/assets/images/icon/color_18/gear.png" />
					</a> 
			</span>
			
			<span style="padding: 9;align:right;" class="tip"> 
					<a title="Ubah Password Login" id="updatePassword" href="#">
						<img src="<%=request.getContextPath()%>/assets/images/icon/color_18/user.png" />
					</a> 
			</span>
			<span class="tip" title="Print Label Barcode" id="printBarcode" style=" color: #0099FF; cursor: pointer;font-size: 10px">
				<img src="<%=request.getContextPath()%>/assets/images/icon/color_18/barcode.png" />
			</span>
	</div>
	<div class="clear" ></div>
	<div class="content" id="thecontent"style="padding-left: 5px; padding-top: 10px;"  >
		<table id="grid"><tr><td/></tr></table>
    	<div id="gridpager"></div>
	</div>
	
</div>
</div>

<div id="dialog9" style="display:none;" title="Print Label Barcode">
	<span id="testHtml">
		
	</span>
</div>
