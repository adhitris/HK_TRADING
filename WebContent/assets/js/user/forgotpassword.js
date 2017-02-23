
$(document).ready(function(){
	//global vars

	var form = $("#forgotpassword");
	var email = $(".email");
	var emailinfo = $("#emailinfo");

	
	email.blur(validateemaillegth);
	email.keyup(validateemaillegth);
	email.change(validateemaillegth);
	
	email.blur(validateemailformat);
	email.keyup(validateemailformat);
	email.change(validateemailformat);
	

	
	form.submit(function(){
		if(validateemaillegth() && validateemailformat()){
				
			return true;
		}else{
		
			return false;
		}
	});
	
	function validateemaillegth(){
		var panjangname=email.val().length;
		if(panjangname <1){
			emailinfo.text("Please Insert Your Email");
			emailinfo.addClass("error");
			return false;
		}
		//if it's valid
		else{
			emailinfo.text("");
			emailinfo.removeClass("error");
			validateemailformat();
			return true;
		}
	}
	
	function validateemailformat(){
		
		var emailval = email.val();
		var filter = /^[a-zA-Z0-9]+[a-zA-Z0-9_.-]+[a-zA-Z0-9_-]+@[a-zA-Z0-9]+[a-zA-Z0-9.-]+[a-zA-Z0-9]+.[a-z]{2,4}$/;
		if(filter.test(emailval)){
			emailinfo.text("");
			emailinfo.removeClass("error");
			return true;
		}
		//if it's valid
		else{
			
			emailinfo.text("Your Email Not Valid");
			emailinfo.addClass("error");
			return false;
		}
	}

	
});

