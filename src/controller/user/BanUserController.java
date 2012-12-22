package controller.user;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.HibernateException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pojos.ThanhVien;
import util.Message;
import controller.GenericController;
import dao.ThanhVienDAO;

public class BanUserController extends GenericController {
	@SuppressWarnings("unchecked")
	@RequestMapping(method = POST, params = { "id", "date" })
	protected @ResponseBody
	String deleteManufacturer(@RequestParam(value = "id") Integer id,
			@RequestParam(value = "date") String date,
			HttpServletRequest arg0, HttpServletResponse response)
			throws JsonGenerationException, JsonMappingException, IOException, ParseException {
		Message message = new Message();
		ObjectMapper objectMapper = new ObjectMapper();
		
		Date expiredDate = new SimpleDateFormat("dd/mm/yyy").parse(date);
		ThanhVien thanhVien = new ThanhVien();
		ThanhVienDAO _thanhVienDAO = new ThanhVienDAO();
		thanhVien = _thanhVienDAO.findById(id);
		thanhVien.setTinhTrang(new Integer(0));
		thanhVien.setNgayHetHanBan(expiredDate);
		
		try {			
			_thanhVienDAO.save(thanhVien);
			message.put("result", "1");
			message.put("task", "ban");
			message.put("id", id);

		} catch (HibernateException ex) {
			message.put("result", "0");
			message.put("task", "ban");
			message.put("id", id);
		}

		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		String out = objectMapper.writeValueAsString(message);
		System.out.println(out);
		return out;
	}
}
