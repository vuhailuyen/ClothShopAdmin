var Class = function(methods) {
	var klass = function() {
		this.initialize.apply(this, arguments);
	};

	for ( var property in methods) {
		klass.prototype[property] = methods[property];
	}

	if (!klass.prototype.initialize)
		klass.prototype.initialize = function() {
		};

	return klass;
};
var Product = Class({
	initialize : function(id, doiTuong, nhaSanXuat, loaiSanPham, tenSanPham,
			moTa, giaBanDau, giaHienTai, tinhTrang, ngayCapNhat, soLuotXem,
			soLuongTon, soLuongDaBan) {
		this.id = id;
		if(doiTuong){
			this.doiTuong = doiTuong;
		}
		
		this.nhaSanXuat = nhaSanXuat;
		this.loaiSanPham = loaiSanPham;
		this.tenSanPham = tenSanPham;
		this.moTa = moTa;
		this.giaBanDau = giaBanDau;
		this.giaHienTai = giaHienTai;
		this.tinhTrang = tinhTrang;
		this.ngayCapNhat = ngayCapNhat;
		this.soLuotXem = soLuotXem;
		this.soLuongTon = soLuongTon;
		this.soLuongDaBan = soLuongDaBan;
	},
	initialize : function(doiTuong, nhaSanXuat, loaiSanPham, tenSanPham, moTa,
			giaBanDau, giaHienTai, tinhTrang, ngayCapNhat, soLuotXem,
			soLuongTon, soLuongDaBan) {
		this.doiTuong = doiTuong;
		this.nhaSanXuat = nhaSanXuat;
		this.loaiSanPham = loaiSanPham;
		this.tenSanPham = tenSanPham;
		this.moTa = moTa;
		this.giaBanDau = giaBanDau;
		this.giaHienTai = giaHienTai;
		this.tinhTrang = tinhTrang;
		this.ngayCapNhat = ngayCapNhat;
		this.soLuotXem = soLuotXem;
		this.soLuongTon = soLuongTon;
		this.soLuongDaBan = soLuongDaBan;
	},
	initialize : function(id) {
		this.id = id;
	},
	toString : function() {
		return "Product";
	}
});

var Category = Class({
	initialize : function(id) {
		this.id = id;
	},
	initialize : function(id, tenLoai, ngayCapNhat, tinhTrang) {
		this.id = id;
		this.tenLoai = tenLoai;
		this.ngayCapNhat = ngayCapNhat;
		this.tinhTrang = tinhTrang;
	},
	toString : function() {
		return "Category";
	}
});