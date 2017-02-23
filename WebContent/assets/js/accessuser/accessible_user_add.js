// JavaScript Document
// ifan effendy


$(document).ready(function(){
	
	if(document.getElementById("hiddenMode").value != ""){
		if(document.getElementById("hiddenMode").value == "am"){
			$("#thecontent3").hide();
			
			$("#thecontent2").show(500);
			$("#buttonShowContent2").remove();
			$("<a href=# id=buttonShowContent2 ><img src="+getUrl+"/assets/images/icon/color_18/directional_up.png /></a>")
					.appendTo("#showContent2");
			
		}else if(document.getElementById("hiddenMode").value == "role"){
			$("#thecontent2").hide();

			$("#thecontent3").show();
			$("#buttonShowContent3").remove();
			$("<a href=# id=buttonShowContent3 ><img src="+getUrl+"/assets/images/icon/color_18/directional_up.png /></a>")
					.appendTo("#showContent3"); 
		}
	}
	
	if( (document.accessible_module_new.id.value == "")&&(document.role_new.id.value == "") ){
		
	$("#thecontent2").hide();
	$("#showContent2")
			.click(
					function() {
						if ($("#thecontent2").is(":visible")) {
							$("#thecontent2").hide(0);
							$("#buttonShowContent2").remove();
							$("<a href=# id=buttonShowContent2 ><img src="+getUrl+"/assets/images/icon/color_18/directional_down.png /></a>")
									.appendTo("#showContent2");
						} else {
							$("#thecontent3").hide(0);
							$("#buttonShowContent3").remove();
							$("<a href=# id=buttonShowContent3 ><img src="+getUrl+"/assets/images/icon/color_18/directional_down.png /></a>")
							.appendTo("#showContent3");
							
							$("#thecontent2").show(500);
							$("#buttonShowContent2").remove();
							$("<a href=# id=buttonShowContent2 ><img src="+getUrl+"/assets/images/icon/color_18/directional_up.png /></a>")
									.appendTo("#showContent2");
						}

					});
	
	
	$("#thecontent3").hide();
	$("#showContent3")
			.click(
					function() {
						if ($("#thecontent3").is(":visible")) {
							$("#thecontent3").hide(0);
							$("#buttonShowContent3").remove();
							$("<a href=# id=buttonShowContent3 ><img src="+getUrl+"/assets/images/icon/color_18/directional_down.png /></a>")
									.appendTo("#showContent3");
						} else {
							$("#thecontent2").hide(0);
							$("#buttonShowContent2").remove();
							$("<a href=# id=buttonShowContent2 ><img src="+getUrl+"/assets/images/icon/color_18/directional_down.png /></a>")
							.appendTo("#showContent2");
							
							$("#thecontent3").show(500);
							$("#buttonShowContent3").remove();
							$("<a href=# id=buttonShowContent3 ><img src="+getUrl+"/assets/images/icon/color_18/directional_up.png /></a>")
									.appendTo("#showContent3");
						}

					});
	
	}
	
	if(document.accessible_module_new.id.value != ""){
		showSelectedNode();
		var selectRole = document.getElementById("roleId").value;
		document.getElementById("role_options").value = selectRole;
	}
	
	if(document.role_new.id.value != ""){
		var val = document.getElementById("hiddenIdRole").value;
		document.getElementById("node-role-"+val).style.color = 'red';
	}
					
	var form = $("#accessible_module_new");
						   
	$("#browser").treeview({
		collapsed: false,
		animated: "medium",
		control:"#sidetreecontrol",
		persist: "location"
	});
	
	var name = $("#name");
	var nameinfo = $("#nameinfo");
	
	var url = $("#url");
	var urlinfo = $("#urlinfo");
	
	var role = $("#role_options");
	var roleinfo = $("#roleinfo");
			
	name.blur(validatename);
	name.keyup(validatename);
	name.change(validatename);
	
	url.blur(validateurl);
	url.keyup(validateurl);
	url.change(validateurl);
	
	role.blur(validaterole);
	role.keyup(validaterole);
	role.change(validaterole);
	
	$("#submitAccModule").click(function(){
			if(validatename() & validateurl() & validaterole()){
				if(document.accessible_module_new.id.value == ""){
					document.accessible_module_new.action = getUrl+"/tools/accessible_module_add_save.do";
					document.accessible_module_new.submit();
				}else{
					document.accessible_module_new.action = getUrl+"/tools/accessible_module_edit_save.do";
					document.accessible_module_new.submit();
				}
				
				return true;
			}else{
			
				return false;
			}
		});
	
	$("#submitRole").click(function(){
		//if(validatename() & validateurl() & validaterole()){
			if(document.role_new.id.value == ""){
				document.role_new.action = getUrl+"/tools/role_add_save.do";
				document.role_new.submit();
			}else{
				document.role_new.action = getUrl+"/tools/role_edit_save.do";
				document.role_new.submit();
			}
			
			return true;
		//}else{
		
			//return false;
		//}
	});
	
	$("#clearForm").click(function() {
		ResetForm();
	});
	
	function validatename(){
		var valname=name.val().length;
		if(valname < 1){
			nameinfo.text("This Field Required");
			nameinfo.addClass("error");

			return false;
		}
		//if it's valid
		else{
			nameinfo.addClass("error");
			nameinfo.text("");
		
			return true;
		}
	}
	
	function validateurl(){
		var valurl=url.val().length;
		if(valurl < 1){
			urlinfo.text("This Field Required");
			urlinfo.addClass("error");

			return false;
		}
		//if it's valid
		else{
			urlinfo.addClass("error");
			urlinfo.text("");
		
			return true;
		}
	}
	
	function validaterole(){
		var valrole=role.val();
		if(valrole == 0){
			roleinfo.text("This Field Required");
			roleinfo.addClass("error");

			return false;
		}
		//if it's valid
		else{
			roleinfo.addClass("error");
			roleinfo.text("");
		
			return true;
		}
	}

function ResetForm(){
	$("#name").val("");
	$("#nameKr").val("");
	$("#startDate").val("");
	$("#endDate").val("");
	$("#accountingPeriod_options").val("");
	$("#hiddenId").val("");
}

function showSelectedNode(){
	var val = document.getElementById("hiddenId").value;
	document.getElementById("node-"+val).style.color = 'red';
}
	
});

function retrieveData(id){
		$("#name").val($("."+id).data(id).nameid);
		$("#nameKr").val($("."+id).data(id).namekr);
		$("#startDate").val($("."+id).data(id).startdate);
		$("#endDate").val($("."+id).data(id).enddate);
	}
