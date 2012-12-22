package controller.category;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.IOException;
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

import pojos.LoaiSanPham;
import util.PagingHelper;
import util.StringConverterHelper;
import controller.GenericController;
import dao.LoaiSanPhamDAO;

@Controller
public class ListCategoriesController extends GenericController {
	private LoaiSanPhamDAO _loaiSanPhamDAO = null;
	private ArrayList<LoaiSanPham> _listLoaiSanPham = null;
	private String _currentDate = null;
	
	public ListCategoriesController(){
		_loaiSanPhamDAO = new LoaiSanPhamDAO();
		_listLoaiSanPham = new ArrayList<LoaiSanPham>();
		_modelAndView = new ModelAndView();
		
	}
	@RequestMapping(method = GET, params = { "page" })
	protected ModelAndView listCategories(
			@RequestParam(value = "page", required = false) Integer page,
			HttpServletRequest arg0, HttpServletResponse arg1) throws IOException {

		Long totalRecords = (Long) _loaiSanPhamDAO.countAll(true);
		List<Integer> pagingNumbers = new ArrayList<Integer>();
		pagingNumbers = PagingHelper.PagingCaculator(page, _recordsPerPage,
				totalRecords);
		_listLoaiSanPham = (ArrayList<pojos.LoaiSanPham>) _loaiSanPhamDAO.findAll(page, _recordsPerPage);
		Integer totalPageInteger = PagingHelper.TotalPagesCaculator(_recordsPerPage, totalRecords);
		getReferenceData();
		getPathHelper(arg0);
		_modelAndView.addObject("listLoaiSanPham", _listLoaiSanPham);
		_modelAndView.addObject("pagingNumbers", pagingNumbers);
		_modelAndView.addObject("current", page);
		_modelAndView.addObject("totalPage", totalPageInteger);
		_modelAndView.addObject("currentDate", _currentDate);
		_modelAndView.addObject("task", "view");
		_modelAndView.setViewName("loaisanpham");

		return _modelAndView;
	}
	@RequestMapping(method = GET, params = { "categoryId", "tenLoai", "tinhTrang", "page" })
	protected ModelAndView filterCategories(
			@RequestParam(value = "page", required = true) Integer page,			
			@RequestParam(value = "categoryId", required = true) String categoryId,
			@RequestParam(value = "tinhTrang", required = true) Integer tinhTrang,
			@RequestParam(value = "tenLoai", required = true) String tenLoai,
			HttpServletRequest arg0, HttpServletResponse arg1) {

		_modelAndView = new ModelAndView();
		LoaiSanPham categoryQuery = getCategoryQuery(page, categoryId, tinhTrang, tenLoai);
		
		Long totalRecords = (Long) _loaiSanPhamDAO.countAll(categoryQuery);

		List<Integer> pagingNumbers = new ArrayList<Integer>();
		pagingNumbers = PagingHelper.PagingCaculator(page, _recordsPerPage,
				totalRecords);
		_listLoaiSanPham = (ArrayList<pojos.LoaiSanPham>) _loaiSanPhamDAO.searchByInstance(categoryQuery, page, _recordsPerPage);
		Integer totalPageInteger = PagingHelper.TotalPagesCaculator(
				_recordsPerPage, totalRecords);
		getReferenceData();
		getPathHelper(arg0);
		
		_modelAndView.addObject("listLoaiSanPham", _listLoaiSanPham);
		_modelAndView.addObject("pagingNumbers", pagingNumbers);
		_modelAndView.addObject("current", page);
		_modelAndView.addObject("totalPage", totalPageInteger);
		_modelAndView.addObject("currentDate", _currentDate);
		_modelAndView.addObject("categoryQuery", categoryQuery);
		_modelAndView.addObject("task", "filter");
		_modelAndView.setViewName("loaisanpham");

		return _modelAndView;
	}
	private void getReferenceData(){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		_currentDate = dateFormat.format(date);
	}
	private LoaiSanPham getCategoryQuery(Integer page, String categoryId, Integer tinhTrang, String tenLoai) {
		Integer id = -1;
		if(StringConverterHelper.isInteger(categoryId)){
			id  = Integer.parseInt(categoryId);
		}
		LoaiSanPham lsp = new LoaiSanPham();
		lsp.setId(id);
		lsp.setTenLoai(tenLoai);
		lsp.setTinhTrang(tinhTrang);
		System.out.println(tenLoai);
		return lsp;
	}
}
