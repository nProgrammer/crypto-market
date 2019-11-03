package com.wagner.norbert.cryptomarket.controller

import com.wagner.norbert.cryptomarket.CryptoFacade
import com.wagner.norbert.cryptomarket.model.User
import com.wagner.norbert.cryptomarket.model.Wallet
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class CryptoController{

    @Autowired
    lateinit var cryptoFacade: CryptoFacade

    @GetMapping("/users")
    fun getUsers(): List<User>{
        return cryptoFacade.getUsers()
    }

    @PostMapping("/user")
    fun addUser(@RequestParam("firstName") firstName: String,
                @RequestParam("lastName") lastName: String): User{
        return cryptoFacade.addUser(firstName, lastName)
    }
    @GetMapping("/user/{id}")
    fun getUser(@PathVariable id: Long): User{
        return cryptoFacade.getUser(id)
    }

    @PostMapping("/user/{id}/sell/btc")
    fun sellBtc(@PathVariable id: Long,
                @RequestParam("amount") amount: Double): Wallet {
        return cryptoFacade.sellBtc(id, amount)
    }
}