package com.ertery.jagger.service

import com.ertery.jagger.entity.Task
import com.ertery.jagger.entity.User
import com.ertery.jagger.enum.AlgoType
import com.ertery.jagger.enum.Status
import com.ertery.jagger.repository.TaskRepository
import com.ertery.jagger.repository.UserRepository
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import javax.transaction.Transactional

@RunWith(SpringRunner::class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
internal class TaskServiceTest{

    @Autowired
    lateinit var service: TaskService

    @Autowired
    lateinit var repository: TaskRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    fun getService() {
        val saved = repository.saveAndFlush(Task())
        assertNotNull(service.getTask(saved.taskId))
    }

    @Test
    fun updateStatus() {
        val saved = repository.saveAndFlush(Task(status = Status.NOT_STARTED.toString()))
        service.updateStatus(saved.taskId, Status.INPROGRESS)
        assertEquals(Status.INPROGRESS.toString(), repository.findOne(saved.taskId).status)
    }

    @Test
    @Transactional
    fun createTaskWithOwner() {
        val owner = userRepository.saveAndFlush(User())
        val task = service.createTaskWithOwner(AlgoType.SHA256, owner)
        assertEquals(userRepository.findOne(owner.id), task.user)
    }


}