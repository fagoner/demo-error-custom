package com.handler.demo.error.services

import com.handler.demo.error.entities.UserEntity
import com.handler.demo.error.models.UserModel
import com.handler.demo.error.utilities.singleOrThrowResourceNotFound
import com.handler.demo.error.utilities.toUser
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service

@Service
class UserService() {
    fun findAll() = transaction {
        UserEntity
                .selectAll()
                .map { it.toUser() }
    }

    fun findById(id: Int): UserModel.User {
        return transaction {
            UserEntity
                    .select { UserEntity.id eq id }
                    .singleOrThrowResourceNotFound(message = "Element not found")
                    .toUser()
        }
    }
}