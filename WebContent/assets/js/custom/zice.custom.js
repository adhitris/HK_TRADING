var getUrl = "";
var mainId="";
var subMainId="";
var subSubMainId="";
var subSubSubMainId="";

// options cookie 10 days
var options = {
	path : '/',
	expires : 10
};


$(document).ready(function() {
					//$("#alertDeploy").hide();
					// unblock when ajax activity stops 
					$('input[type="radio"]').keydown(function(e)
						{
						    var arrowKeys = [37, 38, 39, 40];
						    if (arrowKeys.indexOf(e.which) !== -1)
						    {
						        $(this).blur();
						        return false;
						    }
						});
					
				    $(document).ajaxStop($.unblockUI); 
					getUrl = $('.getUrl').attr('id');
					
					if(isSuperuser()){
						$('#tdnotification').show();
					}
					/*var x = document.cookie;
					alert(x);*/
					
					if(getCookie("isModalClose") != 'true'){
						$("#modal-4").addClass("md-show");
						/*$.ajax({
                    		url:getUrl+'/setting/versionControl/get_version_control_list.html?limit=1',
                    		async: false,
                    		beforeSend : function(xhr) {
                    			xhr.setRequestHeader(	"Accept","application/json");
                    			xhr.setRequestHeader("Content-Type","application/json");
                    		},
                    		success:function(result){
                    			var tm = result;
                    			if(tm.length > 0){
	                    			for(var i = 0; i < tm.length; i++) {
	                    				var obj = tm[i];
	                    				$('#titleVersionControl').append(obj.version);
	                    				
	                    				var tmDtl = obj.detail;
	                    				for(var x = 0; x < tmDtl.length; x++){
	                    					var dtl = tmDtl[x];
		                    				$("<tr height='50px' class='border-bottom'><td><img src='"+getUrl+"/assets/images/icon/new.gif' /></td><td>"+dtl.keterangan+"</td><td>&nbsp;</td><td>"+dtl.requestor+"</td></tr>").appendTo("#detailVersionControl");
	                    				}
	                    				
	                    			}
                    			}
                    		}
                    	});*/
					}
					
					$(".md-close").click(function() {
						$("#modal-4").removeClass("md-show");
						setCookie("isModalClose", "true", "1");
					});
					
					$("#closeRequest").click(function() {
						$("#modal-1").removeClass("md-show");
					});
					/*Whats New Dialog Awal
					var polyfilter_scriptpath = window.location.origin + '/HK/assets/modalWindowEffects/js/';*/
					
					// filter panel
					$("#thecontent").show();

					$("#showContent")
							.click(
									function() {
										if ($("#thecontent").is(":visible")) {
											$("#thecontent").hide(0);
											$("#buttonShowContent").remove();
											$(
													"<a href=# id=buttonShowContent ><img src="+getUrl+"/assets/images/icon/color_18/directional_down.png /></a>")
													.appendTo("#showContent");
										} else {
											$("#thecontent").show(0);
											$("#buttonShowContent").remove();
											$(
													"<a href=# id=buttonShowContent ><img src="+getUrl+"/assets/images/icon/color_18/directional_up.png /></a>")
													.appendTo("#showContent");
										}

									});
					
					
					$('.Delete').click(function(){
							if( confirm("Be careful if you want to delete! This data is might being used, continue?") ){
								return true;
							}else{
								return false;
							}
					   });
					
			
					$(".dialogProgress").dialog({
						autoOpen : false,
						modal: true,
						width : 400,
					    height : 'auto', 
						show : {
							effect : "blind",
							duration : 100
						},
						hide : {
							effect : "blind",
							duration : 100
						}
					});
					
					 $("ul#whatsNew").sidebar({
				        	position:"right",
				            open:"click",
				            close:"click",
				            duration : 300,
				            callback: {
				            	
				                sidebar : {
				                    open : function(){
				                    	/*$.ajax({
				                    		url:getUrl+'/setting/versionControl/get_version_control_list.html?limit=3',
				                    		async: false,
				                    		beforeSend : function(xhr) {
				                    			xhr.setRequestHeader(	"Accept","application/json");
				                    			xhr.setRequestHeader("Content-Type","application/json");
				                    		},
				                    		success:function(result){
				                    			var tm = result;
				                    			if(tm.length > 0){
					                    			for(var i = 0; i < tm.length; i++) {
					                    				var obj = tm[i];
					                    				$("<li class='whatsNewHeader'>"+obj.version+"</li>").appendTo("#whatsNew");
					                    				
					                    				var tmDtl = obj.detail;
					                    				for(var x = 0; x < tmDtl.length; x++){
					                    					var dtl = tmDtl[x];
						                    				$("<li class='whatsNewItem'>"+dtl.keterangan+"</li>").appendTo("#whatsNew");
					                    				}
					                    				
					                    			}
				                    			}
				                    		}
				                    	});*/
				                    	
				                    	$("div.sidebar-body").css({
			                    		 	height: 'auto',
			                    		 	width: 'auto',
			                    		});
				                    	
				                    	$("div.sidebar-container").css({
				                    		height		: 'auto',
				                    		width: 'auto',
				                    		cursor: 'default',
				                    	});
				                    	
				                    },
				                    close : function(){
				                        $("#whatsNew").empty();
				                        $("div.sidebar-container").css({
				                    		cursor: 'pointer',
				                    	});
				                    }
				                }
				            },
				            
				        });
					
					 [].slice.call( document.querySelectorAll( '.progress-button' ) ).forEach( function( bttn, pos ) {
						 
							new UIProgressButton( bttn, {
								callback : function( instance ) {
									var status = null;
									var time = null;
									if(validateMemoInput() & validateAlasanInput()){
										status = true;
										time = 40;
									}else{
										status = false;
										time = 0;
									}
									
										var progress = 0,
										interval = setInterval( function() {
											progress = Math.min( progress + Math.random() * 0.1, 1 );
											instance.setProgress( progress );

											if( progress === 1 ) {
												if(status){

													
													var requestor=$('#requestorInput').val();
													var memo=$('#memoInput').val();
													var alasan=$('#alasanInput').val();
													
													
													var json = {
														"requestor" : requestor,
														"memo" : memo,
														"alasan" : alasan,
													};
													
													if(isUserAktif()==false){
														alert(getMessageSessionTimeout());
														instance.stop(-1);
													}else {
														$.ajax({
															url : getUrl+ "/setting/requestAccessMenu/request_access_menu_add_save.html",
															data : JSON.stringify(json),
															type : "POST",
															beforeSend : function(xhr) {
																xhr.setRequestHeader(	"Accept","application/json");
																xhr.setRequestHeader("Content-Type","application/json");
															},
															success : function(result) {
																
																if(result==true){
																	var respContent = "";
																	respContent += "Request Ticket Berhasil di Simpan";
																	setNotificationAdd(respContent);
																	instance.stop(1);
																	$('#memoInput').val('');
																	$('#alasanInput').val('');
																	$("#modal-1").removeClass("md-show");
																	getPendingNotif();
																}else if(result==false){
																	var respContent = "";
																	respContent += "Request Ticket Gagal di Simpan";
																	alert(respContent);
																	instance.stop(-1);
																}else{
																	generateError(result, 'Request Ticket', 'New');
																	instance.stop(-1);
																}
															}
														});
													}
												}else{
													instance.stop(-1);
												}
												clearInterval( interval );
											}
										}, time );
										
										
									
									
								}
							} );
						} );
				});

