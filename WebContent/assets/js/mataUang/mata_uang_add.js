
var hasil = false;
$(document).ready(
		function() {
			var form = $("#mata_uang_new");

			form.submit(function(event) {
				if (validateId()) {
					$.blockUI({ message: '<br>Loading...</br></br>' }); 
					var id = $('#id').val();
					var version = $('#version').val();
					
					var json = {
						"id" : id,
						"version" : version,
						
						
					};
					if (document.mata_uang_new.hiddenId.value == "") {
						if(isUserAktif()==false){
							alert(getMessageSessionTimeout());
							return false;
						}else if(isCreate()){
							$.blockUI({ message: '<br>Loading...</br></br>' });
							$.ajax({
								url : getUrl+ "/master/mataUang/mata_uang_add_save.html",
								data : JSON.stringify(json),
								type : "POST",
								beforeSend : function(xhr) {
									xhr.setRequestHeader(	"Accept","application/json");
									xhr.setRequestHeader("Content-Type","application/json");
								},
								success : function(result) {
									
									if(result){
										var respContent = "";
										respContent += "Data Dengan Mata Uang ID  : " + $('#id').val()+" Berhasil Ditambahkan";
										
										setNotificationAdd(respContent);
										$('#mata_uang_new')[0].reset();
									}else if(result==false){
										var respContent = "";
										respContent += "Mata Uang Dengan ID  : " + id +" Gagal Ditambahkan";
										alert(respContent);
										return false;
									}else{
										generateError(result, 'Mata Uang ', id);
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
								url : getUrl+ "/master/mataUang/mata_uang_edit_save.html",
								data : JSON.stringify(json),
								type : "POST",
								beforeSend : function(xhr) {
									xhr.setRequestHeader(	"Accept","application/json");
									xhr.setRequestHeader("Content-Type","application/json");
								},
								success : function(result) {
									
									if(result==true){
										var respContent = "Data Dengan Mata Uang ID : "+$('#id').val()+" Berhasil Di Rubah";
										setNotificationEdit(respContent);
									}else if(result==false){
										var respContent = "";
										respContent += "Mata Uang Dengan ID  : " + id +" Gagal Di rubah";
										alert(respContent);
										return false;
									}else{
										generateError(result, 'Mata Uang ', id);
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
		$("#idInfo").text("Mata Uang Id Wajib Diisi");
		$("#idInfo").addClass("error");
		return false;
	} else {
		$("#idInfo").addClass("error");
		$("#idInfo").text("");
		return true;
	}
}




function validateId() {
	if(document.mata_uang_new.hiddenId.value == ""){
		if($("#id").val().length<1){
			if ($("#id").val().length < 1) {
				$("#idInfo").text("Mata Uang Id Wajib Diisi");
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
			mataUangDWR.checkIdMataUang(kode, {
				callback : function(result) {
					if (result & ($("#id").val() != '')) {
						$("#idInfo").text("Error !! Mata Uang ID Ready Exist");
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
