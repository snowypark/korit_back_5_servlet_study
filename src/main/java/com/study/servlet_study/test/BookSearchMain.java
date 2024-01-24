package com.study.servlet_study.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.study.servlet_study.config.DBConnectionMgr;

public class BookSearchMain {
	public static void main(String[] args) {
		
		//검색할 도서명을 입력하세요 >>> text
		//도서명 / 저자명 / 출판사
		
		Scanner scanner = new Scanner(System.in);
		DBConnectionMgr pool = DBConnectionMgr.getInstance(); 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String searchBook = null;
		String sql = null;
		System.out.print("검색할 도서명을 입력하세요 >>> ");
		searchBook = scanner.nextLine();
		System.out.println(searchBook);
		
		
		try {			
			con = pool.getConnection();
			
			sql = "SELECT \r\n"
					+ "	bt.book_id, "
					+ "bt.book_name, \r\n"
					+ "    at.author_id, \r\n"
					+ "    at.author_name, \r\n"
					+ "    pt.publisher_id, \r\n"
					+ "    pt.publisher_name\r\n"
					+ "\r\n"
					+ "FROM \r\n"
					+ "	book_register_tb brt \r\n"
					+ "    LEFT JOIN book_tb bt ON brt.book_id = bt.book_id \r\n"
					+ "	LEFT JOIN author_tb at ON bt.author_id = at.author_id \r\n"
					+ "	LEFT JOIN publisher_tb pt ON bt.publisher_id = pt.publisher_id\r\n"
					+ "WHERE \r\n"
					+ "	bt.book_name LIKE ? \r\n"
					+ "ORDER BY \r\n"
					+ "	bt.book_id";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + searchBook + "%");
			rs = pstmt.executeQuery(); 
			
			List<Author> authorList = new ArrayList<>();

			List<Publisher> publisherList = new ArrayList<>();

			List<Book> bookList = new ArrayList<>();

			//도서명 저자명 출판사
			while(rs.next()) {

				Author author = Author.builder()
						.authorId(rs.getInt(3))
						.authorName(rs.getString(4))
						.build();

				Publisher publisher = Publisher.builder()
						.publisherId(rs.getInt(5))
						.publisherName(rs.getString(6))
						.build();

				Book book = Book.builder()
						.bookId(rs.getInt(1))
						.bookName(rs.getString(2))
						.author(author)
						.publisher(publisher)
						.build();
				
				authorList.add(author);
				publisherList.add(publisher);
				bookList.add(book);
				
			
			}
			
			bookList.forEach(book -> System.out.println(book));

			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {

			pool.freeConnection(con, pstmt, rs);

		}
		
				
	}
	

	
	
		
		
	

}
