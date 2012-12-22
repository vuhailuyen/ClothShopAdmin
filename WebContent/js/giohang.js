// /////////////////////////////////////////////////////////////////////////////////////////////
function f_cancelUI(id) {
	var deleteUI = "<td colspan='6'><h3>Bạn Có Thật Sự Muốn Hủy Đơn Đặt Hàng Nay¨?</h3></td>"
			+ "<td class='id_col'><input type='button' class='btt-refresh' value='Đồng Ý' onclick='f_cancel("
			+ id
			+ ")'/></td>"
			+ "<td class='id_col'><input type='button' class='btt-chrome' value='Thoát' onclick='f_exitDeleteUI("
			+ id + ")'/></td>";
	$("#"+id+"deleteconfirm").html(deleteUI);
	$("#"+id+"deleteconfirm").show();
}
///////////////////////////////////////////////////////////////////////////////////////////////

function f_exitDeleteUI(id) {
	$("#"+id+"deleteconfirm").toggle("fast");
}
///////////////////////////////////////////////////////////////////////////////////////////////

function f_cancel(id){
	$.ajax({
		type : "POST",
		url : "cancel?",
		data : "id=" + id,
		dataType : "json",
		async : true,
		cache : false,
		timeout : 50000,
		success : function(data) {
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
				resultHTML = "<div class='notification-box notification-box-success'><p>"
						+ message
						+ "</p><input class = 'btt-notification-close' type='button' value='x' onclick = 'f_CLoseMessageBox(this);'></input></div>";
			$("#col"+id).remove();
			f_exitDeleteUI(id);
			}
			if (code == 0) {
				resultHTML = "<div class='notification-box notification-box-error'><p>"
						+ message
						+ "</p><input class = 'btt-notification-close' type='button' value='x' onclick = 'f_CLoseMessageBox(this);'></input></div>";
			}
			$("#result").html(resultHTML);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			resultHTML = "<div class='notification-box notification-box-error'><p>"
				+ "Thất Bại"
				+ "</p><input class = 'btt-notification-close' type='button' value='x' onclick = 'f_CLoseMessageBox(this);'></input></div>";	
			$("#result").html(resultHTML);
		}
	});
}