package surveyapplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BrandDao {
	private JdbcTemplate jdbcTemplate;
	private int length = 0;

	public ArrayList<BrandVo> selectAll() {
		jdbcTemplate = JdbcTemplate.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BrandVo> ls = new ArrayList<>();
		String sql = "select \"BRAND_NUMBER\", \"TITLE\" from \"BRAND\" order by \"BRAND_NUMBER\" asc";

		try {
			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			System.out.println("『번호를 선택하세요.』");
			while (rs.next()) {
				BrandVo tmp = new BrandVo(rs.getInt("BRAND_NUMBER"), rs.getString("TITLE"));
				ls.add(tmp);
				length = rs.getInt("BRAND_NUMBER");
			}
			ls.add(new BrandVo(0, "기타(직접 입력)"));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcTemplate.close(pstmt);
			jdbcTemplate.close(conn);
		}
		return ls;
	}

	public boolean selectBrand(int infoNum, int brandNum) {
		if (!brandNumber(brandNum).contains(brandNum)) {
			System.out.println("『해당 번호는 존재하지 않습니다. 다시 입력하세요.』");
			return false;
		}
		boolean result = false;
		jdbcTemplate = JdbcTemplate.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into \"VOTE\" values (\"SEQ_VOTE\".nextval, ?, ?)";

		try {
			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, infoNum);
			pstmt.setInt(2, brandNum);

			result = (pstmt.executeUpdate() == 1) ? true : false;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcTemplate.close(pstmt);
			jdbcTemplate.close(conn);
		}
		return result;
	}

	public boolean insertBrand(int infoNumber, String brand) {
		jdbcTemplate = JdbcTemplate.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into \"BRAND\" values (\"SEQ_BRAND\".nextval, ?)";

		try {
			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, brand);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcTemplate.close(pstmt);
			jdbcTemplate.close(conn);
		}

		return selectBrand(infoNumber, length + 1);
	}

	private ArrayList<Integer> brandNumber(int i) {
		jdbcTemplate = JdbcTemplate.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Integer> ls = new ArrayList<>();
		String sql = "select \"BRAND_NUMBER\" from \"BRAND\"";

		try {
			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ls.add(rs.getInt("BRAND_NUMBER"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcTemplate.close(rs);
			jdbcTemplate.close(pstmt);
			jdbcTemplate.close(conn);
		}
		return ls;
	}
}
