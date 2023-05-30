package com.example.endofterm.pojo;


import java.sql.Date;

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

    /**
     * 获取
     * @return book_id
     */
    public Integer getBook_id() {
        return book_id;
    }

    /**
     * 设置
     * @param book_id
     */
    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    /**
     * 获取
     * @return book_name
     */
    public String getBook_name() {
        return book_name;
    }

    /**
     * 设置
     * @param book_name
     */
    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    /**
     * 获取
     * @return book_author
     */
    public String getBook_author() {
        return book_author;
    }

    /**
     * 设置
     * @param book_author
     */
    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    /**
     * 获取
     * @return book_publisher
     */
    public String getBook_publisher() {
        return book_publisher;
    }

    /**
     * 设置
     * @param book_publisher
     */
    public void setBook_publisher(String book_publisher) {
        this.book_publisher = book_publisher;
    }

    /**
     * 获取
     * @return book_price
     */
    public Double getBook_price() {
        return book_price;
    }

    /**
     * 设置
     * @param book_price
     */
    public void setBook_price(Double book_price) {
        this.book_price = book_price;
    }

    /**
     * 获取
     * @return book_isbn
     */
    public String getBook_isbn() {
        return book_isbn;
    }

    /**
     * 设置
     * @param book_isbn
     */
    public void setBook_isbn(String book_isbn) {
        this.book_isbn = book_isbn;
    }

    /**
     * 获取
     * @return book_introduction
     */
    public String getBook_introduction() {
        return book_introduction;
    }

    /**
     * 设置
     * @param book_introduction
     */
    public void setBook_introduction(String book_introduction) {
        this.book_introduction = book_introduction;
    }

    /**
     * 获取
     * @return book_category
     */
    public Integer getBook_category() {
        return book_category;
    }

    /**
     * 设置
     * @param book_category
     */
    public void setBook_category(Integer book_category) {
        this.book_category = book_category;
    }

    /**
     * 获取
     * @return borrowed_sum
     */
    public Integer getBorrowed_sum() {
        return borrowed_sum;
    }

    /**
     * 设置
     * @param borrowed_sum
     */
    public void setBorrowed_sum(Integer borrowed_sum) {
        this.borrowed_sum = borrowed_sum;
    }

    public String toString() {
        return "Book{book_id = " + book_id +
                ", book_name = " + book_name +
                ", book_author = " + book_author +
                ", book_publisher = " + book_publisher +
                ", book_price = " + book_price +
                ", book_isbn = " + book_isbn +
                ", book_introduction = " + book_introduction +
                ", book_category = " + book_category +
                ", borrowed_sum = " + borrowed_sum + "}";
    }
}
