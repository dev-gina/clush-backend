###백엔드 개발자 과제 - 이진아

1. 자신이 개발한 앱에 대한 설명

이 앱은 사용자가 일정을 조회, 등록, 수정, 삭제할 수 있는 캘린더API 입니다.
Swagger UI를 통해 자동으로 생성하여 쉽게 API를 테스트하고 사용할 수 있었습니다.

--------------------------------------------------------------------------------------------------

2. 소스 빌드 및 실행 방법 메뉴얼 (DB 스키마 및 기초데이터 백업파일 필수)

실행방법 (bash)
mvn clean install
mvn spring-boot:run

MySQL 데이터베이스 확인 (bash)
mysql -u root -p   # 비밀번호 입력 (1234)
USE calendar_db;
SHOW TABLES;
SELECT * FROM event;

2.3 DB 스키마
mysql> SHOW DATABASES;
+--------------------+
| Database           |
+--------------------+
| calendar_db        |
| information_schema |
| mysql              |
| performance_schema |
| sys                |
+--------------------+

mysql> SELECT * FROM event;
+----+-------------+----------------------------+----------------------------+--------+
| id | description | end_time                   | start_time                 | title  |
+----+-------------+----------------------------+----------------------------+--------+
|  2 | string      | 2025-02-14 06:56:20.934000 | 2025-02-14 06:56:20.934000 | string |
+----+-------------+----------------------------+----------------------------+--------+
1 row in set (0.00 sec)

--------------------------------------------------------------------------------------------------

3. 주력으로 사용한 컴포넌트에대한 설명 및 사용 이유 기입

3-1. Swagger UI
- API 문서를 자동으로 생성하고, 손쉽게 API를 테스트 할 수 있습니다.

3-2. Spring Security
- 기본적인 인증 및 권한 부여를 설정하여 보안을 강화하였습니다.

3-3. Spring Data JPA
- 데이터베이스 연동을 간단하게 처리하고, CRUD 작업을 쉽게 구현할 수 있었습니다.

3-4. Spring Boot
- 빠르게 어플리케이션을 개발할 수 있도록 도와줍니다.

--------------------------------------------------------------------------------------------------

4. Api 명세 작성 필수

4-1. 모든 이벤트 조회
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
상태 코드 : 200 OK 

----------------------------------------

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

----------------------------------------

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

----------------------------------------

4-4. 이벤트 삭제
DELETE /api/events/{id}
요청 : DELETE /api/events/{id}
응답 : 204 No Content
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
        event.setTitle("테스트 이벤트");
        event.setDescription("테스트 설명");
        event.setStartTime("2025-02-14T10:00:00");
        event.setEndTime("2025-02-14T12:00:00");

        eventRepository.save(event);

        assertThat(event.getId()).isNotNull();
    }
}


-> 테스트 실행 시 mvn test 명령어 입력

