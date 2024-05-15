package ru.popoff.library.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @Column(name = "name")
    @Size(min = 1, max = 255, message = "Название книги должно содержать от 1 до 255 символов.")
    private String name;

    @Column(name = "author")
    @Size(min = 5, max = 255, message = "Имя автора книги должно содержать от 5 до 255 символов.")
    private String author;

    @Column(name = "year")
    @Min(value = 500, message = "У вас не может быть такой старой книги :)")
    private int year;

    @Column(name="taked_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date takedAt;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person person;

    @Transient
    private Boolean expired;

    public Book() {

    }

    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Date getTakedAt() {
        return takedAt;
    }

    public void setTakedAt(Date takedAt) {
        this.takedAt = takedAt;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }
}