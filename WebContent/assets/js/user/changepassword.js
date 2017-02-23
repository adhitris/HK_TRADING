

$(document).ready(function(){
	//global vars
	var form = $("#new_pass");
	
	var currentpassword = $("#currentpassword");
	var currentpasswordinfo = $("#currentpasswordinfo");
	
	var newpassword = $("#newPassword");
	var newpasswordinfo = $("#newPasswordInfo");
	
	var retypenewpassword = $("#confirmNewPassword");
	var retypenewpasswordinfo = $("#confirmNewPasswordInfo");

	
	currentpassword.blur(validatePasswordLama);
	
	newpassword.blur(validatenewpassword);
	
	retypenewpassword.blur(validateretypenewpassword);
	
	

	form.submit(function(event){
		if(validatePasswordLama() & validatenewpassword() & validateretypenewpassword()){
			var id = $('#userId').val();
			var password = $('#confirmNewPassword').val();
		
	
			var json = {
				"id" : id,
				"password" : password,
				
			};
			$.blockUI({ message: '<br>Loading...</br></br>' });
			$.ajax({
				url : getUrl+"/master/user/change_password_save.html",
				data : JSON.stringify(json),
				type : "POST",
				beforeSend : function(xhr) {
					xhr.setRequestHeader(	"Accept","application/json");
					xhr.setRequestHeader("Content-Type","application/json");
				},
				success : function(result) {
					if(result==true){
						var respContent =  "Password Anda berhasil Dirubah,Silahkan Logout dan login kembali";
						setNotificationEdit(respContent);
					}else if(result==false){
						var respContent = "";
						respContent += "Password Gagal Dirubah";
						alert(respContent);
						return false;
					}else{
						generateError(result, 'User', $('#id').val());
					}
				}
			});

			event.preventDefault();
			return true;
		}else{
				
			return false;
		}
	});
	
	
	function validatecurrentpassword(){
		var panjangname=currentpassword.val().length;
		if(panjangname <3){
			currentpasswordinfo.text("Field Ini Wajib Diisi,panjang Minimal 3 karakter");
			currentpasswordinfo.addClass("error");

			return false;
		}
		//if it's valid
		else{
			currentpasswordinfo.text("");
			currentpasswordinfo.removeClass("error");
		
			return true;
		}
	}
	
	function validatenewpassword(){
			var newpasswordlength=newpassword.val().length;
			if(newpasswordlength <3){
				newpasswordinfo.text("Field Ini Wajib Diisi,panjang Minimal 3 karakter");
				newpasswordinfo.addClass("error");

			return false;
			}
			//if it's valid
			else{
				newpasswordinfo.text("");
				newpasswordinfo.removeClass("error");
			
				return true;
			}
	}
	
	function validateretypenewpassword(){
		//testing regular expression
		var pass2 = newpassword.val();
		
		//if it's valid email
		if(pass2 != retypenewpassword.val()){
			retypenewpasswordinfo.text("Password Baru Yang Anda Masukan Tidak Sama!!");
			retypenewpasswordinfo.addClass("error");

		return false;
			
		}
		//if it's NOT valid
		else{
			retypenewpasswordinfo.text("");
			retypenewpasswordinfo.removeClass("error");
			return true;
		}
	}
	
	function validatePasswordLama(){
		var isAvailable=null;
		var password = $("#currentpassword").val();
		var userId = $("#userId").val();
		$.ajax({
			url:'check_old_password.html?password='+password+'&userId='+userId,
			async: false,
			beforeSend : function(xhr) {
				xhr.setRequestHeader(	"Accept","application/json");
				xhr.setRequestHeader("Content-Type","application/json");
			},
			success:function(result){
				if(result==false){
					$("#currentpasswordinfo").text("Error !! Password Lama Tidak Sesuai !");
					$("#currentpasswordinfo").addClass("error");
					isAvailable=false;
				}else{
					$("#currentpasswordinfo").addClass("error");
					$("#currentpasswordinfo").text("");
					isAvailable=true;
				}
			}
		});
		return isAvailable;
	}

	
});

