# certificate-system

1. spring web mvc 구조
    1. `WebAppInitializer`를 사용하여 DispatcherServlet 초기화
        1. CharacterEncodingFilter 등록
        2. 핸들러가 없을 경우 예외 설정
2. 설정
    1. 애플리케이션 설정
        1. `db.properties`를 읽어 Datasource(ConnectionPool) 설정
    2. 서블릿 설정
        1. 리소스 패스 설정
        2. favicon 설정
        3. Thymeleaf 설정
    3. JPA 설정
        1. EntityManagerFactory 등록
        2. TransactionManager 등록
3. tm