package controller.user;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.text.DateFormat;
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

import pojos.ThanhVien;
import util.PagingHelper;
import util.StringConverterHelper;
import controller.GenericController;
import dao.ThanhVienDAO;

@Controller
public class ListUsersController extends GenericController {
	private ThanhVienDAO _thanhVienDAO = null;
	private ArrayList<ThanhVien> _listThanhVien = null;
	private String _currentDate = null;
	
	public ListUsersController(){
		_modelAndView = new ModelAndView();
		_thanhVienDAO = new ThanhVienDAO();
		_listThanhVien = new ArrayList<ThanhVien>();
	}
	
	@RequestMapping(method = GET, params = { "page" })
	public ModelAndView listUsers(@RequestParam(value = "page", required = false) Integer page,
			HttpServletRequest arg0, HttpServletResponse arg1){
		
		
		Long totalRecords = (Long) _thanhVienDAO.countAll(true);

		List<Integer> pagingNumbers = new ArrayList<Integer>();
		pagingNumbers = PagingHelper.PagingCaculator(page, _recordsPerPage,
				totalRecords);
		_listThanhVien = (ArrayList<pojos.ThanhVien>) _thanhVienDAO.findAll(page, _recordsPerPage);
		Integer totalPageInteger = PagingHelper.TotalPagesCaculator(_recordsPerPage, totalRecords);
		getReferenceData();
		getPathHelper(arg0);
	
		_modelAndView.addObject("listThanhVien", _listThanhVien);
		_modelAndView.addObject("pagingNumbers", pagingNumbers);
		_modelAndView.addObject("current", page);
		_modelAndView.addObject("totalPage", totalPageInteger);
		_modelAndView.addObject("currentDate", _currentDate);
		_modelAndView.addObject("task", "view");
		
		_modelAndView.setViewName("thanhvien");
		return _modelAndView;
	}
	@RequestMapping(method = GET, params = { "userId", "hoVaTen","tenDangNhap", "tinhTrang", "page" })
	protected ModelAndView filterUsers(
			@RequestParam(value = "page", required = true) Integer page,			
			@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "tinhTrang", required = true) Integer tinhTrang,
			@RequestParam(value = "hoVaTen", required = true) String hoVaTen,
			@RequestParam(value = "tenDangNhap", required = true) String tenDangNhap,
			HttpServletRequest arg0, HttpServletResponse arg1) {

		ThanhVien userQuery = getUserQuery(page, userId, tinhTrang, hoVaTen, tenDangNhap);
		
		Long totalRecords = (Long) _thanhVienDAO.countAll(userQuery);

		List<Integer> pagingNumbers = new ArrayList<Integer>();
		pagingNumbers = PagingHelper.PagingCaculator(page, _recordsPerPage,
				totalRecords);
		_listThanhVien = (ArrayList<pojos.ThanhVien>) _thanhVienDAO.searchByInstance(userQuery, page, _recordsPerPage);
		Integer totalPageInteger = PagingHelper.TotalPagesCaculator(
				_recordsPerPage, totalRecords);
		getReferenceData();
		
		_modelAndView.addObject("listThanhVien", _listThanhVien);
		_modelAndView.addObject("pagingNumbers", pagingNumbers);
		_modelAndView.addObject("current", page);
		_modelAndView.addObject("totalPage", totalPageInteger);
		_modelAndView.addObject("currentDate", _currentDate);
		_modelAndView.addObject("userQuery", userQuery);
		_modelAndView.addObject("task", "filter");
		_modelAndView.setViewName("thanhvien");

		return _modelAndView;
	}
	
	private ThanhVien getUserQuery(Integer page, String userId,
			Integer tinhTrang, String hoVaTen, String tenDangNhap) {
		Integer id = -1;
		if(StringConverterHelper.isInteger(userId)){
			id  = Integer.parseInt(userId);
		}
		ThanhVien tv = new ThanhVien();
		tv.setId(id);
		tv.setHoVaTen(hoVaTen);
		tv.setTinhTrang(tinhTrang);
		tv.setTenDangNhap(tenDangNhap);
		return tv;		
	}

	private void getReferenceData(){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		_currentDate = dateFormat.format(date);
	}
	
}
