package com.soap.springboot.repository;

import com.soap.springboot.entity.AuthorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorEntityRepository extends CrudRepository<AuthorEntity, Long> {
    public AuthorEntity findByFirstname(String firstname);
}
