package service.chart;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import dao.ChiTietGioHangDAO;

public class ProductChartService {
	private ChiTietGioHangDAO _chiTietGioHangDAO = null;
	private JsonArray _jsonArray = null;
	public String getTopTenProducts() throws JsonGenerationException,
			JsonMappingException, IOException {
		_chiTietGioHangDAO = new ChiTietGioHangDAO();
		_jsonArray = new JsonArray();
		@SuppressWarnings("unchecked")
		LinkedHashMap<String, String> topTenProducts = (LinkedHashMap<String, String>) _chiTietGioHangDAO
				.getTopTenSellingProducts();
		for(Map.Entry<String, String> entry : topTenProducts.entrySet()){
			JsonObject product = new JsonObject();
			product.addProperty("productName", entry.getKey());
			product.addProperty("quantity", entry.getValue());
			_jsonArray.add(product);
		}
		return _jsonArray.toString();
	}
}
