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
    + 끝나는 부분 추가 (16번 라인) RELEASE 부분
    
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
7. insert 시 한글깨짐 방지

 * `src\main\webapp\WEB-INF\web.xml` 파일 수정 필요 (추가함)
    ``` java 
		<filter>
			<filter-name>encodingFilter</filter-name>
			<filter-class>
				org.springframework.web.filter.CharacterEncodingFilter
			</filter-class>
			<init-param>
				<param-name>encoding</param-name>
				<param-value>UTF-8</param-value>
			</init-param>
		</filter>
		<filter-mapping>
			<filter-name>encodingFilter</filter-name>
			<url-pattern>/*</url-pattern>
		</filter-mapping> 
        ```

> 게시판 리스트

 * modal interFace로 만듬
 
```java
public interface BCommand {
	void excute(Model model);
}
```

 * 보여줄페이지랑 맵핑 
```java
	@RequestMapping(value = "/list")
	public String list(Model model) {
		command = new BListCommand();
		command.excute(model); //동적할당
		return "list";
	}
 ```
 * BDto(Bean)에 저장하여 가져오기
```java
	@Override
	public void excute(Model model) {
		BDao dao = new BDao();
		ArrayList<BDto> dtos = dao.list();
		model.addAttribute("list", dtos);
	}
```
 * DB에서 리스트 불러오기
```java
//list
	public ArrayList<BDto> list() {
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			String sql = "SELECT * FROM tblBoard ORDER BY bID";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				int bHit = rs.getInt("bHit");
				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit);
				dtos.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return dtos;
	}
``` 

> 게시판 내용 수정
 * HTML 페이지
```html
<form action="modify" method="post">
	<table width="500"  border="1">
			<tr>
				<td> 번호 </td>
				<td><input name="bId" readonly="readonly" value="${contentView.bId}"></td>
			</tr>
			<tr>
				<td> 히트 </td>
				<td>${contentView.bHit}</td>
			</tr>
			<tr>
				<td> 이름 </td>
				<td><input  name="bName" value="${contentView.bName}"></td>
			</tr>
			<tr>
				<td> 제목 </td>
				<td><input name="bTitle" value="${contentView.bTitle}"></td>
			</tr>
			<tr>
				<td> 내용 </td>
				<td><textarea rows="10" name="bContent">${contentView.bContent}</textarea></td>
			</tr>
			<tr >
				<td colspan="2">
					<input type="submit" value="수정"> &nbsp;&nbsp;
						<a href="list">목록보기</a> &nbsp;&nbsp;
						<a href="">삭제</a> &nbsp;&nbsp;
				</td>
			</tr>
	</table>
	<input type="hidden" name="bId" value="">
</form>
```

 * Mapping 하는곳

```java
@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(HttpServletRequest req,Model model) {
		model.addAttribute("request", req);
		
		command = new BModifyCommand();
		command.excute(model);
		return "redirect:list";
	}
```

`com.myspring.springBoard.command ->BModifyCommand.java` 파일

```java
@Override
	public void excute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest)map.get("request");
		
		String bId = req.getParameter("bId");
		String bName = req.getParameter("bName");
		String bTitle = req.getParameter("bTitle");
		String bContent = req.getParameter("bContent");
		
		BDao dao = new BDao();
		dao.modify(bId, bName, bTitle, bContent);
	}
```

> 게시판 삭제

 * HTML 파일
```html
	<form action="modify" method="post">
	<table width="500"  border="1">
			<tr>
				<td> 번호 </td>
				<td><input name="bId" readonly="readonly" value="${contentView.bId}"></td>
			</tr>
			<tr>
				<td> 히트 </td>
				<td>${contentView.bHit}</td>
			</tr>
			<tr>
				<td> 이름 </td>
				<td><input  name="bName" value="${contentView.bName}"></td>
			</tr>
			<tr>
				<td> 제목 </td>
				<td><input name="bTitle" value="${contentView.bTitle}"></td>
			</tr>
			<tr>
				<td> 내용 </td>
				<td><textarea rows="10" name="bContent">${contentView.bContent}</textarea></td>
			</tr>
			<tr >
				<td colspan="2">
					<input type="submit" value="수정"> &nbsp;&nbsp; 
					<a href="list">목록보기</a> &nbsp;&nbsp; 
					<a href="delete?bId=${contentView.bId}">삭제</a> &nbsp;&nbsp; 
				</td>
			</tr>
	</table>
	<input type="hidden" name="bId" value="">
	</form>
```

 * mapping 해야됨
 
```java
	//delete
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest req, Model model) {
		model.addAttribute("request", req);
		command = new BDeleteCommand();
		command.excute(model);
		return "redirect:list";
	}
```

 * BDeleteCommand 만들자

```java
	@Override
	public void excute(Model model) {
		Map<String, Object> map = model.asMap(); 
		HttpServletRequest req = (HttpServletRequest)map.get("request");
		String bId = req.getParameter("bId");
		System.out.println(bId);
		BDao dao = new BDao();
		dao.delete(bId);
	}
```
