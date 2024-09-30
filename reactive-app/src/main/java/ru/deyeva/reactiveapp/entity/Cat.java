package ru.deyeva.reactiveapp.entity;

import org.springframework.data.annotation.Id;

public record Cat (
        @Id
        Integer id,
        String name,
        int age,
        String gender,
        String color
){}
