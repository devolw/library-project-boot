package ru.popoff.library.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.popoff.library.models.Book;
import ru.popoff.library.services.BooksService;
import ru.popoff.library.services.PeopleService;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksService booksService;
    private final PeopleService peopleService;

    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }
    
    // Список книг
    @GetMapping()
    public String index(@RequestParam(name = "page", required = false) Integer page,
                        @RequestParam(name = "books_per_page", required = false) Integer booksPerPage,
                        @RequestParam(name = "sort_by_year", required = false) Boolean sortByYear,
                        Model model) {

        if (page != null && booksPerPage != null && sortByYear != null && sortByYear) {
            model.addAttribute("books", booksService.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent());
        } else if (page != null && booksPerPage != null) {
            model.addAttribute("books", booksService.findAll(PageRequest.of(page, booksPerPage)).getContent());
        } else if (sortByYear != null && sortByYear) {
            model.addAttribute("books", booksService.findAll(Sort.by("year")));
        } else {
            model.addAttribute("books", booksService.findAll());
        }
        
        return "books/index";
    }

    // Страница книги
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int bookId, Model model) {
        model.addAttribute("book", booksService.findById(bookId));
        model.addAttribute("owner", booksService.personForBook(bookId));
        model.addAttribute("people", peopleService.findAll());

        return "books/show";
    }

    // Назначить книгу пользователю
    @PatchMapping("/{id}/add")
    public String giveBookForUser(@RequestParam("personId") int personId, @PathVariable("id") int bookId) {
        booksService.giveBook(personId, bookId);

        return "redirect:/books/{id}";
    }

    // Забрать книгу у пользователя
    @PatchMapping("/{id}/delete")
    public String deleteBookForUser(@PathVariable("id") int bookId) {
        booksService.deleteBook(bookId);

        return "redirect:/books/{id}";
    }

    // Страница добавления новой книги
    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("book", new Book());

        return "books/new";
    }

    // Метод добавления новой книги
    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/new";
        }

        booksService.save(book);

        return "redirect:/books";
    }

    // Страница редактирования информации о книге
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int bookId) {
        model.addAttribute("book", booksService.findById(bookId));
        return "books/edit";
    }

    // Метод редактирования информации о книге
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @PathVariable("id") int bookId) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }

        booksService.update(bookId, book);

        return "redirect:/books";
    }

    // Метод удаления книги
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int bookId) {
        booksService.delete(bookId);
        return "redirect:/books";
    }

    // Страница поиска книги
    @GetMapping("/search")
    public String showSearch() {
        return "books/search";
    }

    // Метод поиска книги
    @GetMapping("/search/result")
    public String searchBook(@RequestParam("bookName") String bookName, Model model) {
        List<Book> searchResult = booksService.findBookByName(bookName);
        List<Book> searchResultStartingWith = new ArrayList<>();

        if(searchResult.isEmpty()) {
            searchResultStartingWith = booksService.findByNameStartingWith(bookName);
        }

        model.addAttribute("searchResult", searchResult);
        model.addAttribute("searchResultStartingWith", searchResultStartingWith);

        return "books/search";
    }
}