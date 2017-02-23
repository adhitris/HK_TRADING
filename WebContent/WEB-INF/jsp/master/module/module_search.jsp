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
	src="<%=request.getContextPath()%>/assets/js/module/module_search.js?v=${countVersionJs}"></script>

<div class="onecolumn">
	<div class="header">
		<span>Master Modul</span> <span id="showContent"> <a
			id="buttonShowContent" href="#"><img
				src="<%=request.getContextPath()%>/assets/images/icon/color_18/directional_up.png" /></a>
		</span>
	</div>
	<div class="clear"></div>
	<div class="content" id="thecontent">

		<form id="filterForm" name="filterForm" action="" method="post">

			<table width="501" border="0">

				<tr>
					<td width="126"><strong>Module Name</strong></td>
					<td width="218"><input type="text" id="name" 
						maxlength="50" value="" /></td>
					<td width="143"><span id="sohNoinfo" class="sohNoinfo"></span>
					</td>
				</tr>

				<tr>
					<td width="126"><strong>Parent</strong></td>
					<td width="218"><input type="text" id="path" 
						maxlength="50" value="" /></td>
					<td width="143"><span id="cusNameinfo" class="cusNameinfo"></span>
					</td>
				</tr>

				<tr>
					<td width="126"><strong>Create Date</strong></td>
					<td width="218"><input type="text" readonly class="birthday"
						id="createDate"  maxlength="50" value="" /></td>
					<td width="143"><span id="createDateinfo"
						class="createDateinfo"></span></td>
				</tr>

				<tr>
					<td height="50px"></td>
					<td><input type="button" value='Cari' class="uibutton loading"
						onclick="gridReload()" /> <input type="button" value='Reset'
						class="uibutton special" onclick="gridReset()" /></td>
					<td></td>
				</tr>

			</table>
		</form>
	</div>
</div>
<div class="onecolumn">
	<div class="header">
		<span style="padding: 9" class="tip"> <a title="Tambah Data"
			href="<%=request.getContextPath()%>/master/module/module_add.html">
				<img
				src="<%=request.getContextPath()%>/assets/images/icon/color_18/add.png" />
		</a>
		</span> <span style="padding: 9" class="tip"> <a title="Ubah Data"
			href="#" onclick="updateModule();"> <img
				src="<%=request.getContextPath()%>/assets/images/icon/color_18/pencil.png" />
		</a>
		</span> <span style="padding: 9; align: right;" class="tip"> <a
			title="Hapus Data" href="#" id="deleteModule"> <img
				src="<%=request.getContextPath()%>/assets/images/icon/color_18/trash_can.png" />
		</a>
		</span> <span style="padding: 9; align: right;" class="tip"> <a
			title="Aktif/Non-Aktif" id="editIsActive" href="#"> <img
				src="<%=request.getContextPath()%>/assets/images/icon/color_18/gear.png" />
		</a>
		</span>
		<span style="padding: 9" class="tip"> <a title="Hak Akses User"
			href="#" onclick="editHakAksesUser();"> <img
				src="<%=request.getContextPath()%>/assets/images/icon/color_18/user.png" />
		</a>
		</span>
	</div>
	<div class="clear"></div>
	<div class="content" id="thecontent"
		style="padding-left: 5px; padding-top: 10px;">
		<table id="grid">
			<tr>
				<td />
			</tr>
		</table>
		<div id="gridpager"></div>
	</div>
</div>

<form:form id="module_edit" class="module_edit" name="module_edit"
	method="post" autocomplete="off">
	<div id="dialog1" title="Module Edit ">
		<div id="container">
			<div align="center" id="loading1"></div>
			<h5 id="title1"></h5>
			<table cellpadding="5" cellspacing="5" border="0" width="350;">
				<tr>
					<td width="300">Module Id</td>
					<td width="200"><input type="hidden" id="version"><input class="upercase" name="id" type="text"
						class="id" id="id" disabled='disabled' /></td>
				</tr>
				<tr>
					<td>Nama</td>
					<td><input name="nama" type="text" class="nama" id="nameEdit"
						 maxlength="50" /></td>
				</tr>
				<tr>
					<td>Status</td>
					<td><select name="status" name="status" id="status">
						 	<option value="0">parent</option>
							<option value="1">child</option>
						</select></td>
				</tr>
				<tr>
					<td>Parent</td>
					<td><input type="text" class="name" id="parent" size='15px' onkeyup="setIdNull(event,'parentId')"
						maxlength="50" /> <input
						name="moduleId" type="hidden" id="parentId" /></td>
				</tr>
				<tr>
					<td>PATH</td>
					<td><input name="path" type="text" class="path" id="pathEdit"
						 maxlength="50" /></td>
					<td><span id="pathinfo" class="pathinfo"></span></td>
				</tr>
				<tr>
					<td>URL</td>
					<td><input name="url" type="text" class="url" id="url"
						 maxlength="60"  /></td>
				</tr>
				<tr>
					<td>Urutan</td>
					<td><input name="urutan" type="text" class="urutan"
						id="urutan" size="5" maxlength="50"  /></td>
				</tr>
				<tr>
					<td height="39">&nbsp;</td>
					<td><span class="uibutton loading" id="editSaveModule">Save</span></td>
				</tr>
			</table>
		</div>

	</div>
</form:form>

<form:form id="hak_akses_user_edit">
	<div id="dialogHakAksesUser" title="Hak Akses User ">
		<div id="container">
			<div align="center" id="loadingHakAksesUser"></div>
			<h5 id="titleHakAksesUser"></h5>
			<br/>
			<input type='checkbox' id="selectallUser" /> Select All User<br/>
			<input type='hidden' id="hiddenModuleId" name="hiddenModuleId" />
			<table style="margin-top:10px;" width="100%" cellpadding="0" cellspacing="0" id="tableListUser" class="littleTable">
				<thead>
					<tr>
						<th>-</th>
						<th>User</th>
					</tr>
				</thead>
			</table>
			<br/>
			<span class="uibutton loading" id="editSaveHakAksesUser">Simpan</span>
		</div>
	</div>
</form:form>