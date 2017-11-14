package cn.edu.nju.dzy.domain.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

	private final static Logger log = LoggerFactory.getLogger(JsonUtils.class);

	public static String map2Json(Map<String, Object> map) {
		String json = "{}";
		try {

			ObjectMapper mapper = new ObjectMapper();
			// convert map to JSON string
			json = mapper.writeValueAsString(map);

			// json =
			// mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);

			// pretty print
			// System.out.println(json);

		} catch (JsonGenerationException e) {
			log.debug("Json malfunction ", e);

		} catch (JsonMappingException e) {
			log.debug("Json malfunction ", e);
		} catch (IOException e) {
			log.debug("Json malfunction ", e);
		}
		return json;
	}
	
	public static Map<String,Object> json2Map(String json){
		ObjectMapper mapper = new ObjectMapper();
		

		Map<String, Object> map = new HashMap<String, Object>();

		// convert JSON string to Map
		try {
			map = mapper.readValue(json, new TypeReference<Map<String, String>>(){});
		} catch (Exception e) {
			if (log.isDebugEnabled()){
				log.debug("Json malfunction:"+json , e);
			}
		}
		return map;
	}
}