$(function() {
	LResize();
	$(window).resize(function() {
		LResize();
	});
	$(window).scroll(function() {
		scrollmenu();
	});

	// datepicker
	$("input.datepicker").datepicker({
		autoSize : true,
		appendText : '(dd-mm-yyyy)',
		dateFormat : 'yy-mm-dd'
	});
	$("div.datepickerInline").datepicker({
		dateFormat : 'dd-mm-yy',
		numberOfMonths : 1
	});
	
	$("input.birthday").datepicker({
		changeMonth : true,
		changeYear : true,
		autoSize : true,
		appendText : '(yyyy-mm-dd)',
		dateFormat : 'yy-mm-dd',
		yearRange: '-30:+10',
	});
	
	$("input.birthday2").datepicker({
		changeMonth : true,
		changeYear : true,
		autoSize : true,
		dateFormat : 'yy-mm-dd',
		yearRange: '-30:+10',
	});

	// datetimepicker
	$("input.datetimepicker").datetimepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat : 'yy-mm-dd',
		yearRange: '-90:+0'
	});
	$('.timepicker').timepicker({});

	// tipsy tootip
	$('.tip a ').tipsy({
		gravity : 's',
		live : true
	});
	$('.ntip a ').tipsy({
		gravity : 'n',
		live : true
	});
	$('.wtip a ').tipsy({
		gravity : 'w',
		live : true
	});
	$('.etip a,.Base').tipsy({
		gravity : 'e',
		live : true
	});
	$('.netip a ').tipsy({
		gravity : 'ne',
		live : true
	});
	$('.nwtip a , .setting').tipsy({
		gravity : 'nw',
		live : true
	});
	$('.swtip a,.iconmenu li a ').tipsy({
		gravity : 'sw',
		live : true
	});
	$('.setip a ').tipsy({
		gravity : 'se',
		live : true
	});
	$('.wtip input').tipsy({
		trigger : 'focus',
		gravity : 'w',
		live : true
	});
	$('.etip input').tipsy({
		trigger : 'focus',
		gravity : 'e',
		live : true
	});
	$('.iconBox, div.logout').tipsy({
		gravity : 'ne',
		live : true
	});

	// icon gray Hover
	$('.iconBox.gray').hover(
			function() {
				var name = $(this).find('img').attr('alt');
				$(this).find('img').animate(
						{
							opacity : 0.5
						},
						0,
						function() {
							$(this).attr('src',
									getUrl +'/assets/images/icon/color_18/' + name + '.png')
									.animate({
										opacity : 1
									}, 700);
						});
			},
			function() {
				var name = $(this).find('img').attr('alt');
				$(this).find('img').attr('src',
						getUrl +'/assets/images/icon/gray_18/' + name + '.png');
			})
	// icon Logout
	$('div.logout').hover(function() {
		var name = $(this).find('img').attr('alt');
		$(this).find('img').animate({
			opacity : 0.4
		}, 200, function() {
			$(this).attr('src', getUrl+'/assets/images/' + name + '.png').animate({
				opacity : 1
			}, 500);
		});
	}, function() {
		var name = $(this).find('img').attr('name');
		$(this).find('img').animate({
			opacity : 0.5
		}, 200, function() {
			$(this).attr('src', getUrl+'/assets/images/' + name + '.png').animate({
				opacity : 1
			}, 500);
		});
	})
	// icon setting
	$('div.setting').hover(function() {
		$(this).find('img').addClass('gearhover');
	}, function() {
		$(this).find('img').removeClass('gearhover');
	})

});

