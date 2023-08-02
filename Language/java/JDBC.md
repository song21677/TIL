# JDBC (Java DataBase Connectivity)
**애플리케이션 서버와 DB의 사용법** 
1. **커넥션 연결**: DB와 TCP/IP를 사용해 커넥션을 연결한다. 
2. **SQL 전달**: 연결된 커넥션을 통해 DB에 SQL을 전달한다.
3. **결과 응답**: DB는 전달된 SQL을 수행하고 결과를 응답한다. 

**JDBC가 없을 때 문제점**
DB마다 커넥션 연결 방법, SQL 전달 방법, 결과 응답 방법이 모두 다른데 따라서 개발자는 각 방법들을 새로 학습해야 하고 다른 DB로 변경하면 서버에서  데이터베이스 사용 코드도 함께 변경해야 한다. 이 문제를 해결하기 위해 JDBC가 등장하였다.

**JDBC**
DBMS 종류에 상관없이 데이터베이스에 접근하여 SQL문을 수행할 수 있도록 제공하는 자바 API

Java에서 모든 DBMS에서 공통적으로 사용할 수 있는 인터페이스와 클래스로 구성하는 JDBC를 제공하고, 실제 구현은 DBMS 벤더가 자신의 DB에 맞도록 구현해 라이브러리로 제공한다.

**용어 정리**
1. **JDBC 인터페이스**: JDBC 프로그램을 하기 위한 API들을 말하며 SE에서 제공하는 java.sql 패키지이다.
   * ``java.sql.Connection``: 연결을 담당하는 인터페이스
   * ``java.sql.Statement``: SQL을 전달하는 인터페이스
   * ``java.sql.ResultSet``: SQL 요청 응답을 받는 인터페이스
   * Driver, DriverManager, PreparedStatement 등이 있다.
2. **JDBC 드라이버**: java.sql의 인터페이스를 상속하여 메소드의 몸체를 구현한 라이브러리(클래스 파일(*.jar))
   * JDBC를 이용하기 위해서는 DBMS를 선택하고 DBMS에서 제공하는 JDBC 드라이버가 반드시 필요하다.
   * 예시(Oracle):  ``OracleConnection``, ``OracleStatement``, ``OracleResultSet``

**JDBC 정리**
1. 응용프로그램에서 SQL문을 만들어 JDBC 인터페이스를 통해 전송한다.
2. JDBC 드라이버에서 DBMS 접속을 시도하여 SQL문을 전송한다.
3. 응용프로그램과 DBMS의 Bridge 역할을 하는 것이다.
4. 애플리케이션 로직은 JDBC 표준 인터페이스에만 의존한다.

**표준화 한계**
ANSI SQL이라는 SQL 표준이 있긴 하지만 DB마다 SQL, 데이터타입 등 일부 다르기 때문에 DB를 변경하면 JDBC 코드는 변경하지 않아도 되지만 SQL은 해당 DB에 맞도록 변경해야 한다. JPA(Java Persistence API)를 이용하면 이 부분을 많이 해결할 수 있다.

**JDBC를 이용한 DB연동**
1. **드라이버를 프로젝트 build path에 추가한다.**
   * 오라클의 경우 설치할 때 같이 다운받으며 ``product/버전/server/jdbc/lib``에 존재한다.
   * 버전마다 드라이버가 다르다.
   * 직접 추가하거나 빌드툴(Maven, Gradle)을 이용하여 추가한다.
2. **DB연동하는 클래스에 연결할 DB정보를 저장한다.**
   ```SQL
   // Oracle 예시
   String driver="oracle.jdbc.driver.Oracledriver";
   String url="jdbc:oracle:thin:@localhost:1521:서비스명(DB명)"

    String userid = "SCOTT";
    String passwd = "TIGER";
   ```
3. **OracleDriver 클래스로 인스턴스를 생성하여 DriverManager 클래스에 JDBC Driver로 등록한다.**
   * **Class.forName을 이용해 OracleDriver 클래스를 런타임 동적 로딩**해준다.
   * OracleDriver 클래스에는 static 구문이 있는데 여기서 **OracleDriver 객체를 생성해준다.**
   * 그리고 **객체를 DriverManager에 등록해줌**으로써 객체를 생성하지 않고도 DB에 연결할 수 있다.
    ```sql
    try {
        Class.forName(driver);
    } catch(ClassNotFoundException e) {
        System.out.println(e.getMessage());
        // e.printStackTrace();
    }
    ```
4. **JDBC Driver를 이용하여 DBMS 서버에 접속하고 접속정보(Connection 인스턴스)를 반환받아 저장한다.**
   * getConnection 메서드는 DriverManager의 메서드로 url, id, pw를 매개변수로 받아 **DriverManger가 가지고 있는 드라이버를 통해 DB를 연결한다.**
   ```SQL
   Connection con = null;
   try {
    // Connection은 인터페이스
    // DriverManager가 하위 클래스를 만들어준다.
    con = DriverManager.getConnection(url, userid, passwd);
    } catch(SQLException e) {
        System.out.println(e.getMessage());
        // e.printStackTrace();
    }
   ```
5. sql문을 작성한다.
   ```java
   // 세미콜론을 작성하지 않는다.
   String sql = "select deptno as no, dname, loc from dept"
   ```
6. 접속된 DBMS 서버에 sql문을 전송하기 위한 PrepareStatement 객체를 만든다.
   ```sql
   PrepareStatement pstmt = null;
   try {
    pstmt = con.prepareStatement(sql);
   } catch(SQLException e) {
    System.out.println(e.getMessage());
    // e.printStackTrace();
   }
   ```
7. PrepareStatement 객체로 sql문을 전송하여 실행하고 실행된 결과값을 반환하여 저장한다.
   * JDBC의 DML 작업은 자동커밋된다. (자동커밋을 트랜잭션 이슈로 비활성화 가능하다.(명시적으로 commit 하도록 변경 가능하다.))
   * executeQuery (SELECT 명령을 전달하는 메서드다.)
    ```sql
    // ResultSet에는 실제 select한 결과값을 참조할 수 있는 참조값이 저장된다.
    ResultSet rs = pstmt.executeQuery();
    ```
   * executeUpdate(DML(insert/delete/update) 명령을 전달하는 메서드다.)
    ```sql
    // 결과값은 DML이 적용된 레코드의 갯수가 반환된다.
    int num = pstmt.executeUpdate();
    ```
8. 사용했던 API(Connection, PreparedStatement, ResultSet)를 close()메서드를 통해 close해준다.
   * 역순으로 close해줘야 한다.

### Reference
* 멀티캠퍼스
* https://brilliantdevelop.tistory.com/54
* https://velog.io/@jcrs0907/JDBC