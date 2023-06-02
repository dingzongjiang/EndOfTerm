package com.example.endofterm.controller;

import com.example.endofterm.pojo.Book;
import com.example.endofterm.serivce.BookService;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/book")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/getAllBook")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/getBookById/{bookId}")
    public Book getBookById(@PathVariable Integer bookId) {
        return bookService.getBookById(bookId);
    }


    @GetMapping("/getBookByCondition")
    public List<Book> getBookByCondition(
            @RequestParam(value = "bookName", required = false) String bookName,
            @RequestParam(value = "bookAuthor", required = false) String bookAuthor,
            @RequestParam(value = "bookPublisher", required = false) String bookPublisher
    ) {
        Book book = new Book(bookName, bookAuthor, bookPublisher);
        return bookService.getBookByCondition(book);
    }

    @PostMapping("/addBook")
    public String addBook(@RequestBody Map<String, Object> bookMap) {
        Book book = getBook(bookMap);
        int result = bookService.addBook(book);
        if (result == 1) {
            return "{\"message\":\"添加成功\",\"success\":\"true\"}";
        }
        return "{\"message\":\"添加失败\",\"success\":\"false\"}";
    }

    @PatchMapping("/updateBook/{bookId}")
    public String updateBook(@RequestBody Map<String, Object> bookMap, @PathVariable Integer bookId) {
        Book book = getBook(bookMap);
        System.out.println(book);
        int result = bookService.updateBook(bookId, book);
        if (result > 0) {
            return "{\"message\":\"更新成功\",\"success\":\"true\"}";
        }
        return "{\"message\":\"更新失败\",\"success\":\"false\"}";
    }


    @NotNull
    private static Book getBook(Map<String, Object> bookMap) {
        Book book = new Book();
        if (bookMap.get("book_name") != null)
            book.setBook_name(bookMap.get("book_name").toString());
        if (bookMap.get("book_author") != null)
            book.setBook_author(bookMap.get("book_author").toString());
        if (bookMap.get("book_publisher") != null)
            book.setBook_publisher(bookMap.get("book_publisher").toString());
        if (bookMap.get("book_price") != null)
            book.setBook_price(Double.parseDouble(bookMap.get("book_price").toString()));
        if (bookMap.get("book_isbn") != null)
            book.setBook_isbn(bookMap.get("book_isbn").toString());
        if (bookMap.get("book_introduction") != null)
            book.setBook_introduction(bookMap.get("book_introduction").toString());
        if (bookMap.get("borrowed_sum") != null)
            book.setBorrowed_sum(Integer.parseInt(bookMap.get("borrowed_sum").toString()));
        return book;
    }

    @DeleteMapping("/deleteBook/{bookId}")
    public String deleteBook(@PathVariable Integer bookId) {
        if (getBookById(bookId) != null) {
            int result = bookService.deleteBook(bookId);
            if (result > 0) {
                return "{\"message\":\"删除成功\",\"success\":\"true\"}";
            } else {
                return "{\"message\":\"删除失败\",\"success\":\"false\"}";
            }
        }
        return "{\"message\":\"删除失败,没有这本书\",\"success\":\"false\"}";
    }
}
