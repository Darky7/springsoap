package com.soap.springboot.repository;

import com.soap.springboot.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookEntityRepository extends CrudRepository<BookEntity, Long> {
    public BookEntity findByBookId(Long bookId);

    Iterable<BookEntity> getBookByAuthorId(long authorId);
}
