package ru.deyeva.coroutineapp.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.r2dbc.core.flow
import org.springframework.stereotype.Repository

@Repository
class CatRepository(private val client: DatabaseClient) {
    suspend fun findAllByName(name: String): Flow<Map<String, Any?>> {
        return client
            .sql("SELECT * FROM cat WHERE name = $1")
            .bind(0, name)
            .fetch()
            .flow()
    }

    suspend fun save(name: String, age: Int, gender: String, color: String) =
        client
            .sql("INSERT INTO cat (name, age, gender, color) VALUES($1, $2, $3, $4)")
            .bind(0, name)
            .bind(1, age)
            .bind(2, gender)
            .bind(3, color)
            .fetch()
            .one()
            .awaitFirstOrNull()
}