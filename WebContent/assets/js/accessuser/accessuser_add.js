$(document).ready(function() {
	
	$("#browser").treeview({
		collapsed: true,
		animated: "fast",
		control:"#sidetreecontrol",
		persist: "location"
	});
	
	/*var allSelected = $("#menuSelected").val();
	var allUnselected = $("#menuUnselected").val();
    var allSelectedSplit = allSelected.split(';');
    var allUnselectedSplit = allUnselected.split(';');
    for(var a=0; a<allSelectedSplit.length; a++){
    	$( "#li-"+allSelectedSplit[a] ).addClass( "collapsable" );
    	$( "#ul-"+allSelectedSplit[a] ).attr('style', 'display: block');
    }
    
    for(var a=0; a<allUnselectedSplit.length; a++){
    	$( "#li-"+allUnselectedSplit[a] ).addClass( "expandable" );
    	$( "#ul-"+allUnselectedSplit[a] ).attr('style', 'display: none');
		$("."+allUnselectedSplit[a]).attr("disabled", true);
    }*/
	
});

function checkDoCreate(getVal){
	if($('#doCreate'+getVal).is(':checked')){
		$("#hiddenDoCreate"+getVal).val("true");
	}else{
		$("#hiddenDoCreate"+getVal).val("false");
	}
}

function checkDoUpdate(getVal){
	if($('#doUpdate'+getVal).is(':checked')){
		$("#hiddenDoUpdate"+getVal).val("true");
	}else{
		$("#hiddenDoUpdate"+getVal).val("false");
	}
}

function checkParentChild(getVal){
	alert("masuk");
	var nodeStatus = $("#hiddenStatus-"+getVal).val();
	var children = $("#hiddenChild-"+getVal).val();
	var parents = $("#hiddenParent-"+getVal).val();
	
	var parentsSplit;
	var childrenSplit;
	var count_parentsSplit=0;
	var count_childrenSplit=0;
	
	if(parents != ''){
		parentsSplit = parents.split(';');
		count_parentsSplit = parentsSplit.length;
		
	}else if(children != ''){
		
		childrenSplit = children.split(';');
		count_childrenSplit = childrenSplit.length;
	}
	
	if(nodeStatus == 'parent'){
		if($('#'+getVal).is(':checked')){
			$("#hiddenDoCreate"+getVal).attr("disabled", false);
			$("#hiddenDoUpdate"+getVal).attr("disabled", false);
		}else{
			$("#hiddenDoCreate"+getVal).attr("disabled", true);
			$("#hiddenDoUpdate"+getVal).attr("disabled", true);
			$("."+getVal).attr('checked',false);
			for(var a=0; a<count_childrenSplit; a++){
				alert("masuk 1");
				$("."+childrenSplit[a]).attr('checked',false);
			}
		}
	}else if(nodeStatus == 'parentChild'){
		if($('#'+getVal).is(':checked')){
			for(var a=0; a<count_parentsSplit; a++){
				alert("masuk 2");
				$("#"+parentsSplit[a]).attr('checked',true);
			}
		}else{
			$("."+getVal).attr('checked',false);
			for(var a=0; a<count_childrenSplit; a++){
				alert("masuk 3");
				$("."+childrenSplit[a]).attr('checked',false);
			}
		}
	}else if(nodeStatus == 'child'){
		if($('#'+getVal).is(':checked')){
			$("#doCreate"+getVal).attr("disabled", false);
			$("#doUpdate"+getVal).attr("disabled", false);
			$("#hiddenDoCreate"+getVal).attr("disabled", false);
			$("#hiddenDoUpdate"+getVal).attr("disabled", false);
		}else{
			$("#doCreate"+getVal).attr("disabled", true);
			$("#doUpdate"+getVal).attr("disabled", true);
			$("#hiddenDoCreate"+getVal).attr("disabled", true);
			$("#hiddenDoUpdate"+getVal).attr("disabled", true);
		}
		
		for(var a=0; a<count_parentsSplit; a++){
			alert("masuk 4");
			$("#"+parentsSplit[a]).attr('checked',true);
			$("#hiddenDoCreate"+parentsSplit[a]).attr("disabled", false);
			$("#hiddenDoUpdate"+parentsSplit[a]).attr("disabled", false);
		}
	}
	
}

function disableEnableCheck(getVal){
	if($('#'+getVal).is(':checked')){
		$("."+getVal).removeAttr("disabled");
	}else{
		$("."+getVal).attr('checked',false);
		$("."+getVal).attr("disabled", true);
	}
}