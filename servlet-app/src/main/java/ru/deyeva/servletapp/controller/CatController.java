package ru.deyeva.servletapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import ru.deyeva.servletapp.dto.CatDto;
import ru.deyeva.servletapp.entity.Cat;
import ru.deyeva.servletapp.repository.CatRepository;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cat")
public class CatController {

    private final CatRepository catRepository;

    @PostMapping
    private void saveCat(@RequestBody CatDto catDto) {
        Cat cat = new Cat(null, catDto.name(), catDto.age(), catDto.gender(), catDto.color());
        catRepository.save(cat);
    }

    @GetMapping
    public List<Cat> getCatsByName(@RequestParam String name) {
        return catRepository.findAllByName(name);
    }
}
