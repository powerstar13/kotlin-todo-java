package com.fastcampus.kotlinspring.todo.domain

import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo, Long> {
    // Optional로 감싸는 방법이 아닌 nullable 타입으로 반환 타입을 명시하는 것을 추천
    fun findAllByDoneIsFalseOrderByIdDesc(): List<Todo>?
}