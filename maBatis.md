# SpringNote2 (mybatis)

### 프로젝트명 : springNote2
### 프로젝트 경로 : com.mySpring.springNote2

## URL : https://mvnrepository.com

myBatis 사용을 위하 설정해야 할 것

> pom.xml (myBatis 사용을 위한 추가)
    ```java
    <!-- myBatis 추가에 필요한 라이브러리-->
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.5.1</version>
    </dependency>
    <!-- myBatis-Spring -->
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis-spring</artifactId>
        <version>2.0.1</version>
    </dependency>
    ```
  
경로 : \spring\appServlet\servlet-context.xml

저장되는 경로는 뜻하는 곳이다. 수정해도 좋다.
`value="classpath:com/mySpring/springNote2/dao/mapper/*.xml`

> servlet-context.xml 추가

```html
    <beans:bean name="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource" >
        <beans:property name="driverClassName" value="oracle.jdbc.driver.OracleDriver" />
        <beans:property name="url" value="jdbc:oracle:thin:@localhost:1521:xe" />
        <beans:property name="username" value="hr" /> <!--db아이디-->
        <beans:property name="password" value="hr" /> <!--db패스워드-->
    </beans:bean>
    
    <beans:bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
		<beans:property name="dataSource" ref="dataSource"/>
		<beans:property name="mapperLocations" value="classpath:com/mySpring/springNote2/dao/mapper/*.xml"/>
	</beans:bean>
	
	<beans:bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
		<beans:constructor-arg index="0" ref="sqlSessionFactory"/>
	</beans:bean>
```
> com.mySpring.springNote2.dao.mapper 패키지 추가 (수정해도 좋다 라고 한것과 이름 동일해야함.)

 * NoteDAO.xml 추가
 ```html
 <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.mySpring.springNote2.DAO.INoteDAO">
	<select id="listDAO" resultType="com.mySpring.springNote2.DTO.NoteDTO">
		select * from tblNote order by id desc
	</select>	
</mapper>
 ```
 
> com.mySpring.springNote2.DTO -> NoteDTO 만들기
 
```java
@Data
@AllArgsConstructor
public class NoteDTO {
	private int id;
	private String writer;
	private String content;
}
```
 
> com.mySpring.springNote2.DAO -> INoteDAO 만들기
 
 ```java
public interface INoteDAO {
	public ArrayList<NoteDTO> listDAO();
	public void writeDAO(String writer, String content);
	public void deleteDAO(String id);	
}
 ```
 
> com.mySpring.springNote2.controller -> NoteController 만들기
 
```java
	@Autowired
	private SqlSession sqlSession;

	@RequestMapping(value = "/list")
	public String list(Model model) {
		INoteDAO dao = sqlSession.getMapper(INoteDAO.class);
		model.addAttribute("list", dao.listDAO());
		return "NoteList";
	}
```

> Batis 이용하여 insert 하기

 * NoteDAO.xml 에 추가 (id는 DAO에 선언한 메소드 명이랑 같아야함.)

```html
<insert id="writeDAO">
		insert into tblNote(id,writer, content) values(TBLNOTESEQ.nextVal, #{param1}, #{param2})
</insert>
```

 * Controller 에 mapping 하기
 
```java
@RequestMapping(value = "/wirte")
	public String noteForm(HttpServletRequest req, Model model) {
		INoteDAO dao = sqlSession.getMapper(INoteDAO.class);
		dao.writeDAO(req.getParameter("Notetitle"), req.getParameter("NoteContent"));
		return "redirect:list";
	}
```

> Batis 이용하여 Delete 하기

 * NoteDAO.xml 에 추가 (id는 DAO에 선언한 메소드 명이랑 같아야함.)

```html
<delete id="deleteDAO">
	delete from tblNote where id = #{param}
</delete>
```

 * Controller 에 mapping 하기
 
```java
 @RequestMapping(value = "delete")
	 public String delete(HttpServletRequest req,Model model){
		 INoteDAO dao = sqlSession.getMapper(INoteDAO.class);
		 dao.deleteDAO(req.getParameter("id"));
		 return "redirect:list";
	}
```
