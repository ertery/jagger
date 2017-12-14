package com.ertery.jagger.service

import com.ertery.jagger.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*


@Service
class UserService(val repository: UserRepository) {

    fun getUser(uuid: UUID){
        repository.findByUuid(uuid.toString())
    }
}