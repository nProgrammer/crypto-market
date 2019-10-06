package com.wagner.norbert.cryptomarket.repository

import com.wagner.norbert.cryptomarket.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {
}