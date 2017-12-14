package com.ertery.jagger.service

import com.ertery.jagger.entity.Task
import com.ertery.jagger.entity.User
import com.ertery.jagger.enum.AlgoType
import com.ertery.jagger.enum.Status
import com.ertery.jagger.repository.TaskRepository
import com.ertery.jagger.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import java.time.LocalDateTime
import javax.annotation.Resource
import org.springframework.transaction.annotation.Transactional


@Service
class TaskService(val taskRepository: TaskRepository, val userRepository: UserRepository) {


    @Resource
    lateinit var selfRef: TaskService

    fun getTask(serviceId: Long) {
        taskRepository.findOne(serviceId)
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun createTask(algoType: AlgoType): Task = taskRepository.saveAndFlush(Task(status = Status.NOT_STARTED.toString(), algorithm = algoType.toString()))

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun updateStatus(taskId: Long, status: Status) {
        val task = taskRepository.findOne(taskId)
        task.status = status.toString()

        taskRepository.saveAndFlush(task)
    }

    fun finishTask(taskId: Long) {
        val task = taskRepository.findOne(taskId)
        task.finishTime = LocalDateTime.now()

        taskRepository.saveAndFlush(task)
    }

    fun setResult(taskId: Long, result: String) {
        val task = taskRepository.findOne(taskId)
        task.result = result

        taskRepository.saveAndFlush(task)
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun createTaskWithOwner(algo: AlgoType, owner: User): Task {
        val task = selfRef.createTask(algo)
        owner.tasks.add(task)

        val updatedOwner = userRepository.saveAndFlush(owner)

        task.user = updatedOwner

        return taskRepository.saveAndFlush(task)
    }
}