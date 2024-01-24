package com.study.servlet_study.test;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.study.servlet_study.config.DBConnectionMgr;
import com.study.servlet_study.entity.Author;

public class DBConnectionTestMain {

	public static void main(String[] args) {
		
		//싱글톤 
		DBConnectionMgr pool = DBConnectionMgr.getInstance(); // 글자 대각선 static
														//싱글톤으로 객체 생성 후 사용
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = pool.getConnection(); //DB 연결
			String name = "?"; // "&" + "" + "&"
			String sql = "select * from author_tb where author_name = ?";
			pstmt = con.prepareStatement(sql);	//쿼리입력
			pstmt.setString(1, name); // ? 자리에 찾아감
			
			rs = pstmt.executeQuery(); //실행 Set자료형
			
			List<Author> authorList = new ArrayList<>();
			
			while(rs.next()) {
				authorList.add(Author.builder()
						.authorId(rs.getInt(1))
						.authorName(rs.getString(2))
						.build());
			}
			
			
			authorList.forEach(author -> System.out.println(author));
			
			for(Author author : authorList) {
				System.out.println(author);
			}
			
			for(int i = 0; i < authorList.size(); i++) {
				Author author = authorList.get(i);
				System.out.println(author);
			}
			
			
//			//데이터가있으면 다음으로 커서이동
//			while(rs.next()) {
//				System.out.println("id: " + rs.getInt(1));
//				System.out.println("name: " + rs.getString(2));
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
	}


}
