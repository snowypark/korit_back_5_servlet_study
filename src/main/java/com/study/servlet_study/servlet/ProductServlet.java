package com.study.servlet_study.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.servlet_study.entity.Product;
import com.study.servlet_study.service.ProductService;

@WebServlet("/product")
public class ProductServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private ProductService productService;
	
	
	public ProductServlet() {
		super();
		productService = ProductService.getInstance();
		
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String productName = request.getParameter("productName");
		
		Product product = productService.getProduct(productName);
		response.setStatus(200);
		response.getWriter().println(product);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String productName = request.getParameter("productName");
		String price = request.getParameter("price");
		String size = request.getParameter("size");
		String color = request.getParameter("color");
		
		Product product = Product.builder()
				.productName(productName)
				.price(price)
				.size(size)
				.color(color)
				.build();
		
		int body = productService.addProduct(product);
		response.setStatus(201);
		response.getWriter().println(body);
		
	}

}
