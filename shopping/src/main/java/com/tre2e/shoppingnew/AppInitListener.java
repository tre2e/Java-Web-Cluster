package com.tre2e.shoppingnew;

import com.tre2e.shoppingnew.model.Product;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.util.ArrayList;
import java.util.List;

@WebListener
public class AppInitListener implements ServletContextListener {

    @Override
    public void contextInitialized (ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        List<Product> productList = new ArrayList<>();

        productList.add(new Product(1, "images/polo衫.jpg", "经典纯棉POLO衫", "男士短袖翻领T恤，多色可选，透气舒适", "89", "服饰"));
        productList.add(new Product(2, "images/乐事薯片.jpg", "乐事经典原味薯片", "104g大包装，香脆可口", "12.9", "零食"));
        productList.add(new Product(3, "images/俄罗斯糖果.jpg", "俄罗斯进口紫皮糖", "500g混合装，童年回忆", "39", "糖果"));
        productList.add(new Product(4, "images/华夫饼.jpg", "丹夫华夫饼", "原味/巧克力味 10片独立装", "19.9", "零食"));
        productList.add(new Product(5, "images/双肩包.jpg", "简约双肩背包", "大容量学生书包，防水尼龙材质", "79", "箱包"));
        productList.add(new Product(6, "images/垃圾袋.png", "加厚平口垃圾袋", "45×50cm 100只装，超韧不断", "9.9", "日用"));
        productList.add(new Product(7, "images/手持风扇.jpg", "USB充电手持小风扇", "三档位可调，超静音，带底座", "29", "数码"));
        productList.add(new Product(8, "images/手提篮.jpg", "塑料手提购物篮", "超市买菜篮，结实耐用", "15", "日用"));
        productList.add(new Product(9, "images/打火机.jpg", "普通明火打火机", "10支装，可充气", "10", "日用"));
        productList.add(new Product(10, "images/挂篮.jpg", "浴室壁挂收纳篮", "免打孔置物架，三层", "35", "家居"));
        productList.add(new Product(11, "images/沙琪玛.jpg", "冠生园沙琪玛", "蛋香味 500g", "18", "零食"));
        productList.add(new Product(12, "images/海飞丝洗发水.jpg", "海飞丝去屑洗发水", "清爽去油型 750ml", "55", "洗护"));
        productList.add(new Product(13, "images/相印纸巾.jpg", "心相印抽纸", "3层100抽×24包整箱", "69", "纸品"));
        productList.add(new Product(14, "images/粗根鞋.jpg", "马丁靴女粗跟短靴", "2025春季新款，英伦风", "159", "鞋靴"));
        productList.add(new Product(15, "images/织物鞋.jpg", "老北京布鞋女", "千层底透气软底，一脚蹬", "49", "鞋靴"));
        productList.add(new Product(16, "images/蓝月亮.jpg", "蓝月亮洗衣液", "薰衣草香 3kg×2瓶家庭装", "79", "洗护"));
        productList.add(new Product(17, "images/蓝牙耳机.jpg", "真无线蓝牙耳机", "入耳式降噪，超长续航20小时", "99", "数码"));
        productList.add(new Product(18, "images/蓝牙音箱.jpg", "便携式蓝牙音箱", "户外防水重低音炮", "129", "数码"));
        productList.add(new Product(19, "images/长袖上衣.jpg", "纯棉长袖T恤打底衫", "男女同款，多色可选", "49", "服饰"));
        productList.add(new Product(20, "images/闪充数据线.jpg", "66W超级闪充Type-C数据线", "6A快充 1米", "15", "数码"));
        productList.add(new Product(21, "images/陶瓷盆.jpg", "北欧陶瓷面盆", "台上盆洗手盆洗脸盆", "199", "家居"));
        productList.add(new Product(22, "images/雨伞.jpg", "全自动折叠晴雨伞", "三折加大加固抗风", "39", "日用"));
        productList.add(new Product(23, "images/魔芋爽.jpg", "魔芋爽素毛肚", "香辣味 20g×30包", "35", "零食"));
        productList.add(new Product(24, "images/黑人牙膏.jpg", "黑人牙膏双重薄荷", "220g 清新口气", "19.9", "洗护"));

        context.setAttribute("productList", productList);

    }
}