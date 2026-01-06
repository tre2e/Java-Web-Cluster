package com.example.ad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontendForwardController {
    // 只匹配前端的核心路由，避免/**全量匹配导致循环
    @GetMapping({"/publish", "/list"})
    public String forwardToIndex() {
        return "forward:/index.html"; // 转发到index.html，前端路由接管
    }

    // 根路径直接重定向到publish（匹配前端默认跳转）
    @GetMapping("/")
    public String root() {
        return "redirect:/publish";
    }
}