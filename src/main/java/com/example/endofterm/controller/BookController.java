package com.example.endofterm.controller;

import com.example.endofterm.pojo.Book;
import com.example.endofterm.serivce.BookService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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
            @RequestParam(value = "bookPublisher",required = false) String bookPublisher
    ) {
        Book book = new Book(bookName, bookAuthor, bookPublisher);
        return bookService.getBookByCondition(book);
    }

    @PostMapping("/addBook")
    public String addBook(@RequestBody Book book) {
        int result=bookService.addBook(book);
        if(result==1){
            return "addBookSuccess";
        }
        return "addBook";
    }
}
