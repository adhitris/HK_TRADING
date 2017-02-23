
var hasil = false;
$(document).ready(
		function() {
			var form = $("#unit_new");
			
			
			form.submit(function(event) {
				if (validateId()  & validateNama()) {
					$.blockUI({ message: '<br>Loading...</br></br>' }); 
					var id = $('#id').val();
					var nama = $('#nama').val();
					var version = $('#version').val();
					
					var json = {
						"id" : id,
						"nama" : nama,
						"version" : version,
						
						
					};
					if (document.unit_new.hiddenId.value == "") {
						if(isUserAktif()==false){
							alert(getMessageSessionTimeout());
							return false;
						}else if(isCreate()){
							$.blockUI({ message: '<br>Loading...</br></br>' });
							$.ajax({
								url : getUrl+ "/master/unit/unit_add_save.html",
								data : JSON.stringify(json),
								type : "POST",
								beforeSend : function(xhr) {
									xhr.setRequestHeader(	"Accept","application/json");
									xhr.setRequestHeader("Content-Type","application/json");
								},
								success : function(result) {
									
									if(result==true){
										var respContent = "";
										respContent += "Data Dengan Unit ID  : " + $('#id').val()+" Berhasil Ditambahkan";
										
										setNotificationAdd(respContent);
										$('#unit_new')[0].reset();
									}else if(result==false){
										var respContent = "";
										respContent += "Data Dengan Unit ID  : " + $('#id').val()+" Gagal Di Ditambahkan";
										alert(respContent);
										return false;
									}else{
										generateError(result, 'Unit', $('#id').val());
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
								url : getUrl+ "/master/unit/unit_edit_save.html",
								data : JSON.stringify(json),
								type : "POST",
								beforeSend : function(xhr) {
									xhr.setRequestHeader(	"Accept","application/json");
									xhr.setRequestHeader("Content-Type","application/json");
								},
								success : function(result) {
									
									if(result==true){
										var respContent = "Data Dengan Unit ID : "+$('#id').val()+" Berhasil Di Rubah";
										
										setNotificationEdit(respContent);
									}else if(result==false){
										var respContent = "";
										respContent += "Data Dengan Unit ID  : " + $('#id').val()+" Gagal Di Rubah";
										alert(respContent);
										return false;
									}else{
										generateError(result, 'Unit', $('#id').val());
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



function validateIdEmpty() {
	var kode = $("#id").val().length;
	if (kode < 1) {
		$("#idInfo").text("Unit Id Wajib Diisi");
		$("#idInfo").addClass("error");
		return false;
	} else {
		$("#idInfo").addClass("error");
		$("#idInfo").text("");
		return true;
	}
}


function validateNama() {
	var nama = $("#nama").val().length;
	if (nama < 1) {
		$("#namaInfo").text("Nama Harus Diisi");
		$("#namaInfo").addClass("error");
		return false;
	} else {
		$("#namaInfo").addClass("error");
		$("#namaInfo").text("");
		return true;
	}
}




function validateId() {
	if(document.unit_new.hiddenId.value == ""){
		if($("#id").val().length<1){
			if ($("#id").val().length < 1) {
				$("#idInfo").text("Unit Id Wajib Diisi");
				$("#idInfo").addClass("error");
				return false;
			} else {
				$("#idInfo").addClass("error");
				$("#idInfo").text("");
				return true;
			
			}
		}else if($("#id").val().match(/[^A-Za-z0-9\-_./]/)){
			$("#idInfo").text("Unit Id Tidak Valid,hanya boleh menggunakan character A-Z, a-z, 0-9, -,_,/ dan .");
			$("#idInfo").addClass("error");
			return false;
		}else{
			var kode = $("#id").val();
			unitDWR.checkIdUnit(kode, {
				callback : function(result) {
					if (result & ($("#id").val() != '')) {
						$("#idInfo").text("Error !! Unit ID Ready Exist");
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
