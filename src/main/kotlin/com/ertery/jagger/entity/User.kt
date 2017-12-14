package com.ertery.jagger.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(

        @Id
        @SequenceGenerator(name = "user_seq", sequenceName = "user_id_seq")
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
        val id: Long = -1,

        @Column
        val uuid: UUID = UUID.randomUUID(),

        @OneToMany(mappedBy = "user", cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
        val tasks: MutableList<Task> = mutableListOf()
) {
}