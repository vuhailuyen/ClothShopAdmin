package controller.ad;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controller.GenericController;

@Controller
public class ListAdsController extends GenericController {
	private List<Advertisement> _listQuangCao = null;
	@RequestMapping(method = GET, params = { "page" })
	public ModelAndView listAds(
			@RequestParam(value = "page", required = false) Integer page,
			HttpServletRequest arg0, HttpServletResponse arg1) 
					throws JDOMException, IOException, ParseException {
		_modelAndView = new ModelAndView();
		getPathHelper(arg0);
		InputStream adsMapping = arg0.getServletContext().getResourceAsStream("/resources/advertisements.xml");
		_listQuangCao = getListAdvertisements(adsMapping);
		
		_modelAndView.addObject("listQuangCao", _listQuangCao);
		_modelAndView.setViewName("quangcao");
		return _modelAndView;
	}

	private List<Advertisement> getListAdvertisements(InputStream configFile) 
			throws JDOMException, IOException, ParseException {
		List<Advertisement> list = new ArrayList<Advertisement>();
		SAXBuilder builder = new SAXBuilder();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Document document = (Document) builder.build(configFile);
		Element rootNode = document.getRootElement();
		List<Element> listElement = rootNode.getChildren("ad");
		
		for (Element adNode : listElement) {
			String position = adNode.getChildText("position");
			String link = adNode.getChildText("link");
			String date = adNode.getChildText("date");
			Date _date = dateFormat.parse(date);
			Advertisement ad = new Advertisement();
			ad.setLink(link);
			ad.setPosition(position);
			ad.setDate(_date);
			list.add(ad);
		}
		return list;
	}
}