function ResetForm() {
	$('form').each(function(index) {
		var form_id = $('form:eq(' + index + ')').attr('id');
		if (form_id) {
			$('#' + form_id).get(0).reset();
			$('#' + form_id).validationEngine('hideAll');
			var editor = $('#' + form_id).find('#editor').attr('id');
			if (editor) {
				$('#editor').cleditor()[0].clear();
			}
		}
	});
}

function imgRow() {
	var maxrow = $('.albumpics').width();
	if (maxrow) {
		maxItem = Math.floor(maxrow / 160);
		maxW = maxItem * 160;
		mL = (maxrow - maxW) / 2;
		$('.albumpics ul').css({
			'width' : maxW,
			'marginLeft' : mL
		})
	}
}
function scrollmenu() {
	if ($(window).scrollTop() >= 1) {
		$("#header ").css("z-index", "50");
	} else {
		$("#header ").css("z-index", "47");
	}
}

function LResize() {
	imgRow();
	scrollmenu();
	if ($.cookie("hide_")) {
		$('#hide_panel').show();
	}
	$("#shadowhead").show();

}

function newPopup(url) {
	popupWindow = window
			.open(
					url,
					'popUpWindow',
					'height=500,width=900,left=50,top=50,resizable=yes,scrollbars=yes,toolbar=yes,menubar=no,location=no,directories=no,status=yes')
}

function newNoResizePopup(url) {
	popupWindow = window
			.open(
					url,
					'popUpWindow',
					'height=300,width=300,left=50,top=50,resizable=no,scrollbars=no,toolbar=no,menubar=no,location=no,directories=no,status=yes')
}

function newPopupDUMMY(url) {
	popupWindow = window
			.open(
					url,
					'popUpWindow',
					'height=500,width=900,left=50,top=50,resizable=yes,scrollbars=yes,toolbar=yes,menubar=no,location=no,directories=no,status=yes')
}

function setValue(target, id,val){
	var object = window.opener.document.getElementById(target);
	object.options.length = 0;
	
	var opt = opener.document.createElement("option");
	opt.setAttribute("value", id);
	opt.innerHTML = val;
	
	object.appendChild(opt);
	window.close();
}

function setAttendanceTime(target, val, userId, dateId){
	window.opener.document.getElementById(target).value = val;
	var nameTextBox = "attin";
	if(target.indexOf("attout") == -1){
		nameTextBox = "attout";
	}
	if(val.indexOf("AM") == -1 && val.indexOf("PM") == -1){
		window.opener.document.getElementById(nameTextBox+userId+"-"+dateId).value = val;
	}
	window.close();
}

function setValueVendor(target, id,val){
	var object = window.opener.document.getElementById(target);
	object.options.length = 0;
	var opt = opener.document.createElement("option");
	opt.setAttribute("value", id);
	opt.innerHTML = val;
	object.appendChild(opt);
	
	var id_party = window.opener.document.getElementById('id_party');
	id_party.innerHTML = "<a id='selectVendor1' class='iconBox color' original-title='Chosee Vendor Contact'   href=JavaScript:newPopup('/WHM/master/vendor_contact_list_dialog.do?target=vendor1&partyTypeId=1&partyId="+id+"'); > <img width='13px;' height='13px;' alt='add' src='/WHM/assets/images/icon/color_18/add.png'></a>";
	
	var addmorevendor = window.opener.document.getElementById('addmorevendor');
	addmorevendor.innerHTML = "<span style='font:Arial, Helvetica, sans-serif;color:#0099FF;cursor:pointer' onClick='addVendor("+id+");' >Add More Vendor Contact</span>";
	
	var addfactory = window.opener.document.getElementById('addfactory');
	addfactory.innerHTML = "<a id='selectFactory' class='iconBox color' original-title='Chosee Vendor Contact'   href=JavaScript:newPopup('/WHM/master/party_list_dialog.do?target=factory&partyType=2&id="+id+"'); > <img width='13px;' height='13px;' alt='add' src='/WHM/assets/images/icon/color_18/add.png'></a>";
	
	
	
	window.close();
}

