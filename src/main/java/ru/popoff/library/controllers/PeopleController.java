package ru.popoff.library.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.popoff.library.models.Person;
import ru.popoff.library.services.PeopleService;
import ru.popoff.library.util.PersonValidator;


@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonValidator personValidator;
    private final PeopleService peopleService;

    // Внедрение зависимостей
    @Autowired
    public PeopleController(PersonValidator personValidator, PeopleService peopleService) {
        this.personValidator = personValidator;
        this.peopleService = peopleService;
    }

    // Список пользователей
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", peopleService.findAll());

        return "people/index";
    }

    // Страница пользователя
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int personId, Model model) {
        model.addAttribute("person", peopleService.findById(personId));
        model.addAttribute("books", peopleService.booksForUser(personId));

        return "people/show";
    }

    // Страница добавления нового пользователя
    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());

        return "people/new";
    }

    // Метод добавления нового пользователя
    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if(bindingResult.hasErrors()) {
            return "people/new";
        }
        peopleService.save(person);

        return "redirect:/people";
    }

    // Страница редактирования информации о пользователе
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int personId) {
        model.addAttribute("person", peopleService.findById(personId));

        return "people/edit";
    }

    // Метод редактирования информации о пользователе
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int personId) {
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
        peopleService.update(personId, person);

        return "redirect:/people";
    }

    // Метод удаления пользователя
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int personId) {
        peopleService.deleteById(personId);

        return "redirect:/people";
    }
}