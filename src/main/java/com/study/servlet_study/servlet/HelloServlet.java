package com.study.servlet_study.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/hello") //주소확인
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(request.getMethod());
		System.out.println(request.getRequestURI()); 
		System.out.println(request.getRequestURL()); //url
		System.out.println(response.getStatus());
		
		
		response.setContentType("text/plain");
		
		System.out.println(response.getContentType());
				

		response.getWriter().println("헬로"); //응답
		System.out.println("요청이 들어옴!!!"); //클라이언트 -> 톰캣서버 ->servlet response 객체 생성
	}

}
