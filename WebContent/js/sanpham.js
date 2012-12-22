function f_ToptenSellingProducts(){
	$.ajax({
		type : "POST",
		url : "chart?",
		data : "chartName=TopTenSellingProducts",
		dataType : "json",
		async : true,
		cache : false,
		timeout : 50000,
		success : function(data) {
			var jSonObject = data;	
			var productChartSeries = new Array();
			for(var i = 0 ; i < jSonObject.length ; i++){
				var productChartSerie = new Object();
				productChartSerie.name = jSonObject[i].productName;
				productChartSerie.data = new Array();
				productChartSerie.data.push(jSonObject[i].quantity);
				//alert(productChartSerie.data);
				productChartSerie.type = "column";
				productChartSeries.push(productChartSerie);
			}
			//alert(JSON.stringify(productChartSeries));
			$("#topTenSellingProducts").kendoChart({
				theme : $(document).data("kendoSkin") || "blueopal",
				title : {
					text : "Top 10 Sản Phẩm Bán Chạy Nhất"
				},
				legend : {
					position : "bottom"
				},
				chartArea : {
					background : ""
				},
				seriesDefaults : {
					type : "column"
				},
				series : productChartSeries,
				valueAxis : {
					labels : {
						format : "{000} đơn vị"
					}
				},
				categoryAxis : {
					categories : [ "Các Sản Phẩm" ],
					color : "#FF3366"
				},
				tooltip : {
					visible : true,
					format : "{000} đơn vị"
				}
			});
			$(".column").height($(document).height());
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// alert(XMLHttpRequest.readyState);
			var divResult = document.getElementById("result");
			var resultHTML = '';
			resultHTML = "<div class='notification-box notification-box-error'><p>"
					+ "Thất Bại"
					+ "</p><input class = 'btt-notification-close' type='button' value='x' onclick = 'f_CLoseMessageBox(this);'></input></div>";
			divResult.innerHTML = resultHTML;
		}
	});
}
// /////////////////////////////////////////////////////////////////////////////////////////////
function f_deleteUI(id) {
	var div = document.getElementById(id + 'deleteconfirm');
	var deleteUI = "<td colspan='7'><h3>Bạn Có Thật Sự Muốn Xóa¨?</h3></td>"
			+ "<td colspan='4'><input type='button' class='btt-refresh' value='Đồng Ý' onclick='f_delete("
			+ id
			+ ")'/></td>"
			+ "<td colspan='4'><input type='button' class='btt-chrome' value='Thoát' onclick='f_exitDeleteUI("
			+ id + ")'/></td>";
	div.innerHTML = deleteUI;
}
// /////////////////////////////////////////////////////////////////////////////////////////////
function f_exitDeleteUI(id) {
	var div = document.getElementById(id + 'deleteconfirm');
	div.innerHTML = "";
}
// /////////////////////////////////////////////////////////////////////////////////////////////
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
					var divResult = document.getElementById("result");
					var resultHTML = '';
					resultHTML = "<div class='notification-box notification-box-error'><p>"
							+ "Thất Bại"
							+ "</p><input class = 'btt-notification-close' type='button' value='x' onclick = 'f_CLoseMessageBox(this);'></input></div>";
					divResult.innerHTML = resultHTML;
				}
			});
}
// /////////////////////////////////////////////////////////////////////////////////////////////
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
// /////////////////////////////////////////////////////////////////////////////////////////////
function f_setImagesStatus(){
	var statues = $(".image-status");
	var imageStatues = new Array();
	var productId = $("#productId").val();
	statues.each(function(){
		var imageStatus = new Object();
		imageStatus.image = $(this).attr("data-id");
		imageStatus.status = $(this).val();
		imageStatues.push(imageStatus);
	});
	//alert(productId);
	$.ajax({
		type : "POST",
		url : "image/update-statues/"+productId,
		data : JSON.stringify(imageStatues),
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
function f_updateProduct() {
	var loaiSanPham = new Object();
	loaiSanPham.id = $("#loaiSanPham").val();
	var nhaSanXuat = new Object();
	nhaSanXuat.id = $("#nhaSanXuat").val();
	var doiTuong = new Object();
	doiTuong.id = $("#doiTuong").val();

	var sanPham = new Object();
	sanPham.id = $("#productId").val();
	sanPham.loaiSanPham = loaiSanPham;
	sanPham.nhaSanXuat = nhaSanXuat;
	sanPham.doiTuong = doiTuong;
	sanPham.giaBanDau = $("#giaBanDau").val();
	sanPham.tenSanPham = $("#tenSanPham").val();
	sanPham.giaHienTai = $("#giaHienTai").val();
	sanPham.soLuotXem = $("#soLuotXem").val();
	sanPham.soLuongDaBan = $("#doiTuong").val();
	sanPham.soLuongTon = $("#soLuongBan").val();
	sanPham.tinhTrang = $("#tinhTrang").val();
	var ngayCapNhat = new Date();
	sanPham.ngayCapNhat = ngayCapNhat;
	sanPham.moTa = $("#moTa").val();
	
	//alert(JSON.stringify(sanPham));
	$.ajax({
		type : "POST",
		url : "edit?",
		data : JSON.stringify(sanPham),
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
function f_updateImage(){
	var imageDivs = $(".image-link");
	var imageLinks = new Array();
	imageDivs.each(function(){
		imageLinks.push(this.value);
	});
	var id = $("#productId").val();
	$.ajax({
		type : "POST",
		url : "image/"+id+"/add",
		data : JSON.stringify(imageLinks),
		dataType : "json",
		contentType : 'application/json;charset=utf-8',
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
function f_insert() {
	var formData = $("#sanPhamThemMoi").serializeArray();
	var loaiSanPham = new Object();
	loaiSanPham.id = $("#loaiSanPham").val();
	var nhaSanXuat = new Object();
	nhaSanXuat.id = $("#nhaSanXuat").val();
	var doiTuong = new Object();
	doiTuong.id = $("#doiTuong").val();

	var sanPham = new Object();
	sanPham.loaiSanPham = loaiSanPham;
	sanPham.nhaSanXuat = nhaSanXuat;
	sanPham.doiTuong = doiTuong;
	sanPham.giaBanDau = $("#giaBanDau").val();
	sanPham.tenSanPham = $("#tenSanPham").val();
	sanPham.giaHienTai = $("#giaHienTai").val();
	sanPham.soLuotXem = $("#soLuotXem").val();
	sanPham.soLuongDaBan = $("#doiTuong").val();
	sanPham.soLuongTon = $("#soLuongBan").val();
	sanPham.tinhTrang = $("#tinhTrang").val();
	var ngayCapNhat = new Date();
	sanPham.ngayCapNhat = ngayCapNhat;
	sanPham.moTa = $("#moTa").val();

	formData = JSON.stringify(formData);

	// alert(JSON.stringify(sanPham));
	$.ajax({
		type : "POST",
		url : "add?",
		data : JSON.stringify(sanPham),
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
	// alert(formData);
}
function f_saveHandler(data) {
	var divResult = document.getElementById("result");
	var jSONObject = data;
	var code = jSONObject.result;
	var id = jSONObject.id;
	var message;
	if (code == 1) {
		message = "Thành Công. Click vào <a href='edit?id=" + id
				+ "'>đây</a> để tới trang chi tiết sản phẩm ";
	} else {
		message = "Thất Bại";
	}

	var resultHTML = '';

	if (code == 1) {
		// document.getElementById("id_sp").value = jSONObject.id;
		resultHTML = "<div class='notification-box notification-box-success'><p>"
				+ message
				+ "</p><input class = 'btt-notification-close' type='button' value='x' onclick = 'f_CLoseMessageBox(this);'></input></div>";
	}else{
		resultHTML = "<div class='notification-box notification-box-error'><p>"
				+ message
				+ "</p><input class = 'btt-notification-close' type='button' value='x' onclick = 'f_CLoseMessageBox(this);'></input></div>";
	}
	divResult.innerHTML = resultHTML;

}

function f_filter() {
	var filterParams = $("#filterForm").serialize();
	$.ajax({
		type : "GET",
		url : "list?",
		data : filterParams,
		dataType : "json",
		contentType : 'application/x-www-form-urlencoded',
		async : false,
		cache : false,
		timeout : 50000,

		success : function(data) {
			//f_saveHandler(data);
			console.log('filter');
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// alert(textStatus);
		}
	});
}

function f_ImageChange(id) {
	//alert("asas");
	var imageLink = $("#img_lnk_" + id).val();
	var img = $("#img_" + id);
	img.attr("src", imageLink);
	img.attr("width", "150px");
	img.attr("height", "150px");
}