package com.soap.springboot.service;

import com.soap.springboot.entity.AuthorEntity;

import java.util.List;

public interface AuthorEntityService {

    public AuthorEntity getAuthorById(long id);

    public AuthorEntity getAuthorByFirstname(String firstname);

    public List<AuthorEntity> getAllAuthor();

    public AuthorEntity addAuthor(AuthorEntity entity);

    public boolean updateAuthor(AuthorEntity entity);

    public boolean deleteAuthorById(long id);
}
