# SpringMovie (영화관 매표소)

### 프로젝트명 : springNote3 , springTransaction
### 프로젝트 경로 : com.mySpring.springNote3

> sql문 (5장 이상 구매시 rollback)
```sql
create table tblCard(
  counsumerId varchar2(20 BYTE),
  amount number(4,0)
)

create table tblTicket(
  counsumerId varchar2(20 BYTE),
  amount number(4,0),
  CHECK (amount < 5)
)

-- 
insert into tblCard values('홍길동', 3);
insert into tblTicket values('홍길동', 3);

-- 티켓 5장 이상 발급시 안됨
insert into tblCard values('홍길동', 5);
insert into tblTicket values('홍길동', 5);
```

> 트랜잭션 사용법

 * servlet-context.xml -> DataSoure 아래에 beans 추가
 ```html
	<beans:bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<beans:property name="dataSource" ref="dataSource" />
	</beans:bean>
 ```
 
  * DAO 사용할때 (@Setter 개당 하나씩 줘야함)
```java
@Setter
private PlatformTransactionManager transactionManager;

	// 티켓 구매
	public boolean buyTicket(final TranDTO dto) {
		
		boolean result = false;
		// 트랜잭션 추가
		TransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(definition);
		
		try {
			//카드 입력
			template.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					String sql = "insert into tblCard values(?,?)";
					PreparedStatement psmt = con.prepareStatement(sql);
					psmt.setString(1, dto.getCounsumerId());
					psmt.setInt(2, dto.getAmount());
					return psmt;
				}
			});
			//ticket 입력
			template.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					String sql = "insert into tblTicket values(?,?)";
					PreparedStatement psmt = con.prepareStatement(sql);
					psmt.setString(1, dto.getCounsumerId());
					psmt.setInt(2, dto.getAmount());
					return psmt;
				}
			});
			transactionManager.commit(status);
			result = true;
		}
		catch(Exception e){
			e.printStackTrace();			
			transactionManager.rollback(status);
			result = false;
		}
		return result;
	}
```

> <c:if> 사용법
```html
<c:if test="${flag == true}">
    <font color="blue">티켓이 발행되었습니다.</font><br>
    고객 아이디 : ${tick_info.counsumerId} <br>
    티켓 구매수 : ${tick_info.amount}<br>
    <a href="buy_ticket">야구 예매 하러 가기</a>
</c:if>
<c:if test="${flag == false}">
    <font color="red">카드 결제가 취소 되었습니다.</font><br>
    <a href="buy_ticket">야구 예매 하러 가기</a>
</c:if>		
```
> <c:set> 사용법
```html
<c:choose>
        <c:when test="${ticketInfo.amount > 4}">
            <font color="red">카드 결제가 취소되었습니다.</font><br/>
        </c:when>         
        <c:otherwise>
           <font color="blue">티켓이 발행 되었습니다.</font><br/>
			고객 아이디 : ${ticketInfo.consumerId }<br/>
			티켓 구매수 : ${ticketInfo.amount }<br/>
         </c:otherwise>
</c:choose>
```

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
