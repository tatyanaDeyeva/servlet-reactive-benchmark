package ru.deyeva.coroutineapp.controller

import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.*
import ru.deyeva.coroutineapp.dto.CatDto
import ru.deyeva.coroutineapp.repository.CatRepository

@RestController
@RequestMapping("/cat")
class CatController(
    val catRepository: CatRepository
) {
    @PostMapping
    suspend fun saveCat(@RequestBody cat: CatDto) {
        catRepository.save(cat.name, cat.age, cat.gender, cat.color)
    }

    @GetMapping
    suspend fun getCatsByName(@RequestParam name: String): Flow<Map<String, Any?>> {
        return catRepository.findAllByName(name)
    }
}