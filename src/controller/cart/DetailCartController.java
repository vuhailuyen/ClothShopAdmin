package controller.cart;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pojos.ChiTietGioHang;
import pojos.GioHang;
import util.PagingHelper;
import controller.GenericController;
import dao.ChiTietGioHangDAO;
import dao.GioHangDAO;
@Controller
public class DetailCartController extends GenericController {
	private ChiTietGioHangDAO _chiTietGioHangDAO = null;
	private GioHangDAO _gioHangDAO = null;
	private List<ChiTietGioHang> _listChiTietGioHang = null;
	
	
	@RequestMapping(method = GET, params={"id", "page"}) 
	protected ModelAndView detailCart(
			@RequestParam(value = "id", required = true) Integer id, 
			@RequestParam(value = "page", required = true) Integer page, 
			HttpServletRequest arg0,
			HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException {
		_chiTietGioHangDAO = new ChiTietGioHangDAO();
		_gioHangDAO = new GioHangDAO();
		_modelAndView = new ModelAndView();
		_listChiTietGioHang = new ArrayList<ChiTietGioHang>();
		
		GioHang gioHang = new GioHang();
		gioHang = _gioHangDAO.findById(id);
		
		Long totalRecords = (Long) _chiTietGioHangDAO.countByCart(id);
		Integer totalPageInteger = PagingHelper.TotalPagesCaculator(_recordsPerPage, totalRecords);
		List<Integer> pagingNumbers = new ArrayList<Integer>();
		pagingNumbers = PagingHelper.PagingCaculator(page, _recordsPerPage,	totalRecords);
		_listChiTietGioHang = _chiTietGioHangDAO.findByCart(id, page, _recordsPerPage);
		getPathHelper(arg0);
		
		_modelAndView.addObject("listChiTietGioHang", _listChiTietGioHang);
		_modelAndView.addObject("pagingNumbers", pagingNumbers);
		_modelAndView.addObject("current", page);
		_modelAndView.addObject("gioHang", gioHang);
		_modelAndView.addObject("totalPage", totalPageInteger);
		_modelAndView.addObject("task", "view");
		_modelAndView.setViewName("giohang_detail");
		return _modelAndView;
	}
}
