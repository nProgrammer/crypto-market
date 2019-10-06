package com.wagner.norbert.cryptomarket.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException(val msg: String): RuntimeException(msg){

}