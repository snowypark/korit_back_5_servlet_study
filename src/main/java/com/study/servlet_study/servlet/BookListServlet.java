package com.study.servlet_study.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.servlet_study.service.BookService;
import com.study.servlet_study.test.Author;
import com.study.servlet_study.test.Book;
import com.study.servlet_study.test.Publisher;


@WebServlet("/books")
public class BookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookService bookService;
       

    public BookListServlet() {
        super();
        bookService = BookService.getinstance();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// params 
		// bookName -> like 조회
		// authorName -> like 조회
		// publisherName -> like 조회
		// null = 전체조회 / 값 입력 - 해당 조회
		
		String bookName = request.getParameter("bookName");
		String authorName = request.getParameter("authorName");
		String publisherName = request.getParameter("publisherName");
		bookName = null;
		
		Map<String, String> params = new HashMap<>();
		
		if(bookName != null) {
			params.put("bookName", bookName);
		}
		if(authorName != null) {
			params.put("authorName", authorName);
		}
		if(publisherName != null) {
			params.put("publisherName", publisherName);
		}				
		params.size();		
				
		//map put ....
		
		List<Book> book = bookService.searchBookList(params);
		
		response.setContentType("text/plain");
		response.setStatus(200);
		response.getWriter().println(book);
		
		}

	
}
