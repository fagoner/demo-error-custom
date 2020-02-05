package com.handler.demo.error.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.CONFLICT)
class DuplicatedResourceException(message: String) : RuntimeException(message)