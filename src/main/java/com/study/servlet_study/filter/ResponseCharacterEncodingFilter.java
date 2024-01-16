package com.study.servlet_study.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//클라이언트 -> 서버 (전처리 - doGet - 후처리) 필터 
@WebFilter("/*") //주소 필터
public class ResponseCharacterEncodingFilter extends HttpFilter implements Filter {

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		httpResponse.setCharacterEncoding("utf-8");
		
		//전처리 영역
		
		chain.doFilter(request, response); //request, response -> doFilter(다음필터) -> (최종)servlet
		
		
		//후처리 영역
//		httpResponse.getWriter().println("무조건 후처리함");
	
	}


	public void init(FilterConfig fConfig) throws ServletException {

	}

}
