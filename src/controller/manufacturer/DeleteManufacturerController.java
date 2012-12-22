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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import util.Message;
import controller.GenericController;
import dao.NhaSanXuatDAO;

@Controller
public class DeleteManufacturerController extends GenericController {
	@SuppressWarnings("unchecked")
	@RequestMapping(method = POST, params = { "id" })
	protected @ResponseBody
	String deleteManufacturer(@RequestParam(value = "id") Integer id,
			HttpServletRequest arg0, HttpServletResponse response)
			throws JsonGenerationException, JsonMappingException, IOException {
		Message message = new Message();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			NhaSanXuatDAO _nhaSanXuatDAO = new NhaSanXuatDAO();
			_nhaSanXuatDAO.deleteById(id);
			message.put("result", "1");
			message.put("task", "delete");

		} catch (HibernateException ex) {
			message.put("result", "0");
			message.put("task", "delete");
		}

		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		String out = objectMapper.writeValueAsString(message);
		System.out.println(out);
		return out;
	}
}
