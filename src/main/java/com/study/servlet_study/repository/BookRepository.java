package com.study.servlet_study.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.study.servlet_study.config.DBConnectionMgr;
import com.study.servlet_study.test.Book;

public class BookRepository {
	
	private static BookRepository instance;
	private DBConnectionMgr pool;
	
	private BookRepository() {
		pool = DBConnectionMgr.getInstance();
	}
	
	public static BookRepository getinstance() {
		if(instance == null) {
			instance = new BookRepository();
		}
		return instance;
	}
	
	public int saveBook(Book book) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {			
			con = pool.getConnection();
			String sql = "insert into author_tb values (0, ?)";
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1,  book.getAuthor().getAuthorName());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				book.getAuthor().setAuthorId(rs.getInt(1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt);
		}
		
		try {			
			con = pool.getConnection();
			String sql = "insert into publisher_tb values (0, ?)";
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1,  book.getAuthor().getAuthorName());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				book.getPublisher().setPublisherId(rs.getInt(1));
			}					
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt);
		}		
		
		
		try {			
			con = pool.getConnection();
			String sql = "insert into book_tb values (0, ?, ?, ?)";
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1,  book.getBookName());
			pstmt.setInt(2,  book.getAuthor().getAuthorId());
			pstmt.setInt(3,  book.getPublisher().getPublisherId());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				book.setBookId(rs.getInt(1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt);
		}
		return 1;

	}

}
