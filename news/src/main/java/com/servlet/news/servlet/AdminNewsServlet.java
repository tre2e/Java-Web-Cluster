package com.servlet.news.servlet;


import com.servlet.news.dao.NewsDAO;
import com.servlet.news.model.News;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/news/*")
public class AdminNewsServlet extends HttpServlet {
    private final NewsDAO newsDAO = new NewsDAO();

    private boolean isLoggedIn(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        return req.getSession().getAttribute("adminLoggedIn") != null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pathInfo = req.getPathInfo();
        if ("/list".equals(pathInfo)) {
            List<News> newsList = newsDAO.findAll();
            req.setAttribute("newsList", newsList);
            req.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp").forward(req, resp);
        } else if("/logout".equals(pathInfo)){
            req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
        } else if (pathInfo != null && pathInfo.startsWith("/delete/")) {
            try {
                int id = Integer.parseInt(pathInfo.substring(8));
                newsDAO.deleteById(id);
            } catch (NumberFormatException ignored) {}
            resp.sendRedirect(req.getContextPath() + "/admin/news/list");
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (!isLoggedIn(req)) {
            resp.sendRedirect(req.getContextPath() + "/admin/login");
            return;
        }

        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String category = req.getParameter("category");

        if (title != null && content != null && category != null) {
            News news = new News();
            news.setTitle(title.trim());
            news.setContent(content.trim());
            news.setCategory(category.trim());
            newsDAO.insert(news);
        }

        resp.sendRedirect(req.getContextPath() + "/admin/news/list");
    }
}