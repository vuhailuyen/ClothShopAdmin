package controller.product;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pojos.DoiTuong;
import pojos.LoaiSanPham;
import pojos.NhaSanXuat;
import pojos.SanPham;
import util.PagingHelper;
import util.StringConverterHelper;
import controller.GenericController;
import dao.DoiTuongDAO;
import dao.LoaiSanPhamDAO;
import dao.NhaSanXuatDAO;
import dao.SanPhamDAO;

@Controller
public class ListProductsController extends GenericController {
	private ArrayList<SanPham> _listSanPham = null;
	private SanPhamDAO _sanPhamDAO = null;
	

	private List<DoiTuong> _listDoiTuong = null;
	private List<NhaSanXuat> _listNhaSanXuat = null;
	private List<LoaiSanPham> _listLoaiSanPham = null;

	public ListProductsController() {
		_sanPhamDAO = new SanPhamDAO();
		_listSanPham = new ArrayList<SanPham>();
	}

	@RequestMapping(method = GET, params = { "page" })
	protected ModelAndView listProducts(
			@RequestParam(value = "page", required = false) Integer page,
			HttpServletRequest arg0, HttpServletResponse arg1) {

		_modelAndView = new ModelAndView();
		Long totalRecords = (Long) _sanPhamDAO.countAll(true);

		List<Integer> pagingNumbers = new ArrayList<Integer>();
		pagingNumbers = PagingHelper.PagingCaculator(page, _recordsPerPage,
				totalRecords);
		_listSanPham = (ArrayList<pojos.SanPham>) _sanPhamDAO.findAll(page,
				_recordsPerPage);
		Integer totalPageInteger = PagingHelper.TotalPagesCaculator(
				_recordsPerPage, totalRecords);
		getReferenceData();
		getPathHelper(arg0);
		_modelAndView.addObject("listDoiTuong", _listDoiTuong);
		_modelAndView.addObject("listNhaSanXuat", _listNhaSanXuat);
		_modelAndView.addObject("listLoaiSanPham", _listLoaiSanPham);
		_modelAndView.addObject("listSanPham", _listSanPham);
		_modelAndView.addObject("pagingNumbers", pagingNumbers);
		_modelAndView.addObject("current", page);
		_modelAndView.addObject("page", "sanpham_view");
		_modelAndView.addObject("totalPage", totalPageInteger);
		_modelAndView.setViewName("sanpham_view");

		return _modelAndView;
		//return listProducts(arg0, arg1);
	}

	@RequestMapping(method = GET, params = { "productId", "tenSanPham",
			"nhaSanXuat", "loaiSanPham", "doiTuong", "tinhTrang", "page" })
	protected ModelAndView filterProducts(
			@RequestParam(value = "page", required = true) Integer page,
			@RequestParam(value = "nhaSanXuat", required = true) Integer nhaSanXuat,
			@RequestParam(value = "productId", required = true) String productId,
			@RequestParam(value = "doiTuong", required = true) Integer doiTuong,
			@RequestParam(value = "loaiSanPham", required = true) Integer loaiSanPham,
			@RequestParam(value = "tenSanPham", required = true) String tenSanPham,
			@RequestParam(value = "tinhTrang", required = true) Integer tinhTrang,
			HttpServletRequest arg0, HttpServletResponse arg1) {

		_modelAndView = new ModelAndView();
		SanPham productQuery = getProductQuery(page, nhaSanXuat, productId,
				doiTuong, loaiSanPham, tinhTrang, tenSanPham);
		
		Long totalRecords = (Long) _sanPhamDAO.countAll(productQuery);

		List<Integer> pagingNumbers = new ArrayList<Integer>();
		pagingNumbers = PagingHelper.PagingCaculator(page, _recordsPerPage,
				totalRecords);
		_listSanPham = (ArrayList<pojos.SanPham>) _sanPhamDAO.searchByInstance(productQuery, page, _recordsPerPage);
		Integer totalPageInteger = PagingHelper.TotalPagesCaculator(
				_recordsPerPage, totalRecords);
		getReferenceData();
		getPathHelper(arg0);
		_modelAndView.addObject("listDoiTuong", _listDoiTuong);
		_modelAndView.addObject("listNhaSanXuat", _listNhaSanXuat);
		_modelAndView.addObject("listLoaiSanPham", _listLoaiSanPham);
		_modelAndView.addObject("listSanPham", _listSanPham);
		_modelAndView.addObject("pagingNumbers", pagingNumbers);
		_modelAndView.addObject("current", page);
		_modelAndView.addObject("totalPage", totalPageInteger);
		_modelAndView.addObject("productQuery", productQuery);
		_modelAndView.setViewName("sanpham_filter");

		return _modelAndView;
	}

	@RequestMapping(method = GET)
	protected ModelAndView listProducts(HttpServletRequest arg0,
			HttpServletResponse arg1) {
		_modelAndView = new ModelAndView();
		_modelAndView.setViewName("sanpham");
		return _modelAndView;
	}

	private void getReferenceData() {
		DoiTuongDAO _dtDAO = new DoiTuongDAO();
		NhaSanXuatDAO _nsxDAO = new NhaSanXuatDAO();
		LoaiSanPhamDAO _lspDAO = new LoaiSanPhamDAO();
		_listDoiTuong = new ArrayList<DoiTuong>();
		_listNhaSanXuat = new ArrayList<NhaSanXuat>();
		_listLoaiSanPham = new ArrayList<LoaiSanPham>();
		_listDoiTuong = _dtDAO.findAll();
		_listNhaSanXuat = _nsxDAO.findAll();
		_listLoaiSanPham = _lspDAO.findAll();

	}

	private SanPham getProductQuery(Integer page, Integer nhaSanXuat,
			String productId, Integer doiTuong, Integer loaiSanPham,
			Integer tinhTrang, String tenSanPham) {

		Integer id = -1;
		if(StringConverterHelper.isInteger(productId)){
			id  = Integer.parseInt(productId);
		}
		SanPham sp = new SanPham();
		sp.setId(id);
		sp.setTenSanPham(tenSanPham);
		sp.setLoaiSanPham(new LoaiSanPham(loaiSanPham));
		sp.setNhaSanXuat(new NhaSanXuat(nhaSanXuat));
		sp.setDoiTuong(new DoiTuong(doiTuong));
		sp.setTinhTrang(tinhTrang);
		return sp;
	}
}