function setValueFactory(target, id,val){
	var object = window.opener.document.getElementById(target);
	object.options.length = 0;
	var opt = opener.document.createElement("option");
	opt.setAttribute("value", id);
	opt.innerHTML = val;
	object.appendChild(opt);
	
	var addfactorycontactlist = window.opener.document.getElementById('addfactorycontactlist');
	addfactorycontactlist.innerHTML = "<a class='iconBox color' original-title='Chosee Vendor Contact'   href=JavaScript:newPopup('/WHM/master/vendor_contact_list_dialog.do?target=factory1&partyTypeId=2&partyId="+id+"'); > <img width='13px;' height='13px;' alt='add' src='/WHM/assets/images/icon/color_18/add.png'></a>";
	
	var addmorefactory = window.opener.document.getElementById('addmorefactory');
	addmorefactory.innerHTML = "<span style='font:Arial, Helvetica, sans-serif;color:#0099FF;cursor:pointer' onClick='addFactory("+id+");' >Add More Vendor Contact</span>";
	removeErrorVendorContact(target);
	window.close();
}

function removeErrorDailyTrxAdd(){
	window.opener.$("#accountItemInfo").text("");
	window.opener.$("#accountItemInfo").removeClass("error");
}

function removeErrorVendorContact(id){
	window.opener.$("#"+id).removeClass("error");
}


function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode;
   if (charCode > 31 && (charCode < 48 || charCode > 57))
      return false;

   return true;
}

function setIdNull(e,variableId1) {
    e.which = e.which || e.keyCode;
    if(e.which == 13) {
        
    }else{
    	var variableId2 = "#"+variableId1;
    	$(variableId2).val('');
    }
}

function setNotificationAdd(text) {

        $.bootstrapGrowl(text, { 
        type: 'success',
        align: 'right', // ('left', 'right', or 'center')
        delay : 4000,
        });
    
}

function setNotificationEdit(text) {

    $.bootstrapGrowl(text, { 
    type: 'info',
    align: 'right', // ('left', 'right', or 'center')
    delay : 4000,
    });

}

function setNotificationRemove(text) {

	 $.bootstrapGrowl(text, {
         type: 'danger',
         align: 'right',
         delay : 4000,
     });

}

function fontColorFormatter(cellValue, options, rowObject) {
    if (rowObject.isActive == "N")
    	var color = "silver";
	    var cellHtml = "<span class='isDelete' style='color:" + color + "' originalValue='" + cellValue + "'>" + cellValue + "</span>";
	    return cellHtml;

}

function fontColorFormatterCancel(cellValue, options, rowObject) {
    if (rowObject.isCancel == "Y")
    	var color = "silver";
	    var cellHtml = "<span class='isDelete' style='color:" + color + "' originalValue='" + cellValue + "'>" + cellValue + "</span>";
	    return cellHtml;

}

function fontColorFormatterEnable(cellValue, options, rowObject) {
	 var cellHtml = "";
	if (rowObject.isActive == "N"){
    	var color = "silver";
	    cellHtml = "<span class='isDelete' style='color:" + color + "' originalValue='" + cellValue + "'>" + cellValue + "</span>";
	}else{
		if (cellValue == "Y"){
	    	var color = "#cbd962";
	    	cellHtml = "<span class='cellWithoutBackground' style='background-color:" + color + ";font-weight:bold;' originalValue='" + cellValue + "'>" + cellValue + "</span>";
		}else{
		    cellHtml = "<span class='isDelete'  originalValue='" + cellValue + "'>" + cellValue + "</span>";
		}
	}
	
	return cellHtml;

}

function fontColorFormatterPemutihan(cellValue, options, rowObject) {
		var color = "";
	
		if (rowObject.isCancel == "Y"){
			if(cellValue != '-'){
	    		color = "pink";
	    	}else{
	    		color = "silver";
	    	}
	    }else{
	    	if(cellValue != '-'){
	    		if(parseFloat(cellValue) > 0){
	    			color = "red";
	    		}else{
	    			color = "black";
	    		}
	    	}else{
	    		color = "black";
	    	}
	    }
    	
    	
	    var cellHtml = "<span class='isDelete' style='color:" + color + "' originalValue='" + cellValue + "'>" + cellValue + "</span>";
	    return cellHtml;
	    
}

function fontColorFormatterWarna(cellValue, options, rowObject) {
    	var color = rowObject.forecolor;
    	var bgcolor = rowObject.bgcolor;
	    var cellHtml = "<span class='cellWithoutBackground' style='background-color:#" + bgcolor + ";color:#"+color+"' originalValue='" + cellValue + "'>" + cellValue + "</span>";
	    return cellHtml;
}

function fontColorFormatterWarnaGrade(cellValue, options, rowObject) {
	var color = "000000";
	var bgcolor = rowObject.warnaDiagram;
    var cellHtml = "<span class='cellWithoutBackground' style='background-color:#" + bgcolor + ";color:#"+color+"' originalValue='" + cellValue + "'>" + cellValue + "</span>";
    return cellHtml;
}

function fontColorFormatterMasuk(cellValue, options, rowObject) {
	var color = '000000';
	var bgcolor = '3a99a6';
    var cellHtml = "<span class='cellWithoutBackground' style='background-color:#" + bgcolor + ";color:#"+color+"' originalValue='" + cellValue + "'>" + cellValue + "</span>";
    return cellHtml;
}

