# SpringMovie (영화관 매표소)

### 프로젝트명 : springNote3
### 프로젝트 경로 : com.mySpring.springNote3

> servlet-content.xml (JDBC Template 사용)

```html
	<beans:bean name="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
		<beans:property name="driverClassName" value="oracle.jdbc.driver.OracleDriver" />
		<beans:property name="url" value="jdbc:oracle:thin:@localhost:1521:xe" />
		<beans:property name="username" value="hr" /> <!--db아이디 -->
		<beans:property name="password" value="hr" /> <!--db패스워드 -->
	</beans:bean>
	
	<beans:bean name="template" class="org.springframework.jdbc.core.JdbcTemplate">
		<beans:property name="dataSource" ref="dataSource" />
	</beans:bean>
	
	<beans:bean name="dao" class="com.mySpring.springNote3.DAO.NoteDAO" >
		<beans:property name="template" ref="template" />
	</beans:bean>
```

> web.xml (한글 깨지는거 고치기)

```html
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

> 패키지 생성 : controller, DAO, DTO 생성

> DTO (bean 이라고 생각하면됨) 

@NoArgsConstructor : 디폴트 생성자 만들어 주는곳
@AllArgsConstructor : 모든 필드 디폴트 생성자 만들어주기

 * 삽입, 수정, 삭제
 ```java
@Data
@NoArgsConstructor
@AllArgsConstructor

public class NoteDTO {
	private int id;
	private String writer;
	private String content;
}
 ```
 
> DAO (mgr 이라고 생각하면됨)

 - servlet-context 에서 자동 생성하여 객체를 주입 후 사용

``` java
@Setter
    private JdbcTemplate template;
```

 * 목록(List)
 ```java
	//list
	public ArrayList<NoteDTO> list(){
		String sql = "select * from tblNote order by id desc";
		return (ArrayList<NoteDTO>)template.query(sql, new BeanPropertyRowMapper<NoteDTO> (NoteDTO.class));
	}
 ```

 * 삽입
 
```java
    //write
	public void write(final String writer, final String content) {
		// 익명 클래스로 선언
		template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String sql = "insert into tblNote(id, writer, content)values(tblNoteSeq.nextval,?,?)";
				PreparedStatement pstm = con.prepareStatement(sql);
				pstm.setString(1, writer);
				pstm.setString(2, content);			
				return pstm;
			}
		});
	}
```
 * 삭제
```java
    //delete
	public void delete(final int id) {
		String sql = "delete from tblNote where id=?";
		template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement psmt) throws SQLException {
				psmt.setInt(1, id);
			}
		});
	}
```
 
 * 수정
