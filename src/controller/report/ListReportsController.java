package controller.report;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controller.GenericController;
import controller.ReportsProvider;

@Controller
public class ListReportsController extends GenericController {
	private List<Report> _listReport = null;
	private ReportsProvider _reportProvider = null;
	
	@RequestMapping(method = GET, params = { "page" })
	public ModelAndView listRreports(
			@RequestParam(value = "page", required = false) Integer page,
			HttpServletRequest arg0, HttpServletResponse arg1) throws Exception{
		InputStream reportConfig = arg0.getServletContext().getResourceAsStream("WEB-INF/config/reportsServerConfig.properties");
		InputStream reportMappingPath = arg0.getServletContext().getResourceAsStream("/resources/reportsMapping.xml");
		_modelAndView = new ModelAndView();	
		_reportProvider = new ReportsProvider();
		_reportProvider.loadConfig(reportConfig);	
		_listReport = _reportProvider.getReportList(reportMappingPath);		
		getPathHelper(arg0);
		_modelAndView.addObject("listBaoCao", _listReport);
		_modelAndView.setViewName("baocao");
		return _modelAndView;
	}
	
	
	
}
