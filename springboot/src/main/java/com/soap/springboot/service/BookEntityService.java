package com.soap.springboot.service;

import com.soap.springboot.entity.BookEntity;

import java.util.List;

public interface BookEntityService {

    public BookEntity getBookById(long bookId);

    public BookEntity getBookByAuthorId(long authorId);

    public List<BookEntity> getAllBook();

    public List<BookEntity> getAllBookByAuthorId(long authorId);

    public BookEntity addBook(BookEntity entity);

    public boolean updateBook(BookEntity entity);

    public boolean deleteBookById(long id);
}
