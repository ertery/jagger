package com.ertery.jagger.repository

import com.ertery.jagger.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByUuid(Uuid: String): User
}