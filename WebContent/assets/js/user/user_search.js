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
	
	$('#dialog9').bind('dialogclose', function(event) {
		$("#testHtml").html("");
	 });
	
	$("#printBarcode").click(function() {
		var dataFromTheRow = jQuery('#grid').jqGrid ('getRowData',  mainId);
		var id = jQuery(dataFromTheRow.id).text();
		var isActive = jQuery(dataFromTheRow.isActive).text();
		if(id == ''){
			alert("Silahkan pilih User terlebih dahulu");
			return false;
		}else if(isActive=="N"){
			alert("Permintaan Tidak Dapat Diproses,User Ini Sudah Di Non-Aktifkan");
			return false;
		}
		else{
			printLabelCallSingle(id);
			return true;
		}
		return false;
	});
	
	$("#dialog9").dialog({
		autoOpen : false,
		modal: true,
		width : 800,
	    height : 500, 
		show : {
			effect : "blind",
			duration : 100
		},
		hide : {
			effect : "blind",
			duration : 100
		}
	});
	
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
			var id = jQuery(dataFromTheRow.id).text();
			if(id == ''){
				alert("Silahkan Pilih User Id  terlebih Dahulu");
			}else{
				window.location.href = getUrl+"/master/user/user_edit.html?id="+id;
				setMainIdNull();
			}
			return false;
		}else{
			alert("Permintaan Tidak Dapat Diproses , Anda Tidak Memiliki Hak Akses 'Edit'");
		}
	});
	
	$("#updatePassword").click(function() {
		if(isUserAktif()==false){
			alert(getMessageSessionTimeout());
			return false;
		}else if(isUpdate()){
			var dataFromTheRow = jQuery('#grid').jqGrid ('getRowData',  mainId);
			var id = jQuery(dataFromTheRow.id).text();
			if(id == ''){
				alert("Silahkan Pilih User Id  terlebih Dahulu");
			}else{
				window.location.href = getUrl+"/master/user/user_pass_login_edit.html?id="+id;
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
			var id = jQuery(dataFromTheRow.id).text();
			if(id == ''){
				alert("Silahkan Pilih User ID terlebih Dahulu");
			}else{
				var wannna = confirm("Apakah Anda Akan Menghapus User dengan User Id : "+ id +" ?");
				 if (wannna== true){
						var json = {"id" : id,};
						$.blockUI({ message: '<br>Loading...</br></br>' });
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
									var respContent = "Data Dengan User Id : "+ id +" Berhasil Dihapus";
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
		var statusUser = $("input[name='isActive']:checked").val();
		var nama = jQuery("#nama").val();
		var id = jQuery("#id").val();
		var isCreate = ($("#isCreate").is(':checked') ? 1 : '');
		var isUpdate = ($("#isUpdate").is(':checked') ? 1 : '');
		var isDelete = ($("#isDelete").is(':checked') ? 1 : '');
		var isCancel = ($("#isCancel").is(':checked') ? 1 : '');
		var isPrint = ($("#isPrint").is(':checked') ? 1 : '');
		var isReport = ($("#isReport").is(':checked') ? 1 : '');
		var isSupervisor = ($("#isSupervisor").is(':checked') ? 1 : '');
		
	
		
		window.open(getUrl+"/master/user/download_pdf.html?tipeReport="+tipeReport+
				"&statusUser="+statusUser+
				"&id="+id+"&nama="+nama+
				"&isCreate="+isCreate+
				"&isUpdate="+isUpdate+
				"&isDelete="+isDelete+
				"&isCancel="+isCancel+
				"&isPrint="+isPrint+
				"&isReport="+isReport+
				"&isSupervisor="+isSupervisor,"_blank");
		
		$( "#dialog" ).dialog( "close" );
		return true;
	});
	
	$("#editIsActive").click(function() {
		
		if(isUserAktif()==false){
			alert(getMessageSessionTimeout());
			return false;
		}else if(isSupervisor()){
			var dataFromTheRow = jQuery('#grid').jqGrid ('getRowData',  mainId);
			var id = jQuery(dataFromTheRow.id).text();
			var version = jQuery(dataFromTheRow.version).text();
			
			if(id == ''){
				alert("Silahkan Pilih User Id  terlebih Dahulu");
			}else{
				var message=jQuery(dataFromTheRow.isActive).text();
				if(message=='Y'){
					message="Saat Ini Data Dengan User Id : "+ id +" Dalam Kondisi Aktif,apakah Anda akan Me Non aktifkan nya?";
				}else if(message=='N'){
					message="Saat Ini Data Dengan User Id : "+ id +" Dalam Kondisi Tidak Aktif,apakah Anda akan Mengaktifkan nya?";
				}
				var wannna = confirm(message);
				 if (wannna== true){
					 $.blockUI({ message: '<br>Loading...</br></br>' }); 
						var json = {"id" : id,"version" : version,};
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
									var respContent = "Data Dengan User Id  : "+ id +" Berhasil Dirubah";
									setNotificationEdit(respContent);
									gridReset();
									setMainIdNull();
								}else if(result==false){
									var respContent = "";
									respContent += "Data Dengan User ID  : " + id +" Gagal Di Rubah";
									alert(respContent);
									return false;
								}else{
									generateError(result, 'User', id);
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
	      colNames:[ 'ID', 'NAMA','ACTIVE','CREATE','UPDATE','DELETE','CANCEL','PRINT','REPRINT','REPORT','CONFIRM','UNCONFIRM','SUPERVISOR','SUPERUSER','CREATE BY','CREATE DATE','VERSION'],
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
	                {name:'createBy',index:'createBy', width:120,editable:true,formatter: fontColorFormatter,hidden:true},
	                {name:'createDate',index:'createDate', width:130,editable:true,formatter: fontColorFormatter,hidden:true},
	                {name:'version',index:'version', width:130,editable:true,formatter: fontColorFormatter,hidden:true}
	           ],
	     jsonReader : {
	          repeatitems:false
	     },
	     rowList: [],        // disable page size dropdown
	     pgbuttons: false,     // disable page control like next, back button
	     pgtext: null,         // disable pager text like 'Page 0 of 10'
	     rowNum:500,
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
		    	
		    	if(isUserAktif()==false){
					alert(getMessageSessionTimeout());
					return false;
				}else{
				   
					if(ids == null) {
				    		  ids=0; 
				    		  if(jQuery("#userGudangGrid").jqGrid('getGridParam','records') >0 ) {
				    			  jQuery("#userGudangGrid").jqGrid('setGridParam',{url:"get_gudang_user_jqgrid_by_user.html?id="+ids,page:1}); 
				    			  jQuery("#userGudangGrid").jqGrid('setCaption',"Gudang Untuk User : "+ids) .trigger('reloadGrid'); 
				    		  } 
				    		
				    } else { 
							  jQuery("#userGudangGrid").jqGrid('setGridParam',{url:"get_gudang_user_jqgrid_by_user.html?id="+ids,page:1}); 
			    			  jQuery("#userGudangGrid").jqGrid('setCaption',"Gudang Untuk User : "+ids) .trigger('reloadGrid');
				    } 
				    
				   
				}
	      },
	      gridComplete: function(){
	  		var ids = jQuery("#grid").jqGrid('getDataIDs');
	  		for(var i=0;i < ids.length;i++){
	  			var cl = ids[i];
	  		}	
	  	}
	 });
	 jQuery("#grid").jqGrid('navGrid','#gridpager',{edit:false,add:false,del:false,search: false});
	
	 jQuery("#userGudangGrid").jqGrid({ 
			height: 'auto', 
			url:'get_gudang_user_jqgrid_by_user.html?id=0', 
			datatype: "json", 
			colNames:['ID','GUDANG'], 
			colModel:[ {name:'id',index:'id',width:60,formatter: fontColorFormatterCancel,hidden:false}, 
			           {name:'nama',index:'nama',width:190,formatter: fontColorFormatterCancel,hidden:false}
			          ], 
			           rowNum:10, rowList:[10,20,50], 
			           pager: '#userGudangGridPager',
			           sortname: 'id', 
			           viewrecords: true, 
			           sortorder: "asc", 
			           height: 'auto',
			           caption:"-",
			 	       autowidth: true,
			           multiselect: false,
			           shrinkToFit: false,
			           onSelectRow: function(idsSubMainId) {
				   	    	subMainId=idsSubMainId;
				   		}
					  
		});
	 
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
		var isConfirm = ($("#isConfirm").is(':checked') ? 1 : '');
		var isUnconfirm = ($("#isUnconfirm").is(':checked') ? 1 : '');
		var isSupervisor = ($("#isSupervisor").is(':checked') ? 1 : '');
		var isSuperuser = ($("#isSuperuser").is(':checked') ? 1 : '');
		var isReprint = ($("#isReprint").is(':checked') ? 1 : '');
		var isActive = $("input[name='isActive']:checked").val();

		var startDate = jQuery("#startDate").val();
		var endDate = jQuery("#endDate").val();
		
		var departmentId = jQuery("#departmentId").val();
		
		jQuery("#grid").jqGrid('setGridParam',{url:"get_user_jqgrid.html?id="+id+"&nama="+nama+"&isCreate="+isCreate+"&isUpdate="+isUpdate+"&isDelete="+isDelete+"&isCancel="+isCancel+"&isPrint="+isPrint+"&isReport="+isReport+"&isSupervisor="+isSupervisor+"&isActive="+isActive+"&startDate="+startDate+"&endDate="+endDate+"&departmentId="+departmentId+"&isConfirm="+isConfirm+"&isUnconfirm="+isUnconfirm+"&isSuperuser="+isSuperuser+"&isReprint="+isReprint}).trigger("reloadGrid", [{current:true}]);
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

function showDialogReport(){
	$("#dialog").dialog("open");
}


function printLabelCallSingle(id){
	$("#testHtml").html("<iframe frameborder='0' scrolling='no' width='700' height='700' src='"+getUrl+"/master/user/print_barcode.html?id="+id+"'></iframe>");
	$( "#dialog9" ).dialog( "open" );
}