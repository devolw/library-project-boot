package ru.popoff.library.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personId;

    @Column(name = "fullname")
    @Size(min = 5, max = 255, message = "Имя пользователя должно содержать от 5 до 255 символов.")
    private String fullName;

    @Column(name = "birthdate")
    @Min(value = 1900, message = "Вам не может быть столько лет :)")
    private int birthdate;

    @OneToMany(mappedBy = "person")
    private List<Book> books;

    public Person() {

    }

    public Person(String fullName, int birthdate) {
        this.fullName = fullName;
        this.birthdate = birthdate;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(int birthdate) {
        this.birthdate = birthdate;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}