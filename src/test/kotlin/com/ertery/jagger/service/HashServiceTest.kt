package com.ertery.jagger.service

import com.ertery.jagger.entity.User
import com.ertery.jagger.enum.AlgoType
import com.ertery.jagger.repository.UserRepository
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.io.File


@RunWith(SpringRunner::class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
internal class HashServiceTest {

    @Autowired
    lateinit var service: HashService

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    fun downloadRemote() {
    }

    @Before
    fun setUp() {
    }

    @Test
    fun downloadRemoteFile() {
    }

    @Test
    fun process() {
        service.createProcess(AlgoType.SHA1, File("C:\\Users\\SBT-Golubin-AA\\Desktop\\test.tmp"), userRepository.save(User()))
    }

    @Test
    fun process10() {
        var owner = userRepository.saveAndFlush(User())
        for (i in 1..10) {
            owner = userRepository.findOne(owner.id)
            val future = service.createProcess(AlgoType.SHA1, File("C:\\Users\\SBT-Golubin-AA\\Desktop\\test.tmp"), owner)
            service.processStatusCheck(future)
        }
        Thread.sleep(10000)
    }

    @Test
    fun getSHA() {
        service.getSHA(AlgoType.MD5, File("C:\\Users\\SBT-Golubin-AA\\Desktop\\test.tmp"))
    }

    @After
    fun tearDown() {
    }

}