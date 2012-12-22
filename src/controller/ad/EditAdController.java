package controller.ad;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import util.Message;
import controller.GenericController;

@Controller
public class EditAdController extends GenericController {
	@SuppressWarnings("unchecked")
	@RequestMapping(method = POST, produces = "application/json; charset=utf-8")
	public @ResponseBody
	String editAd(@RequestBody String adJSON, HttpServletRequest request,
			HttpServletResponse response) throws JsonParseException,
			JsonMappingException, IOException, JDOMException {
		ObjectMapper objectMapper = new ObjectMapper();
		Advertisement ad = new Advertisement();
		Message message = new Message();
		ad = objectMapper.readValue(adJSON, Advertisement.class);

		try {
			ad = objectMapper.readValue(adJSON, Advertisement.class);
			String filePath = request.getServletContext().getRealPath("/resources/advertisements.xml");
			System.out.println(filePath);
			editAd(ad, filePath);
			message.put("result", "1");
			message.put("task", "ban");
			message.put("postion", ad.getPosition());
			//message.put("date", date);

		} catch (JDOMException ex) {
			message.put("result", "0");
			message.put("task", "ban");
			message.put("postion", ad.getPosition());
			//ex.printStackTrace();
		}

		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		String out = objectMapper.writeValueAsString(message);
		System.out.println(out);
		return out;

	}

	private void editAd(Advertisement ad, String filePath)
			throws JDOMException, IOException {
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File(filePath);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String date = dateFormat.format(ad.getDate());
		Document doc = (Document) builder.build(xmlFile);
		Element rootNode = doc.getRootElement();
		List<Element> listElement = rootNode.getChildren("ad");
		for (Element adNode : listElement) {
			String position = adNode.getChildText("position");
			if (position.equals(ad.getPosition())) {
				adNode.getChild("link").setText(ad.getLink());
				adNode.getChild("date").setText(date);
				XMLOutputter xmlOutput = new XMLOutputter();
				xmlOutput.setFormat(Format.getPrettyFormat());
				xmlOutput.output(doc, new FileWriter(filePath));
				break;
			}
		}
	}
}
