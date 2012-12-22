/**
 * 
 */
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
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pojos.DoiTuong;
import pojos.LoaiSanPham;
import pojos.NhaSanXuat;
import pojos.SanPham;
import util.Message;
import controller.GenericController;
import dao.DoiTuongDAO;
import dao.LoaiSanPhamDAO;
import dao.NhaSanXuatDAO;
import dao.SanPhamDAO;

/**
 * @author ElC
 * 
 */
@Controller
public class AddProductController extends GenericController {
	private List<DoiTuong> _listDoiTuong = null;
	private List<NhaSanXuat> _listNhaSanXuat = null;
	private List<LoaiSanPham> _listLoaiSanPham = null;
	private SanPhamDAO _sanPhamDAO = null;
	private String _currentDate = null;

	@RequestMapping(method = GET)
	protected ModelAndView addProductUI(HttpServletRequest arg0,
			HttpServletResponse arg1) {
		_modelAndView = new ModelAndView();
		getReferenceData();
		_modelAndView.addObject("currentDate", _currentDate);
		_modelAndView.addObject("listDoiTuong", _listDoiTuong);
		_modelAndView.addObject("listNhaSanXuat", _listNhaSanXuat);
		_modelAndView.addObject("listLoaiSanPham", _listLoaiSanPham);
		_modelAndView.setViewName("sanpham_add");
		return _modelAndView;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = POST, produces=MediaType.ALL_VALUE)
	public @ResponseBody String addProduct(
			@RequestBody String sanPhamJSON, 
			HttpServletRequest request,
			HttpServletResponse response) 
					throws JsonGenerationException, JsonMappingException, IOException,
					HibernateException{
		ObjectMapper objectMapper = new ObjectMapper();
		String out = "";
		SanPham sanPham = new SanPham();
		_sanPhamDAO = new SanPhamDAO();
		Message message = new Message();
		
		try {
			sanPham = objectMapper.readValue(sanPhamJSON, SanPham.class);
			Integer id = _sanPhamDAO.insert(sanPham);
			message.put("result", "1");
			message.put("task", "add");
			message.put("id", id);
		} catch(HibernateException e) {	
			message.put("result", "0");
			message.put("task", "add");
			message.put("id", "-1");
			e.printStackTrace();
		}
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		out = objectMapper.writeValueAsString(message);
		System.out.println(out);
		return out;
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

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		_currentDate = dateFormat.format(date);
	}

}
