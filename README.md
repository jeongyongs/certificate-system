# certificate-system

1. spring web mvc 구조
    1. `WebAppInitializer`를 사용하여 DispatcherServlet 초기화
        1. CharacterEncodingFilter 등록
        2. 핸들러가 없을 경우 예외 설정
2. 설정
    1. 애플리케이션 설정
        1. `db.properties`를 읽어 Datasource(ConnectionPool) 설정
        2. `ObjectMapper` 설정
    2. 서블릿 설정
        1. 리소스 패스 설정
        2. favicon 설정
        3. Thymeleaf 설정
        4. `MessageConverter` 변경
        5. WebMvc 커스텀을 위한 `@EnableWebMvc` 설정
        6. 요청 바디 JSON 매핑을 위한 `@EnableSpringDataWebSupport` 설정
    3. JPA 설정
        1. `EntityManagerFactory` 등록
        2. `TransactionManager` 등록
        3. 트랜젝션 처리를 위한 `@EnableTransactionManagement` 설정
        4. JPA 레포지토리 사용을 위한 `@EnableJpaRepositories` 설정
3. 페이지
    1. 주민 목록
    2. 주민등록등본
    3. 가족관계증명서
    4. 출생신고서
    5. 사망신고서
    6. 증명서 발급 목록
4. 엔터티
    1. 주민
        1. 성별 / 출생장소 / 사망장소 : Enum으로 처리
            - 입력할 수 있는 데이터를 제한하기 위함
            - 기존 DB에 저장된 데이터와 매핑하기 위해 컨버터 사용
            - [Legacy DB의 JPA Entity Mapping (Enum Converter 편)](https://techblog.woowahan.com/2600/)
5. 컨트롤러
    1. 주민 API
        1. 등록 `POST /residents`
        2. 수정 `PUT /residents/{serialNumber}`
