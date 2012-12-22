package controller.authentication;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import controller.GenericController;

@Controller
public class LoginController extends GenericController {
	@RequestMapping(method = GET)
	public ModelAndView login(HttpServletRequest arg0,
			HttpServletResponse arg1){
		_modelAndView = new ModelAndView();
		_modelAndView.addObject("success", "1");
		_modelAndView.setViewName("login");
		return _modelAndView;
	}
	
	@RequestMapping(value="/failed", method = GET)
	public ModelAndView loginFail(HttpServletRequest arg0,
			HttpServletResponse arg1){
		_modelAndView = new ModelAndView();
		_modelAndView.addObject("success", "0");
		_modelAndView.setViewName("login");
		return _modelAndView;
	}
}
