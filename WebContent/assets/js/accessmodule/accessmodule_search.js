$(document).ready(function() {
	
	$( "#role" ).combogrid({
		width:"800px",
		url: 'get_role_list.html',
		debug:true,
		okIcon: true,
		showOn: true,
		colModel: [{'columnName':'id','width':'10','label':'ID'}, {'columnName':'name','width':'30','label':'Name'}],
		select: function( event, ui ) {
			$( "#role" ).val( ui.item.name );
			$( "#roleId" ).val( ui.item.id );
			return false;
		}
	});
	
	$("#updateRole").click(function() {
		var id = $("#roleId").val();
		if(id == ""){
			alert("Silahkan Pilih User Id Terlebih Dahulu");
			//return false;
		}else{
			window.location.href = getUrl+"/master/accessmodule/add_access_module.html?idRole="+id;
			//return true;
		}
	});
	

	
});