$(document).ready(function() {
	
	$( "#unit" ).combogrid({
		width:"800px",
		url: 'get_unit_list.html',
		debug:true,
		okIcon: true,
		showOn: true,
		colModel: [{'columnName':'kode','width':'10','label':'Kode'},{'columnName':'nama','width':'30','label':'Nama Group'}],
		select: function( event, ui ) {
			$( "#unit" ).val( ui.item.kode );
			$( "#unitId" ).val( ui.item.id );
			return false;
		}
	});
	
	$("#addUnit").click(function() {
		if(isUserAktif()==false){
			alert(getMessageSessionTimeout());
			return false;
		}else if(isCreate()){
			window.location.href = getUrl+"/master/unit/unit_add.html";
			return false;
		}else{
			alert("Permintaan Tidak Dapat Diproses , Anda Tidak Memiliki Hak Akses 'Tambah'");
		}
	});
	
	$("#updateUnit").click(function() {
		if(isUserAktif()==false){
			alert(getMessageSessionTimeout());
			return false;
		}else if(isUpdate()){
			var dataFromTheRow = jQuery('#grid').jqGrid ('getRowData',  mainId);
			var id = jQuery(dataFromTheRow.id).text();
			if(id == ''){
				alert("Silahkan Pilih Unit ID terlebih Dahulu");
				//return false;
			}else{
				window.location.href = getUrl+"/master/unit/unit_edit.html?id="+id;
				setMainIdNull();
			}
			return false;
		}else{
			alert("Permintaan Tidak Dapat Diproses , Anda Tidak Memiliki Hak Akses 'Edit'");
			return false;
		}
	});
	
	$("#deleteUnit").click(function() {
		if(isUserAktif()==false){
			alert(getMessageSessionTimeout());
			return false;
		}else if(isDelete()){
			var dataFromTheRow = jQuery('#grid').jqGrid ('getRowData',  mainId);
			var id = jQuery(dataFromTheRow.id).text();
	
			if(id == ''){
				alert("Silahkan Pilih Unit Id  terlebih Dahulu");
			}else{
				var wannna = confirm("Apakah Anda Akan Menghapus Data Dengan Unit ID : "+ id +" ?");
				 if (wannna== true){
						
						var json = {"id" : id,};
						$.blockUI({ message: '<br>Loading...</br></br>' });
						$.ajax({
							url : getUrl+ "/master/unit/unit_delete.html",
							data : JSON.stringify(json),
							type : "POST",
							beforeSend : function(xhr) {
								xhr.setRequestHeader(	"Accept","application/json");
								xhr.setRequestHeader("Content-Type","application/json");
							},
							success : function(result) {
								if(result==true){
									var respContent = "Unit Dengan id : " +id +" Berhasil Dihapus";
									setNotificationRemove(respContent);
									setMainIdNull();
									gridReset();
								}else if(result==false){
									var respContent = "";
									respContent += "Data Dengan Unit ID  : " + $('#id').val()+" Gagal Di Hapus";
									alert(respContent);
									return false;
								}else{
									generateError(result, 'Unit', $('#id').val());
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
				alert("Silahkan Pilih Unit Id  terlebih Dahulu");
			}else{
				var message=jQuery(dataFromTheRow.isActive).text();
				if(message=='Y'){
					message="Saat Ini Data Unit Id : "+ id +" Dalam Kondisi Aktif,apakah Anda akan Me Non aktifkan nya?";
				}else if(message=='N'){
					message="Saat Ini Data Unit Id : "+ id +" Dalam Kondisi Tidak Aktif,apakah Anda akan Mengaktifkan nya?";
				}
				var wannna = confirm(message);
				 if (wannna== true){
						
						var json = {"id" : id,"version" : version,};
						$.blockUI({ message: '<br>Loading...</br></br>' });
						$.ajax({
							url : getUrl+ "/master/unit/unit_edit_is_active.html",
							data : JSON.stringify(json),
							type : "POST",
							beforeSend : function(xhr) {
								xhr.setRequestHeader(	"Accept","application/json");
								xhr.setRequestHeader("Content-Type","application/json");
							},
							success : function(result) {
								if(result==true){
									var respContent = "Data Dengan Unit Id   : "+ id +" Berhasil Dirubah";
									setNotificationEdit(respContent);
									gridReset();
								}else if(result==false){
									var respContent = "";
									respContent += "Data Dengan Unit ID  : " + id+" Gagal Di Rubah";
									alert(respContent);
									return false;
								}else{
									generateError(result, 'Unit', id);
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
	      url:'get_unit_jqgrid.html',
	      datatype: "json",
	      mtype: 'GET',
	      colNames:[ 'UNIT ID','NAMA','AKTIF','CREATE BY','CREATE DATE','VERSION'],
	      colModel:[
	                {name:'id',index:'id', width:250,editable:true,formatter: fontColorFormatter},
	                {name:'nama',index:'nama', width:250,editable:true,formatter: fontColorFormatter},
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
		var nama = $("#nama").val();
		var isActive = $('input[name=isActive]:checked').val();
		jQuery("#grid").jqGrid('setGridParam',{url:"get_unit_jqgrid.html" +
				"?id="+id+
				"&nama="+nama+
				"&isActive="+isActive}).trigger("reloadGrid", [{current:true}]);
	}
}

function gridReset(){
	if(isUserAktif()==false){
		alert(getMessageSessionTimeout());
		return false;
	}else{
		jQuery("#grid").jqGrid('setGridParam',{url:"get_unit_jqgrid.html"}).trigger("reloadGrid", [{current:true}]);
		$('#filterForm')[0].reset();
		setMainIdNull();
	}
}