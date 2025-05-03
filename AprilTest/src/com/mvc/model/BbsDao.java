package com.mvc.model;

import com.mvc.util.Mysql;
import java.sql.*;
import java.util.*;

public class BbsDao {

    // �Խñ� �ۼ�
    public void insert(BbsDto dto) {
        try (Connection conn = Mysql.getConnection()) {
            String sql = "INSERT INTO board (title, id, content, nalja) VALUES (?, ?, ?, ?)";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, dto.getTitle());
            psmt.setString(2, dto.getId());
            psmt.setString(3, dto.getContent());
            psmt.setDate(4, dto.getNalja());  // Date ��ü ���
            psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // �Խñ� ��� (����¡ ó��)
    public List<BbsDto> selectPaged(int startRow, int endRow) {
        List<BbsDto> list = new ArrayList<>();
        try (Connection conn = Mysql.getConnection()) {
            String sql = "SELECT * FROM board ORDER BY num DESC LIMIT ?, ?";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, startRow);
            psmt.setInt(2, endRow);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                BbsDto dto = new BbsDto();
                dto.setNum(rs.getInt("num"));
                dto.setTitle(rs.getString("title"));
                dto.setId(rs.getString("id"));
                dto.setContent(rs.getString("content"));
                dto.setNalja(rs.getDate("nalja"));
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // �Խñ� �˻�
    public List<BbsDto> search(String keyword) {
        List<BbsDto> list = new ArrayList<>();
        try (Connection conn = Mysql.getConnection()) {
            String sql = "SELECT * FROM board WHERE title LIKE ? OR content LIKE ? ORDER BY num DESC";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, "%" + keyword + "%");
            psmt.setString(2, "%" + keyword + "%");
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                BbsDto dto = new BbsDto();
                dto.setNum(rs.getInt("num"));
                dto.setTitle(rs.getString("title"));
                dto.setId(rs.getString("id"));
                dto.setContent(rs.getString("content"));
                dto.setNalja(rs.getDate("nalja"));
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // �Խñ� �󼼺���
    public BbsDto selectByNum(int num) {
        BbsDto dto = null;
        try (Connection conn = Mysql.getConnection()) {
            String sql = "SELECT * FROM board WHERE num = ?";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, num);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                dto = new BbsDto();
                dto.setNum(rs.getInt("num"));
                dto.setTitle(rs.getString("title"));
                dto.setId(rs.getString("id"));
                dto.setContent(rs.getString("content"));
                dto.setNalja(rs.getDate("nalja"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    // �Խñ� ����
    public void update(BbsDto dto) {
        try (Connection conn = Mysql.getConnection()) {
            String sql = "UPDATE board SET title = ?, content = ? WHERE num = ?";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, dto.getTitle());
            psmt.setString(2, dto.getContent());
            psmt.setInt(3, dto.getNum());
            psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // �Խñ� ����
    public void delete(int num) {
        try (Connection conn = Mysql.getConnection()) {
            String sql = "DELETE FROM board WHERE num = ?";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, num);
            psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ��ü �Խñ� ����
    public int getTotalCount() {
        int count = 0;
        try (Connection conn = Mysql.getConnection()) {
            String sql = "SELECT COUNT(*) FROM board";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}
