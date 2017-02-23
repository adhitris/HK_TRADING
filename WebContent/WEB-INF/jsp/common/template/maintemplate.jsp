<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
		
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        
        <title><tiles:insertAttribute name="title"/></title>
        <!-- Link shortcut icon-->
        
        <link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/assets/images/favicon.ico" /> 
        <!-- Link css-->

        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/zice.style.css"/>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/icon.css"/>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/dropdown.css"/>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/ui-custom.css"/> 
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/timepicker.css"  />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/components/tipsy/tipsy.css" media="all" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/smoothness/jquery.ui.combogrid.css"/>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/jquery-ui-1.9.2.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/components/bootstrap-growl/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/megafish.css" media="screen" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/sidebar/red-glass/sidebar.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/modalWindowEffects/css/component.css"/>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/ram_input.css"/>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/CircularProgressButton/css/component.css" />
		
        <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/custom/jquery.min.js?v=${countVersionJs}"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/assets/components/ui/jquery.ui.min.js?v=${countVersionJs}"></script> 
        <script type="text/javascript" src="<%=request.getContextPath()%>/assets/components/ui/timepicker.js?v=${countVersionJs}"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/assets/components/scrolltop/scrolltopcontrol.js?v=${countVersionJs}"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/assets/components/tipsy/jquery.tipsy.js?v=${countVersionJs}"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/custom/jquery.cookie.js?v=${countVersionJs}"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/custom/zice.custom.js?v=${countVersionJs}"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/assets/components/bootstrap-growl/js/bootstrap.min.js?v=${countVersionJs}"></script> 
        <script type="text/javascript" src="<%=request.getContextPath()%>/assets/components/bootstrap-growl/js/jquery.bootstrap-growl.js?v=${countVersionJs}"></script> 
        <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/superclick/superclick.js?v=${countVersionJs}"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/custom/jquery.blockUI.js?v=${countVersionJs}"></script>
		
		<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/custom/jquery.sidebar.js?v=${countVersionJs}"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/assets/modalWindowEffects/js/modalEffects.js?v=${countVersionJs}"></script>

		<script src="<%=request.getContextPath()%>/assets/CircularProgressButton/js/modernizr.custom.js"></script>

		<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js?v=${countVersionJs}'></script>
		<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js?v=${countVersionJs}'></script>
		
        </head>        	
        <body>      
     		<!-- return url to js -->
            <div id="http://<%= request.getServerName() %>:<%= request.getServerPort() %>/HK" class="getUrl"></div>   
          
        	<div id="header" >
        		<tiles:insertAttribute name="navigation"/>   
                
            </div> <!--//  header -->
			<div id="shadowhead" style="padding-top:20px;"></div>
    
            <div style="padding:10px;padding-top:5px">
                <div class="inner">
					<div class="topcolumn" style="padding-top:0px;padding-bottom:80px;">
						<!--//<div class="logo" style="padding-top:20px;"></div>-->
					</div>
                    <div class="clear"></div>
                    	<tiles:insertAttribute name="content"/>
                    <div class="clear"></div>
                    <tiles:insertAttribute name="footer"/>
                </div> <!--// End inner -->
            </div> <!--// End content --> 
		<!-- <div id="livezilla_tracking" style="display:none"></div>
		    <script type="text/javascript">
				var script = document.createElement("script");script.async=true;script.type="text/javascript";var src = "http://192.168.8.4:81/LiveZilla/server.php?a=3ed01&rqst=track&output=jcrpt&ovlp=MjI_&ovlc=IzczYmUyOA__&ovlct=I2ZmZmZmZg__&ovlt=SEsgLSBJVCBTdXBwb3J0IChPbmxpbmUp&ovlto=SEsgLSBJVCBTdXBwb3J0IChPZmZsaW5lKQ__&ovlsx=NQ__&ovlsy=NQ__&ovlsb=NQ__&ovlsc=Njk2OTY5&ovlntwo=MQ__&nse="+Math.random();setTimeout("script.src=src;document.getElementById('livezilla_tracking').appendChild(script)",1);
			</script>
			<noscript>
				<img src="http://192.168.8.4:81/LiveZilla/server.php?a=3ed01&amp;rqst=track&amp;output=nojcrpt&ovlp=MjI_&ovlc=IzczYmUyOA__&ovlct=I2ZmZmZmZg__&ovlt=SEsgLSBJVCBTdXBwb3J0IChPbmxpbmUp&ovlto=SEsgLSBJVCBTdXBwb3J0IChPZmZsaW5lKQ__&ovlsx=NQ__&ovlsy=NQ__&ovlsb=NQ__&ovlsc=Njk2OTY5&ovlntwo=MQ__" width="0" height="0" style="visibility:hidden;" alt=""></img>
			</noscript> -->
			
		<div class="md-overlay"></div>
		<div class="dialogProgress" title="Loading...">
				<h5 class="progressDescription"></h5>
				<div id="container">
					<div class="progressbarDialog"></div>
				</div>
		</div>
		<script src="<%=request.getContextPath()%>/assets/CircularProgressButton/js/uiProgressButton.js"></script>
		<script src="<%=request.getContextPath()%>/assets/CircularProgressButton/js/classie.js"></script>
		<ul id="whatsNew" >
        </ul>
		</body>
</html>
