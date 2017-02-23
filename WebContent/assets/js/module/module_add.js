
var hasil=null;
$(document).ready(function(){
	
	var name = $("#name");
	var nameinfo = $("#nameinfo");
			
	name.blur(validateName);
	name.keyup(validateName);
	name.change(validateName);
	
	var status = $("#status");
	var statusinfo = $("#statusinfo");
			
	status.blur(validateStatus);
	status.change(validateStatus);
	
	var path = $("#path");
	var pathinfo = $("#pathinfo");
			
	path.blur(validatePath);
	path.keyup(validatePath);
	path.change(validatePath);
	
	var url = $("#url");
	var urlinfo = $("#urlinfo");
			
	url.blur(validateUrl);
	url.keyup(validateUrl);
	url.change(validateUrl);
	
	$( "#parent" ).combogrid({
		width:"800px",
		url: 'get_parentModule_list.html',
		debug:true,
		okIcon: true,
		showOn: true,
		colModel: [{'columnName':'nama','width':'60','label':'Nama Module'}, {'columnName':'module.nama','width':'30','label':'Parent Module'}],
		select: function( event, ui ) {
			$( "#parent" ).val( ui.item.nama );
			$( "#parentId" ).val( ui.item.id );
			return false;
		}
	});
	
	var form = $("#module_new");
		
	form.submit(function(event){
		if ( validateId() & validateName() & validateStatus() & validatePath() & validateUrl() & validateParent()) {
			var id = $('#id').val();
			var version = $('#version').val();
			var name = $('#name').val();
			var parentId = $('#parentId').val();
			var path = $('#path').val();
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
			if (document.module_new.hiddenId.value == "") {
				if(isUserAktif()==false){
					alert(getMessageSessionTimeout());
					return false;
				}else if(isCreate()){
					$.blockUI({ message: '<br>Loading...</br></br>' });
					$.ajax({
						url : getUrl+ "/master/module/module_add_save.html",
						data : JSON.stringify(json),
						type : "POST",
						beforeSend : function(xhr) {
							xhr.setRequestHeader(	"Accept","application/json");
							xhr.setRequestHeader("Content-Type","application/json");
						},
						success : function(result) {
							
							if(result){
								var respContent = "";
								respContent += "Data Dengan Module ID  : " + $('#id').val()+" Berhasil Ditambahkan";
								
								setNotificationAdd(respContent);
								$('#module_new')[0].reset();
							}else if(result==false){
								var respContent = "";
								respContent += "Module Dengan ID  : " + id +" Gagal Ditambahkan";
								alert(respContent);
								return false;
							}else{
								generateError(result, 'Module ', id);
							}
						}
					});
	
					event.preventDefault();
				}else{
					alert("Permintaan Tidak Dapat Diproses , Anda Tidak Memiliki Hak Akses 'Tambah'");
					return false;
				}
			/*UPDATE*/
			}else{
				
				if(isUserAktif()==false){
					alert(getMessageSessionTimeout());
					return false;
				}else if(isUpdate()){
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
					alert("Permintaan Tidak Dapat Diproses , Anda Tidak Memiliki Hak Akses 'Edit'");
					return false;
				}
			}
			
			return true;
		}else{
			return false;
		}
	});
	
	function validateName(){
		
		var validate=name.val().length;
		if(validate < 1){
			nameinfo.text("Nama Modul Harus Diisi");
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
	
	function validateStatus(){
		var validate=status.val().length;
		if(validate < 1){
			statusinfo.text("This Field Required");
			statusinfo.addClass("error");
			return false;
		}
		//if it's valid
		else{
			statusinfo.addClass("error");
			statusinfo.text("");
			return true;
		}
	}
	
	
	function validatePath(){
		if(status.val()=='1'){
			var validate=path.val().length;
			if(validate < 1){
				pathinfo.text("Path Harus Disi");
				pathinfo.addClass("error");
				return false;
			}
			//if it's valid
			else{
				pathinfo.addClass("error");
				pathinfo.text("");
				return true;
			}
		}else{
			pathinfo.addClass("error");
			pathinfo.text("");
			return true;
		}
	}
	
	function validateUrl(){
		if(status.val()=='1'){
			var validate=url.val().length;
			if(validate < 1){
				urlinfo.text("URL Harus Diisi");
				urlinfo.addClass("error");
				return false;
			}
			//if it's valid
			else{
				urlinfo.addClass("error");
				urlinfo.text("");
				return true;
			}
		}else{
			urlinfo.addClass("error");
			urlinfo.text("");
			return true;
		}
		
	}
	
	function validateParent(){
		if(status.val()=='1'){
			if($("#parentId").val()==""){
				$("#parentInfo").text("Parent Harus Diisi");
				$("#parentInfo").addClass("error");
				return false;
			}
			//if it's valid
			else{
				$("#parentInfo").addClass("error");
				$("#parentInfo").text("");
				return true;
			}
		}else{
			$("#parentInfo").addClass("error");
			$("#parentInfo").text("");
			return true;
		}
		
	}
	
	
	
});



function validateId() {
	if(document.module_new.hiddenId.value == ""){
		if($("#id").val().length<1){
			if ($("#id").val().length < 1) {
				$("#idInfo").text("Module Id Wajib Diisi");
				$("#idInfo").addClass("error");
				return false;
			} else {
				$("#idInfo").addClass("error");
				$("#idInfo").text("");
				return true;
			
			}
		}else if($("#id").val().match(/[^A-Za-z0-9\-_./]/)){
			$("#idInfo").text("Error,hanya boleh menggunakan character A-Z, a-z, 0-9, -,_,/ dan .");
			$("#idInfo").addClass("error");
			return false;
		}else{
			var kode = $("#id").val();
			moduleDWR.checkIdModule(kode, {
				callback : function(result) {
					if (result & ($("#id").val() != '')) {
						$("#idInfo").text("Error !! Module ID Ready Exist");
						$("#idInfo").addClass("error");
						hasil = false;
						return false;
					} else if ($("#id").val() != '') {
						hasil = true;
						$("#idInfo").addClass("error");
						$("#idInfo").text("");
						return true;
					}
				},
				erroHandler : function(msg) {
					alert(msg);
					hasil = false;
					r = false;
				}
			});
		}
		
	}else{
		hasil=true;
	}
	return hasil;
}
