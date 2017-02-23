$(document).ready(function() {
	
	$( "#action" ).combogrid({
		width:"550px",
		url: 'get_action.html',
		debug:true,
		okIcon: false,
		searchButton: true,
		showOn: true,
		colModel: [{'columnName':'action','width':'50','label':'ACTION','align':'left'}],
		select: function( event, ui ) {
			$("#action").val( ui.item.action );
			return false;
		}
	});
	
	/*$( "#module" ).combogrid({
		width:"550px",
		url: 'get_module.html',
		debug:true,
		okIcon: false,
		showOn: true,
		colModel: [{'columnName':'module','width':'50','label':'ACTION','align':'left'}],
		select: function( event, ui ) {
			$("#module").val( ui.item.module );
			return false;
		}
	});*/
	
	jQuery("#grid").jqGrid({
		  autowidth: true,
		  shrinkToFit:true,
	      url:'get_audit_log_jqgrid.html',
	      datatype: "json",
	      mtype: 'GET',
	      colNames:[ 'ID','ACTION','MODULE','USERNAME','TANGGAL','KETERANGAN'],
	      colModel:[
	                {name:'auditLogId',index:'auditLogId', align:'left',width:50,editable:true,formatter: fontColorFormatter},
	                {name:'action',index:'action', width:50,editable:true,formatter: fontColorFormatter},
	                {name:'module',index:'module', width:100,editable:true,formatter: fontColorFormatter},
	                {name:'username',index:'username', width:50,editable:true,formatter: fontColorFormatter},
	                {name:'createDate',index:'createDate', width:100,editable:true,formatter: fontColorFormatter,hidden:false},
	                {name:'detail',index:'detail',width:500, editable:true,formatter: fontColorFormatter,hidden:false}
	           ],
	     jsonReader : {
	          repeatitems:false
	     },
	      rowNum:15,
	      rowList:[15,30,50,100,500],
	      pager: '#gridpager',
	      sortname: 'id',
	      viewrecords: true,
	      sortorder: "desc",
	      search:false,
	      viewrecords: true,
	      multiselect: false,
	      height: 'auto',
	      autowidth: true,
	      onSelectRow: function(ids) {
		    	mainId=ids;
		    	
		    	var dataFromTheRow = jQuery('#grid').jqGrid ('getRowData',  mainId);
				var keterangan = jQuery(dataFromTheRow.detail).text();
				$("#keteranganTab").html("<br/><b><i>keterangan : </b>"+keterangan+"</i>");
				
				
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
		var dateStart = $("#dateStart").val();
		var dateFinish = $("#dateFinish").val();
		var id="";
		var userId="";
		var action="";
		var module="";
		var keterangan="";
		

		
		if ($('input[name=radioFilter]:checked').val() == 0){
			 id=$("#auditLogId").val();
			 userId=$("#username").val();
			 action=$("#action").val();
			 module=$("#module").val();
			 keterangan=$("#keterangan").val();
		}
		
		jQuery("#grid").jqGrid('setGridParam',{url:"get_audit_log_jqgrid.html"+
			"?id="+id+
			"&userId="+userId+
			"&action="+action+
			"&module="+module+
			"&keterangan="+keterangan+
			"&dateStart="+dateStart+
			"&dateFinish="+dateFinish}).trigger("reloadGrid", [{current:true}]);
	}
}

function gridReset(){
	if(isUserAktif()==false){
		alert(getMessageSessionTimeout());
		return false;
	}else{
		jQuery("#grid").jqGrid('setGridParam',{url:"get_audit_log_jqgrid.html"}).trigger("reloadGrid", [{current:true}]);
		$('#filterForm')[0].reset();
		setMainIdNull();
	}
}

function disableFilter(){
	$("#username").attr("disabled",true);
	$("#action").attr("disabled",true);
	$("#module").attr("disabled",true);
	$("#keterangan").attr("disabled",true);
}

function enableFilter(){
	$("#username").attr("disabled",false);
	$("#action").attr("disabled",false);
	$("#module").attr("disabled",false);
	$("#keterangan").attr("disabled",false);
}