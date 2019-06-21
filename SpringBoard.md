# Spring Board (스프링 게시판 만들기)

> setting 하기 DB연동 포함
  1. 오라클에 테이블 및 시퀀스 생서
  2. STS에서 springBoard 프로젝트 생성
     (package : com.mySpring.springBoard)
  3. pom.xml
    (프로젝트 오른쪽마우스 클릭 : maven -> Update Project)
     * java-version : 1.8
     * org.springframework-version : 5.0.7.RELEASE
     * org.apache.maven.plugins : 3.5.1
     * lombok 추가 
  4. src/main/webapp/main.jsp 생성 -> 실행
  5. Tomcat 9.0 -> server.xml
```html
<Resource auth="Container" driverClassName="oracle.jdbc.OracleDriver" maxActive="50" maxWait="1000" name="jdbc/Oracle11g"
    		  password="hr" type="javax.sql.DataSource" url="jdbc:oracle:thin:@localhost:1521:xe" username="hr"/>
<!-- 154Line 에 Context 사이에 넣어야됨 -->
<ResourceLink name="jdbc/Oracle11g" global="jdbc/Oracle11g" type="javax.sql.DataSource"/>

ex:)
<Resource auth="Container" driverClassName="oracle.jdbc.OracleDriver" maxActive="50" maxWait="1000" name="jdbc/Oracle11g"
    		  password="비밀번호" type="javax.sql.DataSource" url="jdbc:oracle:thin:@localhost:포트번호:오라클버전" username="아이디"/>
```
  6. orcal 세팅
    + </properties> 끝나는 부분 추가 (16번 라인)
    
```html
	<repositories>
		<repository>
			<id>oracle</id>
			<url>http://maven.jahia.org/maven2</url>
		</repository>
	</repositories>
	<dependencies>	
```
    + lombok 설정한곳 바로 밑에 추가 
      
```html
		<!-- 스프링에서 JDBC 를 사용하기 위한 라이브러리 입니다. -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-jdbc</artifactId>
			<version>${org.springframework-version}</version>
		</dependency>


		<!-- 컨넥션 풀을 위한 라이브러리 -->
		<dependency>
			<groupId>commons-dbcp</groupId>
			<artifactId>commons-dbcp</artifactId>
			<version>1.4</version>
		</dependency>

		<!-- 오라클 JDBC 드라이버 -->
		<dependency>
			<groupId>com.oracle</groupId>
			<artifactId>ojdbc6</artifactId>
			<version>12.1.0.2</version>
		</dependency>
```
