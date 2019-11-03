package com.wagner.norbert.cryptomarket

import com.wagner.norbert.cryptomarket.exceptions.NotEnoughMoneyException
import com.wagner.norbert.cryptomarket.exceptions.UserNotFoundException
import com.wagner.norbert.cryptomarket.model.User
import com.wagner.norbert.cryptomarket.model.Wallet
import com.wagner.norbert.cryptomarket.repository.UserRepository
import com.wagner.norbert.cryptomarket.repository.WalletRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface CryptoFacade {
    fun getUsers(): List<User>
    fun addUser(firstName: String, lastName: String): User
    fun getUser(id: Long): User
    fun sellBtc(id: Long, amount: Double): Wallet
}

@Service
class CryptoFacadeImpl: CryptoFacade {

    override fun sellBtc(id: Long, amount: Double): Wallet {
        val user = userRepository.getOne(id)
        val btcAmount = user.wallet.btc

        if(btcAmount >= amount){
            user.wallet.btc -= amount
            user.wallet.usd += amount * BTC_PRICE
            userRepository.save(user)
        }else{
            throw NotEnoughMoneyException("you haven't got enough BTC")
        }
        return user.wallet
    }

    override fun getUser(id: Long): User {
        return userRepository.findById(id).orElseThrow{ throw UserNotFoundException("can't find user with such id") }
    }

    override fun addUser(firstName: String, lastName: String): User {
        val wallet = Wallet(btc = 5.0, usd = 1250.0)
        walletRepository.save(wallet)

        val user = User(firstName = firstName, lastName = lastName, wallet = wallet)
        userRepository.save(user)

        return user
    }

    override fun getUsers(): List<User> {
        return userRepository.findAll()
    }

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var walletRepository: WalletRepository

    companion object{
        const val BTC_PRICE = 5000.0
    }
}