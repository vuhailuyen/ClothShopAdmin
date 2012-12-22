package controller.manufacturer;

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

import pojos.NhaSanXuat;

import com.google.gson.JsonObject;

import controller.GenericController;
import dao.NhaSanXuatDAO;
@Controller
public class EditManufaturerController extends GenericController {
private NhaSanXuatDAO _nhaSanXuatDAO = null;
	
	@RequestMapping(method = POST, produces = "application/json; charset=utf-8")
	public @ResponseBody void updateManufacturer(
			@RequestBody String sanPhamJSON, 
			HttpServletRequest request,
			HttpServletResponse response) 
					throws JsonGenerationException, 
					JsonMappingException, 
					IOException,
					HibernateException{
		ObjectMapper objectMapper = new ObjectMapper();
		String out = "";
		NhaSanXuat nhaSanXuat = new NhaSanXuat();
		_nhaSanXuatDAO = new NhaSanXuatDAO();
		JsonObject _json = new JsonObject();	
		try {
			nhaSanXuat = objectMapper.readValue(sanPhamJSON, NhaSanXuat.class);
			_nhaSanXuatDAO.save(nhaSanXuat);	
			_json.addProperty("result", 1);
            _json.addProperty("task", nhaSanXuat.getId());
            _json.addProperty("id", 1);
            _json.addProperty("content", "Vãi Cặc");
		} catch(HibernateException e) {	
			_json.addProperty("result", 0);
            _json.addProperty("task", "update");
            _json.addProperty("id", nhaSanXuat.getId());
			e.printStackTrace();
		}
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		out = _json.toString();
		System.out.println(out);
		response.getWriter().write(out);
	}
}
