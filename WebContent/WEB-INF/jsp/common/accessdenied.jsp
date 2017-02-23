<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
		
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        
        <title>Exception Viewer</title>
        <!-- Link shortcut icon-->
        <link rel="shortcut icon" type="image/ico" href="images/favicon2.html" /> 
        <!-- Link css-->
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
       
        </head>        	
        <body>        
     	  
        	<div id="header" >
        		
                
            </div> <!--//  header -->
			<div id="shadowhead" style="padding-top:20px;"></div>
    
            <div style="padding:10px;padding-top:5px">
                <div class="inner">
					<div class="topcolumn" style="padding-top:0px;padding-bottom:100px;">
						
                            
					</div>
                    <div class="clear"></div>
                    
                  
                    	 <h1 align="center">ACCESS DENIED!</h1>

							<br/><br/>
							<h5><center>You do not Have Permission to Acces This Page</center></h5>
							
							
							
							
							<br/><br/>
							<div align="center">
							<a style="height: 20px;width: 200px;" href="javascript: history.go(-1)" class="uibutton icon answer">Back to previous page</a>
							</div>
                      
                    <div class="clear"></div>
                    <div id="footer">  </div>
                </div> <!--// End inner -->
            </div> <!--// End content --> 
		</body>
</html>