<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>校园新闻网</title>
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<!-- 导航栏 -->
<header class="navbar">
    <div class="nav-container">
        <div class="nav-brand">校园新闻网</div>
        <nav class="nav-links">
            <a href="index.jsp">全部</a>
            <a href="/?category=tech">科技</a>
            <a href="/?category=sports">体育</a>
            <a href="/?category=politics">时政</a>
            <a href="/admin/login">管理</a>
        </nav>
    </div>
</header>

<main class="container">
    <div class="main-layout">
        <!-- 左侧广告 -->
        <aside class="ad-left">
            <div class="ad-placeholder">左侧广告位</div>
        </aside>

        <!-- 新闻列表 -->
        <section class="news-list">
            <c:forEach items="${newsList}" var="item">
                <article class="news-item">
                    <h2><a href="/news/${item.id}">${item.title}</a></h2>
                    <div class="news-meta">${item.publishTime} | ${item.category}</div>
                </article>
            </c:forEach>
        </section>

        <!-- 右侧广告 -->
        <aside class="ad-right">
            <div class="ad-placeholder">右侧广告位</div>
        </aside>
    </div>
</main>

<script src="js/news.js"></script>
</body>
</html>