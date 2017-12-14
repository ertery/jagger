package com.ertery.jagger.service

import com.ertery.jagger.entity.Task
import com.ertery.jagger.entity.User
import com.ertery.jagger.repository.TaskRepository
import com.ertery.jagger.repository.UserRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
internal class UserServiceTest {

    @Autowired
    lateinit var service: UserService

    @Autowired
    lateinit var repository: UserRepository

    @Autowired
    lateinit var taskRepository: TaskRepository


    @Test
    fun getUser() {
        val user = User(uuid = UUID.randomUUID())
        user.tasks.add(Task(status = "IN PROGRESS"))
        val  savedUser = repository.saveAndFlush(user)

        val task = Task(status = "New Progress")
        task.user = savedUser

        taskRepository.saveAndFlush(task)
    }

}