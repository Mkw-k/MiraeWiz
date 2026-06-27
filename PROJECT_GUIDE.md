# 🚀 MiraeWiz 프로젝트 인수인계 및 운영 가이드

본 문서는 **MiraeWiz 홈페이지 및 통합 관리 시스템** 프로젝트를 원활하게 인수인계하고 빌드, 배포, 커스터마이징할 수 있도록 작성된 기술 가이드라인입니다.

---

## 1. 프로젝트 스펙 (Project Specifications)

프로젝트에 적용된 핵심 기술 및 버전 정보는 다음과 같습니다.

*   **Java 버전:** JDK 17
*   **프레임워크:** Spring Boot 3.2.5
*   **보안:** Spring Security 6.2.4
*   **데이터베이스 & ORM:** MyBatis 3.0.3, H2 (로컬 개발용), MySQL (실제 운영용)
*   **템플릿 엔진:** Thymeleaf
*   **빌드 도구:** Maven
*   **API 문서 자동화:** Spring REST Docs (MockMvc & Asciidoctor)
*   **기타 주요 의존성:** Lombok, Developer Tools, MySQL Connector/J

---

## 2. 빌드 및 구동 방법 (Build & Execution)

### 2.1. 로컬 환경 구동 (H2 데이터베이스 사용)
프로젝트 루트 디렉토리에서 아래 명령어를 수행합니다. 기본 설정으로 로컬 H2 인메모리 데이터베이스를 사용하므로 별도의 DB 설치 없이 즉시 실행 가능합니다.

