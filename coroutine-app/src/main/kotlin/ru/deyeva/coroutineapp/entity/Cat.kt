package ru.deyeva.coroutineapp.entity

import org.springframework.data.annotation.Id

data class Cat (
    @Id
    val id: Int?,
    val name: String,
    val age: Int,
    val gender: String,
    val color: String
)
