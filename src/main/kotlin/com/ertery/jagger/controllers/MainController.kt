package com.ertery.jagger.controllers

import com.ertery.jagger.dto.FileDTO
import com.ertery.jagger.enum.AlgoType
import com.ertery.jagger.repository.UserRepository
import com.ertery.jagger.service.HashService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/task")
@CrossOrigin(origins = arrayOf("*"))
class MainController(val service: HashService, val repository: UserRepository) {

    @PostMapping("")
    fun startTask(@RequestBody file: FileDTO){
        service.startProcess(AlgoType.valueOf(file.algo), file.path, repository.findByUuid("cb3fa955-4d16-4806-83f8-8e9aab28459c"))
    }
}