package DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
	
	static {
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}


	private static final String URL = "jdbc:mysql://localhost:3306/mafiag";
	private static final String USER = "root";
	public static String PASSWORD = "0000";

	// �α���
	public static String checkLogin(String id, String password) {
		String sql = "SELECT * FROM member WHERE member_id = ? AND password = ?";
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, id);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString("nickname");
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// ���̵� ã��
	public static String findMemberIdByEmail(String email) {
		String sql = "SELECT member_id FROM member WHERE email = ?";
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString("member_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// ��й�ȣ ã��
	public static boolean findPasswordByEmailAndId(String id, String email) {
		String sql = "SELECT * FROM member WHERE member_id = ? AND email = ?";
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, id);
			pstmt.setString(2, email);
			ResultSet rs = pstmt.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// ID �ߺ� Ȯ��
	public static boolean isIdDuplicate(String id) {
		String sql = "SELECT COUNT(*) FROM member WHERE member_id = ?";
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// �г��� �ߺ� Ȯ��
	public static boolean isNicknameDuplicate(String nickname) {
		String sql = "SELECT COUNT(*) FROM member WHERE nickname = ?";
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, nickname);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// �̸��� �ߺ� Ȯ��
	public static boolean isEmailDuplicate(String email) {
		String sql = "SELECT COUNT(*) FROM member WHERE email = ?";
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// ȸ�� ���� ó��
	public static boolean insertNewMember(String id, String password, String nickname, String email) {
		String sql = "INSERT INTO member (member_id, password, email, nickname) VALUES (?, ?, ?, ?)";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, id);
			pstmt.setString(2, password);
			pstmt.setString(3, email);
			pstmt.setString(4, nickname);

			int rows = pstmt.executeUpdate();
			return rows > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// ������ ���� ���� ��������
	public static int getUserScore(String username) {
		int score = 0;
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			 PreparedStatement pstmt = conn.prepareStatement(
					 "SELECT score FROM member WHERE member_id = ?")) {

			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				score = rs.getInt("score");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return score;
	}

	// ���� ���� �� ���� �ݿ� (Gemini ����, ������ ��� �¸� ó��)
	public static void updateScoresAfterGame(List<String> winners, List<String> participants) {
	    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
	        for (String player : participants) {
	            if (player.equals("Gemini")) continue;

	            int scoreChange = 0;

	            if (winners.contains("Gemini")) {
	                // Gemini�� ���Ե� �¸����� ���: ������ ���� -1��
	                scoreChange = -1;
	            } else {
	                // �Ϲ� ������ �� ����: +5�� / ������: -1��
	                if (winners.contains(player)) {
	                    scoreChange = 5;
	                } else {
	                    scoreChange = -1;
	                }
	            }

	            // ���� ������Ʈ
	            try (PreparedStatement pstmt = conn.prepareStatement(
	                    "UPDATE member SET score = score + ? WHERE member_id = ?")) {
	                pstmt.setInt(1, scoreChange);
	                pstmt.setString(2, player);
	                pstmt.executeUpdate();
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}



	// �α׾ƿ� ó��
	public static void logoutUser(String username) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			 PreparedStatement pstmt = conn.prepareStatement(
					 "UPDATE member SET last_login = NOW() WHERE member_id = ?")) {

			pstmt.setString(1, username);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ��ŷ ���� ���� n�� ��������
	public static List<UserScore> getTopRankers(int limit) {
		List<UserScore> rankers = new ArrayList<>();
		String sql = "SELECT nickname, score FROM member ORDER BY score DESC LIMIT ?";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, limit);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String name = rs.getString("nickname");
				int score = rs.getInt("score");
				rankers.add(new UserScore(name, score));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rankers;
	}
}