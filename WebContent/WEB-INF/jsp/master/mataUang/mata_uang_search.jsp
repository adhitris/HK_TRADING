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
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/jqgrid/jquery.jqGrid.min.js?v=${countVersionJs}"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/mataUang/mata_uang_search.js?v=${countVersionJs}"></script>


  <script type="text/javascript">

           

        </script>
<div class="onecolumn">
	<div class="header">
		<span>Master Mata Uang</span> <span
			id="showContent"> <a id="buttonShowContent" href="#"><img
				src="<%=request.getContextPath()%>/assets/images/icon/color_18/directional_up.png" />
		</a> </span>
	</div>
	<div class="clear"></div>
	<div class="content" id="thecontent">

		<form id="filterForm" name="filterForm" action="" method="post">
			


			<div id="container">
			
					<table width="600" border="0">
			        	<tr>
			            	<td width="200"><strong>Mata Uang Id</strong></td>
			              	<td width="200"><input name="id" type="text" class="id" id="id" /></td>
			                <td width="200"></td>
			          	</tr>
			 
			          	<tr>
							<td><strong>Active</strong>
							</td>
							<td><input type="radio" id="isActive" checked="checked" value="1" name="isActive" />Aktif
								<input type="radio" id="isActive"  value="0" name="isActive" />Non Aktif</td>
							<td></td>
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
			<c:if test="${isCreate=='true'}">
			<span style="padding: 9" class="tip"> 
					<a title="Tambah Data" id="addMataUang">
						<img src="<%=request.getContextPath()%>/assets/images/icon/color_18/add.png" />
					</a> 
			</span>
			</c:if>
			<c:if test="${isUpdate=='true'}">
			<span style="padding: 9" class="tip"> 
					<a title="Ubah Data"  href="#" id="updateMataUang">
						<img src="<%=request.getContextPath()%>/assets/images/icon/color_18/pencil.png" />
					</a> 
			</span>
			</c:if>
			<c:if test="${isDelete=='true'}">
			<span style="padding: 9;align:right;" class="tip"> 
					<a title="Hapus Data"  href="#" id="deleteMataUang">
						<img src="<%=request.getContextPath()%>/assets/images/icon/color_18/trash_can.png" />
					</a> 
			</span>
			</c:if>
			<c:if test="${isSupervisor=='true'}">
			<span style="padding: 9;align:right;" class="tip"> 
					<a title="Aktif/Non Aktif" id="editIsActive"  href="#">
						<img src="<%=request.getContextPath()%>/assets/images/icon/color_18/gear.png" />
					</a> 
			</span>
			</c:if>

	</div>
	<div class="clear"></div>
	<div class="content" id="thecontent"style="padding-left: 5px; padding-top: 10px;"  >
		<table id="grid"><tr><td/></tr></table>
    	<div id="gridpager"></div>
	</div>
</div>

