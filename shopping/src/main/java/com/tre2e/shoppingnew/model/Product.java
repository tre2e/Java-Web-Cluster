package com.tre2e.shoppingnew.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int id;
    private String imgUrl;
    private String name;
    private String description;
    private String price;
    private String category;
}
