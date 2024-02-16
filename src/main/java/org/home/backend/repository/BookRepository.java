package org.home.backend.repository;

import org.home.backend.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value="select distinct count(b.id) from data.book b where b.sage_id = ?1", nativeQuery = true)
    Integer howManyBookInASage(long sage_id);
}
