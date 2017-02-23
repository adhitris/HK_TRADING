$(document).ready(function() {
	
	$('#selectallUser').click(function(event) {  //on click
        if(this.checked) { // check select status
            $('.user').each(function() { //loop through each checkbox
                this.checked = true;  //select all checkboxes with class "checkbox1"              
            });
        }else{
            $('.user').each(function() { //loop through each checkbox
                this.checked = false; //deselect all checkboxes with class "checkbox1"                      
            });        
        }
    });
	
	$( "#parent" ).combogrid({
		width:"600px",
		url: 'get_parentModule_list.html',
		debug:true,
		okIcon: true,
		showOn: true,
		colModel: [{'columnName':'nama','width':'30','label':'Nama Module','align':'left'}, {'columnName':'module.nama','width':'30','label':'Parent Module','align':'left'}],
		select: function( event, ui ) {
			$( "#parent" ).val( ui.item.nama );
			$( "#parentId" ).val( ui.item.id );
			return false;
		}
	});
	
	$("#editSaveModule").click(function(event){
		var id = $('#id').val();
		var version = $('#version').val();
		var name = $('#nameEdit').val();
		var parentId = $('#parentId').val();
		var path = $('#pathEdit').val();
		var url = $('#url').val();
		var status = $('#status').val();
		var urutan = $('#urutan').val();
		
		var json = {
			"id" : id,
			"version" : version,
			"nama" : name,
			"moduleId" : parentId,
			"path" : path,
			"url" : url,
			"status" : status,
			"urutan" : urutan,
		};

		
		if(isUserAktif()==false){
			alert(getMessageSessionTimeout());
			return false;
		}else if(!isUpdate()){
			alert("Permintaan Tidak Dapat Diproses , Anda Tidak Memiliki Hak Akses 'Edit data'");
			return false;
		}else if(validateName() & validateStatus() & validatePath() & validateUrl() & validateParent()){
			$.blockUI({ message: '<br>Loading...</br></br>' });
			$.ajax({
				url : getUrl+ "/master/module/module_edit_save.html",
				data : JSON.stringify(json),
				type : "POST",
				beforeSend : function(xhr) {
					xhr.setRequestHeader(	"Accept","application/json");
					xhr.setRequestHeader("Content-Type","application/json");
				},
				success : function(result) {
					
					if(result==true){
						var respContent = "Data Dengan Module ID : "+$('#id').val()+" Berhasil Di Rubah";
						setNotificationEdit(respContent);
						$("#dialog1").dialog("close");
						gridReload();
					}else if(result==false){
						var respContent = "";
						respContent += "Module Dengan ID  : " + id +" Gagal Di rubah";
						alert(respContent);
						return false;
					}else{
						generateError(result, 'Module ', id);
					}
				}
			});

			event.preventDefault();
		}else{
			return false;
		}
	
	});
	
	$("#dialog1").dialog({
		open: function(event, ui) {
	        $("input").blur();
	    },
		autoOpen: false,
		height: 520,
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
	
	$("#dialogHakAksesUser").dialog({
		open: function(event, ui) {
	        $("input").blur();
	    },
		autoOpen: false,
		height: 600,
		width: 320,
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
	
	$( "#module" ).combogrid({
		width:"800px",
		url: 'get_module_list.html',
		debug:true,
		okIcon: true,
		showOn: true,
		colModel: [{'columnName':'id','width':'10','label':'ID'}, {'columnName':'name','width':'30','label':'Name'}],
		select: function( event, ui ) {
			$( "#module" ).val( ui.item.name );
			$( "#moduleId" ).val( ui.item.id );
			return false;
		}
	});
	
	jQuery("#grid").jqGrid({
		  autowidth: true,
		  shrinkToFit:true,
	      url:'get_module_jqgrid.html',
	      datatype: "json",
	      mtype: 'GET',
	      colNames:['Name','Sub Parent','Parent','STATUS','Parent ID','Urut', 'Path', 'URL','ACTIVE','Module Id','Create By','Create Date','VERSION'],
	      colModel:[
					{name:'nama',index:'nama',width:250,editable:false,formatter: fontColorFormatter},
					{name:'module.nama',index:'module.nama', editable:false,formatter: fontColorFormatter},
					{name:'parent',index:'parent', editable:false,formatter: fontColorFormatter},
					{name:'status',index:'status', editable:true,formatter: fontColorFormatter},
					{name:'parentId',index:'parentId', editable:false,formatter: fontColorFormatter,hidden:true},
					{name:'urutan',index:'urutan',width:50,editable:true,formatter: fontColorFormatter},
	                {name:'path',index:'path',width:300, editable:false,formatter: fontColorFormatter},
	                {name:'url',index:'url',width:350,editable:false,formatter: fontColorFormatter},
					{name:'isActive',width:80,index:'isActive',editable:true,formatter: fontColorFormatter,hidden:false},
					{name:'id',index:'id',width:300, editable:true,formatter: fontColorFormatter,hidden:false},
					{name:'createBy',index:'createBy',editable:true,formatter: fontColorFormatter},
					{name:'createDate',index:'createDate', editable:true,formatter: fontColorFormatter},
					{name:'version',index:'version', editable:true,formatter: fontColorFormatter,hidden:true}
	           ],
	     jsonReader : {
	          repeatitems:false
	     },
	      rowNum:10,
	      rowList:[10,30,50,100,500],
	      pager: '#gridpager',
	      sortname: 'urutan',
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
	
	$("#updateModule").click(function() {
		if(isUserAktif()==false){
			alert(getMessageSessionTimeout());
			return false;
		}else if(isUpdate()){
			var dataFromTheRow = jQuery('#grid').jqGrid ('getRowData',  mainId);
			var id = jQuery(dataFromTheRow.id).text();
			if(id == ''){
				alert("Silahkan Pilih Module ID terlebih Dahulu");
				//return false;
			}else{
				window.location.href = getUrl+"/master/module/module_edit.html?id="+id;
				setMainIdNull();
			}
			return false;
		}else{
			alert("Permintaan Tidak Dapat Diproses , Anda Tidak Memiliki Hak Akses 'Edit'");
			return false;
		}
	});
	
	$("#deleteModule").click(function() {
		if(isUserAktif()==false){
			alert(getMessageSessionTimeout());
			return false;
		}else if(isDelete()){
			var dataFromTheRow = jQuery('#grid').jqGrid ('getRowData',  mainId);
			var id = jQuery(dataFromTheRow.id).text();
			if(id == ''){
				alert("Silahkan Pilih Module Id  terlebih Dahulu");
			}else{
				var wannna = confirm("Apakah Anda Akan Menghapus Data Dengan Module ID : "+ id +" ?");
				 if (wannna== true){
						
						var json = {"id" : id,};
						$.blockUI({ message: '<br>Loading...</br></br>' });
						$.ajax({
							url : getUrl+ "/master/module/module_delete.html",
							data : JSON.stringify(json),
							type : "POST",
							beforeSend : function(xhr) {
								xhr.setRequestHeader(	"Accept","application/json");
								xhr.setRequestHeader("Content-Type","application/json");
							},
							success : function(result) {
								if(result==true){
									var respContent = "Module Dengan id : " +id +" Berhasil Dihapus";
									setNotificationRemove(respContent);
									gridReset();
									setMainIdNull();
								}else if(result==false){
									var respContent = "";
									respContent += "Module Dengan ID  : " + id +" Gagal Dihapus";
									alert(respContent);
									return false;
								}else{
									if(containsWord(result, "constraint")){
								    	//alert("Data Module Dengan Module Id  : "+id+" Gagal di Hapus Karena Berelasi Dengan Yang Lain ");
								    	var r = confirm("Apakah Anda yakin akan menghapus Module dari semua User ?");
								    	if (r == true) {
								    		var json = {"id" : id,};
											$.blockUI({ message: '<br>Loading...</br></br>' });
											$.ajax({
												url : getUrl+ "/master/module/module_force_delete.html",
												data : JSON.stringify(json),
												type : "POST",
												beforeSend : function(xhr) {
													xhr.setRequestHeader(	"Accept","application/json");
													xhr.setRequestHeader("Content-Type","application/json");
												},
												success : function(result) {
													if(result==true){
														var respContent = "Module Dengan id : " +id +" Berhasil Dihapus";
														setNotificationRemove(respContent);
														gridReset();
														setMainIdNull();
													}else if(result==false){
														var respContent = "";
														respContent += "Module Dengan ID  : " + id +" Gagal Dihapus";
														alert(respContent);
														return false;
													}else{
														generateError(result,'Module',id);
													}
													
												}
											});
								    	}
								    }
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
				alert("Silahkan Pilih Module Id  terlebih Dahulu");
			}else{
				var message=jQuery(dataFromTheRow.isActive).text();
				if(message=='Y'){
					message="Saat Ini Data Module Id : "+ id +" Dalam Kondisi Aktif,apakah Anda akan Me Non aktifkan nya?";
				}else if(message=='N'){
					message="Saat Ini Data Module Id : "+ id +" Dalam Kondisi Tidak Aktif,apakah Anda akan Mengaktifkan nya?";
				}
				var wannna = confirm(message);
				 if (wannna== true){
						
						var json = {"id" : id,"version" : version,};
						$.blockUI({ message: '<br>Loading...</br></br>' });
						$.ajax({
							url : getUrl+ "/master/module/module_edit_is_active.html",
							data : JSON.stringify(json),
							type : "POST",
							beforeSend : function(xhr) {
								xhr.setRequestHeader(	"Accept","application/json");
								xhr.setRequestHeader("Content-Type","application/json");
							},
							success : function(result) {
								if(result==true){
									var respContent = "Data Dengan Module Id   : "+ dataFromTheRow.id +" Berhasil Dirubah";
									setNotificationEdit(respContent);
									gridReset();
								}else if(result==false){
									var respContent = "";
									respContent += "Module Dengan ID  : " + id +" Gagal Dirubah";
									alert(respContent);
									return false;
								}else{
									generateError(result, 'Module ', id);
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
	
	$("#editSaveHakAksesUser").click(function(event){
		var formData = $("#hak_akses_user_edit").serialize();
		var moduleId = mainId;

		var userArray = new Array();
		$("input[name='userArray[]']:checked").each(function(){
			userArray.push($(this).val());
		});
		
		var json = {
			"moduleId" : moduleId,
			"userArray" : userArray,
		};

		
		if(isUserAktif()==false){
			alert(getMessageSessionTimeout());
			return false;
		}else if(!isUpdate()){
			alert("Permintaan Tidak Dapat Diproses , Anda Tidak Memiliki Hak Akses 'Edit data'");
			return false;
		}else{
			$.blockUI({ message: '<br>Loading...</br></br>' });
			$.ajax({
				url : getUrl+ "/master/module/access_user_edit_save.html",
				data : JSON.stringify(json),
				type : "POST",
				beforeSend : function(xhr) {
					xhr.setRequestHeader(	"Accept","application/json");
					xhr.setRequestHeader("Content-Type","application/json");
				},
				success : function(result) {
					if(result==true){
						var respContent = "Hak Akses User Berhasil Diubah";
						setNotificationEdit(respContent);
						$("#dialogHakAksesUser").dialog("close");
						gridReload();
					}else if(result==false){
						var respContent = "";
						respContent += "Hak Akses User Gagal Diubah";
						alert(respContent);
						return false;
					}else{
						generateError(result, 'Hak Akses User ', id);
					}
				}
			});

			event.preventDefault();
		}
		
		return false;
	
	});
});

function gridReload(){
	if(isUserAktif()==false){
		alert(getMessageSessionTimeout());
		return false;
	}else{
	var name = jQuery("#name").val();
	var path = jQuery("#path").val();
	var createDate = jQuery("#createDate").val();
	jQuery("#grid").jqGrid('setGridParam',{url:"get_module_jqgrid.html?searchTerm1="+name+"&searchTerm2="+path+"&searchTerm3="+createDate}).trigger("reloadGrid", [{current:true}]);

	}
}

function gridReset(){
	if(isUserAktif()==false){
		alert(getMessageSessionTimeout());
		return false;
	}else{
		jQuery("#grid").jqGrid('setGridParam',{url:"get_module_jqgrid.html"}).trigger("reloadGrid", [{current:true}]);
		$('#filterForm')[0].reset();
	}
}

function updateModule(){
	if(isUserAktif()==false){
		alert(getMessageSessionTimeout());
		return false;
	}else if(!isUpdate()){
		alert("Permintaan Tidak Dapat Diproses , Anda Tidak Memiliki Hak Akses 'Edit'");
		return false;
	}else if(mainId==''){
		alert("Silahkan Pilih Module Terlebih Dahulu");
	}else{
		$(function() {
			var dataFromTheRow = jQuery('#grid').jqGrid ('getRowData',  mainId);
			var nama = jQuery(dataFromTheRow.nama).text();
			var status = jQuery(dataFromTheRow.status).text();
			var statusSelect = document.getElementById("status");
			var parent = jQuery(dataFromTheRow.parent).text();
			var parentId = jQuery(dataFromTheRow.parentId).text();
			var path = jQuery(dataFromTheRow.path).text();
			var url = jQuery(dataFromTheRow.url).text();
			var urutan = jQuery(dataFromTheRow.urutan).text();
			var version = jQuery(dataFromTheRow.version).text();
			$("#dialog1").dialog("open");
			$("#id").val(mainId);
			$("#nameEdit").val(nama);
			$("#status").val(status);
			setSelectedValue(statusSelect, status);
			$("#parent").val(parent);
			$("#parentId").val(parentId);
			$("#pathEdit").val(path);
			$("#url").val(url);
			$("#urutan").val(urutan);
			$("#version").val(version);
			$("#title1").html("Edit Module Untuk Module : " + mainId);
		});
		
		return false;
	}
}

function setSelectedValue(selectObj, valueToSet) {
    for (var i = 0; i < selectObj.options.length; i++) {
        if (selectObj.options[i].text== valueToSet) {
            selectObj.options[i].selected = true;
            return;
        }
    }
}

function validateName(){
	
	var validate=$("#nameEdit").val().length;
	if(validate < 1){
		setNotificationRemove("Nama Module Harus Diisi");
		$("#nameEdit").addClass("error");
		return false;
	}
	//if it's valid
	else{
		return true;
	}
}

function validateStatus(){
	var validate=$("#status").val().length;
	if(validate < 1){
		setNotificationRemove("Status Harus Diisi");
		return false;
	}
	//if it's valid
	else{
		$("#status").removeClass("error");
		return true;
	}
}


function validatePath(){
	if($("#status").val()=='1'){
		var validate=$("#pathEdit").val().length;
		if(validate < 1){
			setNotificationRemove("Path Harus Diisi");
			$("#pathEdit").addClass("error");
			return false;
		}
		//if it's valid
		else{
			$("#pathEdit").removeClass("error");
			return true;
		}
	}else{
		return true;
	}
}

function validateUrl(){
	if($("#status").val()=='1'){
		var validate=$("#url").val().length;
		if(validate < 1){
			setNotificationRemove("Url Harus Diisi");
			$("#url").addClass("error");
			return false;
		}
		//if it's valid
		else{
			$("#url").removeClass("error");
			return true;
		}
	}else{
		return true;
	}
	
}

function validateParent(){
	if($("#status").val()=='1'){
		if($("#parentId").val()==""){
			setNotificationRemove("Parent Harus Diisi");
			$("#parent").addClass("error");
			return false;
		}
		//if it's valid
		else{
			$("#parent").removeClass("error");
			return true;
		}
	}else{
		return true;
	}
	
}

function editHakAksesUser(){
	$("#dialogHakAksesUser").dialog('open');
	
	var dataFromTheRow = jQuery('#grid').jqGrid ('getRowData',  mainId);
	var nama = jQuery(dataFromTheRow.nama).text();
	$("#titleHakAksesUser").html('Edit Hak Akses Untuk Module : '+nama);
	
	$.blockUI({ message: '<br>Loading...</br></br>' });
	$.ajax({
		url:'get_user_accessuser_by_module.html?moduleId='+mainId,
		success:function(result){
			var tm = JSON.parse(result);
		
			for(var i = 0; i < tm.length; i++) {
			    var obj = tm[i];
			    var bg='';
			    if(i%2==0){
			    	bg='#F5F1F0';
			    }else{
			    	bg='#dce9f9';
			    	
			    }
			    
			    var checked = "";
			    
			    if(obj.check){
			    	checked = "checked";
			    }
			    
			    	$("<tr bgColor='"+bg+"' id='trItem"+ i +"' style='text-align:center'>" +
						"<td height='10px'> <input type='checkbox' name='userArray[]' class='user' value='"+obj.userId+"' "+checked+"></td>" +
				    	"<td height='10px'>" +
							obj.userId +
				    	"</td>" +
			    	"</tr>")
					.appendTo("#tableListUser");
			   
			}
		}
	});
}