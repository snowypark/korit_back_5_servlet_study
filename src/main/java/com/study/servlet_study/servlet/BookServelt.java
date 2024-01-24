package com.study.servlet_study.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.servlet_study.service.BookService;
import com.study.servlet_study.test.Author;
import com.study.servlet_study.test.Book;
import com.study.servlet_study.test.Publisher;


@WebServlet("/book")
public class BookServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private BookService bookService;
    

    public BookServelt() {
        super();
        bookService = BookService.getinstance();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		Book book = bookService.getBook(bookId);
		
		response.setContentType("text/plain");
		response.setStatus(200);
		response.getWriter().println(book);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookName = request.getParameter("bookName");
		String authorName = request.getParameter("authorName");
		String publisherName = request.getParameter("publisherName");
		
		Book book = Book.builder()
				.bookName(bookName)
				.author(Author.builder().authorName(authorName).build())
				.publisher(Publisher.builder().publisherName(publisherName).build())
				.build();
		
		boolean insertStatus = bookService.addBook(book);
		response.setContentType("text/plain");
		response.setStatus(201);
		response.getWriter().println(insertStatus);
		
	}

}
