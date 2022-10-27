package com.fastcampus.kotlinspring.todo.service

import com.fastcampus.kotlinspring.todo.api.model.TodoRequest
import com.fastcampus.kotlinspring.todo.domain.Todo
import com.fastcampus.kotlinspring.todo.domain.TodoRepository
import org.springframework.data.domain.Sort.Direction
import org.springframework.data.domain.Sort.by
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime

@Service
class TodoService(
    private val todoRepository: TodoRepository,
) {
    @Transactional(readOnly = true)
    fun findAll() =
        todoRepository.findAll(by(Direction.DESC, "id"))

    @Transactional(readOnly = true)
    fun findById(id: Long) =
        todoRepository.findByIdOrNull(id)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    @Transactional
    fun create(request: TodoRequest?): Todo { // nullable로 선언된 request
        // request에 대해 null 체크
        checkNotNull(request) { "TodoRequest is null" }

        // request가 non-null 타입이되어 안전연산자를 사용하지 않고도 바로 프로퍼티를 사용할 수 있다.
        val todo = Todo(
            title = request.title,
            description = request.description,
            done = false,
            createdAt = LocalDateTime.now()
        )

        return todoRepository.save(todo)
    }

    @Transactional
    fun update(
        id: Long,
        request: TodoRequest?
    ): Todo {
        checkNotNull(request) { "TodoRequest is null" }

        return this.findById(id)
            .let {
                it.update(
                    request.title,
                    request.description,
                    request.done
                )

                todoRepository.save(it)
            }
    }

    fun delete(id: Long) =
        todoRepository.deleteById(id)
}