package com.servlet.news.servlet;


import com.servlet.news.dao.NewsDAO;
import com.servlet.news.model.News;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/news/*")
public class NewsServlet extends HttpServlet {
    private final NewsDAO newsDAO = new NewsDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || "/list".equals(pathInfo)) {
            String category = req.getParameter("category");
            if (category == null || "all".equals(category)) {
                category = "all";
            }
            List<News> newsList = "all".equals(category) ? newsDAO.findAll() : newsDAO.findByCategory(category);
            req.setAttribute("newsList", newsList);
            req.setAttribute("currentCategory", category);
            req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
        } else if (pathInfo.matches("/\\d+")) {
            int id = Integer.parseInt(pathInfo.substring(1));
            News news = newsDAO.findById(id);
            if (news != null) {
                req.setAttribute("news", news);
                req.getRequestDispatcher("/WEB-INF/views/news-detail.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}