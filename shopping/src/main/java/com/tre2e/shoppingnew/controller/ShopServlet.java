package com.tre2e.shoppingnew.controller;

import com.tre2e.shoppingnew.model.Product;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/shop")
public class ShopServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        // 只处理加入购物车
        if ("add".equals(action)) {
            String idStr = req.getParameter("id");
            if (idStr == null || idStr.isBlank()) {
                resp.sendRedirect(req.getContextPath() + "/index.jsp");
                return;
            }

            int id = Integer.parseInt(idStr);

            // 从 application 里拿到全部商品
            @SuppressWarnings("unchecked")
            List<Product> allProducts = (List<Product>) getServletContext().getAttribute("productList");

            Product toAdd = allProducts.stream()
                    .filter(p -> p.getId() == id)
                    .findFirst()
                    .orElse(null);

            if (toAdd == null) {
                resp.sendRedirect(req.getContextPath() + "/index.jsp");
                return;
            }

            // 从 session 获取或创建购物车（List<Product>）
            HttpSession session = req.getSession();
            List<Product> cart = (List<Product>) session.getAttribute("cart");

            if (cart == null) {
                cart = new ArrayList<>();
                session.setAttribute("cart", cart);
            }

            cart.add(toAdd);   // 加入购物车（允许重复）

            // 加入成功后跳转到购物车页面或继续留在详情页
            // 这里选择：留在当前详情页并提示成功
            req.setAttribute("p", toAdd);  // 重新放回，防止刷新丢失
            req.setAttribute("addMsg", "已成功加入购物车！");
            req.getRequestDispatcher("/product.jsp").forward(req, resp);
        }
    }

    // 查看购物车 + 清空购物车
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("clear".equals(action)) {
            req.getSession().removeAttribute("cart");
            resp.sendRedirect("shop"); // 刷新购物车页面
            return;
        }

        // 默认：显示购物车
        req.getRequestDispatcher("/cart.jsp").forward(req, resp);
    }
}