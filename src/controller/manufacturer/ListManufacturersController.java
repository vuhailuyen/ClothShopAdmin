package controller.manufacturer;

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

import pojos.NhaSanXuat;
import pojos.QuocGia;
import util.PagingHelper;
import util.StringConverterHelper;
import controller.GenericController;
import dao.NhaSanXuatDAO;
import dao.QuocGiaDAO;

@Controller
public class ListManufacturersController extends GenericController {
	private String _currentDate = null;
	private NhaSanXuatDAO _nhaSanXuatDAO = null;
	private QuocGiaDAO _quocGiaDAO = null;
	private ArrayList<NhaSanXuat> _listNhaSanXuat= null;
	private ArrayList<QuocGia> _listQuocGia= null;

	@RequestMapping(method = GET, params = { "page" })
	protected ModelAndView listManufacturers(
			@RequestParam(value = "page", required = false) Integer page,
			HttpServletRequest arg0, HttpServletResponse arg1) {

		_modelAndView = new ModelAndView();
		_nhaSanXuatDAO = new NhaSanXuatDAO();
		// Integer __pageInteger = Integer.parseInt(page);
		Long totalRecords = (Long) _nhaSanXuatDAO.countAll(true);

		List<Integer> pagingNumbers = new ArrayList<Integer>();
		pagingNumbers = PagingHelper.PagingCaculator(page, _recordsPerPage,
				totalRecords);
		_listNhaSanXuat = (ArrayList<pojos.NhaSanXuat>) _nhaSanXuatDAO.findAll(page, _recordsPerPage);
		Integer totalPageInteger = PagingHelper.TotalPagesCaculator(_recordsPerPage, totalRecords);
		getReferenceData();
		getPathHelper(arg0);
		
		_modelAndView.addObject("listNhaSanXuat", _listNhaSanXuat);
		_modelAndView.addObject("listQuocGia", _listQuocGia);
		_modelAndView.addObject("pagingNumbers", pagingNumbers);
		_modelAndView.addObject("current", page);
		_modelAndView.addObject("totalPage", totalPageInteger);
		_modelAndView.addObject("currentDate", _currentDate);
		_modelAndView.addObject("task", "view");
		_modelAndView.setViewName("nhasanxuat");

		return _modelAndView;
	}
	@RequestMapping(method = GET, params = { "manufacturerId", "tenNhaSanXuat","quocGia", "tinhTrang", "page" })
	protected ModelAndView filterManufacturers(
			@RequestParam(value = "page", required = true) Integer page,			
			@RequestParam(value = "manufacturerId", required = true) String manufacturerId,
			@RequestParam(value = "tinhTrang", required = true) Integer tinhTrang,
			@RequestParam(value = "tenNhaSanXuat", required = true) String tenNhaSanXuat,
			@RequestParam(value = "quocGia", required = true) Integer quocGia,
			HttpServletRequest arg0, HttpServletResponse arg1) {

		_modelAndView = new ModelAndView();
		_nhaSanXuatDAO = new NhaSanXuatDAO();
		NhaSanXuat manufacturerQuery = getManufacturerQuery(manufacturerId, tinhTrang, tenNhaSanXuat, quocGia);
		
		Long totalRecords = (Long) _nhaSanXuatDAO.countAll(manufacturerQuery);

		List<Integer> pagingNumbers = new ArrayList<Integer>();
		pagingNumbers = PagingHelper.PagingCaculator(page, _recordsPerPage,
				totalRecords);
		_listNhaSanXuat = (ArrayList<pojos.NhaSanXuat>) _nhaSanXuatDAO.searchByInstance(manufacturerQuery, page, _recordsPerPage);
		Integer totalPageInteger = PagingHelper.TotalPagesCaculator(
				_recordsPerPage, totalRecords);
		getReferenceData();
		
		_modelAndView.addObject("listQuocGia", _listQuocGia);
		_modelAndView.addObject("listNhaSanXuat", _listNhaSanXuat);
		_modelAndView.addObject("pagingNumbers", pagingNumbers);
		_modelAndView.addObject("current", page);
		_modelAndView.addObject("totalPage", totalPageInteger);
		_modelAndView.addObject("currentDate", _currentDate);
		_modelAndView.addObject("manufacturerQuery", manufacturerQuery);
		_modelAndView.addObject("task", "filter");
		_modelAndView.setViewName("nhasanxuat");

		return _modelAndView;
	}
	private void getReferenceData(){
		_quocGiaDAO = new QuocGiaDAO();
		_listQuocGia = new ArrayList<QuocGia>();
		_listQuocGia = (ArrayList<QuocGia>) _quocGiaDAO.findAll();
		System.out.println(_listQuocGia.size());
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		_currentDate = dateFormat.format(date);
	}
	private NhaSanXuat getManufacturerQuery(String manufacturerId, Integer tinhTrang, String tenNhaSanXuat, Integer quocGia) {
		Integer id = -1;
		if(StringConverterHelper.isInteger(manufacturerId)){
			id  = Integer.parseInt(manufacturerId);
		}
		NhaSanXuat nsx = new NhaSanXuat();
		nsx.setId(id);
		nsx.setTenNhaSanXuat(tenNhaSanXuat);
		nsx.setTinhTrang(tinhTrang);
		nsx.setQuocGia(new QuocGia(quocGia));
		return nsx;
	}
}
