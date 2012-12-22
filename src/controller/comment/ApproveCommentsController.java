package controller.comment;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;
import java.util.ArrayList;

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

import util.Message;
import controller.GenericController;
import dao.BinhLuanDAO;

@Controller
public class ApproveCommentsController extends GenericController {
	@RequestMapping(value= {"/approve"}, method = POST)
	protected @ResponseBody
	String approveComments(@RequestBody String jSonIdList,
			HttpServletRequest arg0, HttpServletResponse response) 
					throws JsonGenerationException, JsonMappingException, IOException {
		return verifyComments(jSonIdList, new Integer(1), arg0, response);
	}
	@RequestMapping(value= {"/disapprove"}, method = POST)
	protected @ResponseBody
	String disapproveComments(@RequestBody String jSonIdList,
			HttpServletRequest arg0, HttpServletResponse response) 
					throws JsonGenerationException, JsonMappingException, IOException {
		return verifyComments(jSonIdList, new Integer(-1), arg0, response);
	}
	
	@SuppressWarnings("unchecked")
	String verifyComments(String jSonIdList, Integer value,
			HttpServletRequest arg0, HttpServletResponse response)
			throws JsonGenerationException, JsonMappingException, IOException {
		Message message = new Message();
		ObjectMapper objectMapper = new ObjectMapper();
		ArrayList<Integer> idList = new ArrayList<Integer>();
		ArrayList<String> idListString = new ArrayList<String>();
		idListString =  objectMapper.readValue(jSonIdList, ArrayList.class);
		for(String id : idListString){
			Integer _id = Integer.parseInt(id);
			idList.add(_id);
		}
		try {
			BinhLuanDAO _binhLuanDAO = new BinhLuanDAO();
			Integer affectedRows = _binhLuanDAO.verify(idList, value);
			message.put("result", "1");
			message.put("task", "approve");
			message.put("affected", affectedRows);

		} catch (HibernateException ex) {
			message.put("result", "0");
			message.put("task", "approve");
		}

		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		String out = objectMapper.writeValueAsString(message);
		System.out.println(out);
		return out;
	}
}