function fontColorFormatterKeluar(cellValue, options, rowObject) {
	var color = '000000';
	var bgcolor = 'd16f6f';
    var cellHtml = "<span class='cellWithoutBackground' style='background-color:#" + bgcolor + ";color:#"+color+"' originalValue='" + cellValue + "'>" + cellValue + "</span>";
    return cellHtml;
}

function fontColorFormatterLevelSisa(cellValue, options, rowObject) {
	var color = "";
	if (rowObject.percentSisa >= $("#highLevelColorPercent").val()){
    	color = $("#highLevelColor").val();
	}else if(rowObject.percentSisa < $("#highLevelColorPercent").val() & rowObject.percentSisa >= $("#mediumLevelColorPercent").val() ){
		color = $("#mediumLevelColor").val();
	}else if(rowObject.percentSisa < $("#mediumLevelColorPercent").val() & rowObject.percentSisa >= $("#lowLevelColorPercent").val() ){
		color = $("#lowLevelColor").val();
	}
	    var cellHtml = "<span class='cellWithoutBackground' style='background-color:" + color + "' originalValue='" + cellValue + "'>" + cellValue + "</span>";
	    return cellHtml;
}

function fontColorFormatterLevelBbmBeli(cellValue, options, rowObject) {
	var color = "";
	var percentSisa = "";
	
	var sisa = rowObject.qtyPO - rowObject.qtyBBMBeli;
	if(sisa == 0 ){
		percentSisa = 0;
	}else{
		percentSisa = (sisa / rowObject.qtyPO) * 100;
	}
	
	if (percentSisa >= $("#highLevelColorPercent").val()){
    	color = $("#highLevelColor").val();
	}else if(percentSisa < $("#highLevelColorPercent").val() & percentSisa >= $("#mediumLevelColorPercent").val() ){
		color = $("#mediumLevelColor").val();
	}else if(percentSisa < $("#mediumLevelColorPercent").val() & percentSisa >= $("#lowLevelColorPercent").val() ){
		color = $("#lowLevelColor").val();
	}
	    var cellHtml = "<span class='cellWithoutBackground' style='background-color:" + color + "' originalValue='" + cellValue + "'>" + cellValue + "</span>";
	    return cellHtml;
}

function fontColorFormatterLevelPO(cellValue, options, rowObject) {
	var color = "";
	var percentSisa ="";
	
	var sisa = rowObject.qtyBeli - rowObject.qtyPO;
	
	if(sisa == 0 ){
		percentSisa = 0;
	}else{
		percentSisa = (sisa / rowObject.qtyBeli) * 100;
	}

	if (percentSisa >= $("#highLevelColorPercent").val()){
		color = $("#highLevelColor").val();
	}else if(percentSisa < $("#highLevelColorPercent").val() & percentSisa >= $("#mediumLevelColorPercent").val() ){
		color = $("#mediumLevelColor").val();
	}else if(percentSisa < $("#mediumLevelColorPercent").val() & percentSisa >= $("#lowLevelColorPercent").val() ){
		color = $("#lowLevelColor").val();
	}
	    var cellHtml = "<span class='cellWithoutBackground' style='background-color:" + color + "' originalValue='" + cellValue + "'>" + cellValue + "</span>";
	    return cellHtml;
}

function fontColorFormatterLevelDoCelup(cellValue, options, rowObject) {
	var color = "";
	var percentSisa = "";
	if(rowObject.gwm == 'WARNA' | rowObject.gwm == 'MOTIF'){
		if(rowObject.qtyBookingGreige > 0 | rowObject.qtyBeliGreige > 0){
			if(!(rowObject.qtyDOCelup == 0 & rowObject.qtyDOPrinting > 0)){
				var sisa = rowObject.totalQuantity - rowObject.qtyDOCelup;
				if(sisa == 0 ){
					percentSisa = 0;
				}else{
					percentSisa = (sisa / rowObject.totalQuantity) * 100;
				}
				
				if (percentSisa >= $("#highLevelColorPercent").val()){
			    	color = $("#highLevelColor").val();
				}else if(percentSisa < $("#highLevelColorPercent").val() & percentSisa >= $("#mediumLevelColorPercent").val() ){
					color = $("#mediumLevelColor").val();
				}else if(percentSisa < $("#mediumLevelColorPercent").val() & percentSisa >= $("#lowLevelColorPercent").val() ){
					color = $("#lowLevelColor").val();
				}
			}
			
		}
	}
		    var cellHtml = "<span class='cellWithoutBackground' style='background-color:" + color + "' originalValue='" + cellValue + "'>" + cellValue + "</span>";
		    return cellHtml;
}

