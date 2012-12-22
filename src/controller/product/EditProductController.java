package controller.product;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pojos.DoiTuong;
import pojos.HinhAnhSanPham;
import pojos.LoaiSanPham;
import pojos.NhaSanXuat;
import pojos.SanPham;

import com.google.gson.JsonObject;

import controller.GenericController;
import dao.DoiTuongDAO;
import dao.HinhAnhSanPhamDAO;
import dao.LoaiSanPhamDAO;
import dao.NhaSanXuatDAO;
import dao.SanPhamDAO;

@Controller
public class EditProductController extends GenericController {
	private SanPhamDAO _sanPhamDAO = null;
	private SanPham _sanPham = null;
	private String _currentDate = null;
	
	private List<HinhAnhSanPham> _listHinhAnhSanPham = null;
	private List<DoiTuong> _listDoiTuong = null;
	private List<NhaSanXuat> _listNhaSanXuat = null;
	private List<LoaiSanPham> _listLoaiSanPham = null;

	public EditProductController() {

		_sanPhamDAO = new SanPhamDAO();
		_sanPham = new SanPham();
	}

	@RequestMapping(method = GET, params = { "id" })
	protected ModelAndView listProduct(@RequestParam(value = "id") Integer id,
			HttpServletRequest arg0, HttpServletResponse arg1) {
		_modelAndView = new ModelAndView();
		getReferenceData(id);
		getPathHelper(arg0);
		_sanPham = _sanPhamDAO.findById(id);
		_modelAndView.addObject("oldSanPham", _sanPham);
		_modelAndView.setViewName("sanpham_update");

		_modelAndView.addObject("currentDate", _currentDate);
		_modelAndView.addObject("listDoiTuong", _listDoiTuong);
		_modelAndView.addObject("listNhaSanXuat", _listNhaSanXuat);
		_modelAndView.addObject("listLoaiSanPham", _listLoaiSanPham);
		_modelAndView.addObject("listHinhAnhSanPham", _listHinhAnhSanPham);
		_modelAndView.setViewName("sanpham_update");
		return _modelAndView;
	}

	@RequestMapping(method = GET)
	protected ModelAndView listProduct(HttpServletRequest arg0,
			HttpServletResponse arg1) {
		_modelAndView = new ModelAndView();
		_modelAndView.setViewName("sanpham_update");
		return _modelAndView;
	}
	
	@RequestMapping(method = POST, produces = "application/json; charset=utf-8")
	public @ResponseBody void updateProduct(
			@RequestBody String sanPhamJSON, 
			HttpServletRequest request,
			HttpServletResponse response) 
					throws JsonGenerationException, 
					JsonMappingException, 
					IOException,
					HibernateException{
		ObjectMapper objectMapper = new ObjectMapper();
		String out = "";
		SanPham sanPham = new SanPham();
		_sanPhamDAO = new SanPhamDAO();
		JsonObject _json = new JsonObject();	
		try {
			sanPham = objectMapper.readValue(sanPhamJSON, SanPham.class);
			_sanPhamDAO.save(sanPham);	
			_json.addProperty("result", 1);
            _json.addProperty("task", sanPham.getId());
            _json.addProperty("id", 1);
            _json.addProperty("content", "Vãi Cặc");
		} catch(HibernateException e) {	
			_json.addProperty("result", 0);
            _json.addProperty("task", "update");
            _json.addProperty("id", sanPham.getId());
			e.printStackTrace();
		}
		//response.sendRedirect("ClothShopAdmin/default.html");
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		out = _json.toString();
		System.out.println(out);
		response.getWriter().write(out);
		//return out;
	}

	private void getReferenceData(Integer id) {
		DoiTuongDAO _dtDAO = new DoiTuongDAO();
		NhaSanXuatDAO _nsxDAO = new NhaSanXuatDAO();
		LoaiSanPhamDAO _lspDAO = new LoaiSanPhamDAO();
		HinhAnhSanPhamDAO _haspDAO = new HinhAnhSanPhamDAO();
		
		_listHinhAnhSanPham = new ArrayList<HinhAnhSanPham>();
		_listDoiTuong = new ArrayList<DoiTuong>();
		_listNhaSanXuat = new ArrayList<NhaSanXuat>();
		_listLoaiSanPham = new ArrayList<LoaiSanPham>();
		_listDoiTuong = _dtDAO.findAll();
		_listNhaSanXuat = _nsxDAO.findAll();
		_listLoaiSanPham = _lspDAO.findAll();
		_listHinhAnhSanPham = _haspDAO.findBySanPham(id);

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		_currentDate = dateFormat.format(date);
	}
}
