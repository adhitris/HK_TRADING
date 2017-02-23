var hasil=false;
$(document).ready(function(){
	
	$("#pegawaiId").combogrid({
		width:"600px",
		url: 'get_pegawai_list.html',
		debug:true,
		okIcon: true,
		showOn: true,
		colModel: [{'columnName':'id','width':'30','label':'PEGAWAI ID','align':'left'}],
		select: function( event, ui ) {
			$("#pegawaiId").val( ui.item.id );
			return false;
		}
	});
	
	$('#selectall').click(function(event) {  //on click
        if(this.checked) { // check select status
            $('.gudang').each(function() { //loop through each checkbox
                this.checked = true;  //select all checkboxes with class "checkbox1"              
            });
        }else{
            $('.gudang').each(function() { //loop through each checkbox
                this.checked = false; //deselect all checkboxes with class "checkbox1"                      
            });        
        }
    });
	
	$('#selectallKas').click(function(event) {  //on click
        if(this.checked) { // check select status
            $('.kasBank').each(function() { //loop through each checkbox
                this.checked = true;  //select all checkboxes with class "checkbox1"              
            });
        }else{
            $('.kasBank').each(function() { //loop through each checkbox
                this.checked = false; //deselect all checkboxes with class "checkbox1"                      
            });        
        }
    });
	
	var a=0; $( "#tabs" ).tabs({selected: a});
	var form = $("#user_new");

	form.submit(function(event) {
		if (validateFirstName() & validateId()) {
			
			var id = $('#id').val();
			var nama = $('#nama').val();
			var pegawaiId = $('#pegawaiId').val();
			var nama = $('#nama').val();
			var version = $('#version').val();
			var mruLimit = $('#mruLimit').val();
			var isCreate =($("#isCreate").is(':checked') ? 1 : '');
			var isUpdate =($("#isUpdate").is(':checked') ? 1 : '');
			var isDelete=($("#isDelete").is(':checked') ? 1 : '');
			var isCancel=($("#isCancel").is(':checked') ? 1 : '');
			var isPrint=($("#isPrint").is(':checked') ? 1 : '');
			var isReport=($("#isReport").is(':checked') ? 1 : '');
			var isConfirm=($("#isConfirm").is(':checked') ? 1 : '');
			var isUnconfirm=($("#isUnconfirm").is(':checked') ? 1 : '');
			var isSupervisor=($("#isSupervisor").is(':checked') ? 1 : '');
			var isSuperuser=($("#isSuperuser").is(':checked') ? 1 : '');
			var isReprint=($("#isReprint").is(':checked') ? 1 : '');
			
			if(isCreate==''){isCreate=false;}else{isCreate=true;}
			if(isUpdate==''){isUpdate=false;}else{isUpdate=true;}
			if(isDelete==''){isDelete=false;}else{isDelete=true;}
			if(isCancel==''){isCancel=false;}else{isCancel=true;}
			if(isPrint==''){isPrint=false;}else{isPrint=true;}
			if(isReport==''){isReport=false;}else{isReport=true;}
			if(isConfirm==''){isConfirm=false;}else{isConfirm=true;}
			if(isUnconfirm==''){isUnconfirm=false;}else{isUnconfirm=true;}
			if(isSupervisor==''){isSupervisor=false;}else{isSupervisor=true;}
			if(isSuperuser==''){isSuperuser=false;}else{isSuperuser=true;}
			if(isReprint==''){isReprint=false;}else{isReprint=true;}
			
			var userRoleArray = new Array();
			$("input[name='role[]']:checked").each(function(){
				userRoleArray.push($(this).val());
			});
			
			var userGudangArray = new Array();
			$("input[name='gudang[]']:checked").each(function(){
				userGudangArray.push($(this).val());
			});
			
			var userKasBankArray = new Array();
			$("input[name='kasBank[]']:checked").each(function(){
				userKasBankArray.push($(this).val());
			});
			
	
			var json = {
				"id" : id,
				"nama" : nama,
				"pegawaiId":pegawaiId,
				"mruLimit":mruLimit,
				"isCreate" : isCreate,
				"isUpdate" : isUpdate,
				"isDelete" : isDelete,
				"isCancel" : isCancel,
				"isPrint" : isPrint,
				"isReport" : isReport,
				"isConfirm" : isConfirm,
				"isUnconfirm" : isUnconfirm,
				"isSupervisor" : isSupervisor,
				"isSuperuser" : isSuperuser,
				"isReprint" : isReprint,
				"userRoleArray":userRoleArray,
				"userGudangArray":userGudangArray,
				"userKasBankArray":userKasBankArray,
				"version":version,
				
			};
			if (document.user_new.hiddenId.value == "") {
				if(isUserAktif()==false){
					alert(getMessageSessionTimeout());
					return false;
				}else if(isCreateOveride()){
					$.blockUI({ message: '<br>Loading...</br></br>' });
					$.ajax({
						url : getUrl+ "/master/user/user_add_save.html",
						data : JSON.stringify(json),
						type : "POST",
						beforeSend : function(xhr) {
							xhr.setRequestHeader(	"Accept","application/json");
							xhr.setRequestHeader("Content-Type","application/json");
						},
						
						success : function(result) {
							if(result==true){
								var respContent =  "Data Dengan User Id : "+id+" Berhasil Di Tambah";
								
								setNotificationAdd(respContent);
								$('#user_new')[0].reset();
							}else if(result==false){
								var respContent = "";
								respContent += "Data Dengan User ID  : " + $('#id').val()+" Gagal Di tambahkan";
								alert(respContent);
								return false;
							}else{
								generateError(result, 'User', $('#id').val());
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
				}else if(isUpdateOveride()){
					$.blockUI({ message: '<br>Loading...</br></br>' });
					$.ajax({
						url : getUrl+ "/master/user/user_edit_save.html",
						data : JSON.stringify(json),
						type : "POST",
						beforeSend : function(xhr) {
							xhr.setRequestHeader(	"Accept","application/json");
							xhr.setRequestHeader("Content-Type","application/json");
						},
						success : function(result) {
							if(result==true){
								var respContent =  "Data Dengan User Id : "+id+" Berhasil Di Rubah";
								
								setNotificationEdit(respContent);
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
	
});

function validateNip(){
	var nip=$("#nip").val().length;
	if(nip < 1){
		$("#nipInfo").text("NIP Harus Diisi");
		$("#nipInfo").addClass("error");
		return false;
	}else{
		$("#nipInfo").addClass("error");
		$("#nipInfo").text("");
		return true;
	}
}

function validateIdEmpty(){
	var id=$("#id").val().length;
	if(id < 1){
		$("#idInfo").text("Id Harus Diisi");
		$("#idInfo").addClass("error");
		return false;
	}else{
		$("#idInfo").addClass("error");
		$("#idInfo").text("");
		return true;
	}
}

function validatePassword(){
	var password=$("#password").val().length;
	if(password < 1){
		$("#passwordInfo").text("Password Wajib Diisi");
		$("#passwordInfo").addClass("error");
		return false;
	}else{
		$("#passwordInfo").addClass("error");
		$("#passwordInfo").text("");
		return true;
	}
}

function validateFirstName(){
	var nama=$("#nama").val().length;
	if(nama < 1){
		$("#namaInfo").text("Nama Harus Diisi");
		$("#namaInfo").addClass("error");
		return false;
	}else{
		$("#namaInfo").addClass("error");
		$("#namaInfo").text("");
		return true;
	}
}

function validateId(){
	if(document.user_new.hiddenId.value == ""){
		if($("#id").val().length<1){
			if ($("#id").val().length < 1) {
				$("#idInfo").text("User Id Wajib Diisi");
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
			var id=$("#id").val();
			userDWR.checkId(id,
					{
						callback:function(result){
							if(result & ($("#id").val()!='')){
								$("#idInfo").text("Error !! User Id Ready Exist");
								$("#idInfo").addClass("error");
								hasil=false;
								return false;
							}else if($("#id").val()!=''){
								hasil=true;
								$("#idInfo").addClass("error");
								$("#idInfo").text("");
								return true;
							}
						},erroHandler:function(msg){
							alert(msg);
							hasil=false;
							r=false;
						}
					});
		}
	}else{

		hasil=true;
	}
		return hasil;
}

function isUpdateOveride(){
	var isUpdate=null;
	$.ajax({
		url:getUrl+'/akses_user/is_update.html',
		async: false,
		beforeSend : function(xhr) {
			xhr.setRequestHeader(	"Accept","application/json");
			xhr.setRequestHeader("Content-Type","application/json");
		},
		success:function(result){
			if(result==true){
				isUpdate=true;
			}else{
				isUpdate=false;
			}
		}
	});
	
	return isUpdate;
}

function isCreateOveride(){
	var isCreate=null;
	$.ajax({
		url:getUrl+'/akses_user/is_create.html',
		async: false,
		beforeSend : function(xhr) {
			xhr.setRequestHeader(	"Accept","application/json");
			xhr.setRequestHeader("Content-Type","application/json");
		},
		success:function(result){
			if(result==true){
				isCreate=true;
			}else{
				isCreate=false;
			}
		}
	});
	
	return isCreate;
}