<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<span id="loginPage" class="loginPage"></span>
<html>
<head>
<meta charset="utf-8" />
<title>Harapan Kurnia</title>
<!--[if lt IE 9]>
          <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js?v=${countVersionJs}"></script>
        <![endif]-->
<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/assets/images/favicon.ico" />  
<link href="<%=request.getContextPath()%>/assets/css/zice.style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/assets/css/icon.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/components/tipsy/tipsy.css"
	media="all" />
<style type="text/css">
html {
	background-image: none;
}

#versionBar {
	background-color: #212121;
	position: fixed;
	width: 100%;
	height: 35px;
	bottom: 0;
	left: 0;
	text-align: center;
	line-height: 35px;
}

.copyright {
	text-align: center;
	font-size: 10px;
	color: #CCC;
}

.copyright a {
	color: #A31F1A;
	text-decoration: none
}

.error {
	font: Arial, Helvetica, sans-serif;
	color: #FF0000;
		left;
	padding-left:20px;
}


</style>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/zice.style.css"/>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/icon.css"/>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/dropdown.css"/>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/ui-custom.css"/>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/timepicker.css"  />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/components/tipsy/tipsy.css" media="all" />

   
        <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/custom/jquery.min.js?v=${countVersionJs}"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/assets/components/ui/jquery.ui.min.js?v=${countVersionJs}"></script> 
        <script type="text/javascript" src="<%=request.getContextPath()%>/assets/components/ui/timepicker.js?v=${countVersionJs}"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/assets/components/scrolltop/scrolltopcontrol.js?v=${countVersionJs}"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/assets/components/tipsy/jquery.tipsy.js?v=${countVersionJs}"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/custom/jquery.cookie.js?v=${countVersionJs}"></script>
<%--         <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/custom/zice.custom.js?v=${countVersionJs}"></script>  
 --%>        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/assets/components/dropdown/hoverIntent.js?v=${countVersionJs}"></script>
		<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/assets/components/dropdown/jquery.dropdown.js?v=${countVersionJs}"></script>    
	<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/login.js?v=${countVersionJs}"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/custom/jquery.sidebar.js?v=${countVersionJs}"></script>
	<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/custom/MaskedPassword.js?v=${countVersionJs}"></script> --%>
</head>

<body>
	<script type="text/javascript">
	setCookie("isModalClose", "false", "1");
	function setCookie(cname, cvalue, exdays) {
	    var d = new Date();
	    d.setTime(d.getTime() + (exdays*24*60*60*1000));
	    var expires = "expires="+d.toUTCString();
	    document.cookie = cname + "=" + cvalue + "; " + expires;
	}
	</script>
	<div id="alertMessage" class="error"></div>
	<div id="successLogin"></div>
	<div class="text_success">
		<img src="<%=request.getContextPath()%>/assets/images/loadder/loader_green.gif" alt="ziceAdmin" /><span>Please
			wait</span>
	</div>

	<div id="login">

		<div class="inner" align="center">
			<form class="login-form" action="j_spring_security_check" id="formLogin" method="post" >
				<span class="errormessage" id="errormessage">
								<c:if test="${not empty param.error}">
								   	<div class="error">
								        Login Gagal, Silahkan Coba Lagi.<br />
								        Reason: ${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}
								    </div>
								</c:if>
							</span>
				<table width="380" border="0">
					
					<tr>
						<td height="26" colspan="2" align="center">
						</td>
					</tr>
					<tr>
						<td height="26" colspan="2" align="center"><b>Login<b>
						</td>
					</tr>
					

					<tr>
						<td width="87" height="36">User ID</td>
						<td width="277"><input autocomplete="off"  type="text" class="email" id="username_id" name="j_username" style="margin-top: 8px" title="Username" />
						</td>
					</tr>
					<tr>
						<td height="38">Password</td>
						<td valign="middle"><input autocomplete="off" name="j_password" type="password" id="password" class="password" title="Password" style="margin-top: 8px" />
						</td>
					</tr>
					<tr>
						<td height="38">&nbsp;</td>
						<td>
							<div>
								<ul class="uibutton-group">
									<li><input type="submit" value="Login" class="uibutton submit_form">
									</li>
									
								</ul>
							</div></td>
					</tr>
				</table>
			</form>
		</div>

		<div class="clear"></div>
		<div class="shadow"></div>
	</div>

	<!--Login div-->
	<div class="clear"></div>

		<div class="copyright" style="padding-top: 450px;">
			&copy; Copyright 2013 All Rights Reserved <span class="tip"><a
				href="#" title="Lawencon International"></a> </span>
		</div>
		<!-- // copyright-->
	
	<!-- Link JScript-->

</body>
</html>