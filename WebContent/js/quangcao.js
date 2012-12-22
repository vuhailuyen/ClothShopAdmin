function f_enableAdDivs() {
	$(".ad-div").each(function() {
		$(this).show();
	});
}

function f_editUI(postion) {
	var editUI = "<td colspan='2'></td>"
			+ "<td><input id='"
			+ postion
			+ "link' type='text' size='35'/></td>"
			+ "<td><input id='"
			+ postion
			+ "date' type='text' required='required'/></td>"
			+ "<td class='id_col center_col'><input type='button' class='btt-refresh' value='Ok' onclick='f_editAd(&quot;"
			+ postion + "&quot;)'/></td>" + "<td></td>";
	$("#" + postion).html(editUI);
	$("#" + postion).show();
	$("#" + postion + "date").datepicker({
		dateFormat : "dd/mm/yy"
	});

	$("#" + postion + "link").val($("#" + postion + "-old-link").html());
}
function f_exitEditUI(postion) {
	$("#" + postion).toggle("medium");
}
function f_editAd(position) {
	var ad = new Object();
	if ($("#" + position + "date").val() == "") {
		$("#" + position + "date").val("THIS FIELD is REQUIRED");
		return false;
	}

	var date = Date.parseExact($("#" + position + "date").val(), "dd/MM/yyyy");
	if (isNaN(date)) {
		$("#" + position + "date").val("INVALID");
		return false;
	}
	
	ad.position = position;
	ad.link = $("#" + position + "link").val();
	ad.date = date.toString("yyyy-MM-dd");
	//alert(date.toString("MM-dd-yyyy"));

	 $.ajax({
		 type : "POST",
		 url : "edit?",
		 data : JSON.stringify(ad),
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
			 // setTimeout('location.reload()', 1000);
					
		 },
	 error : function(XMLHttpRequest, textStatus, errorThrown) {
			 message = "Thất Bại";
			 resultHTML = "<div class='notification-box notification-box-error'><p>"
			 + message
			 + "</p><input class = 'btt-notification-close' type='button' value='x' onclick = 'f_CLoseMessageBox(this);'></input></div>";
			 $("#result").html(resultHTML);
	 	}
	 });
}