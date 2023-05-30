package com.example.endofterm.serivce.Impl;

import com.example.endofterm.mapper.BookMapper;
import com.example.endofterm.pojo.Book;
import com.example.endofterm.serivce.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookMapper bookMapper;

    public BookServiceImpl(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookMapper.getAllBooks();
    }

    @Override
    public Book getBookById(Integer bookId) {
        return bookMapper.getBookById(bookId);
    }

    @Override
    public List<Book> getBookByCondition(Book book) {
        return bookMapper.getBookByCondition(book);
    }

    @Override
    public int addBook(Book book) {
        return bookMapper.addBook(book);
    }

    @Override
    public int updateBook(Integer bookId, Book book) {
        return bookMapper.updateBook(bookId,book);
    }

    @Override
    public int deleteBook(Integer bookId) {
        return bookMapper.deleteBook(bookId);
    }
}
