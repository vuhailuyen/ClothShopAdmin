package controller.report;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import controller.ReportsProvider;

@Controller
public class ReportItemController {
	ReportsProvider _reportProvider = null;
	List<Report> _listReport = null;
	Report _report = null;
	
	@RequestMapping(value = "/{reportName}", method = GET, params={"type"})
	public void getReport(HttpServletRequest arg0, HttpServletResponse response,
			@PathVariable("reportName") String reportName,
			@RequestParam(value="type", required = true) String type) 
			throws Exception{
		//response.getWriter().write(reportName+ " " + type);
		InputStream reportMapping = arg0.getServletContext().getResourceAsStream("/resources/reportsMapping.xml");
		InputStream reportConfig = arg0.getServletContext().getResourceAsStream("/WEB-INF/config/reportsServerConfig.properties");
		_reportProvider = new ReportsProvider();
		_reportProvider.loadConfig(reportConfig);
		
		_report = new Report();
		
		_report = _reportProvider.getReportByName(reportName, reportMapping);
		_reportProvider.getReport(_report, type, null, response);
	}
}
