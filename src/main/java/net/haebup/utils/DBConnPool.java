package net.haebup.utils;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnPool {
	public Connection con;
	public Statement stmt;
	public PreparedStatement pstm;
	public ResultSet rs;
	
	
	// 연결
	public DBConnPool() {
		try {
			Context initCtx = new InitialContext();
			Context ctx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) ctx.lookup("jdbc_haebup");
			con = ds.getConnection();
			System.out.println("==================================================");
			System.out.println(" DB 커넥션 풀 생성 ");
			System.out.println("con1 String : " + con);
			System.out.println("==================================================");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("==================================================");
			System.out.println(" DB 커넥션 풀 접속 에러 ");
			System.out.println("에러메세지 : "+e.getMessage());
			System.out.println("==================================================");
		}
		
	}
	
	
	// 연결 해제
	public void close() {
		try {
			if( rs != null) rs.close();
			if( pstm != null) pstm.close();
			if( stmt != null) stmt.close();
			if( con != null) con.close();
			
			System.out.println("==================================================");
			System.out.println("DB 커넥션 풀 자원해제 ");
			System.out.println("==================================================");
		
		}catch(Exception e) {
			System.out.println("==================================================");
			System.out.println("DB 커넥션 풀 오류 : "+e.getMessage());
			System.out.println("==================================================");
		}
	}
}
