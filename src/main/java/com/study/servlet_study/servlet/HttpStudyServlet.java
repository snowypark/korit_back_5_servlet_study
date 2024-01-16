package com.study.servlet_study.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.servlet_study.utills.ParamsConverter;


@WebServlet("/http")
public class HttpStudyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public HttpStudyServlet() {
        super();
    }

    // HTTP 메소드
    // POST요청		-> Create(추가)
    // GET요청 		-> Read(조회)
    // PUT요청 		-> Update(수정)
    // DELETE요청 	-> Delete(삭제)
    
    //로그인 POST 요청
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		Map<String, String> paramsMap = new HashMap();		
		
		request.getParameterMap().forEach((k, v) -> {
			
			StringBuilder builder = new StringBuilder();			
			Arrays.asList(v).forEach(value -> builder.append(value));
			
			paramsMap.put(k, builder.toString());

		});
		
		System.out.println(paramsMap);
		System.out.println(request.getParameter("name"));
		
		Map<String, String> paramsMap2 = new HashMap<>();
		Iterator<String> ir = request.getParameterNames().asIterator();
		while(ir.hasNext()) {
			String key = ir.next();
			paramsMap2.put(key, request.getParameter(key));
			
//			System.out.println("\t" + ir.next());
		}
 
//		Map<String, String[]> map = request.getParameterMap();
		//map.foreach -> { map에서 v 꺼내서 String으로 }
//		map.forEach((k, v) -> {
//		String[] valueArray = v; //배열 -> List 변경
//		System.out.println(k + ": " + builder.toString()); //builder = 주소값 -> toString = String 변경
		
//		System.out.println(k + ": " + v);
//		for(String value : v) {
//			System.out.println(value);
//		}
//		System.out.println();
		
		String nameParams = request.getParameter("name");
		String phoneParams = request.getParameter("phone");
		String emailParams = request.getParameter("email");
		String addressParams = request.getParameter("address");
		
		System.out.println(nameParams);
		System.out.println(phoneParams);
		System.out.println(emailParams);
		System.out.println(addressParams);
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, String> paramsMap = ParamsConverter.convertParamasMapToMap(request.getParameterMap());

		System.out.println(paramsMap);
		
	}


	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	
	}
	
	
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
	}

}
