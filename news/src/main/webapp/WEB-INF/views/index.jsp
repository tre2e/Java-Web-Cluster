<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>新闻首页</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <nav>
        <a href="${pageContext.request.contextPath}/news/list?category=all"
           class="${currentCategory == 'all' ? 'active' : ''}">全部</a>
        <a href="${pageContext.request.contextPath}/news/list?category=科技"
           class="${currentCategory == '科技' ? 'active' : ''}">科技</a>
        <a href="${pageContext.request.contextPath}/news/list?category=娱乐"
           class="${currentCategory == '娱乐' ? 'active' : ''}">娱乐</a>
        <a href="${pageContext.request.contextPath}/news/list?category=国际"
           class="${currentCategory == '国际' ? 'active' : ''}">国际</a>
        <a href="${pageContext.request.contextPath}/news/list?category=体育"
           class="${currentCategory == '体育' ? 'active' : ''}">体育</a>
        <a href="${pageContext.request.contextPath}/admin/login">[管理后台]</a>
    </nav>

    <div class="ad-left">
        <iframe src="${pageContext.request.contextPath}/ads/ad-placeholder.html" frameborder="0" width="100%" height="300"></iframe>
    </div>

    <main>
        <c:choose>
            <c:when test="${empty newsList}">
                <p>暂无新闻</p>
            </c:when>
            <c:otherwise>
                <c:forEach items="${newsList}" var="item">
                    <div class="news-item">
                        <h3><a href="${pageContext.request.contextPath}/news/${item.id}">${item.title}</a></h3>
                        <p class="meta">[${item.category}] <fmt:formatDate value="${item.publishTime}" pattern="yyyy年MM月dd日 HH:mm"/></p>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </main>

    <div class="ad-right">
        <iframe src="${pageContext.request.contextPath}/ads/ad-placeholder.html" frameborder="0" width="100%" height="300"></iframe>
    </div>
</div>
</body>
</html>