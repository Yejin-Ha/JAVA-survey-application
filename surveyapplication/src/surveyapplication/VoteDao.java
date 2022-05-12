package surveyapplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class VoteDao {
	private JdbcTemplate jdbcTemplate;

	public VoteDao() {
		System.out.println("1. 전체 현황 보기");
		System.out.println("2. 나이대 별 선호도 보기");
	}

	public void selectAll() {
		jdbcTemplate = JdbcTemplate.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select \"B\".\"TITLE\"\r,count(*)\n" + "from \"VOTE\" \"V\", \"BRAND\" \"B\"\r\n"
				+ "WHERE \"V\".brand_number = \"B\".BRAND_NUMBER\r\n" + "group by \"B\".\"TITLE\"";

		try {
			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.print(rs.getString(1) + "\t---> ");
				System.out.println(rs.getInt(2) + "표");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcTemplate.close(rs);
			jdbcTemplate.close(pstmt);
			jdbcTemplate.close(conn);
		}
	}

	public void selectAge() {
		ArrayList<Integer> ages = getAges();
		HashMap<Integer, ArrayList<VoteVo>> map = null;

		if (ages == null) {
			System.out.println("『투표한 사람이 없습니다.』");
			return;
		}
		for (int i : ages) {
			map = getVotes(i);

			Iterator<Integer> keys = map.keySet().iterator();
			int person = keys.next();

			System.out.println(i + "대 (총 " + person + "명)");
			for (int j = 0; j < map.get(person).size(); j++) {
				System.out.println("\t" + map.get(person).get(j));
			}
		}
	}

	private ArrayList<Integer> getAges() {
		jdbcTemplate = JdbcTemplate.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Integer> ls = new ArrayList<>();
		String sql = "select unique \"AGE\" \r\n" + "from \"VOTE\" \"V\", \"INFO\" \"I\"\r\n"
				+ "where \"V\".\"INFO_NUMBER\" = \"I\".\"INFO_NUMBER\"\r\n" + "order by \"I\".AGE ASC";

		try {
			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				ls.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcTemplate.close(rs);
			jdbcTemplate.close(pstmt);
			jdbcTemplate.close(conn);
		}
		return (ls.size() == 0) ? null : ls;
	}

	private HashMap<Integer, ArrayList<VoteVo>> getVotes(int age) {
		int cnt = 0;
		jdbcTemplate = JdbcTemplate.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<VoteVo> ls = new ArrayList<>();
		HashMap<Integer, ArrayList<VoteVo>> map = new HashMap<>();
		String sql = "select (select TITLE from brand where brand_number=VT.brand_number) as TITLE, count(*) as CNT\r\n"
				+ "from \"VOTE\" VT where info_number in(select info_number from info where age=?) group by brand_number";

		try {
			conn = jdbcTemplate.getConnection();
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
			jdbcTemplate.close(rs);
			jdbcTemplate.close(pstmt);
			jdbcTemplate.close(conn);
		}
		return map;
	}
}
