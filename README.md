# 🚀 MiraeWiz 홈페이지 및 통합 관리 시스템

이 프로젝트는 **Spring Boot, MyBatis, Thymeleaf**를 기반으로 한 **확장 가능한 CMS형 회사 소개 사이트**입니다. 모든 콘텐츠는 관리자 페이지를 통해 동적으로 관리됩니다.

---

## 🛠️ 핵심 기술 스택
- **Backend:** Spring Boot 3.2.x, Spring Security 6.x
- **Database:** MyBatis, H2 (Local), MySQL (Prod)
- **Frontend:** Thymeleaf, Vanilla CSS (Modern Clean Style)
- **DevOps:** GitHub Actions (CI/CD 준비), Maven

---

## ✨ 주요 기능
### 1. 사용자 페이지 (User Site)
- **메인(Home):** 카카오톡 참고 사진 스타일의 세련된 히어로 섹션.
- **프로그램(Programs):** 'WW Kingdom' 앱의 37개 스크린샷 갤러리 및 상세 정보.
- **사용후기(Reviews):** 비회원 작성/삭제(비밀번호 검증) 기능이 포함된 카드형 게시판.
- **FAQ:** 아코디언 토글 방식의 자주 묻는 질문.
- **회사소개(About):** DB에서 관리되는 CEO 인사말 및 연혁.

### 2. 관리자 시스템 (Admin Panel - `/admin`)
- **보안:** 비밀스러운 관리자 로그인 (URL 직접 입력 접속).
- **콘텐츠 관리:** 프로그램, 후기, FAQ, 사이트 텍스트를 실시간으로 CRUD.
- **API 문서화:** Spring REST Docs를 통한 관리자 API 명세 자동화.

---

## 🚀 시작하기
1. **Repository 클론:** `git clone https://github.com/Mkw-k/MiraeWiz.git`
2. **로컬 실행:** `./mvnw spring-boot:run`
3. **메인 접속:** `http://localhost:8080`
4. **관리자 접속:** `http://localhost:8080/admin`
   - 초기 계정: `admin` / `admin123!`

---

## 📁 프로젝트 구조
- `src/main/resources/schema.sql`: 테이블 구조 정의
- `src/main/resources/data.sql`: 초기 데이터 (WW Kingdom 및 회사 정보)
- `src/test/java/.../AdminApiDocsTest.java`: REST Docs 생성 테스트
