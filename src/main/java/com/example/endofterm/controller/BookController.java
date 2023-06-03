package com.example.endofterm.controller;

import com.example.endofterm.pojo.Book;
import com.example.endofterm.serivce.BookService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @NotNull
    private static Book getBook(@NotNull Map<String, Object> bookMap) {
        Book book = new Book();
        if (bookMap.get("book_id") != null) book.setBook_id(Integer.parseInt(bookMap.get("book_id").toString()));
        if (bookMap.get("book_name") != null) book.setBook_name(bookMap.get("book_name").toString());
        if (bookMap.get("book_author") != null) book.setBook_author(bookMap.get("book_author").toString());
        if (bookMap.get("book_publisher") != null) book.setBook_publisher(bookMap.get("book_publisher").toString());
        if (bookMap.get("book_price") != null)
            book.setBook_price(Double.parseDouble(bookMap.get("book_price").toString()));
        if (bookMap.get("book_isbn") != null) book.setBook_isbn(bookMap.get("book_isbn").toString());
        if (bookMap.get("book_introduction") != null)
            book.setBook_introduction(bookMap.get("book_introduction").toString());
        if (bookMap.get("borrowed_sum") != null)
            book.setBorrowed_sum(Integer.parseInt(bookMap.get("borrowed_sum").toString()));
        if (bookMap.get("book_category") != null)
            book.setBook_category(Integer.parseInt(bookMap.get("book_category").toString()));
        return book;
    }

    @GetMapping("/getAllBooks")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> bookList = bookService.getAllBooks();
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @GetMapping("/getBookById/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer bookId) {
        Book book = bookService.getBookById(bookId);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/getBookByCondition")
    public ResponseEntity<List<Book>> getBookByCondition(@RequestParam(value = "bookName", required = false) String bookName, @RequestParam(value = "bookAuthor", required = false) String bookAuthor, @RequestParam(value = "bookPublisher", required = false) String bookPublisher) {
        Book book = new Book(bookName, bookAuthor, bookPublisher);
        List<Book> bookList = bookService.getBookByCondition(book);
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @PostMapping("/addBook")
    public ResponseEntity<String> addBook(@RequestBody Map<String, Object> bookMap) {
        Book book = getBook(bookMap);
        if (book.getBook_name() == null || book.getBook_name().equals(""))
            return new ResponseEntity<>("{\"message\":\"添加失败,图书名未输入\",\"success\":\"false\"}", HttpStatus.BAD_REQUEST);
        if (book.getBook_author() == null || book.getBook_author().equals(""))
            return new ResponseEntity<>("{\"message\":\"添加失败,图书作者未输入\",\"success\":\"false\"}", HttpStatus.BAD_REQUEST);
        if (book.getBook_publisher() == null || book.getBook_publisher().equals(""))
            return new ResponseEntity<>("{\"message\":\"添加失败,图书出版社未输入\",\"success\":\"false\"}", HttpStatus.BAD_REQUEST);
        if (book.getBook_price() == null)
            return new ResponseEntity<>("{\"message\":\"添加失败,图书价格未输入\",\"success\":\"false\"}", HttpStatus.BAD_REQUEST);
        if (book.getBook_isbn() == null || book.getBook_isbn().equals(""))
            return new ResponseEntity<>("{\"message\":\"添加失败,图书ISBN未输入\",\"success\":\"false\"}", HttpStatus.BAD_REQUEST);
        if (book.getBook_introduction() == null || book.getBook_introduction().equals(""))
            return new ResponseEntity<>("{\"message\":\"添加失败,图书简介未输入\",\"success\":\"false\"}", HttpStatus.BAD_REQUEST);
        if (book.getBook_category() == null)
            return new ResponseEntity<>("{\"message\":\"添加失败,图书类别未输入\",\"success\":\"false\"}", HttpStatus.BAD_REQUEST);
        int result = bookService.addBook(book);
        if (result == 1) {
            return new ResponseEntity<>("{\"message\":\"添加成功\",\"success\":\"true\"}", HttpStatus.OK);
        }
        return new ResponseEntity<>("{\"message\":\"添加失败\",\"success\":\"false\"}", HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/updateBook/{bookId}")
    public String updateBook(@RequestBody Map<String, Object> bookMap, @PathVariable Integer bookId) {
        if (bookMap.isEmpty()) {
            return "{\"message\":\"更新失败,没有传入更新信息\",\"success\":\"false\"}";
        }
        Book book = getBook(bookMap);
        System.out.println(book);
        int result = bookService.updateBook(bookId, book);
        if (result > 0) {
            return "{\"message\":\"更新成功\",\"success\":\"true\"}";
        }
        return "{\"message\":\"更新失败\",\"success\":\"false\"}";
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
