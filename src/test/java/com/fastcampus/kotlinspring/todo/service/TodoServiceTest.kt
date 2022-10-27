package com.fastcampus.kotlinspring.todo.service

import com.fastcampus.kotlinspring.todo.domain.Todo
import com.fastcampus.kotlinspring.todo.domain.TodoRepository
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
internal class TodoServiceTest {

    @MockkBean
    lateinit var repository: TodoRepository // lateinit은 추후에 프레임워크에서 해당 객체를 mockking을 해준다는 의미이다.

    lateinit var service: TodoService

    val stub: Todo by lazy { // by lazy를 사용하면 변수를 immutable하게 가져갈 수 있다. (val 타입으로 선언됨)
        Todo(
            id = 1L,
            title = "테스트",
            description = "테스트 상세",
            done = false,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
        )
    }

    @BeforeEach
    fun setUp() {
        service = TodoService(repository)
    }

    @Test
    internal fun `한개의 TODO를 반환해야 한다`() {
        // Given
        every { repository.findByIdOrNull(1L) } returns stub // every는 given과 같은 것이다. returns는 중위표현식으로 DSL과 같은 문법이다.

        // When
        val actual = service.findById(1L)

        // Then
        assertThat(actual).isNotNull
        assertThat(actual).isEqualTo(stub)
    }
}