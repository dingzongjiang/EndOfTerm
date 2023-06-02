package com.example.endofterm.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BorrowRecord {
    private Integer borrow_id;
    private Integer user_id;
    private Integer book_id;
    private LocalDateTime borrow_date;
    private LocalDateTime return_date;


    public BorrowRecord() {
    }

    public BorrowRecord(Integer borrow_id, Integer user_id, Integer book_id, LocalDateTime borrow_date, LocalDateTime return_date) {
        this.borrow_id = borrow_id;
        this.user_id = user_id;
        this.book_id = book_id;
        this.borrow_date = borrow_date;
        this.return_date = return_date;
    }


}
