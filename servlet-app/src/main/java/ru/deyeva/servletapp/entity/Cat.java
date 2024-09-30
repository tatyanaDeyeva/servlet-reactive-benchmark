package ru.deyeva.servletapp.entity;

import org.springframework.data.annotation.Id;

public record Cat (
    @Id
    Integer id,
    String name,
    Integer age,
    String gender,
    String color
){}