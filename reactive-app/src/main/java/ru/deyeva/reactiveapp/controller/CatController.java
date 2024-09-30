package ru.deyeva.reactiveapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.deyeva.reactiveapp.dto.CatDto;
import ru.deyeva.reactiveapp.entity.Cat;
import ru.deyeva.reactiveapp.repository.CatRepository;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cat")
public class CatController {
    private final CatRepository catRepository;

    @PostMapping
    public Mono<Cat> save(@RequestBody CatDto catDto) {
        Cat cat = new Cat(null, catDto.name(), catDto.age(), catDto.gender(), catDto.color());
        return catRepository.save(cat);
    }

    @GetMapping
    public Flux<Cat> findAllByName(@RequestParam String name) {
        return catRepository.findAllByName(name);
    }
}
