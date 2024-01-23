package com.study.servlet_study.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.study.servlet_study.config.DBConnectionMgr;
import com.study.servlet_study.entity.Author;

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
		
		System.out.print("검색할 도서명을 입력하세요 >>> ");
		searchBook = scanner.nextLine();
		System.out.println(searchBook);
		
		
		try {			
			con = pool.getConnection();
			
			String sql = "select\r\n"
					+ "	0,\r\n"
					+ "	bt.book_name,\r\n"
					+ "	at.author_name,\r\n"
					+ "	pt.publisher_name\r\n"
					+ "from\r\n"
					+ "	book_tb bt\r\n"
					+ "		left outer join author_tb at on(at.author_id = bt.author_id)\r\n"
					+ "		left outer join publisher_tb pt on(pt.publisher_id = bt.publisher_id)\r\n"
					+ "group by\r\n"
					+ "	bt.book_name,\r\n"
					+ "    at.author_id,\r\n"
					+ "    pt.publisher_id";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery(); 
			
			List<Book> bookList = new ArrayList<>();
			
			//도서명 저자명 출판사
			while(rs.next()) {
				bookList.add(Book.builder()
						.bookName(rs.getString(2))
						.build());
			}
			
			bookList.forEach(book -> System.out.println(book));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
				
	}
	

	
	
		
		
	

}
