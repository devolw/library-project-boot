package ru.popoff.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.popoff.library.models.Book;
import ru.popoff.library.models.Person;
import ru.popoff.library.repositories.BooksRepository;
import ru.popoff.library.repositories.PeopleRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;
    private final PeopleRepository peopleRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository, PeopleRepository peopleRepository) {
        this.booksRepository = booksRepository;
        this.peopleRepository = peopleRepository;
    }

    // Получить список всех книг
    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    // Перегрузка findAll(), используется для пагинации
    public Page<Book> findAll(Pageable pageable) {
        return booksRepository.findAll(pageable);
    }

    // Перегрузка findAll(), используется для сортировки
    public List<Book> findAll(Sort sort) {
        return booksRepository.findAll(sort);
    }

    // Поиск книги по id
    public Book findById(int bookId) {
        Optional<Book> foundBook = booksRepository.findById(bookId);

        return foundBook.orElse(null);
    }

    // Создать книгу
    @Transactional
    public Book save(Book book) {
        return booksRepository.save(book);
    }

    // Обновить книгу
    @Transactional
    public void update(int bookId, Book updatedBook) {
        updatedBook.setBookId(bookId);
        booksRepository.save(updatedBook);
    }

    // Удалить книгу
    @Transactional
    public void delete(int bookId) {
        booksRepository.deleteById(bookId);
    }

    // Получить пользователя книги
    public Person personForBook(int bookId) {
        Optional<Book> optionalBook = booksRepository.findById(bookId);
        Book book = optionalBook.get();

        return book.getPerson();
    }

    // Назначить пользователю книгу
    @Transactional
    public void giveBook(int personId, int bookId) {
        Optional<Book> optionalBook = booksRepository.findById(bookId);
        Book book = optionalBook.get();

        Optional<Person> personOptional = peopleRepository.findById(personId);
        Person person = personOptional.get();

        book.setTakedAt(new Date());
        book.setPerson(person);
        person.setBooks(new ArrayList<>(java.util.Collections.singletonList(book)));
    }

    // Забрать книгу у пользователя
    @Transactional
    public void deleteBook(int bookId) {
        Optional<Book> optionalBook = booksRepository.findById(bookId);
        Book book = optionalBook.get();

        book.setTakedAt(null);
        book.setPerson(null);
    }

    // Поиск книги по имени
    public List<Book> findBookByName(String bookName) {
        return booksRepository.findBookByName(bookName);
    }

    // Поиск книги по первым буквам
    public List<Book> findByNameStartingWith(String starting) {
        return booksRepository.findByNameStartingWith(starting);
    }
}
