package ru.popoff.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.popoff.library.models.Book;
import ru.popoff.library.models.Person;
import ru.popoff.library.repositories.PeopleRepository;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    // Получить список всех пользователей
    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    // Получить пользователя по id
    public Person findById(int personId) {
        Optional<Person> foundPerson = peopleRepository.findById(personId);

        return foundPerson.orElse(null);
    }

    // Spring JPA
    // Перегрузка метода `show()`, метод используется для валидации
    public Optional<Person> show(String fullName, int birthdate) {
        return peopleRepository.findByFullNameAndBirthdate(fullName, birthdate);
    }

    // Создать пользователя
    @Transactional
    public Person save(Person person) {
        return peopleRepository.save(person);
    }

    // Обновить пользователя
    @Transactional
    public void update(int personId, Person updatedPerson) {
        updatedPerson.setPersonId(personId);
        peopleRepository.save(updatedPerson);
    }

    // Удалить пользователя
    @Transactional
    public void deleteById(int personId) {
        peopleRepository.deleteById(personId);
    }

    // Получить список книг пользователя
    @Transactional
    public List<Book> booksForUser(int personId) {
        Optional<Person> foundPerson = peopleRepository.findById(personId);

        Person person = foundPerson.orElse(null);

        if (person == null) {
            return Collections.emptyList();
        }

        List<Book> books = person.getBooks();

        for (Book book : books) {
            long diffInMilles = Math.abs(book.getTakedAt().getTime() - new Date().getTime());

            if (diffInMilles > 864000000) {
                book.setExpired(true);
            } else {
                book.setExpired(false);
            }
        }

        return books;
    }
}