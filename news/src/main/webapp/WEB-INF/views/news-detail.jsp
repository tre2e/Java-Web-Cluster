<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${news.title}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <nav>
        <a href="${pageContext.request.contextPath}/news/list">← 返回首页</a>
        <a href="${pageContext.request.contextPath}/admin/login">[管理]</a>
    </nav>

    <div class="ad-left">
        <iframe src="${pageContext.request.contextPath}/ads/ad-placeholder.html" frameborder="0" width="100%" height="300"></iframe>
    </div>

    <main>
        <article>
            <h1>${news.title}</h1>
            <p class="meta">[${news.category}] <fmt:formatDate value="${news.publishTime}" pattern="yyyy年MM月dd日 HH:mm"/></p>
            <div class="content">${news.content}</div>
        </article>
    </main>

    <div class="ad-right">
        <iframe src="${pageContext.request.contextPath}/ads/ad-placeholder.html" frameborder="0" width="100%" height="300"></iframe>
    </div>
</div>
</body>
</html>
