package com.fastcampus.kotlinspring.todo.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "todos")
class Todo(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0,

    @Column(name = "title")
    var title: String,

    @Lob
    @Column(name = "description")
    var description: String,

    @Column(name = "done")
    var done: Boolean,

    @Column(name = "created_at")
    var createdAt: LocalDateTime,

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null
) {
    fun update(title: String, description: String, done: Boolean) {
        // JPA의 특성에 의해 변경 가능한 변수에 대해서 var로 열어주어야 한다... (JPA의 아쉬운 점)
        // JPA가 아닌 다른 Spring Data를 사용할 경우엔 val로 유지해도 된다.
        this.title = title
        this.description = description
        this.done = done
        this.updatedAt = LocalDateTime.now()
    }
}