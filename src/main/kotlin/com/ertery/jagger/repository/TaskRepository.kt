package com.ertery.jagger.repository

import com.ertery.jagger.entity.Task
import org.springframework.data.jpa.repository.JpaRepository

interface TaskRepository: JpaRepository<Task, Long> {
}