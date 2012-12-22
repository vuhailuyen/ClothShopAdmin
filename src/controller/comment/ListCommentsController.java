package controller.comment;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pojos.BinhLuan;
import pojos.SanPham;
import pojos.ThanhVien;
import util.PagingHelper;
import controller.GenericController;
import dao.BinhLuanDAO;
@Controller
public class ListCommentsController extends GenericController {
	private String _currentDate = null;
	private BinhLuanDAO _binhLuanDAO = null;
	private ArrayList<BinhLuan> _listBinhLuan = null;
	
	@RequestMapping(method = GET, params = { "page" })
	protected ModelAndView listManufacturers(
			@RequestParam(value = "page", required = false) Integer page,
			HttpServletRequest arg0, HttpServletResponse arg1) {

		_modelAndView = new ModelAndView();
		_binhLuanDAO = new BinhLuanDAO();
		_listBinhLuan = new ArrayList<BinhLuan>();
		_recordsPerPage = 30;
		
		Long totalRecords = (Long) _binhLuanDAO.countAll(false);

		List<Integer> pagingNumbers = new ArrayList<Integer>();
		pagingNumbers = PagingHelper.PagingCaculator(page, _recordsPerPage,
				totalRecords);
		_listBinhLuan = (ArrayList<pojos.BinhLuan>) _binhLuanDAO.findAll(page, _recordsPerPage);
		Integer totalPageInteger = PagingHelper.TotalPagesCaculator(_recordsPerPage, totalRecords);
		getReferenceData();
		getPathHelper(arg0);
		
		_modelAndView.addObject("listBinhLuan", _listBinhLuan);
		_modelAndView.addObject("pagingNumbers", pagingNumbers);
		_modelAndView.addObject("current", page);
		_modelAndView.addObject("totalPage", totalPageInteger);
		_modelAndView.addObject("currentDate", _currentDate);
		_modelAndView.addObject("task", "view");
		_modelAndView.setViewName("binhluan");

		return _modelAndView;
	}
	
	@RequestMapping(method = GET, params = { "sanPham", "thanhVien", "ngayDang", "tinhTrang", "page" })
	protected ModelAndView filterComments(
			@RequestParam(value = "page", required = true) Integer page,			
			@RequestParam(value = "sanPham", required = true) String sanPham,
			@RequestParam(value = "thanhVien", required = true) String thanhVien,
			@RequestParam(value = "ngayDang", required = true) String ngayDang,
			@RequestParam(value = "tinhTrang", required = true) Integer tinhTrang,
			HttpServletRequest arg0, HttpServletResponse arg1) throws ParseException {

		_modelAndView = new ModelAndView();
		_binhLuanDAO = new BinhLuanDAO();
		BinhLuan commentQuery = getCommentQuery(sanPham, thanhVien, ngayDang, tinhTrang);
		
		Long totalRecords = (Long) _binhLuanDAO.countAll(commentQuery);

		List<Integer> pagingNumbers = new ArrayList<Integer>();
		pagingNumbers = PagingHelper.PagingCaculator(page, _recordsPerPage, totalRecords);
		_listBinhLuan = (ArrayList<pojos.BinhLuan>) _binhLuanDAO.searchByInstance(commentQuery, page, _recordsPerPage);
		Integer totalPageInteger = PagingHelper.TotalPagesCaculator(_recordsPerPage, totalRecords);
		getReferenceData();
		getPathHelper(arg0);
		
		_modelAndView.addObject("listBinhLuan", _listBinhLuan);
		_modelAndView.addObject("pagingNumbers", pagingNumbers);
		_modelAndView.addObject("current", page);
		_modelAndView.addObject("totalPage", totalPageInteger);
		_modelAndView.addObject("currentDate", _currentDate);
		_modelAndView.addObject("commentQuery", commentQuery);
		_modelAndView.addObject("task", "filter");
		_modelAndView.setViewName("binhluan");

		return _modelAndView;
	}
	
	private void getReferenceData(){
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		_currentDate = dateFormat.format(date);
	}
	
	private BinhLuan getCommentQuery(String tenSanPham, String tenDangNhap, String ngayDang, Integer tinhTrang) 
			throws ParseException {
		BinhLuan bl = new BinhLuan();
		ThanhVien tv = new ThanhVien();
		SanPham sp = new SanPham();
		tv.setTenDangNhap(tenDangNhap);
		sp.setTenSanPham(tenSanPham);
		bl.setSanPham(sp);
		bl.setThanhVien(tv);
		if(!ngayDang.equals("")){
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date _ngayDang = dateFormat.parse(ngayDang);
			bl.setNgayDang(_ngayDang);
		}
		bl.setTinhTrang(tinhTrang);
		
		return bl;
	}
	
}


