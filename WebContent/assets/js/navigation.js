$(document).mouseup(function (e)
{
    var container = $(".sf-mega");
    var container2 = $(".sf-menu");
    
    if (!container.is(e.target) // if the target of the click isn't the container...
        && container.has(e.target).length === 0 ) // ... nor a descendant of the container
    {
    	if(e.target.id != 'masterdatalink'){
    		container.hide();
    	}
    }
});

$(document).ready(function(){
	var countModule=1;
	var lastChild = '';
	
	if(isSuperuser()){
		reloadNotification();
	}
	/*$('#left-menu').sidr();*/
	
	
	navigationDWR.parentNode({
		callback : function(result) {
			extractParentJSON(result);
		},
		erroHandler : function(msg) {
			alert(msg);
			r = false;
		}
	});
	
	function extractParentJSON(getValue){
		var countParentNode = getValue.length;
		for(var x=0; x<countParentNode; x++){
			$.each(getValue[x], function(key, val) {
				if(key == 'module'){
					$("<li style='padding-left:15px;' class='module' id='module"+countModule+"' onclick=showDiv('"+countModule+"');><a href='#' id='masterdatalink' style='background: transparent url("+getUrl+"/assets/images/icon/gray_18/"+val.replace(" ","").replace("/","")+".png) no-repeat scroll left center;'  class='mainMenu'>"+val+"</a>").appendTo("#example");
					$("<div class='sf-mega'><ul class='sub_menu' id='menu"+countModule+"'>").appendTo("#module"+countModule);
					$("</ul></li>").appendTo("#example");
					
				}else if(key == 'moduleId'){
					childNodeJSON(val,countModule);
					countModule++;
				}
			});
		}
	}
	
	function childNodeJSON(getValue1,getValue2){
		navigationDWR.childNode(getValue1,{
			callback : function(result) {
				extractChildJSON(result,getValue2);
			},
			erroHandler : function(msg) {
				alert(msg);
				r = false;
			}
		});
	}
	
	function childChildNodeJSON(getValue1,getValue2){
		navigationDWR.childNode(getValue1,{
			callback : function(result) {
				extractChildChildJSON(result,getValue2);
			},
			erroHandler : function(msg) {
				alert(msg);
				r = false;
			}
		});
	}
	
	function extractChildJSON(getValue1,getValue2){
		var name = '';
		var url = '';
		var menuId = '';
		var path = '';
		var parentId='';
		var countchildNode = getValue1.length;
		for(var x=0; x<countchildNode; x++){
			$.each(getValue1[x], function(key, val) {
				if(key == '1menuId'){
					menuId = val;
				}if(key == '2menu'){
					name = val;
				}if(key == '3path'){
					path = val;
				}if(key == '4url'){
					url = val;
				}if(key == 'parentId'){
					parentId = val;
				}if(key == '5haveChild'){
					if(val == "no"){
						
						
					}else{
						$(" <div class='sf-mega-section' id='childParent"+menuId+"'><span class='parentMenu'>"+name+"</span> ").appendTo("#menu"+getValue2);
						$("<ul id='childParentChild"+menuId+"'><br/>").appendTo("#childParent"+menuId);
						childChildNodeJSON(menuId,menuId);
					}
				}
			});
		}
	}
	
	function extractChildChildJSON(getValue1,getValue2){
		var name = '';
		var url = '';
		var menuId = '';
		var path = '';
		var parentId = '';
		var countchildNode = getValue1.length;
		for(var x=0; x<countchildNode; x++){
			$.each(getValue1[x], function(key, val) {
				if(key == '1menuId'){
					menuId = val;
				}else if(key == '2menu'){
					name = val;
				}else if(key == '3path'){
					path = val;
				}else if(key == '4url'){
					url = val;
				}else if(key == 'parentId'){
					parentId = val;
				}else if(key == '5haveChild'){
					
					if(val == "no"){
						$("<li><a class='childMenu' href='"+getUrl+path+url+"'>"+name+"</a></li>").appendTo("#childParentChild"+getValue2);
						$("</ul>").appendTo("#childParent"+getValue2);
						$("</li>").appendTo("#childParent"+getValue2);
						name = '';
						url = '';
						menuId = '';
						lastChild = '';
						path = '';
					}else{
						
						
					}
				}
			});
		}
	}
	
	
	$("#requestAccessMenuShow").click(function() {
		$("#modal-1").addClass("md-show");
	});
	
	
	
}); 

function showDiv(a){
	
	var display = $('#module'+a+' .sf-mega').css('display');
	if(display == 'block'){
		$('#module'+a+' .sf-mega').css('display', 'none');
	}else{
		$('.sf-mega').css('display', 'none');
		
		$('#module'+a+' .sf-mega').show( "fast" );
	}
}

function reloadNotification(){
	setInterval(function(){
		getPendingNotif();
	}, 1800000);
}

function getPendingNotif(){

	$.ajax({
		url:getUrl+'/setting/requestAccessMenu/get_request_access_menu_pending_list.html?limit=',
		async: false,
		beforeSend : function(xhr) {
			xhr.setRequestHeader(	"Accept","application/json");
			xhr.setRequestHeader("Content-Type","application/json");
		},
		success:function(result){
			var tm = result;
			var counterNotif = 0;
			
			$("#innernotification").empty();
			$(".icon_notif").empty();
			
			if(tm.length > 0){
    			for(var i = 0; i < tm.length; i++) {
    				var obj = tm[i];
    				$("<a href='"+getUrl+"/setting/requestAccessMenu/request_access_menu_search.html'><div class='list_notif'>" +
    						"<span class='headernotif'>"+obj.requestor.toUpperCase()+"</span>" +
    						"<span class='bodynotif'>"+obj.memo+"</span>" +
    						"<span class='detailnotif'>"+obj.createDate+"</span>" +
    					"</div></a>").appendTo("#innernotification");
    				counterNotif++;
    			}
			}else{
				$("Tidak Ada Pemberitahuan Baru").appendTo("#innernotification");
			}
			
			$(".icon_notif").append(counterNotif);
			
			if(counterNotif > 0){
				$(".icon_notif").show();
			}else{
				$(".icon_notif").hide();
			}
			
			
		}
	});

}
