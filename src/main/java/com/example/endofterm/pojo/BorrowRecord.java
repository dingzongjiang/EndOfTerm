package com.example.endofterm.pojo;

import lombok.Data;

import java.sql.Date;
@Data
public class BorrowRecord {
    private Integer borrow_id;
    private Integer user_id;
    private Integer book_id;
    private Date borrow_date;
    private Date return_date;


    public BorrowRecord() {
    }

    public BorrowRecord(Integer borrow_id, Integer user_id, Integer book_id, Date borrow_date, Date return_date) {
        this.borrow_id = borrow_id;
        this.user_id = user_id;
        this.book_id = book_id;
        this.borrow_date = borrow_date;
        this.return_date = return_date;
    }

}
