<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理后台</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <nav>
        <a href="${pageContext.request.contextPath}/admin/logout">退出登录</a>
    </nav>

    <div style="grid-column: 2 / span 1;">
        <h2>发布新闻</h2>
        <form method="post" action="${pageContext.request.contextPath}/admin/news/list">
            <div><input type="text" name="title" placeholder="标题" required style="width:100%; padding:8px;"></div>
            <div><textarea name="content" placeholder="内容" required style="width:100%; height:150px; margin:10px 0;"></textarea></div>
            <div>
                <select name="category" required>
                    <option value="">--选择分类--</option>
                    <option value="科技">科技</option>
                    <option value="娱乐">娱乐</option>
                    <option value="国际">国际</option>
                    <option value="体育">体育</option>
                </select>
            </div>
            <button type="submit">发布</button>
        </form>

        <h2>已发布新闻</h2>
        <c:forEach items="${newsList}" var="n">
            <div style="border-bottom:1px solid #eee; padding:10px 0;">
                <strong>${n.title}</strong> [${n.category}]
                <a href="${pageContext.request.contextPath}/admin/news/delete/${n.id}"
                   onclick="return confirm('确定删除？')">删除</a>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
