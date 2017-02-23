<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/navigationDWR.js?v=${countVersionJs}"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/navigation.js?v=${countVersionJs}"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/css/gdown/gdown.js?v=${countVersionJs}"></script>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/gdown/gdown.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/font-awesome/css/font-awesome.css"/>

<table width="100%" border="0" class="tableheader">
	<tr>
		<td class="tdheading">
		
			<ul  class="sf-menu" id="example" >
				<li><a href="<%=request.getContextPath()%>/index.html" id="homelink">Home</a></li>
			</ul>
		</td>
		<td class="tdheading">
			<div id="account_info" style="float: left">
					<b>Welcome,</b> 
					<b class="red"> 
							<font color="red">
								<b>
									<%=SecurityContextHolder.getContext().getAuthentication().getName()%>
								</b>
							</font> 
					</b>
			</div>
		</td>
		<td class="tdheading" id="tdnotification" style="text-align: right;width: 1%;display: none">
			<div class="fim-dropdown" title="Toggle Navigation">
				<label><i class="fa fa-lg fa-bell-o"></i>
				<div class="icon_notif"></div>
				</label>
				<div class="inner" id="innernotification" style="overflow-y: scroll;max-height: 280px;">
				</div>
		 	</div>
		</td>
		<td class="tdheading" style="text-align: right;width: 1%;">
			<div class="fim-dropdown" title="Toggle Navigation">
				<label><i class="fa fa-lg fa-bars"></i></label>
				<div class="inner">
					<a class="panel_btn" href="<%=request.getContextPath()%>/master/user/change_password.html"> 
						<i class="fa fa-2x fa-cog"></i><br>
						Ubah Password
					</a>
					<a class="panel_btn" id="requestAccessMenuShow"> 
						<i class="fa fa-2x fa-ticket"></i><br>
						Request Ticket
					</a>
					<a class="panel_btn" href="<%=request.getContextPath()%>/logout"> 
						<i class="fa fa-2x fa-power-off"></i><br>
						Log Out
					</a>
				</div>
		 	</div>
		</td>
	</tr>
</table>
