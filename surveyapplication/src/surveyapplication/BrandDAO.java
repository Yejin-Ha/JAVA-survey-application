package surveyapplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BrandDAO {
	private JdbcTemplate jdbcTemplate;

	public BrandDAO() {
		jdbcTemplate = JdbcTemplate.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select \"BRAND_NUMBER\", \"TITLE\" from \"BRAND\"";

		try {
			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			System.out.println("번호를 선택하세요.");
			while (rs.next()) {
				System.out.print(rs.getInt("BRAND_NUMBER") + ". ");
				System.out.println(rs.getString("TITLE"));
			}
			System.out.println("0. 기타(직접 입력)");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcTemplate.close(pstmt);
			jdbcTemplate.close(conn);
		}
	}

	public void selectBrand(int infoNum, int brandNum) {
		jdbcTemplate = JdbcTemplate.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into \"VOTE\" values (\"SEQ_VOTE\".nextval, ?, ?)";

		try {
			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, infoNum);
			pstmt.setInt(2, brandNum);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcTemplate.close(pstmt);
			jdbcTemplate.close(conn);
		}
	}
	
	public void insertBrand(String brand) {
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
	}
}
