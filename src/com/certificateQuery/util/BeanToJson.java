package com.certificateQuery.util;

import java.util.ArrayList;
import java.util.List;

import com.certificateQuery.model.WorkBean;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BeanToJson {
	public static String WorkBeanToJson(List<WorkBean> list){
		if(list.size() == 0)
			return "{\"result\":\"false\"}";
		List<String> jsons = new ArrayList<String>();
		for(int i = 0; i<list.size(); i++) {
			WorkBean work = list.get(i);
			ObjectMapper objectMapper = new ObjectMapper();
			try {
				jsons.add(i,objectMapper.writeValueAsString(work));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		String result = "{\"result\":\"true\","
				+ "\"p\":[";
		for(int i = 0; i<jsons.size(); i++) {
			result += jsons.get(i) + ",";
			
		}
		result = result.substring(0, result.length()-1);
		result += "]}";
		System.out.println(result);
		return result;
	}
}
