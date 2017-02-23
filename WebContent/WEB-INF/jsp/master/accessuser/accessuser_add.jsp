<%@page import="org.springframework.jdbc.core.RowCallbackHandler"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/components/jquery.treeview/jquery.treeview.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/components/jquery.treeview/demo/screen.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/components/jquery.treeview/lib/jquery.cookie.js?v=${countVersionJs}" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/components/jquery.treeview/jquery.treeview.js?v=${countVersionJs}" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/accessmodule/accessmodule_add.js?v=${countVersionJs}" ></script>     

<div class="onecolumn" >
		<div class="header" align="right"><span ><span style="padding: 0" class="tip"> 
				<a title="Kembali"  href="javascript: history.go(-1)">
					<img src="<%=request.getContextPath()%>/assets/images/icon/color_18/directional_left.png" />
				</a> 
			</span>	Akses User Setting</span></div>
	<div class="clear"></div>
	
	<form:form action="/HK/master/accessuser/add_access_user_save.html" id="accessmodule_new" class="accessmodule_new" name="accessmodule_new" method="post" modelAttribute="user">


	<input type="hidden" id="menuSelected" value="<c:out value="${menuSelected}"/>"/>
	<input type="hidden" id="menuUnselected" value="<c:out value="${menuUnselected}"/>"/>
	
	<input type="hidden" name="userId" value="${user.id}"/>
	<h6 style="padding-left: 10px;font-size:14px">USER ID : <label>${user.id}</label>
	<span style="padding: 20px;"> <input name="submit" type="submit" class="uibutton icon add " value="submit" />  </span>     
	</h6>
	
	<div id="sidetreecontrol" style="padding-left:20px;padding-bottom: 10px;padding-top: 10px;"> <a href="?#">Collapse All</a> | <a href="?#">Expand All</a> </div>
	
	<ul id="browser" class="filetree" style="padding-left:20px;">
		<c:forEach items="${accessModuleList}" var="level1">
			<li>
			<span class="folder"><input type="checkbox" name="moduleList" onchange="checkParentChild('${level1.id}');" id="<c:out value="${level1.id}"/>" class="" value="${level1.module.id }" <c:if test="${level1.selected}">checked="checked"</c:if>/><c:out value="${level1.module.nama}"/>
			<input type="hidden" <c:if test="${level1.selected == false}">disabled="disabled"</c:if> id="hiddenDoCreate${level1.id}" name="doCreateList" value="false" />
			<input type="hidden" <c:if test="${level1.selected == false}">disabled="disabled"</c:if> id="hiddenDoUpdate${level1.id}" name="doUpdateList" value="false"/>
			<input type="hidden" id="hiddenStatus-${level1.id}" value="parent" />
			<input type="hidden" id="hiddenParent-${level1.id}" value="" />
			<input type="hidden" id="hiddenChild-${level1.id}" value="${level1.idChild}" />
			</span>
			<ul>
					<c:forEach items="${level1.levelTwoMenuDTO}" var="level2">
						<c:choose>
							<c:when test="${not empty level2.levelThreeMenuDTO}">
								<li>
									<span class=folder><input type="checkbox" name="moduleList" onchange="checkParentChild('${level2.id}');" id="<c:out value="${level2.id}"/>" class="<c:out value="${level1.id}"/>" value="${level2.module.id }" <c:if test="${level2.selected}">checked="checked"</c:if>/><c:out value="${level2.module.nama}"/>
									<input type="hidden" <c:if test="${level2.selected == false}">disabled="disabled"</c:if> name="doCreateList" value="false" />
									<input type="hidden" <c:if test="${level2.selected == false}">disabled="disabled"</c:if> name="doUpdateList" value="false" />
									<input type="hidden" id="hiddenStatus-${level2.id}" value="parentChild" />
									<input type="hidden" id="hiddenParent-${level2.id}" value="${level2.idParent}" />
									<input type="hidden" id="hiddenChild-${level2.id}" value="${level2.idChild}" />
									</span>
								<ul>
        						<c:forEach items="${level2.levelThreeMenuDTO}" var="level3">
        							<c:choose>
									<c:when test="${not empty level3.levelFourMenuDTO}">
										<li><span class="folder"><input type="checkbox" name="moduleList" onchange="checkParentChild('${level3.id}');" id="<c:out value="${level3.id}"/>" class="<c:out value="${level2.id}"/>" value="${level3.module.id }" <c:if test="${level3.selected}">checked="checked"</c:if>/><c:out value="${level3.module.nama}"/>
											<input type="hidden" id="hiddenStatus-${level3.id}" value="parentChild" />
											<input type="hidden" id="hiddenParent-${level3.id}" value="${level3.idParent}" />
											<input type="hidden" id="hiddenChild-${level3.id}" value="" />
											</span>
										<ul>
											<c:forEach items="${level3.levelFourMenuDTO}" var="level4">
												<li><span class="file"><input type="checkbox" onchange="checkParentChild('${level4.id}');" name="moduleList" class="<c:out value="${level3.id}"/>" value="${level4.module.id }" <c:if test="${level4.selected}">checked="checked"</c:if>/><c:out value="${level4.module.nama}"/>
													<input type="hidden" id="hiddenStatus-${level4.id}" value="child" />
													<input type="hidden" id="hiddenParent-${level4.id}" value="${level4.idParent}" />
													<input type="hidden" id="hiddenChild-${level4.id}" value="" />
													</span>
												</li>
											</c:forEach>
										</ul>
										</li>
									</c:when>
									<c:when test="${empty level3.levelFourMenuDTO}">
										<li><span class="file"><input type="checkbox" onchange="checkParentChild('${level3.id}');" name="moduleList" class="<c:out value="${level2.id}"/>" value="${level3.module.id }" <c:if test="${level3.selected}">checked="checked"</c:if>/><c:out value="${level3.module.nama}"/>
											<input type="hidden" id="hiddenStatus-${level3.id}" value="child" />
											<input type="hidden" id="hiddenParent-${level3.id}" value="${level3.idParent}" />
											<input type="hidden" id="hiddenChild-${level3.id}" value="" />
											</span>
										</li>
									</c:when>
									</c:choose>
        						</c:forEach>
        						</ul>
        					</li>
    						</c:when>
    						<c:when test="${empty level2.levelThreeMenuDTO}">
    							<li><span class="file"><input type="checkbox" id="<c:out value="${level2.id}"/>" onchange="checkParentChild('${level2.id}');" name="moduleList" class="<c:out value="${level1.id}"/>" value="${level2.module.id }" <c:if test="${level2.selected}">checked="checked"</c:if>/><c:out value="${level2.module.nama}"/>
    	
    								<input type="hidden" <c:if test="${level2.selected == false}">disabled="disabled"</c:if> id="hiddenDoCreate${level2.id}" name="doCreateList" value="${level2.doCreate}" />
									<input type="hidden" <c:if test="${level2.selected == false}">disabled="disabled"</c:if> id="hiddenDoUpdate${level2.id}" name="doUpdateList" value="${level2.doUpdate}"/>
    								<input type="hidden" id="hiddenStatus-${level2.id}" value="child" />
									<input type="hidden" id="hiddenParent-${level2.id}" value="${level2.idParent}" />
									<input type="hidden" id="hiddenChild-${level2.id}" value="${level2.idChild}" />
    								</span>
    							</li>
    						</c:when>
						</c:choose>
					</c:forEach>
				</ul>
			</li>
		</c:forEach>
	</ul>
	<br>
    </form:form>
    
 	<%-- <form:form action="/JAS/master/accessmodule/add_access_module_save.html" id="accessmodule_new" class="accessmodule_new" name="accessmodule_new" method="post" modelAttribute="role">
		<table cellpadding="10">
			
			<tr >
				<td colspan="2">Accessible Module for <strong><c:out value="${role.nama}"/></strong> :</td>
			</tr>
			<c:forEach items="${accessModuleList}" var="aml">
				<tr style="height: 35px;">
					<td style="width: 12px;">
						<input type="checkbox"  name="moduleList" id="moduleList" class="chkbox" value="${aml.key.id }" <c:if test="${aml.value}">checked="checked"</c:if>/>
					</td>
					<td>
						<c:out value="${aml.key.nama}"/> 
					</td>
					<td>
						<c:out value="${aml.key.url}"/>
					</td>
				</tr>
			</c:forEach>
			<tr>
            	<td colspan="2"><input name="submit" type="submit" class="uibutton icon add " value="submit"/></td>
            </tr>
		</table>
	</form:form>     --%>
</div>
				                      
                
	
	
	