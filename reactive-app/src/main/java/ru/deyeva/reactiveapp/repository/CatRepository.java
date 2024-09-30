package ru.deyeva.reactiveapp.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import ru.deyeva.reactiveapp.entity.Cat;

@Repository
public interface CatRepository extends ReactiveCrudRepository<Cat, Integer> {
    Flux<Cat> findAllByName(String name);
}
