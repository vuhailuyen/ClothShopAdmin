package service.chart;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import dao.SanPhamDAO;

public class CategoryChartService {
	private JsonArray _jsonArray = null;
	private SanPhamDAO _sanPhamDAO = null;

	public String getTopTenCategoriesInStock() throws JsonGenerationException,
			JsonMappingException, IOException {
		_sanPhamDAO = new SanPhamDAO();
		_jsonArray = new JsonArray();
		@SuppressWarnings("unchecked")
		LinkedHashMap<String, String> topTenCategoriesInStock = (LinkedHashMap<String, String>) _sanPhamDAO
				.getTopTenCategoriesInStock();
		for (Map.Entry<String, String> entry : topTenCategoriesInStock.entrySet()) {
			JsonObject category = new JsonObject();
			category.addProperty("categoryName", entry.getKey());
			category.addProperty("quantity", entry.getValue());
			_jsonArray.add(category);
		}
		return _jsonArray.toString();
	}
}
