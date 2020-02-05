package com.handler.demo.error.config

import com.handler.demo.error.database.DatabaseConnector
import com.handler.demo.error.database.DatabaseEnvironment
import com.handler.demo.error.entities.UserEntity
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DatabaseConfig {
    val databaseEnvironmentTest: DatabaseEnvironment
        @Bean
        @Qualifier("databaseEnvironmentTest")
        get() = DatabaseEnvironment.Memory

    @Autowired
    @Qualifier("databaseEnvironmentTest")
    fun setDatabaseEnvironment(databaseEnvironment: DatabaseEnvironment) {
        DatabaseConnector.init(databaseEnvironment)
        /*Creating some data*/
        transaction {
            SchemaUtils.create(UserEntity)
            UserEntity.insert { it[name] = "First name" }
            UserEntity.insert { it[name] = "Second name" }
        }
        println("Database created and populated")


    }
}