// /////////////////////////////////////////////////////////////////////////////////////////////
function f_deleteUI(id) {
	var div = document.getElementById(id + 'deleteconfirm');
	var deleteUI = "<td colspan='3'><h3>Bạn Có Thật Sự Muốn Xóa¨?</h3></td>"
			+ "<td colspan='2'><input type='button' class='btt-refresh' value='Đồng Ý' onclick='f_delete("
			+ id
			+ ")'/></td>"
			+ "<td colspan='2'><input type='button' class='btt-chrome' value='Thoát' onclick='f_exitDeleteUI("
			+ id + ")'/></td>";
	div.innerHTML = deleteUI;
}
///////////////////////////////////////////////////////////////////////////////////////////////
function f_editUI(id) {
	var div = document.getElementById(id + 'editconfirm');
	var date = new Date();
	var quocGiaSelectHTML = $("#quocGia").html();
	var tenNhaSanXuat = $("#"+id+"-old-tenNhaSanXuat").html();
	var email = $("#"+id+"-old-email").html();
	var soDienThoai = $("#"+id+"-old-soDienThoai").html();
	var diaChi = $("#"+id+"-old-diaChi").html();
	//alert(quocGiaSelectHTML);
	var editUI = "<td class='id_col'></td><td class='id_col'></td><td lass='ncn_col'><input type='text' size='15' value='"+tenNhaSanXuat+"' id='tenNhaSanXuatChinhSua'/></td>"
			+"<td><input type='text' value='"+diaChi+"' id='diaChiChinhSua'/></td>"
			+"<td><input type='text' value='"+email+"' id='emailChinhSua'/></td>"
			+"<td><input type='text' size='15' value='"+soDienThoai+"' id='soDienThoaiChinhSua'/></td>"
			+"<td class='ncn_col'><select id='quocGiaChinhSua'> </select></td>"
		+"<td class='ncn_col'><input type='text' size='15' value='"+date.format("dd/mm/yyyy")+"'/></td>"
			+"<td class='id_col'><select id='tinhTrangChinhSua'>"
                                +"<option value='1'>1</option>"
                                +"<option value='0'>0</option>"
                                +"</select></td>"
			+ "<td class='id_col'><input type='button' class='btt-refresh' value='Đồng Ý' onclick='f_edit(" + id + ")'/></td>"	
			+ "<td class='id_col'><input type='button' class='btt-chrome' value='Thoát' onclick='f_exitEditUI("
			+ id + ")'/></td>";
	div.innerHTML = editUI;
	$("#quocGiaChinhSua").html(quocGiaSelectHTML);
}
// /////////////////////////////////////////////////////////////////////////////////////////////
function f_exitDeleteUI(id) {
	var div = document.getElementById(id + 'deleteconfirm');
	div.innerHTML = "";
}
///////////////////////////////////////////////////////////////////////////////////////////////
function f_exitEditUI(id) {
	var div = document.getElementById(id + 'editconfirm');
	div.innerHTML = "";
}
function f_insert() {
	var nhaSanXuat = new Object();
	nhaSanXuat.tenNhaSanXuat = $("#tenNhaSanXuat").val();
	var ngayCapNhat = new Date();
	var quocGia = new Object();
	quocGia.id = $("#quocGia").val();
	nhaSanXuat.ngayCapNhat = ngayCapNhat;
	nhaSanXuat.tinhTrang = $("#tinhTrangThemMoi").val();
	nhaSanXuat.diaChi = $("#diaChi").val();
	nhaSanXuat.email = $("#email").val();
	nhaSanXuat.soDienThoai = $("#soDienThoai").val();
	nhaSanXuat.quocGia = quocGia;
	//alert(JSON.stringify(nhaSanXuat));
	$.ajax({
		type : "POST",
		url : "add?",
		data : JSON.stringify(nhaSanXuat),
		dataType : "json",
		contentType : 'application/json',
		async : true,
		cache : false,
		timeout : 50000,

		success : function(data) {
			f_saveHandler(data);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// alert(textStatus);
		}
	});
}
///////////////////////////////////////////////////////////////////////////////////////////////
function f_delete(id) {
	$
			.ajax({
				type : "POST",
				url : "delete?",
				data : "id=" + id,
				dataType : "json",
				async : true,
				cache : false,
				timeout : 50000,
				success : function(data) {
					f_deleteHandler(data, id);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					// alert(XMLHttpRequest.readyState);	
				}
			});
}
function f_edit(id) {
	var nhaSanXuat = new Object();
	nhaSanXuat.tenNhaSanXuat = $("#tenNhaSanXuatChinhSua").val();
	var ngayCapNhat = new Date();
	var quocGia = new Object();
	quocGia.id = $("#quocGiaChinhSua").val();
	nhaSanXuat.ngayCapNhat = ngayCapNhat;
	nhaSanXuat.tinhTrang = $("#tinhTrangChinhSua").val();
	nhaSanXuat.diaChi = $("#diaChiChinhSua").val();
	nhaSanXuat.email = $("#emailChinhSua").val();
	nhaSanXuat.soDienThoai = $("#soDienThoaiChinhSua").val();
	nhaSanXuat.quocGia = quocGia;
	nhaSanXuat.id = id;
	
	//alert(JSON.stringify(nhaSanXuat));
	$.ajax({
		type : "POST",
		url : "edit?",
		data : JSON.stringify(nhaSanXuat),
		dataType : "json",
		contentType : 'application/json',
		async : true,
		cache : false,
		timeout : 50000,

		success : function(data) {
			f_exitEditUI(id);
			var divResult = document.getElementById("result");
			// alert(returnValue);
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
			divResult.innerHTML = resultHTML;
			setTimeout('location.reload()', 1000);
			
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// alert(textStatus);
		}
	});
}
function f_saveHandler(data) {
	var divResult = document.getElementById("result");
	var jSONObject = data;
	var code = jSONObject.result;
	var id = jSONObject.id;
	var message;
	if (code == 1) {
		message = "Thành Công. Click vào <a href='list?id=" + id
				+ "&page=1'>đây</a> để tới trang chi tiết sản phẩm ";
	} else {
		message = "Thất Bại";
	}

	var resultHTML = '';

	if (code == 1) {
		// document.getElementById("id_sp").value = jSONObject.id;
		resultHTML = "<div class='notification-box notification-box-success'><p>"
				+ message
				+ "</p><input class = 'btt-notification-close' type='button' value='x' onclick = 'f_CLoseMessageBox(this);'></input></div>";
	} else {
		resultHTML = "<div class='notification-box notification-box-error'><p>"
				+ message
				+ "</p><input class = 'btt-notification-close' type='button' value='x' onclick = 'f_CLoseMessageBox(this);'></input></div>";
	}
	divResult.innerHTML = resultHTML;

}
function f_deleteHandler(data, id) {

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
		f_exitDeleteUI(id);
	}
	if (code == 0) {
		resultHTML = "<div class='notification-box notification-box-error'><p>"
				+ message
				+ "</p><input class = 'btt-notification-close' type='button' value='x' onclick = 'f_CLoseMessageBox(this);'></input></div>";
	}
	divResult.innerHTML = resultHTML;

}
