package com.handler.demo.error.utilities

import com.handler.demo.error.entities.UserEntity
import com.handler.demo.error.models.UserModel
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toUser() = UserModel.User(id = this[UserEntity.id], name = this[UserEntity.name])