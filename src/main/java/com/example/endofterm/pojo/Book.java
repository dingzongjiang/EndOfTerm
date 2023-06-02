package com.example.endofterm.pojo;


import lombok.Data;

import java.sql.Date;
@Data
public class Book {
    private Integer book_id;
    private String book_name;
    private String book_author;
    private String book_publisher;
    private Double book_price;
    private String book_isbn;
    private String book_introduction;
    private Integer book_category;
    private Integer borrowed_sum;
    private Integer isBack;//0表示未归还，1表示已归还
    private Category category;
    public Book() {
    }

    public Book(String book_name, String book_author, String book_publisher) {
        this.book_name = book_name;
        this.book_author = book_author;
        this.book_publisher = book_publisher;
    }

    public Book(Integer book_id, String book_name, String book_author, String book_publisher, Double book_price, String book_isbn, String book_introduction, Integer book_category, Integer borrowed_sum) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.book_author = book_author;
        this.book_publisher = book_publisher;
        this.book_price = book_price;
        this.book_isbn = book_isbn;
        this.book_introduction = book_introduction;
        this.book_category = book_category;
        this.borrowed_sum = borrowed_sum;
    }

    public Book(Integer book_id, String book_name, String book_author, String book_publisher, Double book_price, String book_isbn, String book_introduction, Integer book_category, Integer borrowed_sum, Integer isBack, Category category) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.book_author = book_author;
        this.book_publisher = book_publisher;
        this.book_price = book_price;
        this.book_isbn = book_isbn;
        this.book_introduction = book_introduction;
        this.book_category = book_category;
        this.borrowed_sum = borrowed_sum;
        this.isBack = isBack;
        this.category = category;
    }
}
