package survey;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class VoteDao {
	private ConnectionPool pool = null;

	public VoteDao() {
		try {
			pool = ConnectionPool.getInstance();
		} catch (Exception e) {
			System.out.println("Error: Connection load failed");
		}
	}

	public void vote(String brandNum, Object num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		// number, infoNum, brandNum
		String query = "insert into \"VOTE\" values (\"SEQ_VOTE\".nextval, ?, ?)";
		try {
			conn = pool.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, (int) num);
			pstmt.setString(2, brandNum);
			pstmt.executeQuery();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			pool.close(pstmt);
			if (conn != null) {
				try {
					pool.releaseConnection(conn);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public ArrayList<VoteVo> selectAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select \"B\".\"TITLE\", count(*) as \"COUNT\"\r\n" + "from \"VOTE\" \"V\", \"BRAND\" \"B\"\r\n"
				+ "where \"V\".\"BRAND_NUMBER\" = \"B\".\"BRAND_NUMBER\"\r\n" + "group by \"B\".\"TITLE\"";
		ArrayList<VoteVo> result = new ArrayList<>();
		try {
			conn = pool.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result.add(new VoteVo(rs.getString("TITLE"), rs.getInt("COUNT")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.close(rs);
			pool.close(pstmt);
			if (conn != null) {
				try {
					pool.releaseConnection(conn);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public void selectAge() {
		ArrayList<Integer> ages = getAges();
		HashMap<Integer, ArrayList<VoteVo>> map = null;

		if (ages == null) {
			return;
		}
		for (int i : ages) {
			map = getVotes(i);

			Iterator<Integer> keys = map.keySet().iterator();
			int person = keys.next();

			System.out.println(i + "�� (�� " + person + "��)");
			for (int j = 0; j < map.get(person).size(); j++) {
				System.out.println("\t" + map.get(person).get(j));
			}
		}
	}

	public ArrayList<Integer> getAges() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Integer> ls = new ArrayList<>();
		String sql = "select unique \"AGE\" \r\n" + "from \"VOTE\" \"V\", \"INFO\" \"I\"\r\n"
				+ "where \"V\".\"INFO_NUMBER\" = \"I\".\"INFO_NUMBER\"\r\n" + "order by \"I\".AGE ASC";

		try {
			conn = pool.getConnection();
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				ls.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.close(rs);
			pool.close(pstmt);
			if (conn != null) {
				try {
					pool.releaseConnection(conn);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return (ls.size() == 0) ? null : ls;
	}

	public HashMap<Integer, ArrayList<VoteVo>> getVotes(int age) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<VoteVo> ls = new ArrayList<>();
		HashMap<Integer, ArrayList<VoteVo>> map = new HashMap<>();
		String sql = "select (select TITLE from brand where brand_number=VT.brand_number) as TITLE, count(*) as CNT\r\n"
				+ "from \"VOTE\" VT where info_number in(select info_number from info where age=?) group by brand_number";

		try {
			conn = pool.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, age);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				ls.add(new VoteVo(rs.getString("TITLE"), rs.getInt("CNT")));
				cnt += rs.getInt("CNT");
			}
			map.put(cnt, ls);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.close(rs);
			pool.close(pstmt);
			if (conn != null) {
				try {
					pool.releaseConnection(conn);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return map;
	}
}
