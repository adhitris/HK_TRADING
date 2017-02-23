<%@page import="org.springframework.jdbc.core.RowCallbackHandler"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
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
	src="<%=request.getContextPath()%>/assets/js/custom/jquery.ui.combogrid-1.6.2.js?v=${countVersionJs}"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/jqgrid/jquery.jqGrid.min.js?v=${countVersionJs}">
</script>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/gCharts/loader.js?v=${countVersionJs}"></script>
	
<%-- <script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/gCharts/jsapi.js?v=${countVersionJs}"></script>
	
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/gCharts/uds_api_contents.js?v=${countVersionJs}"></script>
	
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/gCharts/jsapi_compiled_corechart_module.js?v=${countVersionJs}"></script>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/gCharts/jsapi_compiled_default_module.js?v=${countVersionJs}"></script>
	
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/gCharts/jsapi_compiled_format_module.js?v=${countVersionJs}"></script>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/gCharts/jsapi_compiled_ui_module.js?v=${countVersionJs}"></script> --%>
	
<script type="text/javascript">
	google.charts.load('current', {'packages':['corechart']});
</script>

<style>
.error {
	font: Arial, Helvetica, sans-serif;
	color: #FF0000;
}

#error {
	font: Arial, Helvetica, sans-serif;
	color: #FF0000;
}
</style>
<div class="md-modal md-effect-9" id="modal-4">
	<div class="md-content">
		<h3 style="color: white;">What's New !</h3>
		<div>
			<p
				style="font-size: 18px; color: #ef5b03; font-weight: 900; margin-bottom: 20px;"
				id="titleVersionControl"></p>

			<table id="detailVersionControl" cellspacing="0">
				<tr align="left" style="color: #ff8000;">
					<th width="40px">&nbsp;</th>
					<th>Update Detail</th>
					<th width="100px;">&nbsp;</th>
					<th width="160px;">Requestor</th>
				</tr>
			</table>
			<br /> <br />
			<button class="md-close">Tutup</button>
		</div>
	</div>
</div>
<div class="md-overlay"></div>
<!-- the overlay element -->