function fontColorFormatterLevelBbmCelup(cellValue, options, rowObject) {
	var color = "";
	var percentSisa = "";
	if(rowObject.gwm == 'WARNA' | rowObject.gwm == 'MOTIF'){
		if(rowObject.qtyBookingGreige > 0 | rowObject.qtyBeliGreige > 0){
			var sisa = rowObject.qtyDOCelup - rowObject.qtyBBMCelup;
			if(sisa == 0 ){
				percentSisa = 0;
			}else{
				percentSisa = (sisa / rowObject.qtyDOCelup) * 100;
			}
			
			if (percentSisa >= $("#highLevelColorPercent").val()){
		    	color = $("#highLevelColor").val();
			}else if(percentSisa < $("#highLevelColorPercent").val() & percentSisa >= $("#mediumLevelColorPercent").val() ){
				color = $("#mediumLevelColor").val();
			}else if(percentSisa < $("#mediumLevelColorPercent").val() & percentSisa >= $("#lowLevelColorPercent").val() ){
				color = $("#lowLevelColor").val();
			}
		}
	}
		    var cellHtml = "<span class='cellWithoutBackground' style='background-color:" + color + "' originalValue='" + cellValue + "'>" + cellValue + "</span>";
		    return cellHtml;
}

function fontColorFormatterLevelDoPrinting(cellValue, options, rowObject) {
	var color = "";
	var percentSisa = "";
	if(rowObject.gwm == 'MOTIF'){
		if(rowObject.qtyBookingGreige > 0 | rowObject.qtyBeliGreige > 0 | rowObject.qtyBookingWarna > 0 | rowObject.qtyBeliWarna > 0){
			
			var sisa = rowObject.totalQuantity - rowObject.qtyDOPrinting;
			if(sisa == 0 ){
				percentSisa = 0;
			}else{
				percentSisa = (sisa / rowObject.totalQuantity) * 100;
			}
			
			if (percentSisa >= $("#highLevelColorPercent").val()){
		    	color = $("#highLevelColor").val();
			}else if(percentSisa < $("#highLevelColorPercent").val() & percentSisa >= $("#mediumLevelColorPercent").val() ){
				color = $("#mediumLevelColor").val();
			}else if(percentSisa < $("#mediumLevelColorPercent").val() & percentSisa >= $("#lowLevelColorPercent").val() ){
				color = $("#lowLevelColor").val();
			}
		}
	}
		    var cellHtml = "<span class='cellWithoutBackground' style='background-color:" + color + "' originalValue='" + cellValue + "'>" + cellValue + "</span>";
		    return cellHtml;
}

function fontColorFormatterLevelBbmPrinting(cellValue, options, rowObject) {
	var color = "";
	var percentSisa = "";
	if(rowObject.gwm == 'MOTIF'){
		if(rowObject.qtyBookingGreige > 0 | rowObject.qtyBeliGreige > 0 | rowObject.qtyBookingWarna > 0 | rowObject.qtyBeliWarna > 0){
			var sisa = rowObject.qtyDOPrinting - rowObject.qtyBBMPrinting;
			if(sisa == 0 ){
				percentSisa = 0;
			}else{
				percentSisa = (sisa / rowObject.qtyDOPrinting) * 100;
			}
			
			if (percentSisa >= $("#highLevelColorPercent").val()){
		    	color = $("#highLevelColor").val();
			}else if(percentSisa < $("#highLevelColorPercent").val() & percentSisa >= $("#mediumLevelColorPercent").val() ){
				color = $("#mediumLevelColor").val();
			}else if(percentSisa < $("#mediumLevelColorPercent").val() & percentSisa >= $("#lowLevelColorPercent").val() ){
				color = $("#lowLevelColor").val();
			}
		}
	}
		    var cellHtml = "<span class='cellWithoutBackground' style='background-color:" + color + "' originalValue='" + cellValue + "'>" + cellValue + "</span>";
		    return cellHtml;
}

function fontColorFormatterLevelLamaSo(cellValue, options, rowObject) {
	var color = "";
	if(parseInt(rowObject.lamaSo) > 30){
		color = 'red';
	}
	
	var cellHtml = "<span class='cellWithoutBackground' style='background-color:" + color + "' originalValue='" + cellValue + "'>" + cellValue + "</span>";
	return cellHtml;
}

function fontColorFormatterGambarMotif(cellValue, options, rowObject) {
	var d = new Date(); // for now
	var datetext = d.getFullYear()+""+d.getMonth()+""+d.getDate()+""+d.getHours()+""+d.getMinutes()+""+d.getSeconds();
    return "<img class='zoom' width='100' src='"+getUrl+"/master/kainMotif/gambar_motif.html?id="+rowObject.id+"&v="+datetext+"'/>";
}

function changeCurrencyFormat(getVal1, getVal2){
	var number = $("#"+getVal1).val();
	var result = accounting.formatMoney(number, "", 4, ",", ".");
	$("#"+getVal1).val(result);
	if(number==""){
		$("#"+getVal2).val('');
	}else{
		var unformat = accounting.unformat(number);
		$("#"+getVal2).val(unformat);
	}
}

function changeWeightFormat(getVal1, getVal2){
	var number = $("#"+getVal1).val();
	var result = accounting.formatMoney(number, "", 2, ",", ".");
	$("#"+getVal1).val(result);
	if(number==""){
		$("#"+getVal2).val('');
	}else{
		var unformat = accounting.unformat(number);
		$("#"+getVal2).val(unformat);
	}
}

function changeCurrencyFormatString(money){
	var result = accounting.formatMoney(money, "", 4, ",", ".");
	return result;
}

