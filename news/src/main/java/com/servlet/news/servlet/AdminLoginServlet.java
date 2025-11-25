package com.servlet.news.servlet;


import com.servlet.news.dao.AdminDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({"/admin/login", "/admin/logout"})
public class AdminLoginServlet extends HttpServlet {
    private final AdminDAO adminDAO = new AdminDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        if ("/admin/login".equals(path)) {
            req.getRequestDispatcher("/WEB-INF/views/admin/login.jsp").forward(req, resp);
        } else if ("/admin/logout".equals(path)) {
            req.getSession().invalidate();
            resp.sendRedirect(req.getContextPath() + "/admin/news/logout");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (adminDAO.validate(username, password)) {
            req.getSession().setAttribute("adminLoggedIn", true);
            resp.sendRedirect(req.getContextPath() + "/admin/news/list");
        } else {
            req.setAttribute("error", "用户名或密码错误");
            req.getRequestDispatcher("/WEB-INF/views/admin/login.jsp").forward(req, resp);
        }
    }
}