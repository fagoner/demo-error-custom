package com.handler.demo.error

import com.handler.demo.error.models.UserModel
import com.handler.demo.error.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.WebRequest
import java.sql.SQLNonTransientConnectionException

@SpringBootApplication
class ErrorApplication

fun main(args: Array<String>) {
    runApplication<ErrorApplication>(*args)
}

@Component
class MyCustomErrorAttributes : DefaultErrorAttributes() {
    override fun getErrorAttributes(
            webRequest: WebRequest, includeStackTrace: Boolean): Map<String, Any> {
        val errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace)

        val throwable = getError(webRequest)
        val message = throwable.message

        when(throwable) {
            is SQLNonTransientConnectionException -> {
                errorAttributes.replace("message", "Error in database, please check")
            }
        }

        errorAttributes.remove("trace")
        errorAttributes.remove("path")
        return errorAttributes
    }
}

@RestController
@RequestMapping("/users")
class UserController() {

    @Autowired
    lateinit var userService: UserService

    @GetMapping("")
    fun index() = userService.findAll()

    @GetMapping("/{id}")
    fun getResource(@PathVariable("id") id: Int): UserModel.User {
        if(id == 50) throw SQLNonTransientConnectionException("Error provocado")

        return userService.findById(id)
    }

}