function toCurrencyFormat(getVal1, getVal2,number){
	var result = accounting.formatMoney(number, "", 4, ",", ".");
	$("#"+getVal1).val(result);
	if(number==""){
		$("#"+getVal2).val('');
	}else{
		var unformat = accounting.unformat(number);
		$("#"+getVal2).val(unformat);
	}
}

function toWeightFormat(getVal1, getVal2,number){
	var result = accounting.formatMoney(number, "", 2, ",", ".");
	$("#"+getVal1).val(result);
	if(number==""){
		$("#"+getVal2).val('');
	}else{
		var unformat = accounting.unformat(number);
		$("#"+getVal2).val(unformat);
	}
}

function roundToFourReal(num) {    
    return +(Math.round(num + "e+4")  + "e-4");
}

function roundToFour(num) {    
    return +(Math.round(num + "e+2")  + "e-2");
}

function roundToTwo(num) {    
    return +(Math.round(num * 100) / 100);
}

function setMainIdNull() {
	mainId="";
}

function setSubMainIdNull() {
	subMainId="";
}

function setSubSubMainIdNull() {
	subSubMainId="";
}

function generateError(result,nama,id){
	

    if(containsWord(result, "constraint")){
    	alert("Data "+nama+" Dengan "+nama+" Id  : "+id+" Gagal di Tambahkan Karena ID Telah Digunakan Oleh Data Yang Lain ");
    }else if(containsWord(result, "locking")){
    	alert("Data "+nama+" Dengan "+nama+" Id  : "+id+"  telah diupdate atau dihapus oleh transaksi lain,Reresh Kembali Browser Anda Untuk Mendapatkan Update Data Terbaru");
    }else if(containsWord(result, "Can not construct instance of java.lang.Double from String value")){
    	alert("Data "+nama+ " yang anda inputkan tidak valid , hanya boleh mengunakan angka!");
    }else if(containsWord(result, 'org.hibernate.StaleObjectStateException:')){
		alert("Data "+nama+" Dengan "+nama+" Id  : "+id+"  telah diupdate atau dihapus oleh transaksi lain,Reresh Kembali Browser Anda Untuk Mendapatkan Update Data Terbaru");
	}else{
    	setNotificationRemove(jQuery(result).text());
    }
}

function containsWord(string, word) {
    return new RegExp('(?:[^.\w]|^|^\\W+)' + word + '(?:[^.\w]|\\W(?=\\W+|$)|$)').test(string);
}

function isDelete(){
	var isDelete=null;
	$.ajax({
		url:getUrl+'/akses_user/is_delete.html',
		async: false,
		beforeSend : function(xhr) {
			xhr.setRequestHeader(	"Accept","application/json");
			xhr.setRequestHeader("Content-Type","application/json");
		},
		success:function(result){
			alert("masuk");
			if(result==true){
				isDelete=true;
			}else{
				generateError(result);
				isDelete=false;
			}
		}
	});
	
	return isDelete;
}

function isDelete(){
	var isDelete=null;
	$.ajax({
		url:getUrl+'/akses_user/is_delete.html',
		async: false,
		beforeSend : function(xhr) {
			xhr.setRequestHeader(	"Accept","application/json");
			xhr.setRequestHeader("Content-Type","application/json");
		},
		success:function(result){
			if(result==true){
				isDelete=true;
			}else{
				isDelete=false;
			}
		}
	});
	
	return isDelete;
}

