# 🚀 项目介绍：Java Web Cluster - 广告投放网站群

## 🎯 项目概述

本项目旨在构建一个**基于广告投放的 Java Web 网站集群**，通过连接一个核心**广告投放网站**与三个面向不同用户群体的垂直应用网站（**购物**、**视频**、**新闻**），形成一个完整的生态系统。它不仅是一个实验性作业，更是一个涵盖现代 Web 开发核心技术的综合性平台。

---

## 💻 核心技术栈

我们采用业界主流且成熟的全栈技术体系，兼顾开发效率与系统性能：

![Vue.js](https://img.shields.io/badge/Vue.js-4FC08D?style=for-the-badge&logo=vue.js&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)


---

## 🌐 网站集群构成与功能详情

本项目由一个核心管理网站和三个用户端网站组成，共同实现广告的投放、展示。

### 1. 📢 广告投放网站（面向广告投放方 / 管理员）
(http://10.100.164.21:8082)
- **核心功能**：广告管理、内容分发与投放。
- **管理员操作**：
  - 投放、编辑、管理**图片**、**视频**三种形式的广告；
- **智能投放**：随机向购物、视频、新闻网站推送广告内容。

### 2. 🛍️ 购物网站（面向顾客群体）
(http://10.100.164.24:8080/ShoppingNew)
- **核心功能**：商品浏览、内容展示、广告嵌入。
- **用户体验**：游客可自由浏览商品信息，页面自动加载个性化广告。
- **广告集成**：在首页及商品列表页嵌入来自广告平台的动态广告位。

### 3. 📺 视频网站（面向视频观众）
(http://10.100.164.21:1000)
- **核心功能**：视频聚合、播放、主页广告展示。
- **用户体验**：用户可浏览视频缩略图并点击观看。
- **广告集成**：在主页面关键区域展示高相关性广告。
- **广告插入**：在视频打开时和中间插入广告。

### 4. 📰 新闻网站（面向新闻读者）
(http://10.100.164.22:8080/news/)
- **核心功能**：新闻分类阅读、内容推送、侧边栏广告。
- **用户体验**：按类别（科技、体育、财经等）筛选新闻。
- **广告集成**：在新闻详情页两侧边栏动态插入兴趣匹配广告。
---

## 🤝 小组成员
| 成员名称 | GitHub Username |
| :-------: | :---------------: |
| 伍岳伟 | [tre2e](https://github.com/tre2e) |
| 王梓严 | [Hoshino-123](https://github.com/Hoshino-123) |
| 王福阳 | [qwq-no](https://github.com/qwq-no) |

---

## 💡 项目价值与总结

1. **系统解耦**：四站完全独立，仅通过广告平台间接协同，架构清晰；
2. **扩展性强**：支持多类型广告、多维度兴趣标签，易于后续迭代。

---
