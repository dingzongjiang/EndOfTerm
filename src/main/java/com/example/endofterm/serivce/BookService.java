package com.example.endofterm.serivce;

import com.example.endofterm.pojo.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    Book getBookById(Integer bookId);

    List<Book> getBookByCondition(Book book);

    int addBook(Book book);

    int updateBook(Integer bookId, Book book);

    int deleteBook(Integer bookId);
}
