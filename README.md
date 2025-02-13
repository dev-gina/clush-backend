###백엔드 개발자 과제 - 이진아

1. 자신이 개발한 앱에 대한 설명

이 앱은 사용자가 일정을 조회, 등록, 수정, 삭제할 수 있는 캘린더API 입니다.
Swagger UI를 통해 자동으로 생성하여 쉽게 API를 테스트하고 사용할 수 있었습니다.

--------------------------------------------------------------------------------------------------

2. 소스 빌드 및 실행 방법 메뉴얼 (DB 스키마 및 기초데이터 백업파일 필수)

2-1. git clone https://github.com/dev-gina/clush-backend.git
cd clush-backend

2-2. 
1) MySQL 실행
2) CREATE DATABASE calendar_db;
3) application.properties 파일 (하기 내용 수정)
spring.datasource.url=jdbc:mysql://localhost:3306/calendar_db
spring.datasource.username=root
spring.datasource.password=your_password
4) mvn clean install
5) mvn spring-boot:run
6) http://localhost:8080/swagger-ui/index.html

--------------------------------------------------------------------------------------------------

3. 주력으로 사용한 컴포넌트에대한 설명 및 사용 이유 기입

3-1. Swagger UI
Swagger UI는 API를 문서화하고 테스트할 수 있는 UI를 제공합니다. 이를 통해 API의 엔드포인트를 쉽게 확인하고, 데이터를 입력하여 테스트할 수 있습니다. 이 프로젝트에서는 Swagger를 사용하여 API 문서를 자동으로 생성하고 제공합니다.

3-2. Spring Security
Spring Security는 인증 및 권한 부여 기능을 제공합니다. 프로젝트에서는 기본적인 사용자 인증을 위해 Spring Security를 사용하고 있으며, 로그인 시 팝업창을 통해 인증을 요구합니다. 사용자는 미리 정의된 admin 또는 user 계정을 사용하여 로그인할 수 있습니다.

3-3. Spring Data JPA
Spring Data JPA는 데이터베이스 연동을 쉽게 할 수 있도록 도와주는 기술입니다. 이 프로젝트에서는 이벤트 정보를 저장하고 조회하는데 Spring Data JPA를 사용하여 코드의 복잡성을 줄이고 효율적으로 데이터베이스 작업을 처리합니다.

3-4. Spring Boot
Spring Boot는 빠르게 애플리케이션을 시작할 수 있는 프레임워크로, 복잡한 설정 없이 간단하게 웹 애플리케이션을 구축할 수 있게 해줍니다. 이 프로젝트에서는 Spring Boot를 사용하여 API 서버를 구성했습니다.

--------------------------------------------------------------------------------------------------

4. Api 명세 작성 필수

4-1. 모든 이벤트 가져오기
GET /api/events
응답 : 
{
    "id": 1,
    "title": "Test Event",
    "description": "This is a test event",
    "location": "Test Location",
    "startDate": "2025-02-13T10:00:00",
    "endDate": "2025-02-13T12:00:00"
}

4-2. 새로운 이벤트 추가
POST /api/events
요청 : 
{
  "title": "Test Event",
  "description": "This is a test event",
  "location": "Test Location",
  "startDate": "2025-02-13T10:00:00",
  "endDate": "2025-02-13T12:00:00"
}
응답 : 
{
  "id": 1,
  "title": "Test Event",
  "description": "This is a test event",
  "location": "Test Location",
  "startDate": "2025-02-13T10:00:00",
  "endDate": "2025-02-13T12:00:00"
}
상태 코드: 201 Created

4-3. 이벤트 수정
PUT /api/events/{id}
요청 : 
{
  "title": "Updated Event",
  "description": "This is an updated test event",
  "location": "Updated Location",
  "startDate": "2025-02-13T11:00:00",
  "endDate": "2025-02-13T13:00:00"
}
응답 : 
{
  "id": 1,
  "title": "Updated Event",
  "description": "This is an updated test event",
  "location": "Updated Location",
  "startDate": "2025-02-13T11:00:00",
  "endDate": "2025-02-13T13:00:00"
}
상태 코드: 200 OK

4-4. 이벤트 삭제
DELETE /api/events/{id}
요청 본문: 없음
응답 본문: 없음
상태 코드: 204 No Content


--------------------------------------------------------------------------------------------------

5. 테스트케이스 작성 필수
package com.example.calendarapi;

import com.example.calendarapi.model.Event;
import com.example.calendarapi.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EventCreationTests {

    @Autowired
    private EventRepository eventRepository;

    @Test
    public void testCreateEvent() {
        Event event = new Event();
        event.setTitle("Test Event");
        event.setDescription("This is a test event");
        event.setLocation("Test Location");
        
        eventRepository.save(event);

        assertThat(event.getId()).isNotNull(); // Event should be saved and have an ID
    }
}

-> 테스트 실행 시 mvn test 명령어 입력

