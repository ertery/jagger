package com.ertery.jagger.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "task")
data class Task(

        @Id
        @SequenceGenerator(name = "task_seq", sequenceName = "task_task_id_seq")
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq")
        @Column(name = "task_id")
        val taskId: Long = -1,

        @Column
        val startTime: LocalDateTime = LocalDateTime.now(),

        @Column
        var finishTime: LocalDateTime? = null,

        @Column()
        var status: String = "",

        @Column()
        var algorithm: String = "",

        @Column()
        var result: String = ""

) {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    lateinit var user: User
}