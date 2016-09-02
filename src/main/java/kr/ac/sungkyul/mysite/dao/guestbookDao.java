package kr.ac.sungkyul.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.sungkyul.mysite.vo.guestbookVo;

@Repository
public class guestbookDao {
	
	@Autowired
	private DataSource dataSource;

	public List<guestbookVo> getList() {

		List<guestbookVo> list = new ArrayList<guestbookVo>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn=dataSource.getConnection();

			stmt=conn.createStatement();
			String sql = "select no,name,password,introduction,to_char(reg_date,'yyyy-mm-dd') from guestbook order by no";
			rs=stmt.executeQuery(sql);
			
			while(rs.next()){
				Long no=rs.getLong(1);
				String name=rs.getString(2);
				String password=rs.getString(3);
				String introduction=rs.getString(4);
				String regDate=rs.getString(5);
				
				
				guestbookVo vo =new guestbookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setPassword(password);
				vo.setIntroduction(introduction);
				vo.setReg_date(regDate);
				
				list.add(vo);
				
			}
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				
				if(rs!=null){
					rs.close();
				}
				
				if(stmt!=null){
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error : " + e);
			}
		}

		return list;

	}
	
	public boolean insert(guestbookVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count=0;
		try {
			
			conn=dataSource.getConnection();

			// 3. statement 생성 ?-> 값이 바인딩 된다.
			String sql = "insert into guestbook VALUES(seq_guestbook.nextval,?,?,?,sysdate)";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getIntroduction());

			// 5. 쿼리 실행
			count= pstmt.executeUpdate(); // pstmt.executeUpdate(sql) ->sql문을 줄
											// 필요가 없다
		} catch (SQLException e) {
			System.out.println("error : " + e);
			return false;
		} finally {
			try {
				// 6. 자원정리
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error : " + e);
				return false;
			}

		}
		return (count==1);
	}
	
	public boolean delete(guestbookVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count=0;
		try {
			conn=dataSource.getConnection();
		
			String sql="delete from guestbook where no=? and password=?";
			pstmt=conn.prepareStatement(sql);
			
			// 4. 바인딩
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getPassword());
			
			// 5. sql 실행
			count=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("연결 오류 : " + e);
		} finally {
			try {

				if (conn != null) {
					conn.close();
				}
				if(pstmt!=null){
					pstmt.close();
				}
			} catch (SQLException e) {
				System.out.println("error : " + e);
			}
		}
		return (count==1);
	}

	
	

}
