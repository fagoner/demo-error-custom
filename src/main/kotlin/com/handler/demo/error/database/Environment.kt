package com.handler.demo.error.database

import org.jetbrains.exposed.sql.Database

sealed class DatabaseEnvironment {
    object Memory : DatabaseEnvironment()
    object Develop : DatabaseEnvironment()
}

object DatabaseConnector {
    fun init(databaseEnvironment: DatabaseEnvironment = DatabaseEnvironment.Memory) {
        println("Starting Database")
        when(databaseEnvironment) {
            is DatabaseEnvironment.Memory -> setMemoryDatabase()
            is DatabaseEnvironment.Develop -> setDevelopDatabase()
        }
    }

    private fun setMemoryDatabase() {
        Database.connect("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", driver = "org.h2.Driver")
    }

    private fun setDevelopDatabase() {
        Database.connect(url = "jdbc:mysql://mysql57:3306/DAPI?autoReconnect=true",
                driver = "com.mysql.cj.jdbc.Driver",
                user = "user",
                password = "password")
    }
}
