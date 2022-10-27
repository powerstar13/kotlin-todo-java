# 자바 프로젝트(TODO 프로젝트) 코틀린으로 리팩토링하기

## TODO 서비스 코드 살펴보기

1. build.gradle
2. TodoController
3. TodoService
4. TodoRepository
5. Todo
6. api-test.http

## 자바 프로젝트에 코틀린 설정 적용하기

1. Kotlin DSL을 사용해 빌드 스크립트 마이그레이션
    1. build.gradle (마이그레이션 전)
        1. build.gralde.kts (마이그레이션 후)
    2. settings.gradle (마이그레이션 전)
        1. settings.gradle.kts (마이그레이션 후)

## 리팩토링

1. 컨트롤러 레이어 리팩토링
2. 서비스 레이어 리팩토링
3. 도메인 레이어 리팩토링
4. 테스트 코드 리팩토링
