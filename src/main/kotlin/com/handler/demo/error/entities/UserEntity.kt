package com.handler.demo.error.entities

import org.jetbrains.exposed.sql.Table

object UserEntity: Table(name = "users") {
    val id = integer(name = "id").primaryKey().autoIncrement()
    val name = varchar(name = "name", length = 60)
}