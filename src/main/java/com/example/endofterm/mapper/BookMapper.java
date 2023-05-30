package com.example.endofterm.mapper;

import com.example.endofterm.pojo.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    List<Book> getAllBooks();

    Book getBookById(Integer bookId);

    List<Book> getBookByCondition(Book book);

    int addBook(Book book);
}
