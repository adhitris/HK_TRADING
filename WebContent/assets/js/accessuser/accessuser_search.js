var pilihUser="";
$(document).ready(function() {
	
	$( "#department").combogrid({
		width:"600px",
		url: 'get_department_list.html',
		debug:true,
		okIcon: true,
		showOn: true,
		colModel: [{'columnName':'id','width':'40','label':'ID','align':'left'},{'columnName':'nama','width':'40','label':'NAMA','align':'left'}],
		select: function( event, ui ) {
			$( "#department" ).val( ui.item.nama );
			$( "#departmentId" ).val( ui.item.id );
			return false;
		}
	});
	
	$( "#module" ).combogrid({
		width:"600px",
		url: 'get_module_list.html',
		debug:true,
		okIcon: true,
		showOn: true,
		colModel: [{'columnName':'nama','width':'100','label':'NAMA','align':'left'}],
		select: function( event, ui ) {
			$( "#module" ).val( ui.item.nama );
			$( "#moduleId" ).val( ui.item.id );
			return false;
		}
	});
	
	$("#diloagCopy").dialog({
		open: function(event, ui) {
	        $("input").blur();
	    },
		autoOpen: false,
		height: 300,
		width: 500,
		modal: true,
		show : {
			effect : "blind",
			duration : 200
		},
		hide : {
			effect : "blind",
			duration : 700
		}
	});
	
	$( "#accessUserCopy" ).click(function() {
		if(isUserAktif()==false){
			alert(getMessageSessionTimeout());
			return false;
		}else{
			var dataFromTheRow = jQuery('#grid').jqGrid ('getRowData',  mainId);
			var id = jQuery(dataFromTheRow.id).text();
			if(id == ''){
				alert("Silahkan Pilih User Id  terlebih Dahulu");
				return false;
			}else{
				pilihUser = id;
				$( "#userCopy" ).combogrid({
					width:"800px",
					url: 'get_user_list.html?idTarget='+pilihUser,
					debug:true,
					okIcon: true,
					showOn: true,
					colModel: [{'columnName':'id','width':'10','label':'ID'}, {'columnName':'name','width':'30','label':'Name'}],
					select: function( event, ui ) {
						$( "#userCopy" ).val( ui.item.name );
						$( "#idCopy" ).val( ui.item.id );
						return false;
					}
				});
				$("#title5").html("Copy Hak Akses");
				$("#diloagCopy").dialog("open");
				return false;
			}
		}
	});
	
	$("#updateUser").click(function() {
		if(isUserAktif()==false){
			alert(getMessageSessionTimeout());
			return false;
		}else if(isSupervisor()){
			var dataFromTheRow = jQuery('#grid').jqGrid ('getRowData',  mainId);
			var id = jQuery(dataFromTheRow.id).text();
			if(id == ''){
				alert("Silahkan Pilih User Id  terlebih Dahulu");
				return false;
			}else{
				window.location.href = getUrl+"/master/accessuser/add_access_user.html?idUser="+id;
				setMainIdNull();
				return false;
			}
		}else{
			alert("Permintaan Tidak Dapat Diproses , Anda Tidak Memiliki Hak Akses 'SUPERVISOR'");
			return false;
		}
	});
	
	
	$(function() {
		$("#dialog").dialog({
			autoOpen : false,
			show : {
				effect : "blind",
				duration : 100
			},
			hide : {
				effect : "blind",
				duration : 100
			}
		});
		$("#opener").click(function() {
			$("#dialog").dialog("open");
		});
	});
	
	$("#download").click(function() {
		var tipeReport = $("input[name='tipeReport']:checked").val();
		var statusUser = $("input[name='statusUser']:checked").val();
		window.open(getUrl+"/master/user/download_pdf.html?tipeReport="+tipeReport+"&statusUser="+statusUser,"_blank");
		$( "#dialog" ).dialog( "close" );
		return true;
	});
	
	jQuery("#grid").jqGrid({
		  autowidth: true,
		  shrinkToFit:true,
	      url:'get_user_jqgrid.html',
	      datatype: "json",
	      mtype: 'GET',
	      colNames:[ 'USER ID', 'NAMA','ACTIVE','CREATE','UPDATE','DELETE','CANCEL','PRINT','REPRINT','REPORT','CONFIRM','UNCONFIRM','SUPERVISOR','SUPERUSER','Create Date'],
	      colModel:[
	               
	                {name:'id',index:'id', width:250, sortable:true, editable:false,formatter: fontColorFormatter},
	                {name:'nama',index:'nama', width:350,editable:false,formatter: fontColorFormatter},
	                {name:'isActive',index:'isActive', width:100,editable:false,formatter: fontColorFormatter,hidden:true},
	                {name:'isCreate',index:'isCreate', width:100,editable:false,formatter: fontColorFormatterEnable},
	                {name:'isUpdate',index:'isUpdate', width:100,editable:false,formatter: fontColorFormatterEnable},
	                {name:'isDelete',index:'isDelete', width:100,editable:false,formatter: fontColorFormatterEnable},
	                {name:'isCancel',index:'isCancel', width:100,editable:false,formatter: fontColorFormatterEnable},
	                {name:'isPrint',index:'isPrint', width:100,editable:false,formatter: fontColorFormatterEnable},
	                {name:'isReprint',index:'isReprint', width:100,editable:false,formatter: fontColorFormatterEnable},
	                {name:'isReport',index:'isReport', width:100,editable:false,formatter: fontColorFormatterEnable},
	                {name:'isConfirm',index:'isConfirm', width:100,editable:false,formatter: fontColorFormatterEnable},
	                {name:'isUnconfirm',index:'isUnconfirm', width:100,editable:false,formatter: fontColorFormatterEnable},
	                {name:'isSupervisor',index:'isSupervisor', width:130,editable:false,formatter: fontColorFormatterEnable},
	                {name:'isSuperuser',index:'isSuperuser', width:130,editable:false,formatter: fontColorFormatterEnable},
	                {name:'createDate',index:'createDate', width:250,editable:false,formatter: fontColorFormatter}
	           ],
	     jsonReader : {
	          repeatitems:false
	     },
	     rowNum:500,
	      rowList:[],
	      pgbuttons: false,     // disable page control like next, back button
	      pgtext: null,         // disable pager text like 'Page 0 of 10'
	      pager: '#gridpager',
	      sortname: 'id',
	      viewrecords: true,
	      sortorder: "asc",
	      search:false,
	      viewrecords: true,
	      multiselect: false,
	      height: 'auto',
	      autowidth: true,
	      onSelectRow: function(ids) {
		    	mainId=ids;
	      },
	      gridComplete: function(){
	  		var ids = jQuery("#grid").jqGrid('getDataIDs');
	  	}
	 });
	 jQuery("#grid").jqGrid('navGrid','#gridpager',{edit:false,add:false,del:false,search: false});
	
	 
	 $("#copyHakAkses").click(function() {
		 	var dataFromTheRow = jQuery('#grid').jqGrid ('getRowData',  mainId);
			var id = jQuery(dataFromTheRow.id).text();
			var idCopy = $('#idCopy').val().length;
			if(isUserAktif()==false){
				alert(getMessageSessionTimeout());
				return false;
			}else if(idCopy < 1){
				alert("Sumber UserId tidak boleh kosong !");
				return false;
			}else{
				var json = {};
				$.ajax({
					url : getUrl+ "/master/accessuser/copy.html?idCopy="+$('#idCopy').val()+"&id="+id,
					data : JSON.stringify(json),
					type : "POST",
					beforeSend : function(xhr) {
						xhr.setRequestHeader(	"Accept","application/json");
						xhr.setRequestHeader("Content-Type","application/json");
					},
					success : function(result) {
						if(result==true){
							var respContent = "Data Dengan Record   : "+ id +" Berhasil Mempunyai Hak Akses "+ $('#idCopy').val();
							setNotificationEdit(respContent);
							gridReload();
							$("#diloagCopy").dialog("close");
						}else if(result==false){
							var respContent = "";
							respContent += "Data Dengan Record  : " + id +" Gagal Dicopy";
							alert(respContent);
							return false;
						}else{
							generateError(result, 'User ', id);
						}
						
					}
				});		
			}
		});
	
});

function gridReload(){
	if(isUserAktif()==false){
		alert(getMessageSessionTimeout());
		return false;
	}else{
		var firstName = jQuery("#firstName").val();
		var module = jQuery("#moduleId").val();
		var departmentId = jQuery("#departmentId").val();
		
		jQuery("#grid").jqGrid('setGridParam',{url:"get_user_jqgrid.html?firstName="+firstName+
			"&module="+module+"&departmentId="+departmentId}).trigger("reloadGrid", [{current:true}]);

	}
}
function gridReset(){
	if(isUserAktif()==false){
		alert(getMessageSessionTimeout());
		return false;
	}else{
		jQuery("#grid").jqGrid('setGridParam',{url:"get_user_jqgrid.html"}).trigger("reloadGrid", [{current:true}]);
		$('#filterForm')[0].reset();
		setMainIdNull();
	}
}