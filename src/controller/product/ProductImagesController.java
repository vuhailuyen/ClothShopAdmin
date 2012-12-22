package controller.product;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pojos.HinhAnhSanPham;
import pojos.SanPham;
import util.Message;
import controller.GenericController;
import dao.HinhAnhSanPhamDAO;

@Controller
public class ProductImagesController extends GenericController{
	private HinhAnhSanPhamDAO _hinhAnhSanPhamDAO = null;
	private ObjectMapper _objectMapper = null;
	private Message _message = null;
	//private String _currentDate = null;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/{id}/add", method = POST)
	public @ResponseBody  String addProductImages(
			@PathVariable("id") Integer id,
			@RequestBody String json,
			HttpServletRequest arg0, HttpServletResponse response) 
					throws JsonParseException, JsonMappingException, IOException{
		_hinhAnhSanPhamDAO = new HinhAnhSanPhamDAO();
		_objectMapper = new ObjectMapper();
		_message = new Message();
		
		ArrayList<String> __imageLinks = new ArrayList<String>();
		__imageLinks = _objectMapper.readValue(json, ArrayList.class);
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		try{
			for(String link: __imageLinks){
				getReferenceData();
				HinhAnhSanPham hinhAnh = new HinhAnhSanPham();
				hinhAnh.setSanPham(new SanPham(id));
				hinhAnh.setDuongDan(link);
				hinhAnh.setNgayCapNhat(new Date());
				hinhAnh.setTinhTrang(new Integer(0));
				_hinhAnhSanPhamDAO.insert(hinhAnh);
			}
			_message.put("result", "1");
			_message.put("task", "addProductImages");
			_message.put("id", id);
		}catch(HibernateException ex){
			ex.printStackTrace();
			_message.put("result", "0");
			_message.put("task", "addProductImages");
			_message.put("id", id);
		}
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		String out = _objectMapper.writeValueAsString(_message);
		System.out.println(out);
		return out;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/update-statues/{id}", method = POST)
	public @ResponseBody  String updateProductImagesStatues(
			@PathVariable("id") Integer id,
			@RequestBody String json,
			HttpServletRequest arg0, HttpServletResponse response) 
					throws JsonParseException, JsonMappingException, IOException{
		_hinhAnhSanPhamDAO = new HinhAnhSanPhamDAO();
		_objectMapper = new ObjectMapper();
		_message = new Message();
		
		List<HashMap<String, Integer>> __imageStatuess = new ArrayList<HashMap<String, Integer>>();
		__imageStatuess = _objectMapper.readValue(json, ArrayList.class);
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		try{
			for(HashMap<String, Integer> entry : __imageStatuess) {
				String imageId =  (String) entry.values().toArray()[0];
				String imageStatus = (String) entry.values().toArray()[1];
				Integer imgId = Integer.parseInt(imageId);
				Integer imgStatus = Integer.parseInt(imageStatus);
				HinhAnhSanPham hinhAnhSanPham = _hinhAnhSanPhamDAO.findById(imgId);
				hinhAnhSanPham.setTinhTrang(imgStatus);
				_hinhAnhSanPhamDAO.save(hinhAnhSanPham);
			}
			_message.put("result", "1");
			_message.put("task", "updateProductImagesStatues");
			_message.put("id", id);
		}catch(HibernateException ex){
			ex.printStackTrace();
			_message.put("result", "0");
			_message.put("task", "updateProductImagesStatues");
			_message.put("id", id);
		}
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		String out = _objectMapper.writeValueAsString(_message);
		System.out.println(out);
		return out;
	}
	private void getReferenceData() {
//		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//		Date date = new Date();
//		_currentDate = dateFormat.format(date);
	}
}
