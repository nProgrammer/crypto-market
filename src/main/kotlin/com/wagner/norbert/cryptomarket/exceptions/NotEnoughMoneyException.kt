package com.wagner.norbert.cryptomarket.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.FORBIDDEN)
class NotEnoughMoneyException(val msg: String): RuntimeException(msg){

}