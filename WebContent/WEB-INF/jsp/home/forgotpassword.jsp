<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html>
<head>
<meta charset="utf-8" />
<title>Bengkel</title>
<!--[if lt IE 9]>
          <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js?v=${countVersionJs}"></script>
        <![endif]-->
<link href="css/zice.style.css" rel="stylesheet" type="text/css" />
<link href="css/icon.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="components/tipsy/tipsy.css"
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
        <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/custom/zice.custom.js?v=${countVersionJs}"></script>  
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/assets/components/dropdown/hoverIntent.js?v=${countVersionJs}"></script>
		<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/assets/components/dropdown/jquery.dropdown.js?v=${countVersionJs}"></script>    
		<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/login.js?v=${countVersionJs}"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/forgotpassword.js?v=${countVersionJs}"></script>
</head>
<body>

	

	<div id="login">

		<div class="inner" align="center">
			<form name="forgotpassword" class="login-form" method="post" id="forgotpassword">
        <table width="380" border="0">
		<tr>
            <td align="center" height="26" colspan="2"><b>Forgot Password</b></td>
          </tr>
          <tr>
            <td height="26" colspan="2" align="center"><span class="emailinfo" id="emailinfo"></span></td>
          </tr>
          
          <tr>
            <td width="87" height="36">Email</td>
            <td width="277"><input name="email" type="text" class="email" id="username_id" style="margin-top:8px"  title="email"   /></td>
          </tr>
          <tr>
            <td height="38">&nbsp;</td>
            <td>
            	<div> 
                <ul class="uibutton-group">
           
				   <li><input type="submit" class="uibutton submit_form"  id="forgetpass" value="submit"></li>
               </ul>
              </div>
            </td>
          </tr>
        </table>
    </form>
		</div>

		<div class="clear"></div>
		<div class="shadow"></div>
	</div>

	<!--Login div-->
	<div class="clear"></div>
	<div id="versionBar">
		<div class="copyright">
			&copy; Copyright 2013 All Rights Reserved <span class="tip"><a href="#" title=""></a> </span>
		</div>
		<!-- // copyright-->
	</div>
	<!-- Link JScript-->
	
</body>
</html>