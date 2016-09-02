package kr.ac.sungkyul.mysite.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.sungkyul.mysite.vo.MapVo;

@Repository
public class MapDao {
	
	@Autowired
	private DataSource dataSource;
	
	public List<MapVo> getList() {

		List<MapVo> list = new ArrayList<MapVo>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn=dataSource.getConnection();

			stmt=conn.createStatement();
			String sql = "select no,name,localx,localy,percent,region_no from map order by percent desc";

			rs=stmt.executeQuery(sql);
			
			while(rs.next()){
				Long no=rs.getLong(1);
				String name=rs.getString(2);
				Double localx=rs.getDouble(3);
				Double localy=rs.getDouble(4);
				String percent=rs.getString(5);
				Long regionno=rs.getLong(6);
				
				
				MapVo vo =new MapVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setLocalx(localx);
				vo.setLocaly(localy);
				vo.setPercent(percent);
				vo.setRegionno(regionno);
				
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

}
