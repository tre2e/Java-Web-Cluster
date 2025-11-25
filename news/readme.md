# 📰 新闻发布系统（News Portal）

一个基于 Java Servlet + JSP 的轻量级新闻发布与管理系统，支持分类浏览、新闻详情查看、后台登录、新闻发布与删除等功能。

---

## ✨ 功能特性

### 前台（用户端）
- 首页展示新闻列表（支持按“全部 / 娱乐 / 科技 / 国际 / 体育”分类筛选，可自行更改）
- 点击新闻标题进入详情页
- 左右广告位占位（可扩展）

### 后台（管理员）
- 管理员登录/登出
- 发布新闻（标题、内容、分类）
- 查看已发布新闻列表
- 删除新闻（带确认提示）

---

## 🛠 技术栈
- **架构**：MVC 架构  
- **后端**：Java 17+、Servlet 5.0（Jakarta EE 11）、JSP  
- **前端**：HTML5、CSS3、JavaScript（少量内联）  
- **数据库**：MySQL 8.0+  
- **构建工具**：Maven  
- **服务器**：Apache Tomcat 10  

---

## 🚀 快速部署

### 1. 创建数据库

```sql
CREATE DATABASE IF NOT EXISTS news_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE news_db;

CREATE TABLE news (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    category VARCHAR(50) NOT NULL,
    publish_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE admin (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL
);

INSERT INTO admin (username, password) VALUES ('admin', '123456'); -- 添加默认管理员，可自行修改
```
### 2. 配置数据库连接
- 修改 src/main/resources/db.properties（或硬编码在 DAO 中）：

```properties
db.url=jdbc:mysql://localhost:3306/news_db?useSSL=false&serverTimezone=Asia/Shanghai
db.username=your_username  # 修改为你的用户名
db.password=your_password  # 修改为你的密码
```

### 3. 构建并部署
- Maven 项目：运行 mvn clean package，将生成的 .war 文件放入 Tomcat 的 webapps/ 目录
- IDE 直接运行：在 Eclipse/IDEA 中配置 Tomcat Server，直接启动

### 4. 访问应用
- 前台首页：http://localhost:8080/news/index
- 管理后台：http://localhost:8080/news/admin/login

---

## 📝 待办事项（未来可扩展）
- 添加用户注册/登录（前台）
- 新闻分页功能
- 广告系统集成
- 使用 Filter 统一权限控制
- 密码加密存储（如 MD5/BCrypt）

---
