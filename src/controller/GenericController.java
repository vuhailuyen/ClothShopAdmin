package controller;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

public class GenericController {
	protected ModelAndView _modelAndView;
	protected Integer _recordsPerPage = 25;
	protected Properties _pathHelper;
	
	protected void getPathHelper(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		String logOutURL = String.format("%s/authentication/logout", contextPath);
		_pathHelper = new Properties();
		try {
			_pathHelper.load(request.getServletContext().getResourceAsStream("/WEB-INF/config/pathMappings.properties"));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error Loading Properties File");
		}
		if(_modelAndView != null){
			_modelAndView.addObject("pathMappings", _pathHelper);
			_modelAndView.addObject("logout", logOutURL);
		}
	}
}
