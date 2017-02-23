$(document).ready(function() {
	
	$( "#mataUang" ).combogrid({
		width:"800px",
		url: 'get_mata_uang_list.html',
		debug:true,
		okIcon: true,
		showOn: true,
		colModel: [{'columnName':'kode','width':'10','label':'Kode'},{'columnName':'nama','width':'30','label':'Nama Group'}],
		select: function( event, ui ) {
			$( "#mataUang" ).val( ui.item.kode );
			$( "#mataUangId" ).val( ui.item.id );
			return false;
		}
	});
	
	$("#addMataUang").click(function() {
		if(isUserAktif()==false){
			alert(getMessageSessionTimeout());
			return false;
		}else if(isCreate()){
			window.location.href = getUrl+"/master/mataUang/mata_uang_add.html";
			return false;
		}else{
			alert("Permintaan Tidak Dapat Diproses , Anda Tidak Memiliki Hak Akses 'Tambah'");
		}
	});
	
	$("#updateMataUang").click(function() {
		if(isUserAktif()==false){
			alert(getMessageSessionTimeout());
			return false;
		}else if(isUpdate()){
			var dataFromTheRow = jQuery('#grid').jqGrid ('getRowData',  mainId);
			var id = jQuery(dataFromTheRow.id).text();
			if(id == ''){
				alert("Silahkan Pilih Mata Uang ID terlebih Dahulu");
				//return false;
			}else{
				window.location.href = getUrl+"/master/mataUang/mata_uang_edit.html?id="+id;
				setMainIdNull();
			}
			return false;
		}else{
			alert("Permintaan Tidak Dapat Diproses , Anda Tidak Memiliki Hak Akses 'Edit'");
			return false;
		}
	});
	
	$("#deleteMataUang").click(function() {
		if(isUserAktif()==false){
			alert(getMessageSessionTimeout());
			return false;
		}else if(isDelete()){
			var dataFromTheRow = jQuery('#grid').jqGrid ('getRowData',  mainId);
			var id = jQuery(dataFromTheRow.id).text();
	
			if(id == ''){
				alert("Silahkan Pilih Mata Uang Id  terlebih Dahulu");
			}else{
				var wannna = confirm("Apakah Anda Akan Menghapus Data Dengan Mata Uang ID : "+ id +" ?");
				 if (wannna== true){
						
						var json = {"id" : id,};
						$.blockUI({ message: '<br>Loading...</br></br>' });
						$.ajax({
							url : getUrl+ "/master/mataUang/mata_uang_delete.html",
							data : JSON.stringify(json),
							type : "POST",
							beforeSend : function(xhr) {
								xhr.setRequestHeader(	"Accept","application/json");
								xhr.setRequestHeader("Content-Type","application/json");
							},
							success : function(result) {
								if(result==true){
									var respContent = "Mata Uang Dengan id : " +id +" Berhasil Dihapus";
									setNotificationRemove(respContent);
									gridReset();
									setMainIdNull();
								}else if(result==false){
									var respContent = "";
									respContent += "Mata Uang Dengan ID  : " + id +" Gagal Dihapus";
									alert(respContent);
									return false;
								}else{
									generateError(result, 'Mata Uang ', id);
								}
								
							}
						});
		
				 }
				 
				//return true;
			}
			return false;
		}else{
			alert("Permintaan Tidak Dapat Diproses , Anda Tidak Memiliki Hak Akses 'Hapus'");
			return false;
		}
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
				alert("Silahkan Pilih Mata Uang Id  terlebih Dahulu");
			}else{
				var message=jQuery(dataFromTheRow.isActive).text();
				if(message=='Y'){
					message="Saat Ini Data Mata Uang Id : "+ id +" Dalam Kondisi Aktif,apakah Anda akan Me Non aktifkan nya?";
				}else if(message=='N'){
					message="Saat Ini Data Mata Uang Id : "+ id +" Dalam Kondisi Tidak Aktif,apakah Anda akan Mengaktifkan nya?";
				}
				var wannna = confirm(message);
				 if (wannna== true){
						
						var json = {"id" : id,"version" : version,};
						$.blockUI({ message: '<br>Loading...</br></br>' });
						$.ajax({
							url : getUrl+ "/master/mataUang/mata_uang_edit_is_active.html",
							data : JSON.stringify(json),
							type : "POST",
							beforeSend : function(xhr) {
								xhr.setRequestHeader(	"Accept","application/json");
								xhr.setRequestHeader("Content-Type","application/json");
							},
							success : function(result) {
								if(result==true){
									var respContent = "Data Dengan Mata Uang Id   : "+ dataFromTheRow.id +" Berhasil Dirubah";
									setNotificationEdit(respContent);
									gridReset();
								}else if(result==false){
									var respContent = "";
									respContent += "Mata Uang Dengan ID  : " + id +" Gagal Dirubah";
									alert(respContent);
									return false;
								}else{
									generateError(result, 'Mata Uang ', id);
								}
								
							}
						});
				 }
				//return true;
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
	      url:'get_mata_uang_jqgrid.html',
	      datatype: "json",
	      mtype: 'GET',
	      colNames:[ 'MATA UANG ID','AKTIF','CREATE BY','CREATE DATE','VERSION'],
	      colModel:[
	                {name:'id',index:'id', width:250,editable:true,formatter: fontColorFormatter},
	                {name:'isActive',index:'isActive', width:250,editable:true,formatter: fontColorFormatter},
	                {name:'createBy',index:'createBy', width:120,editable:true,formatter: fontColorFormatter},
	                {name:'createDate',index:'createDate', width:130,editable:true,formatter: fontColorFormatter},
	                {name:'version',index:'version', width:130,editable:true,formatter: fontColorFormatter,hidden:true}
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
	  		
	  	}
	 });
	 jQuery("#grid").jqGrid('navGrid','#gridpager',{edit:false,add:false,del:false,search: false});

	
});

function gridReload(){
	if(isUserAktif()==false){
		alert(getMessageSessionTimeout());
		return false;
	}else{
		var id = $("#id").val();
		var isActive = $('input[name=isActive]:checked').val();
		jQuery("#grid").jqGrid('setGridParam',{url:"get_mata_uang_jqgrid.html?id="+id+"&isActive="+isActive}).trigger("reloadGrid", [{current:true}]);
	}
}

function gridReset(){
	if(isUserAktif()==false){
		alert(getMessageSessionTimeout());
		return false;
	}else{
		jQuery("#grid").jqGrid('setGridParam',{url:"get_mata_uang_jqgrid.html"}).trigger("reloadGrid", [{current:true}]);
		$('#filterForm')[0].reset();
		setMainIdNull();
	}
}