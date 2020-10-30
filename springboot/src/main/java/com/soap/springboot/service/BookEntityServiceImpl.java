package com.soap.springboot.service;

import com.soap.springboot.entity.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.soap.springboot.repository.BookEntityRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BookEntityServiceImpl implements BookEntityService {

    private BookEntityRepository repository;

    public BookEntityServiceImpl() {

    }

    @Autowired
    public BookEntityServiceImpl(BookEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public BookEntity getBookById(long bookId) {
        return this.repository.findById(bookId).get();
    }

    @Override
    public BookEntity getBookByAuthorId(long authorId) {
        return this.repository.findById(authorId).get();
    }

    @Override
    public List<BookEntity> getAllBook() {
        List<BookEntity> list = new ArrayList<>();
        repository.findAll().forEach(e -> list.add(e));
        return list;
    }

    @Override
    public List<BookEntity> getAllBookByAuthorId(long authorId) {
        List<BookEntity> list = new ArrayList<>();
        repository.getBookByAuthorId(authorId).forEach(e -> list.add(e));
        return list;
    }

    @Override
    public BookEntity addBook(BookEntity entity) {
        try {
            return this.repository.save(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateBook(BookEntity entity) {
        try {
            this.repository.save(entity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteBookById(long id) {
        try {
            this.repository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
