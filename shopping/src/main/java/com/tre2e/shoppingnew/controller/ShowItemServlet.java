package com.tre2e.shoppingnew.controller;

import com.tre2e.shoppingnew.model.Product;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/show")
public class ShowItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        int id = Integer.parseInt(idStr);
        ServletContext  context = getServletContext();
        List<Product> all = (List<Product>) context.getAttribute("productList");
        Product currentProduct = all.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
        if (currentProduct == null) {
            resp.sendRedirect("index.jsp");
        } else {
            req.setAttribute("p", currentProduct);
            req.getRequestDispatcher("product.jsp").forward(req, resp);
        }
    }
}
