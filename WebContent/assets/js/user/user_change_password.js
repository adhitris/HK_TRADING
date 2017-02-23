$(document).ready(function(){

	//global vars
	var form = $("#mainform");

	var password = $("#password");
	var passwordinfo = $("#passwordinfo");
	
	var retypepassword = $("#retypepassword");
	var retypepasswordinfo = $("#retypepasswordinfo");
	
	password.blur(validatepassword);
	password.keyup(validatepassword);
	password.change(validatepassword);
	
	retypepassword.blur(validateretypepassword);
	retypepassword.keyup(validateretypepassword);
	retypepassword.change(validateretypepassword);
	
	form.submit(function(){
		if(validatepassword() & validateretypepassword()){
			document.mainform.action = getUrl+"/master/user/user_change_password_save.html";
			//document.mainform.submit();
			return true;
		} else {
			return false;
		}
	});
	
	function validatepassword(){
		var panjangpassword=password.val().length;
		if(panjangpassword < 6){
			passwordinfo.text("This Field Required");
			passwordinfo.addClass("error");

			return false;
		} else{
			passwordinfo.text("");
			passwordinfo.removeClass("error");
		
			return true;
		}
	}
	
	function validateretypepassword(){
		//testing regular expression
		var pass2 = retypepassword.val();
		var passnull = retypepassword.val().length;
		
		//if it's valid password
		if(pass2 != password.val()){
			retypepasswordinfo.addClass("error");
			retypepasswordinfo.show();
			retypepasswordinfo.text("Please insert same password as above");
			return false;
			
		} else if(passnull < 1){
			retypepasswordinfo.text("This Field Required");
			retypepasswordinfo.addClass("error");

			return false;
		}
		//if it's NOT valid
		else{
			retypepasswordinfo.text("");
			retypepasswordinfo.hide();
			return true;
		}
	}
	
	
	
});
