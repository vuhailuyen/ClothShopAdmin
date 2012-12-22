// /////////////////////////////////////////////////////////////////////////////////////////////
function f_banUI(id) {
	var div = document.getElementById(id + 'banconfirm');
	var banUI = "<th><b>Lý Do</b></th>"
			+ "<td colspan='3'><textarea type='text' name='lidoBan' id='lidoBan' ></textarea></td>"
			+ "<th><b>Ngày Hết Hạn</b></th>"
			+ "<td colspan='2'><input type='text' size='15' name='date' id='ngayHetHan' /></td>"
			+ "<td class='id_col'><input type='button' class='btt-refresh' value='Đồng Ý' onclick='f_ban("
			+ id
			+ ")'/></td>"
			+ "<td class='id_col'><input type='button' class='btt-chrome' value='Thoát' onclick='f_exitBanUI("
			+ id + ")'/></td>" + "<input type='hidden' name='id' value='" + id
			+ "'/>";
	div.innerHTML = banUI;
	$("#ngayHetHan").datepicker({
		dateFormat : "dd/mm/yy"
	});
}
// /////////////////////////////////////////////////////////////////////////////////////////////
function f_exitBanUI(id) {
	var div = document.getElementById(id + 'banconfirm');
	div.innerHTML = "";
}
function f_ban(id) {
	var banData = jQuery.param({
		id : id,
		date : $("#ngayHetHan").val()
	});
	// alert(banData);
	$
			.ajax({
				type : "POST",
				url : "ban?",
				data : banData,
				dataType : "json",
				async : true,
				cache : false,
				timeout : 50000,
				success : function(data) {
					f_banHandler(data, id);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					// alert(XMLHttpRequest.readyState);
					var divResult = document.getElementById("result");
					var message = "Thất Bại";
					resultHTML = "<div class='notification-box notification-box-error'><p>"
							+ message
							+ "</p><input class = 'btt-notification-close' type='button' value='x' onclick = 'f_CLoseMessageBox(this);'></input></div>";
					divResult.innerHTML = resultHTML;
				}
			});
}
function f_banHandler(data, id) {

	var divResult = document.getElementById("result");
	// alert(returnValue);
	var jSONObject = data;

	var code = jSONObject.result;
	var message = "";
	if (code == 1) {
		message = "Thành Công";
	} else {
		message = "Thất Bại";
	}
	var resultHTML = '';

	if (code == 1) {
		divRow = document.getElementById("col" + id);
		resultHTML = "<div class='notification-box notification-box-success'><p>"
				+ message
				+ "</p><input class = 'btt-notification-close' type='button' value='x' onclick = 'f_CLoseMessageBox(this);'></input></div>";
		divRow.parentNode.removeChild(divRow);
		f_exitBanUI(id);
	}
	if (code == 0) {
		resultHTML = "<div class='notification-box notification-box-error'><p>"
				+ message
				+ "</p><input class = 'btt-notification-close' type='button' value='x' onclick = 'f_CLoseMessageBox(this);'></input></div>";
	}
	divResult.innerHTML = resultHTML;

}