```bash
# Maven Wrapper를 사용하는 경우
./mvnw spring-boot:run

# 로컬에 설치된 Maven을 사용하는 경우
mvn spring-boot:run
```
*   **사용자 페이지 접속:** [http://localhost:8080](http://localhost:8080)
*   **관리자 페이지 접속:** [http://localhost:8080/admin](http://localhost:8080/admin)
*   **H2 콘솔 접속:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console) (JDBC URL: `jdbc:h2:mem:miraewiz`, ID: `sa`, PW: 없음)

### 2.2. 운영(Production) 빌드 및 배포
실제 MySQL 서버 환경에 배포하기 위해 빌드를 수행합니다.

```bash
# 1. 프로젝트 빌드 및 패키징 (테스트 코드를 수행하며 jar 파일 생성)
./mvnw clean package

# 또는 테스트를 제외하고 빌드하려는 경우
./mvnw clean package -DskipTests
```
빌드가 완료되면 `target/` 폴더 내에 `homepage-0.0.1-SNAPSHOT.jar` 파일이 생성됩니다.

```bash
# 2. 생성된 JAR 파일을 운영 프로필(prod)로 백그라운드 구동 (nohup 사용)
nohup java -jar target/homepage-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod > nohup.out 2>&1 &

# 3. 구동 상태 확인 (로그 모니터링)
tail -f nohup.out

# 4. 백그라운드 프로세스 종료 방법
# (1) 실행 중인 프로세스 ID(PID) 확인
ps -ef | grep homepage-0.0.1-SNAPSHOT.jar

# (2) 확인된 PID를 이용해 프로세스 안전 종료 (예: PID가 1234인 경우)
kill -15 1234
```

---

## 3. DB 설정법 (Database Configuration)

데이터베이스 접속 정보는 `src/main/resources/` 내의 설정 파일(`.properties`)로 관리되며, 구동 시 프로필(`spring.profiles.active`)을 지정하여 활성화합니다.

### 3.1. 로컬 개발 환경 (`application-local.properties`)
인메모리 H2 데이터베이스를 사용합니다. 애플리케이션 구동 시 `src/main/resources/schema.sql`과 `data.sql`이 자동으로 로드되어 테이블 생성 및 초기 데이터를 세팅합니다.
```properties
spring.datasource.url=jdbc:h2:mem:miraewiz;DB_CLOSE_DELAY=-1
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

### 3.2. 운영 서버 환경 (`application-prod.properties`)
실서버 배포 시에는 로컬 또는 외부의 MySQL 데이터베이스를 연동합니다.
```properties
# Production (MySQL)
spring.datasource.url=jdbc:mysql://localhost:3306/miraewiz?serverTimezone=UTC&characterEncoding=UTF-8
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=your_password
```
> ⚠️ **주의:** 실서버(MySQL)에 최초 배포 시, `schema.sql`과 `data.sql` 스크립트를 MySQL 툴(Navicat, Workbench 등)을 사용하여 실행해주거나, 테이블 및 기본 데이터를 사전에 준비해야 정상 동작합니다.

---

## 4. 로고, 회사소개 및 프로그램 변경법 (하드코딩 수정 가이드)

일부 고정된 UI 영역은 템플릿 HTML 파일에 하드코딩 되어 있습니다. 이를 변경하려면 아래 파일들을 수정해야 합니다.

### 4.1. 로고 이미지 변경
*   **수정 파일:** `src/main/resources/templates/fragments.html` [fragments.html:14](file:///Users/mkw111/Desktop/project/MiraeWiz/src/main/resources/templates/fragments.html#L14)
*   **가이드:** 로고 이미지 경로가 `<img src="/images/logo_placeholder.png" alt="Logo" class="nav-logo-img">`로 지정되어 있습니다.
    *   **방법 A:** `src/main/resources/static/images/logo_placeholder.png` 파일을 원하는 새로운 로고 파일(동일 이름)로 덮어씌웁니다.
    *   **방법 B:** 다른 경로의 새로운 이미지 파일(예: `my_logo.png`)을 `static/images/` 폴더에 넣고 `fragments.html`의 `src` 경로를 업데이트합니다.

### 4.2. 회사소개(Footer 영역) 정보 변경
*   **수정 파일:** `src/main/resources/templates/fragments.html` [fragments.html:45-92](file:///Users/mkw111/Desktop/project/MiraeWiz/src/main/resources/templates/fragments.html#L45-L92)
*   **가이드:** 모든 페이지 하단에 노출되는 상호명, 대표, 주소, 연락처 등의 정보가 하드코딩 되어 있으므로 해당 파일의 내용을 비즈니스 정보에 맞게 직접 텍스트를 편집합니다.
    *   대표자명 (`이선우`), 주소 (`서울특별시 성북구 솔샘로 11길 44`), 고객센터 전화번호, 이메일, 저작권 문구 수정 가능.

### 4.3. 회사소개 페이지 (`/about`) 내용 변경
*   **수정 파일:** `src/main/resources/templates/about.html` [about.html](file:///Users/mkw111/Desktop/project/MiraeWiz/src/main/resources/templates/about.html)
*   **가이드:** 
    *   상단 히어로 섹션 타이틀 및 소개글 (L12-13)
    *   통계 수치 (학교/학원 수, 학습자 수, 영단어 수) (L20-37)
    *   비전 스토리와 설립 및 출시 연혁(History) (L43-61)
    *   해당 영역의 텍스트와 수치를 직접 수정하십시오. (회사 소개 내용의 일부는 DB의 `site_contents` 테이블에서도 불러올 수 있도록 마이그레이션 구조가 잡혀있으나, 현재 뷰 템플릿의 일부 섹션은 하드코딩이 함께 적용되어 있어 직접 파일 수정이 필요합니다.)

### 4.4. 프로그램 상세 정보 및 스크린샷 미리보기 변경
*   **수정 파일:** `src/main/resources/templates/programs.html` [programs.html](file:///Users/mkw111/Desktop/project/MiraeWiz/src/main/resources/templates/programs.html)
*   **가이드:**
    *   **다운로드 링크:** Google Play 스토어 주소 (L61)를 수정합니다.
    *   **스크린샷 이미지 슬라이더:** 현재 37개의 미리보기 이미지가 자동으로 루프를 돕니다. (L80-83)
        *   이미지들은 `src/main/resources/static/images/ref/` 폴더에 `01.png`~`37.png` (일부 `.jpg`) 형식으로 적재되어 있습니다. 
        *   새로운 앱 이미지로 교체 시, 동일하게 폴더 내의 파일을 교체하거나 갯수가 바뀔 경우 L80의 `${#numbers.sequence(1, 37)}` 루프 크기를 수정해야 합니다.

---

## 5. 관리자 계정 정보 (Admin Credentials)

*   **관리자 페이지 경로:** `/admin` 또는 `/admin/login`
*   **초기 관리자 ID:** `admin`
*   **초기 관리자 비밀번호:** `admin123!`

### ⚙️ 관리자 비밀번호 재설정 방법
비밀번호는 보안을 위해 **BCrypt 해시** 알고리즘으로 암호화되어 데이터베이스에 저장됩니다.
1.  프로젝트 루트 디렉토리에 포함된 `BCryptGen.java` 파일을 열어 원하는 신규 비밀번호 문자열을 암호화합니다.
    ```java
    // BCryptGen.java 파일 수정 후 실행
    new BCryptPasswordEncoder().encode("새로운비밀번호")
    ```
2.  콘솔에 출력된 암호화 해시값(예: `$2a$10$...`)을 획득합니다.
3.  데이터베이스의 `members` 테이블에서 `username = 'admin'`인 레코드의 `password` 컬럼 값을 획득한 해시값으로 업데이트합니다.
    ```sql
    UPDATE members SET password = '획득한_BCrypt_해시값' WHERE username = 'admin';
    ```

---

## 6. 보안 설정 (Security Config)

이 프로젝트의 웹 보안은 `SecurityConfig.java` [SecurityConfig.java](file:///Users/mkw111/Desktop/project/MiraeWiz/src/main/java/com/miraewiz/homepage/config/SecurityConfig.java) 파일을 통해 Spring Security로 제어됩니다.

### 6.1. 접근 권한 제어
*   **관리자 보호 영역:** `/admin`, `/admin/**`, `/api/admin/**`
    *   해당 경로로 인가되지 않은 접근 시 자동으로 `/admin/login` 폼 로그인 페이지로 리다이렉트 됩니다.
*   **공개 영역:** 그 외의 모든 홈페이지 경로 (메인 `/`, `/about`, `/programs`, `/reviews`, `/faq` 등)는 비로그인 사용자도 자유롭게 접근 가능합니다.

### 6.2. 폼 로그인 & 로그아웃 설정
*   **로그인 처리 URL:** `/admin/login-process` (POST 요청 시 스프링 시큐리티에서 인증을 처리합니다.)
*   **로그인 성공 시:** 관리자 전용 사용후기 관리 메뉴인 `/admin/reviews`로 리다이렉트 됩니다.
*   **로그아웃 처리 URL:** `/admin/logout` (성공 시 메인 화면 `/`로 이동합니다.)

### 6.3. CSRF 및 비밀번호 인코더
*   **CSRF 보호 비활성화:** 개발 편의를 위해 `http.csrf(csrf -> csrf.disable())` 설정이 적용되어 있습니다. 
    > ⚠️ **보안 권장:** 프로덕션 환경에 배포 시, 필요에 따라 CSRF 필터를 활성화하고 템플릿에 CSRF 토큰을 동적으로 심어두는 것을 권장합니다.
*   **PasswordEncoder 빈:** 암호 검증 및 해싱 시 `BCryptPasswordEncoder`가 빈으로 등록되어 작동합니다.

---

## 7. API 명세서 (API Specification)

본 프로젝트는 관리자 대시보드에서 각 콘텐츠를 CRUD 하기 위해 REST API를 제공합니다.

### 7.1. FAQ 관리 API (`/api/admin/faqs`)
*   **전체 조회 (GET):** `GET /api/admin/faqs?search={검색어}&page={페이지번호}&size={크기}`
    *   **파라미터:** `search` (선택, 질문/답변 검색), `page` (기본 1), `size` (기본 10)
    *   **반답 형태:** JSON (페이징 정보 및 content 목록 포함)
*   **등록 (POST):** `POST /api/admin/faqs`
    *   **Request Body:** `{"question": "질문내용", "answer": "답변내용", "category": "카테고리"}`
*   **수정 (PUT):** `PUT /api/admin/faqs/{id}`
    *   **Request Body:** `{"question": "수정할질문", "answer": "수정할답변", "category": "수정할카테고리"}`
*   **삭제 (DELETE):** `DELETE /api/admin/faqs/{id}`

### 7.2. 프로그램 관리 API (`/api/admin/programs`)
*   **전체 조회 (GET):** `GET /api/admin/programs`
    *   **반환 형태:** JSON Array (`Program` 객체 리스트)
*   **등록 (POST):** `POST /api/admin/programs`
    *   **Request Body:** `{"title": "앱이름", "description": "앱설명", "iconUrl": "아이콘경로", "googlePlayUrl": "스토어주소", "displayOrder": 1}`
*   **수정 (PUT):** `PUT /api/admin/programs/{id}`
    *   **Request Body:** `{"title": "수정이름", "description": "수정설명", ...}`
*   **삭제 (DELETE):** `DELETE /api/admin/programs/{id}`

### 7.3. 사용후기 관리 API (`/api/admin/reviews`)
*   **전체 조회 (GET):** `GET /api/admin/reviews?search={검색어}&page={페이지}&size={크기}`
*   **노출 여부 수정 (PATCH):** `PATCH /api/admin/reviews/{id}/visibility?isVisible={true/false}`
    *   사용자가 작성한 리뷰를 홈페이지 메인/사용후기 게시판에서 숨기거나 노출시킬 수 있습니다.
*   **삭제 (DELETE):** `DELETE /api/admin/reviews/{id}`

### 7.4. 사이트 텍스트(소개글 등) API (`/api/admin/contents`)
*   **전체 조회 (GET):** `GET /api/admin/contents`
*   **수정 (PUT):** `PUT /api/admin/contents/{key}`
    *   **Request Body:** `{"contentValue": "수정할텍스트값", "description": "설명"}`
    *   **주요 Key:** `ABOUT_VISION`, `ABOUT_GREETING`, `ABOUT_HISTORY`

---
*문서 최종 수정일: 2026-06-27*
