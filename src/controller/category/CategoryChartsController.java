package controller.category;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import service.chart.CategoryChartService;

public class CategoryChartsController {
	private CategoryChartService _categoryChartService = null;
	
	@RequestMapping(method = POST, params = {"chartName"})
	public @ResponseBody  void chartsProvider(
			@RequestParam(value="chartName", required = true) String chartName, 
			HttpServletRequest request, HttpServletResponse response) 
					throws JsonGenerationException, JsonMappingException, IOException{
		_categoryChartService = new CategoryChartService();
		String out = "";
		try {
			if(chartName.equals("TopTenCategoriesInStock")){
				out = _categoryChartService.getTopTenCategoriesInStock();
			}
		} catch(Exception e) {	
			
			e.printStackTrace();
		}
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		System.out.println(out);
		response.getWriter().write(out);
	}
}
