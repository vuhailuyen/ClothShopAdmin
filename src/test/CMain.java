package test;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import controller.GenericController;
import dao.*;
import service.chart.*;
public class CMain extends GenericController{

	/**
	 * @param args
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		ProductChartService productChart = new ProductChartService();
		CategoryChartService chartService = new CategoryChartService();
		SanPhamDAO sanPhamDAO = new SanPhamDAO();
		sanPhamDAO.getTopTenCategoriesInStock();
		System.out.println(chartService.getTopTenCategoriesInStock());
	}
	public void a(){
		//getPathHelper();
		
	}
}
