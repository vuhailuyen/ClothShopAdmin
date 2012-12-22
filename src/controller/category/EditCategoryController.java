package controller.category;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pojos.LoaiSanPham;

import com.google.gson.JsonObject;

import controller.GenericController;
import dao.LoaiSanPhamDAO;

@Controller
public class EditCategoryController extends GenericController {
	private LoaiSanPhamDAO _loaiSanPhamDAO = null;
	
	@RequestMapping(method = POST, produces = "application/json; charset=utf-8")
	public @ResponseBody void updateCategory(
			@RequestBody String sanPhamJSON, 
			HttpServletRequest request,
			HttpServletResponse response) 
					throws JsonGenerationException, 
					JsonMappingException, 
					IOException,
					HibernateException{
		ObjectMapper objectMapper = new ObjectMapper();
		String out = "";
		LoaiSanPham loaiSanPham = new LoaiSanPham();
		_loaiSanPhamDAO = new LoaiSanPhamDAO();
		JsonObject _json = new JsonObject();	
		try {
			loaiSanPham = objectMapper.readValue(sanPhamJSON, LoaiSanPham.class);
			_loaiSanPhamDAO.save(loaiSanPham);	
			_json.addProperty("result", 1);
            _json.addProperty("task", loaiSanPham.getId());
            _json.addProperty("id", 1);
            _json.addProperty("content", "Vãi Cặc");
		} catch(HibernateException e) {	
			_json.addProperty("result", 0);
            _json.addProperty("task", "update");
            _json.addProperty("id", loaiSanPham.getId());
			e.printStackTrace();
		}
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		out = _json.toString();
		System.out.println(out);
		response.getWriter().write(out);
	}
}
