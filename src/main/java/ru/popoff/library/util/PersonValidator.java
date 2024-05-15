package ru.popoff.library.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.popoff.library.models.Person;
import ru.popoff.library.services.PeopleService;

@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    // Валидация - запрет создания одинаковых пользователей
    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (peopleService.show(person.getFullName(), person.getBirthdate()).isPresent()) {
            errors.rejectValue("fullName", "", "");
            errors.rejectValue("birthdate", "", "Такой пользователь уже существует.");
        }
    }
}