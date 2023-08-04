# Mybatis 프레임워크
* JDBC 사용을 효율적으로, 편리하게, 파워풀하게 사용하도록 만든 프레임워크
* ibatis (old 버전)
* ``http://mybatis.org``

**특징**
1. 4가지 정보(driver, url, userid, passwd)는 xml 파일에 저장한다.
2. sql은 xml에 저장한다.
3. 드라이버 클래스 로드, 커넥션 얻기, sql 전송, close 등을 SqlSession API가 해준다.
   * 메서드: SelectOne(), SelectList(), insert(), update(), delete(), close()...
4. 비런타임 계열을 런타임계열로 정의해줘 예외처리를 할 필요가 없다.

## 사용방법
1. RDBMS 설치 (오라클)
2. 오라클 드라이버 
3. mybatis 기능을 가진 jar 파일 다운
   * ``https://github.com/mybatis/mybatis-3/releases``
4. 오라클 드라이버와 mybatis jar 파일 2개를 build path한다.
5. 2개의 xml 파일을 작성한다. (패키지 사용 가능하다.)
   * 설정정보 저장 xml: 1개 작성한다.
   * sql 저장 xml: 테이블 당 하나씩 작성한다.
6. java 코드에서 설정 xml파일을 읽는다.
7. SqlSession을 얻는다.
8. SqlSession 메서드로 sql을 실행한다.
9. SqlSession을 close한다.

## 1. 설정정보 xml 작성법
1. mybatis docs에서 설정정보를 저장하는 xml 코드를 제공해준다.(XML에서 SqlSessionFactory 빌드하기) 
2. 해당 코드를 복사하고 4가지 정보(driver, url, userid, passwd)를 사용하고자 하는 db 정보로 수정해준다.
3. 어떤 Mapper를 사용할 건지 ``<mapper resource=""/>``에 지정할 Mapper의 namespace를 넣어준다.
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
  "https://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
  <environments default="development">
    <environment id="development">
      <transactionManager type="JDBC"/>
      <dataSource type="POOLED">
        <property name="driver" value="oracle.jdbc.driver.Oracledriver"/>
        <property name="url" value="jdbc:oracle:thin:@localhost:1521:xe"/>
        <property name="username" value="SCOTT"/>
        <property name="password" value="TIGER"/>
      </dataSource>
    </environment>
  </environments>
  <mappers>
    <mapper resource="DeptMapper.xml"/>
  </mappers>
</configuration>
```

## 2. sql xml 작성법
1. 파일 명명법은 ``테이블명Mapper.xml``이다.
2. mybatis docs에서 제공해주는 코드를 복사하고 필요한 부분은 수정한다. (매핑된 SQL 구문 살펴보기)
3. sql의 파라미터(JDBC에서는 ``?``)는 ``#{컬럼명}``와 같이 작성해야 한다.
4. 파라미터 타입을 알려줘야 한다. (타입의 alias를 사용해도 된다.) 예) ``<select parameterType="int">sql</select>``
5. ``id``값은 sql 실행시키는 코드에서 참조하는 값이다. id값으로 sql을 실행시킬 수 있다.
6. ``resultType``에는 sql을 실행시킨 결과를 저장하는 DTO 클래스를 저장해주면 된다. 그러면 실행 결과를 지정된 DTO에 자동으로 저장된다.
7. sql 실행 결과의 컬럼헤딩과 지정한 DTO의 멤버변수의 이름이 같아야 한다.
8. 서로다른 테이블의 sql문이 작성되어 있는 xml 파일들에서 id는 같아도 되지만 namespace는 달라야 한다.
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
  
<mapper namespace="DeptMapper">

  <select id="findAll" resultType="DeptDTO">
    select deptno, dname, loc
    from dept
  </select>
  
  <select id="findByDeptno" resultType="Deptdto" parameterType="int">
    select deptno, substr(dname, 0) as dname, loc
    from dept
    where deptno = #{deptno}
  </select>
  
  <select id="findByDeptnoAndDname" resultType="DeptDTO" parameterType="DeptDTO">
    select deptno, dname, loc
    from dept
    where deptno = #{deptno} or dname=#{dname}
  </select>
  
</mapper>
```

## 3. java 코드에서 설정 파일 읽기
1. XML에서 SqlSessionFactory 빌드하기에서 코드가 제공된다. 
2. 만든 설정 파일의 경로로 수정한다.
```java
String resource = "Configuration.xml";
InputStream inputStream = Resources.getResourceAsStream(resource);
SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
```

## 4. SqlSession 얻기
```java
SqlSession session = sqlSessionFactory.openSession();
```

## 5. SqlSession API 
### 5-1. DQL 메서드
1. 단일행 (DTO 저장)
   * 파라미터가 없는 경우
    ``DeptDTO dto = session.selectOne("mapper id값");``
   * 파라미터가 있는 경우
    ``DeptDTO dto = session.selectOne("mapper id값", Object obj)``
    ```java
	DeptDTO dto = session.selectOne("DeptMapper.findByDeptno", 10);
	System.out.println(dto);
    ```
2. 복수행 (여러 DTO를 자동으로 List에 저장한다.)
   * 파라미터가 없는 경우: ``List<DeptDTO> list = session.selectList("mapper id값");``
   * 파라미터가 있는 경우: ``List<DeptDTO> list = session.selectList("mapper id값", Object obj);``
    * 일정 갯수만큼 검색 하기 (paging 처리에서 주로 사용된다.)
      *  ``List<DeptDTO> list = session.selectList("mapper id값", Object obj, Rowbounds rowbounds);``
      * ``Rowbounds bounds = new RowBounds(시작위치idx, 갯수);``
    ```java
    RowBounds bounds = new RowBounds(0, 3);
	List<DeptDTO> list4 = session.selectList("DeptMapper.findAllPage", null, bounds);
    ```

### 5-2. DML 메서드
1. insert
   * ``int n = session.insert("");``
   * ``int n = session.insert("", Object ob);``
2. update
   * ``int n = session.update("");``
   * ``int n = session.update("", Object ob);``
3. delete
   * ``int n = session.delete("");``
   * ``int n = session.delete("", Object ob);``
4. session.commit() 호출
   * JDBC에서는 DML이 기본적으로 auto commit이다.
   * Mybatis에서는 기본적으로 auto commit이 아니기 때문에 명시적으로 commit 호출해야 한다.
   * auto commit으로 변경하려면 openSession(true)로 지정하면 된다. (기본은 false다.)

## 6. SqlSession close하기
```java
session.close();
```

## XML 문법
1. 클래스 별칭 만들기
   ```XML
   <typeAliases>
    <typeAlias alias="별칭" type="패키지명[.패키지명. ...].클래스"/>
   </typeAliases>
   ```
2. 감싼 SQL문을 문자데이터로 처리한다.
   ```XML
   <![CDATA[
        SQL문
   ]]>
   ```

## 7. 예외
* Mapped Statements collection does not contain value for : Mapper id가 일치하는지 확인해야 한다.