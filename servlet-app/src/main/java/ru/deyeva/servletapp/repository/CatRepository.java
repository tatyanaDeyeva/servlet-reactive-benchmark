package ru.deyeva.servletapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.deyeva.servletapp.entity.Cat;

import java.util.List;

@Repository
public interface CatRepository extends CrudRepository<Cat,Integer> {
    List<Cat> findAllByName(String name);
}
