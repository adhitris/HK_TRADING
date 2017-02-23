$(document).ready(function() {
	
	$( "#user" ).combogrid({
		width:"800px",
		url: 'get_user_list.html',
		debug:true,
		okIcon: true,
		showOn: true,
		colModel: [{'columnName':'id','width':'10','label':'ID'}, {'columnName':'name','width':'30','label':'Name'}],
		select: function( event, ui ) {
			$( "#user" ).val( ui.item.name );
			$( "#userId" ).val( ui.item.id );
			return false;
		}
	});
	
	$("#addUser").click(function() {
		if(isUserAktif()==false){
			alert(getMessageSessionTimeout());
			return false;
		}else if(isCreate()){
			window.location.href = getUrl+"/master/user/user_add.html";
			return false;
		}else{
			alert("Permintaan Tidak Dapat Diproses , Anda Tidak Memiliki Hak Akses 'Tambah'");
		}
	});
	
	$("#updateUser").click(function() {
		if(isUserAktif()==false){
			alert(getMessageSessionTimeout());
			return false;
		}else if(isUpdate()){
			var dataFromTheRow = jQuery('#grid').jqGrid ('getRowData',  mainId);
			var check = jQuery(dataFromTheRow.id).text();
			if(check == ''){
				alert("Silahkan Pilih User Id  terlebih Dahulu");
			}else{
				window.location.href = getUrl+"/master/user/user_pass_otorisasi_edit.html?id="+check;
				setMainIdNull();
			}
			return false;
		}else{
			alert("Permintaan Tidak Dapat Diproses , Anda Tidak Memiliki Hak Akses 'Edit'");
		}
	});
	
	$("#deleteUser").click(function() {
		if(isUserAktif()==false){
			alert(getMessageSessionTimeout());
			return false;
		}else if(isDelete()){
			var dataFromTheRow = jQuery('#grid').jqGrid ('getRowData',  mainId);
			var check = jQuery(dataFromTheRow.id).text();
			if(check == ''){
				alert("Silahkan Pilih User ID terlebih Dahulu");
			}else{
				var wannna = confirm("Apakah Anda Akan Menghapus User dengan User Id : "+ check +" ?");
				 if (wannna== true){
						var json = {"id" : check,};
						$.ajax({
							url : getUrl+ "/master/user/user_delete.html",
							data : JSON.stringify(json),
							type : "POST",
							beforeSend : function(xhr) {
								xhr.setRequestHeader(	"Accept","application/json");
								xhr.setRequestHeader("Content-Type","application/json");
							},
							success : function(result) {
								if(result==true){
									var respContent = "Data Dengan User Id : "+ check +" Berhasil Dihapus";
									setNotificationRemove(respContent);
									 setMainIdNull();
									gridReset();
								}else if(result==false){
									var respContent = "";
									respContent += "Data Dengan User ID  : " + $('#id').val()+" Gagal Di Hapus";
									alert(respContent);
									return false;
								}else{
									generateError(result, 'User', $('#id').val());
								}
								
							}
						});
				 }
				
			}
			
		}else{
			alert("Permintaan Tidak Dapat Diproses , Anda Tidak Memiliki Hak Akses 'Hapus'");
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
	
	$("#print").click(function(){
		$.blockUI({ message: '<br>Loading...</br></br>' }); 
		  $.get("generate_print_report.html",function(result){
		    if(result){
		    	setNotificationAdd("Data Berhasil Di Print");
		    }else{
		    	alert("Gagal");
		    }
		  });
	});
	
	$("#download").click(function() {
		var tipeReport = $("input[name='tipeReport']:checked").val();
		var statusUser = $("input[name='statusUser']:checked").val();
		window.open(getUrl+"/master/user/download_pdf.html?tipeReport="+tipeReport+"&statusUser="+statusUser,"_blank");
		$( "#dialog" ).dialog( "close" );
		return true;
	});
	
	$("#editIsActive").click(function() {
		if(isUserAktif()==false){
			alert(getMessageSessionTimeout());
			return false;
		}else if(isSupervisor()){
			var dataFromTheRow = jQuery('#grid').jqGrid ('getRowData',  mainId);
			var check = jQuery(dataFromTheRow.id).text();
	
			if(check == ''){
				alert("Silahkan Pilih User Id  terlebih Dahulu");
			}else{
				var message=jQuery(dataFromTheRow.isActive).text();
				if(message=='Y'){
					message="Saat Ini Data Dengan User Id : "+ check +" Dalam Kondisi Aktif,apakah Anda akan Me Non aktifkan nya?";
				}else if(message=='N'){
					message="Saat Ini Data Dengan User Id : "+ check +" Dalam Kondisi Tidak Aktif,apakah Anda akan Mengaktifkan nya?";
				}
				var wannna = confirm(message);
				 if (wannna== true){
						
						var json = {"id" : check,};
						$.ajax({
							url : getUrl+ "/master/user/user_edit_is_active.html",
							data : JSON.stringify(json),
							type : "POST",
							beforeSend : function(xhr) {
								xhr.setRequestHeader(	"Accept","application/json");
								xhr.setRequestHeader("Content-Type","application/json");
							},
							success : function(result) {
								if(result==true){
									var respContent = "Data Dengan User Id  : "+ dataFromTheRow.id +" Berhasil Dirubah";
									setNotificationEdit(respContent);
									gridReset();
									setMainIdNull();
								}else if(result==false){
									var respContent = "";
									respContent += "Data Dengan User ID  : " + $('#id').val()+" Gagal Di Rubah";
									alert(respContent);
									return false;
								}else{
									generateError(result, 'User', $('#id').val());
								}
								
							}
						});
				 }
			}
			return false;
		}else{
			alert("Permintaan Tidak Dapat Diproses , Anda Tidak Memiliki Hak Akses 'Supervisor'");
			return false;
		}
	});
	
	jQuery("#grid").jqGrid({
		  autowidth: true,
		  shrinkToFit:true,
	      url:'get_user_jqgrid.html',
	      datatype: "json",
	      mtype: 'GET',
	      colNames:[ 'ID', 'NAMA','ACTIVE','CREATE','UPDATE','DELETE','CANCEL','PRINT','REPORT','SUPERVISOR','CREATE BY','CREATE DATE'],
	      colModel:[
	                {name:'id',index:'id', width:250, sortable:false, editable:false,formatter: fontColorFormatter},
	                {name:'nama',index:'nama', width:350,editable:false,formatter: fontColorFormatter},
	                {name:'isActive',index:'isActive', width:100,editable:false,formatter: fontColorFormatter},
	                {name:'isCreate',index:'isCreate', width:100,editable:false,formatter: fontColorFormatter},
	                {name:'isUpdate',index:'isUpdate', width:100,editable:false,formatter: fontColorFormatter},
	                {name:'isDelete',index:'isDelete', width:100,editable:false,formatter: fontColorFormatter},
	                {name:'isCancel',index:'isCancel', width:100,editable:false,formatter: fontColorFormatter},
	                {name:'isPrint',index:'isPrint', width:100,editable:false,formatter: fontColorFormatter},
	                {name:'isReport',index:'isReport', width:100,editable:false,formatter: fontColorFormatter},
	                {name:'isSupervisor',index:'isSupervisor', width:130,editable:false,formatter: fontColorFormatter},
	                {name:'createBy',index:'createBy', width:120,editable:true,formatter: fontColorFormatter},
	                {name:'createDate',index:'createDate', width:130,editable:true,formatter: fontColorFormatter}
	           ],
	     jsonReader : {
	          repeatitems:false
	     },
	     rowNum:15,
	      rowList:[15,30,50,100,500],
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
	  		for(var i=0;i < ids.length;i++){
	  			var cl = ids[i];
	  		}	
	  	}
	 });
	 jQuery("#grid").jqGrid('navGrid','#gridpager',{edit:false,add:false,del:false,search: false});
	
	
});

function gridReload(){
	if(isUserAktif()==false){
		alert(getMessageSessionTimeout());
		return false;
	}else{
		var nama = jQuery("#nama").val();
		var id = jQuery("#id").val();
		var isCreate = ($("#isCreate").is(':checked') ? 1 : '');
		var isUpdate = ($("#isUpdate").is(':checked') ? 1 : '');
		var isDelete = ($("#isDelete").is(':checked') ? 1 : '');
		var isCancel = ($("#isCancel").is(':checked') ? 1 : '');
		var isPrint = ($("#isPrint").is(':checked') ? 1 : '');
		var isReport = ($("#isReport").is(':checked') ? 1 : '');
		var isSupervisor = ($("#isSupervisor").is(':checked') ? 1 : '');
		
		var startDate = jQuery("#startDate").val();
		var endDate = jQuery("#endDate").val();
		jQuery("#grid").jqGrid('setGridParam',{url:"get_user_jqgrid.html?id="+id+"&nama="+nama+"&isCreate="+isCreate+"&isUpdate="+isUpdate+"&isDelete="+isDelete+"&isCancel="+isCancel+"&isPrint="+isPrint+"&isReport="+isReport+"&isSupervisor="+isSupervisor+"&startDate="+startDate+"&endDate="+endDate}).trigger("reloadGrid", [{current:true}]);
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