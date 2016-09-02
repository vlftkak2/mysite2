package kr.ac.sungkyul.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.ac.sungkyul.mysite.vo.BoardVo;

@Repository
public class BoardDao {

	private Connection getConnection() throws SQLException {

		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("error : " + e);
		}
		return conn;
	}

	public List<BoardVo> get(String keyword) {

		List<BoardVo> list = new ArrayList<BoardVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			conn = getConnection();

			String sql = "select b.no,b.title, a.name, b.VIEW_COUNT,b.depth,to_char(b.REG_DATE,'yyyy-mm-dd hh:mm:ss'),b.user_no from users a, boards b where b.title='?'";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, keyword);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String name = rs.getString(3);
				Integer count = rs.getInt(4);
				Integer depth = rs.getInt(5);
				String date = rs.getString(6);
				Long userno = rs.getLong(7);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setName(name);
				vo.setCount(count);
				vo.setDepth(depth);
				vo.setDate(date);
				vo.setUserNo(userno);

			}

		} catch (SQLException e) {
			System.out.println("error : " + e);
		}

		return list;

	}
	
	public void updatereplyCount(int groupNo,int orderNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();

			String sql = "update boards set order_no=order_no+1 where group_no=? and order_no>=?";
			pstmt = conn.prepareStatement(sql);


			pstmt.setInt(1, groupNo);		
			pstmt.setInt(2, orderNo);
			
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateViewCount(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();

			String sql = "update boards set view_count = view_count + 1 where no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void insert(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			
			conn = getConnection();

			// 3. statement 생성 ?-> 값이 바인딩 된다.
			String sql = (vo.getGroupNo()==null) ?
					"insert into boards VALUES(seq_boards.nextval,?,?,1,nvl((select max(group_no) from boards), 0)+1,1,1,?,sysdate)"
					:   "insert into boards values(seq_boards.nextval, ?, ?, 0, ?, ?, ?, ?,sysdate)";
			pstmt = conn.prepareStatement(sql);

			// nvl((select max(group_no) from board),0+1)

			if(vo.getGroupNo()==null){
			// 4. 바인딩 타이틀,컨텐 츠,번호
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getUserNo());
			}else{
				
		    pstmt.setString(1, vo.getTitle());
		    pstmt.setString(2, vo.getContent());
		    pstmt.setInt(3, vo.getGroupNo());
		    pstmt.setInt(4, vo.getGroupOrderNo());
		    pstmt.setInt(5, vo.getDepth());
		    pstmt.setLong(6, vo.getUserNo());
				
			}

			// 5. 쿼리 실행
			pstmt.executeUpdate(); // pstmt.executeUpdate(sql) ->sql문을 줄
									// 필요가 없다
		} catch (SQLException e) {
			e.printStackTrace();
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

			}

		}

	}

	public void delete(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "delete from boards where no=? ";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setLong(1, vo.getNo());

			// 5. sql 실행
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("연결 오류 : " + e);
		} finally {
			try {

				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				System.out.println("error : " + e);
			}
		}

	}

	public BoardVo get2(Long no1) {

		BoardVo vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			conn = getConnection();

			String sql = "select no,title,content,user_no,depth,order_no,group_no from boards where no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no1);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				Long userno = rs.getLong(4);
				Integer depth=rs.getInt(5);
				Integer orderno=rs.getInt(6);
				Integer groupno=rs.getInt(7);

				vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setUserNo(userno);
				vo.setDepth(depth);
				vo.setGroupOrderNo(orderno);
				vo.setGroupNo(groupno);

			}

		} catch (SQLException e) {
			System.out.println("error : " + e);
		}

		return vo;

	}

	public BoardVo get(Long userno) {

		BoardVo vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			conn = getConnection();

			String sql = "select no,title,content,user_no from boards where user_no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, userno);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				Long usernumber = rs.getLong(4);

				vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setUserNo(usernumber);

			}

		} catch (SQLException e) {
			System.out.println("error : " + e);
		}

		return vo;

	}

	public List<BoardVo> getList(int page, int pagesize,String keyword) {
		List<BoardVo> list = new ArrayList<BoardVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql =(keyword ==null || "".equals(keyword))? 
		    "select * from(select c.*,rownum rn from(select a.no,a.title,b.name,a.user_no,a.view_count,to_char(a.reg_date, 'yyyy-mm-dd pm hh:mi:ss'),a.depth from boards a, users b where a.user_no=b.no order by group_no desc, order_no asc) c) where ?<=rn and rn<=?"
			:"select * from(select c.*,rownum rn from(select a.no,a.title,b.name,a.user_no,a.view_count,to_char(a.reg_date, 'yyyy-mm-dd pm hh:mi:ss'),a.depth from boards a, users b where a.user_no=b.no and (title like ? or content like ?) order by group_no desc, order_no asc) c) where ?<=rn and rn<=?";

				
			pstmt = conn.prepareStatement(sql);
			
			if(keyword ==null || "".equals(keyword)){

			pstmt.setInt(1, (page - 1) * pagesize + 1);
			pstmt.setInt(2, page * pagesize);
			
			}else{
				
				
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+keyword+"%");
			pstmt.setInt(3, (page - 1) * pagesize + 1);
			pstmt.setInt(4, page * pagesize);
			}
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String userName = rs.getString(3);
				Long userNo = rs.getLong(4);
				Integer viewCount = rs.getInt(5);
				String regDate = rs.getString(6);
				Integer depth = rs.getInt(7);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setName(userName);
				vo.setUserNo(userNo);
				vo.setCount(viewCount);
				vo.setDate(regDate);
				vo.setDepth(depth);

				list.add(vo);
			}
			return list;
			
		} catch (SQLException ex) {
			
			System.out.println("error: " + ex);
			return list;
			
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		
	}

	public int getTotalCount() {

		int totalCount = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = "select count(*) from boards";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				totalCount = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return totalCount;

	}

	public void update(BoardVo vo) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = getConnection();

			String sql = "update boards set title=?, content=? where no=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getNo());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {

			try {

				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error : " + e);
			}
		}
	}

}
