package com.wagner.norbert.cryptomarket.controller

import com.wagner.norbert.cryptomarket.exceptions.UserNotFoundException
import com.wagner.norbert.cryptomarket.model.User
import com.wagner.norbert.cryptomarket.model.Wallet
import com.wagner.norbert.cryptomarket.repository.UserRepository
import com.wagner.norbert.cryptomarket.repository.WalletRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class CryptoController{

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var walletRepository: WalletRepository

    @GetMapping("/users")
    fun getUsers(): List<User>{
        return userRepository.findAll()
    }

    @PostMapping("/user")
    fun addUser(@RequestParam("firstName") firstName: String,
                    @RequestParam("lastName") lastName: String): User{
        val wallet = Wallet(btc = 5.0, usd = 1250.0)
        walletRepository.save(wallet)

        val user = User(firstName = firstName, lastName = lastName, wallet = wallet)
        userRepository.save(user)

        return user
    }
    @GetMapping("/user/{id}")
    fun getUser(@PathVariable id: Long): User{
        return userRepository.findById(id).orElseThrow{ throw UserNotFoundException("can't find user with such id")}
    }
}