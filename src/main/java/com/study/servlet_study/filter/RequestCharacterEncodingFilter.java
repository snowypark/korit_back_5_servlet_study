package com.study.servlet_study.filter;

import java.io.IOException;
import java.util.Arrays;

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


@WebFilter("/*")
public class RequestCharacterEncodingFilter extends HttpFilter implements Filter {


	public void destroy() {
		
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
//		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String[] methods = new String[] { "POST", "PUT" }; //대소문자 구분
				
		if(Arrays.asList(methods).contains(httpRequest.getMethod().toUpperCase())) {
			httpRequest.setCharacterEncoding("utf-8");
			
		}				
		
		chain.doFilter(request, response); //request, response -> doFilter(다음필터) -> (최종)servlet
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
