package ru.deyeva.servletapp.dto;

public record CatDto (
        String name,
        int age,
        String gender,
        String color
) {}
