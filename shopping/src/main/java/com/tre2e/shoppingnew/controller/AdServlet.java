package com.tre2e.shoppingnew.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@WebServlet("/ad")
public class AdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://10.100.164.21:8082/api/ad/get"))
                .GET()
                .build();

        try {
            HttpResponse<String> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            if (httpResponse.statusCode() == 200) {
                JsonObject json = JsonParser.parseString(httpResponse.body()).getAsJsonObject();
                int code = json.get("code").getAsInt();
                if (code == 200) {
                    JsonObject data = json.getAsJsonObject("data");
                    String resourceUrl = data.get("resourceUrl").getAsString();
                    String adTitle = data.has("adTitle") ? data.get("adTitle").getAsString() : "广告";

                    // 将数据传递给JSP
                    request.setAttribute("resourceUrl", resourceUrl);
                    request.setAttribute("adTitle", adTitle);
                }
            }
        } catch (Exception e) {
            // 简单错误处理，可记录日志
            request.setAttribute("adTitle", "广告加载失败");
        }
    }
}
