package com.servlet.news.dao;


import com.servlet.news.model.News;
import com.servlet.news.util.DBUtil;
import java.util.Date;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsDAO {

    public List<News> findAll() {
        return findByCategory(null);
    }

    public List<News> findByCategory(String category) {
        List<News> list = new ArrayList<>();
        String sql = category == null ?
                "SELECT * FROM news ORDER BY publish_time DESC" :
                "SELECT * FROM news WHERE category = ? ORDER BY publish_time DESC";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            if (category != null) {
                stmt.setString(1, category);
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                News n = new News();
                n.setId(rs.getInt("id"));
                n.setTitle(rs.getString("title"));
                n.setContent(rs.getString("content"));
                n.setCategory(rs.getString("category"));
                Timestamp ts = rs.getTimestamp("publish_time");
                if (ts != null) {
                    n.setPublishTime(new Date(ts.getTime())); // 或直接 news.setPublishTime(ts);
                }
                list.add(n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public News findById(int id) {
        String sql = "SELECT * FROM news WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                News n = new News();
                n.setId(rs.getInt("id"));
                n.setTitle(rs.getString("title"));
                n.setContent(rs.getString("content"));
                n.setCategory(rs.getString("category"));
                Timestamp ts = rs.getTimestamp("publish_time");

                    n.setPublishTime(new Date(ts.getTime())); // 或直接 news.setPublishTime(ts);

                return n;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insert(News news) {
        String sql = "INSERT INTO news (title, content, category) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, news.getTitle());
            stmt.setString(2, news.getContent());
            stmt.setString(3, news.getCategory());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM news WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}