function isUpdate(){
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

function isCreate(){
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


function isCancel(){
	var isCancel=null;
	$.ajax({
		url:getUrl+'/akses_user/is_cancel.html',
		async: false,
		beforeSend : function(xhr) {
			xhr.setRequestHeader(	"Accept","application/json");
			xhr.setRequestHeader("Content-Type","application/json");
		},
		success:function(result){
			if(result==true){
				isCancel=true;
			}else{
				isCancel=false;
			}
		}
	});
	
	return isCancel;
}

function isPrint(){
	var isPrint=null;
	$.ajax({
		url:getUrl+'/akses_user/is_print.html',
		async: false,
		beforeSend : function(xhr) {
			xhr.setRequestHeader(	"Accept","application/json");
			xhr.setRequestHeader("Content-Type","application/json");
		},
		success:function(result){
			if(result==true){
				isPrint=true;
			}else{
				isPrint=false;
			}
		}
	});
	
	return isPrint;
}

function isReport(){
	var isReport=null;
	$.ajax({
		url:getUrl+'/akses_user/is_report.html',
		async: false,
		beforeSend : function(xhr) {
			xhr.setRequestHeader(	"Accept","application/json");
			xhr.setRequestHeader("Content-Type","application/json");
		},
		success:function(result){
			if(result==true){
				isReport=true;
			}else{
				isReport=false;
			}
		}
	});
	
	return isReport;
}

function isSupervisor(){
	var isSupervisor=null;
	$.ajax({
		url:getUrl+'/akses_user/is_supervisor.html',
		async: false,
		beforeSend : function(xhr) {
			xhr.setRequestHeader(	"Accept","application/json");
			xhr.setRequestHeader("Content-Type","application/json");
		},
		success:function(result){
			if(result==true){
				isSupervisor=true;
			}else{
				isSupervisor=false;
			}
		}
	});
	
	return isSupervisor;
}

function isSuperuser(){
	var isSuperuser=null;
	$.ajax({
		url:getUrl+'/akses_user/is_superuser.html',
		async: false,
		beforeSend : function(xhr) {
			xhr.setRequestHeader(	"Accept","application/json");
			xhr.setRequestHeader("Content-Type","application/json");
		},
		success:function(result){
			if(result==true){
				isSuperuser=true;
			}else{
				isSuperuser=false;
			}
		}
	});
	
	return isSuperuser;
}

function isConfirmVoid(){
	var isConfirm=null;
	$.ajax({
		url:getUrl+'/akses_user/is_confirm.html',
		async: false,
		beforeSend : function(xhr) {
			xhr.setRequestHeader(	"Accept","application/json");
			xhr.setRequestHeader("Content-Type","application/json");
		},
		success:function(result){
			if(result==true){
				isConfirm=true;
			}else{
				isConfirm=false;
			}
		}
	});
	
	return isConfirm;
}

function isUnconfirm(){
	var isUnconfirm=null;
	$.ajax({
		url:getUrl+'/akses_user/is_unconfirm.html',
		async: false,
		beforeSend : function(xhr) {
			xhr.setRequestHeader(	"Accept","application/json");
			xhr.setRequestHeader("Content-Type","application/json");
		},
		success:function(result){
			if(result==true){
				isUnconfirm=true;
			}else{
				isUnconfirm=false;
			}
		}
	});
	
	return isUnconfirm;
}

function isUserAktif(){
	var isUserAktif=null;
	$.ajax({
		url:getUrl+'/akses_user/cek_aktif_user.html',
		async: false,
		beforeSend : function(xhr) {
			xhr.setRequestHeader(	"Accept","application/json");
			xhr.setRequestHeader("Content-Type","application/json");
		},
		success:function(result){
			var errorLogin = $(result).filter('span.loginPage');
		    if($(errorLogin).html() != null){
		        isUserAktif = false;
		    }else{
		    	isUserAktif = true;
		    }
		}
	});
	
	return isUserAktif;
}

function getMessageSessionTimeout(){
	var messages="Maaf Permintaan anda tidak dapat di proses ,Sesi Telah Habis Silahkan Logout,dan Login kembali,sesi akn habis dalam waktu 15 menit Setelah Aplikasi tidak dioperasikan";
	return messages;
}

function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1);
        if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
    }
    return "";
}

function deleteCookie(cname) {
	setCookie(cname,"",-1);
}

function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
}

function adjust_textarea(h) {
    h.style.height = "20px";
    h.style.height = (h.scrollHeight)+"px";
}

function validateMemoInput(){
	var memo = $('#memoInput').val();
	
	if(memo.length > 0){
		$('#memoInput').removeClass('invalid');
		return true;
	}else{
		setNotificationRemove("Memo Wajib Diisi !");
		$('#memoInput').addClass('invalid');
		return false;
	}
}

function validateAlasanInput(){
	var alasan = $('#alasanInput').val();
	
	if(alasan.length > 0){
		$('#alasanInput').removeClass('invalid');
		return true;
	}else{
		setNotificationRemove("Alasan Wajib Diisi !");
		$('#alasanInput').addClass('invalid');
		return false;
	}
}

function loadingProgress(eventSource,actionMessage){
	$('.dialogProgress').dialog('open');
	$( ".progressbarDialog" ).progressbar({
		value: 1
	});
	
	$('.progressDescription').html(actionMessage);
	
	eventSource.onmessage = function(e) {
		 //console.log(event.data);
		var  event = JSON.parse(e.data);
		
		if(event.status == "unfinished"){
			 var total = event.records;
			 var percentage = (event.success/total)*100;
			 
			 $( ".progressbarDialog" ).progressbar({
				 value: percentage
			 });
       	    
			 $('.progressDescription').html(actionMessage+" "+event.success+" dari "+total+" No.Urut");
			 
		 }else{
			
			 $('.dialogProgress').dialog('close');
			 var respContent = "";
			 respContent += actionMessage+" Selesai";
			 setNotificationEdit(respContent);
				
			 eventSource.close();
		 }
		 
    };
}

function formatNumber(number,type)
{
	if(type == "float"){
		 number = number.toFixed(2) + '';
	}else{
		 number = number + '';
	}
   
    x = number.split('.');
    x1 = x[0];
    x2 = x.length > 1 ? '.' + x[1] : '';
    var rgx = /(\d+)(\d{3})/;
    while (rgx.test(x1)) {
        x1 = x1.replace(rgx, '$1' + ',' + '$2');
    }
    return x1 + x2;
}

function getQueryVariable(variable) {
	  var query = window.location.search.substring(1);
	  var vars = query.split("&");
	  for (var i=0;i<vars.length;i++) {
	    var pair = vars[i].split("=");
	    if (pair[0] == variable) {
	      return pair[1];
	    }
	  } 
}