package com.wagner.norbert.cryptomarket.repository

import com.wagner.norbert.cryptomarket.model.Wallet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WalletRepository: JpaRepository<Wallet, Long> {
}