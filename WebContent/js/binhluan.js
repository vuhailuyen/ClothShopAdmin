function f_Approve(){
	var approveIds = new Array();
	
	//alert(JSON.stringify(approveIds));
	$.ajax({
		type : "POST",
		url : "verify/approve",
		data : JSON.stringify(approveIds),
		dataType : "json",
		contentType : 'application/json',
		async : true,
		cache : false,
		timeout : 50000,

		success : function(data) {
			var jSONObject = data;
			var resultHTML = '';
			var code = jSONObject.result;
			var message = "";
			if (code == 1) {
				message = "Thành Công";
				resultHTML = "<div class='notification-box notification-box-success'><p>"
					+ message
					+ "</p><input class = 'btt-notification-close' type='button' value='x' onclick = 'f_CLoseMessageBox(this);'></input></div>";
				
			} else {
				message = "Thất Bại";
				resultHTML = "<div class='notification-box notification-box-error'><p>"
					+ message
					+ "</p><input class = 'btt-notification-close' type='button' value='x' onclick = 'f_CLoseMessageBox(this);'></input></div>";
			}
			$("#result").html(resultHTML);
			setTimeout('location.reload()', 1000);
			
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// alert(textStatus);
		}
	});
}


function f_Disapprove(){
	var approveIds = new Array();
	
	//alert(JSON.stringify(approveIds));
	$.ajax({
		type : "POST",
		url : "verify/disapprove",
		data : JSON.stringify(approveIds),
		dataType : "json",
		contentType : 'application/json',
		async : true,
		cache : false,
		timeout : 50000,

		success : function(data) {
			var jSONObject = data;
			var resultHTML = '';
			var code = jSONObject.result;
			var message = "";
			if (code == 1) {
				message = "Thành Công";
				resultHTML = "<div class='notification-box notification-box-success'><p>"
					+ message
					+ "</p><input class = 'btt-notification-close' type='button' value='x' onclick = 'f_CLoseMessageBox(this);'></input></div>";
				
			} else {
				message = "Thất Bại";
				resultHTML = "<div class='notification-box notification-box-error'><p>"
					+ message
					+ "</p><input class = 'btt-notification-close' type='button' value='x' onclick = 'f_CLoseMessageBox(this);'></input></div>";
			}
			$("#result").html(resultHTML);
			setTimeout('location.reload()', 1000);
			
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// alert(textStatus);
		}
	});
}