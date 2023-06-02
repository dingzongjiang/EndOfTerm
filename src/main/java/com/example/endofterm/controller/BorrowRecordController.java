package com.example.endofterm.controller;

import com.example.endofterm.pojo.Book;
import com.example.endofterm.pojo.BorrowRecord;
import com.example.endofterm.serivce.BorrowRecordService;
import com.example.endofterm.serivce.Impl.BookServiceImpl;
import com.example.endofterm.serivce.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.endofterm.pojo.User;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/borrowRecord")
public class BorrowRecordController {
    private final BorrowRecordService borrowRecordService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private BookServiceImpl bookService;
    @Autowired
    public BorrowRecordController(BorrowRecordService borrowRecordService) {
        this.borrowRecordService = borrowRecordService;
    }

    @PostMapping("/addBorrowRecord")
    public String addBorrowRecord(@RequestBody Map<String, Object> recordMap) {
        Integer bookId = Integer.parseInt((String) recordMap.get("book_id"));
        Integer userId = Integer.parseInt((String) recordMap.get("user_id"));
        Book book = bookService.getBookById(bookId);
        if (book.getIsBack()==0) {
            return "{\"message\":\"借阅失败，该书已被借阅\",\"success\":\"false\"}";
        }
        BorrowRecord borrowRecord = new BorrowRecord();
        borrowRecord.setUser_id(userId);
        borrowRecord.setBook_id(bookId);
        LocalDateTime time = LocalDateTime.now();
        borrowRecord.setBorrow_date(time);
        int result = borrowRecordService.addBorrowRecord(borrowRecord);
        if (result > 0) {
            User user = userService.getUserById(userId);
            user.setSum(user.getSum()+1);
            user.setNoBack(user.getNoBack()+1);
            userService.updateUser(userId,user);
            book.setIsBack(0);
            book.setBorrowed_sum(book.getBorrowed_sum()+1);
            bookService.updateBook(bookId,book);
            return "{\"message\":\"借阅成功\",\"success\":\"true\"}";
        }
        return "{\"message\":\"借阅失败\",\"success\":\"false\"}";
    }
}
