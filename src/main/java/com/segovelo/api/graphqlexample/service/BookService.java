package com.segovelo.api.graphqlexample.service;

import java.util.List;

import com.segovelo.api.graphexample.model.Book;

public interface BookService {
    Book getBookWithTitle(String title);

    List<Book> getAllBooks();

    Book addBook(Book book);

    Book updateBook(Book book);

    boolean deleteBook(Book book);

}
