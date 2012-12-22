package controller.cart;

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

import pojos.GioHang;
import pojos.ThanhVien;
import util.PagingHelper;
import controller.GenericController;
import dao.GioHangDAO;

@Controller
public class ListCartsController extends GenericController {
	private GioHangDAO _gioHangDAO = null;
	private ArrayList<GioHang> _listGioHang = null;
	private String _currentDate = null;	

	@RequestMapping(method = GET, params = { "page" })
	public ModelAndView listCarts(@RequestParam(value = "page", required = false) Integer page,
			HttpServletRequest arg0, HttpServletResponse arg1){
		getPathHelper(arg0);
		_modelAndView = new ModelAndView();
		_listGioHang = new ArrayList<GioHang>();
		_gioHangDAO = new GioHangDAO();
		
		Long totalRecords = (Long) _gioHangDAO.countAll(false);
		List<Integer> pagingNumbers = new ArrayList<Integer>();
		pagingNumbers = PagingHelper.PagingCaculator(page, _recordsPerPage,	totalRecords);
		_listGioHang = (ArrayList<pojos.GioHang>) _gioHangDAO.findAll(page, _recordsPerPage);
		Integer totalPageInteger = PagingHelper.TotalPagesCaculator(_recordsPerPage, totalRecords);
		
		getPathHelper(arg0);
		getReferenceData();
		
		_modelAndView.addObject("listGioHang", _listGioHang);
		_modelAndView.addObject("pagingNumbers", pagingNumbers);
		_modelAndView.addObject("current", page);
		_modelAndView.addObject("totalPage", totalPageInteger);
		_modelAndView.addObject("currentDate", _currentDate);
		_modelAndView.addObject("task", "view");
		_modelAndView.setViewName("giohang");
		return _modelAndView;
	}
	

	@RequestMapping(method = GET, params = {"thanhVien","ngayGhiNhan","tinhTrang", "page" })
	public ModelAndView filterCarts(
			@RequestParam(value = "page", required = true) Integer page,			
			@RequestParam(value = "thanhVien", required = true) String thanhVien,
			@RequestParam(value = "tinhTrang", required = true) Integer tinhTrang,
			@RequestParam(value = "ngayGhiNhan", required = true) String ngayGhiNhan,
			HttpServletRequest arg0, HttpServletResponse arg1) throws ParseException{
		
		_modelAndView = new ModelAndView();
		_listGioHang = new ArrayList<GioHang>();
		_gioHangDAO = new GioHangDAO();
		
		GioHang cartQuery = getCartQuery(thanhVien, tinhTrang, ngayGhiNhan);
		
		Long totalRecords = (Long) _gioHangDAO.countAll(cartQuery);

		List<Integer> pagingNumbers = new ArrayList<Integer>();
		pagingNumbers = PagingHelper.PagingCaculator(page, _recordsPerPage,
				totalRecords);
		_listGioHang = (ArrayList<pojos.GioHang>) _gioHangDAO.searchByInstance(cartQuery, page, _recordsPerPage);
		Integer totalPageInteger = PagingHelper.TotalPagesCaculator(_recordsPerPage, totalRecords);
		
		getPathHelper(arg0);
		_modelAndView.addObject("listGioHang", _listGioHang);
		_modelAndView.addObject("pagingNumbers", pagingNumbers);
		_modelAndView.addObject("current", page);
		_modelAndView.addObject("totalPage", totalPageInteger);
		_modelAndView.addObject("currentDate", _currentDate);
		_modelAndView.addObject("cartQuery", cartQuery);
		_modelAndView.addObject("task", "filter");
		_modelAndView.setViewName("giohang");
		return _modelAndView;
	}
	private GioHang getCartQuery( String thanhVien,
			Integer tinhTrang, String ngayGhiNhan) throws ParseException {
		GioHang gh = new GioHang();
		ThanhVien tv = new ThanhVien();
		
		gh.setTinhTrang(tinhTrang);
		tv.setTenDangNhap(thanhVien);
		gh.setThanhVien(tv);
		if(!ngayGhiNhan.equals("")){
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date _ngayGhiNhan = dateFormat.parse(ngayGhiNhan);
			gh.setNgayCapNhat(_ngayGhiNhan);
		}
		return gh;
	}
	private void getReferenceData(){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		_currentDate = dateFormat.format(date);
	}
	

}
