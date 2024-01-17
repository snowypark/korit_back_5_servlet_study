package com.study.servlet_study.utills;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ParamsConverter {
	
	public static Map<String, String> convertParamsMapToMap(Map<String, String[]> paramsMap) {
		Map<String, String> map = new HashMap<>();
		
		paramsMap.forEach((k, v) -> {
			StringBuilder builder = new StringBuilder();
			
			Arrays.asList(v).forEach(value -> builder.append(value));
			
			map.put(k, builder.toString());
		});
		
		return map;
	}

}
