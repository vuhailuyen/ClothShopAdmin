package controller.category;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;

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

import pojos.LoaiSanPham;
import util.Message;
import dao.LoaiSanPhamDAO;

@Controller
public class AddCategoryController {
	private LoaiSanPhamDAO _loaiSanPhamDAO = null;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = POST, produces=MediaType.ALL_VALUE)
	public @ResponseBody String addCategory(
			@RequestBody String loaiSanPhamJSON, 
			HttpServletRequest request,
			HttpServletResponse response) 
					throws JsonGenerationException, JsonMappingException, IOException,
					HibernateException{
		ObjectMapper objectMapper = new ObjectMapper();
		String out = "";
		LoaiSanPham loaiSanPham = new LoaiSanPham();
		_loaiSanPhamDAO = new LoaiSanPhamDAO();
		Message message = new Message();
		
		try {
			loaiSanPham = objectMapper.readValue(loaiSanPhamJSON, LoaiSanPham.class);
			Integer id = _loaiSanPhamDAO.insert(loaiSanPham);
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
}
