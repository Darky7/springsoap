package com.soap.springboot.service;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

import com.soap.springboot.entity.AuthorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.soap.springboot.repository.AuthorEntityRepository;

@Service
@Transactional
public class AuthorEntityServiceImpl implements AuthorEntityService {

    private AuthorEntityRepository repository;

    public AuthorEntityServiceImpl() {

    }

    @Autowired
    public AuthorEntityServiceImpl(AuthorEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public AuthorEntity getAuthorById(long id) {
        return this.repository.findById(id).get();
    }

    @Override
    public AuthorEntity getAuthorByFirstname(String firstname) {
        return this.repository.findByFirstname(firstname);
    }

    @Override
    public List<AuthorEntity> getAllAuthor() {
        List<AuthorEntity> list = new ArrayList<>();
        repository.findAll().forEach(e -> list.add(e));
        return list;
    }

    @Override
    public AuthorEntity addAuthor(AuthorEntity entity) {
        try {
            return this.repository.save(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateAuthor(AuthorEntity entity) {
        try {
            this.repository.save(entity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteAuthorById(long id) {
        try {
            this.repository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
