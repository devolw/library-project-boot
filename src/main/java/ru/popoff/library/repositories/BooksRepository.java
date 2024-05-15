package ru.popoff.library.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.popoff.library.models.Book;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {

    Page<Book> findAll(Pageable pageable);
    List<Book> findAll(Sort sort);
    List<Book> findBookByName(String bookName);
    List<Book> findByNameStartingWith(String starting);
}