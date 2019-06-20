# SpringFrm
스프링 공부중

Spring Download URl : https://spring.io/tools3/sts/all
lombok Download URl : https://projectlombok.org/download
------------

### 개발 IDE : Eclipe EE

> 단축키
 + `alt+ F5` updateProject

> 호출순서
 + `servlet-context.xml` -> Controller -> @RequestMapping -> .jsp

> Get, Post 한글깨짐
 * JSP 처럼 해도 해결 안됨
 	* <% request.setCharacterEncoding("UTF-8"); %>
 * `WEB-INF\web.xml` 파일 수정 필요 (추가함)
	  * ``` java 
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

> 다운받은 sts.exe 실행
 + 경로는 C:\Spring\sts-bundle\sts-3.9.8.RELEASE\sts.exe 과 같다.
 
> Project 만들기 & setting 
   1. new -> Legacy Project -> mvcProject 선택 후 (프로젝트명) -> Plugin (com.spring.애플리케이션명)

      pom.xml 수정 11 ~ 14, 139 라인
```java
<java-version>1.8</java-version>
	<org.springframework-version>5.0.7.RELEASE</org.springframework-version>
	<org.aspectj-version>1.6.10</org.aspectj-version>
	<org.slf4j-version>1.6.6</org.slf4j-version>

<version>3.5.1</version>
	<configuration>
	    <source>1.8</source>
	    <target>1.8</target>
```
 > member 폴더
 ```java
 @Controller
public class MemberController {
	// loginForm 호출함.
	@RequestMapping("member/loginForm")
	public String loginForm() {
		return "member/loginForm";
	}
	// req 이전페이지 값 받고 model에 저장	
	@RequestMapping("member/confirmId")
	public String confirmId(HttpServletRequest req, Model model) {
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
		model.addAttribute("id", id);
		model.addAttribute("pwd", pwd);
		return "member/confirmId";
	}
}
 ```
  + `@RequestParam("id") String id` : Param으로 받아 변수에 저장하겠다.
  + Dto (Bean을 사용함.)
  ```java
  @RequestMapping("member/join")
	public String insertDto(@RequestParam("id") String id,
			@RequestParam("pwd") String pwd,
			@RequestParam("name") String name,
			@RequestParam("email") String email,
			Model model) {
		
		MemberDto member = new MemberDto();
		
		member.setId(id);
		member.setPwd(pwd);
		member.setName(name);
		member.setEmail(email);
		
		return "member/join";
	}
	
  // 받을때
	id : ${member.id}<br>
	pwd : ${member.pwd}<br>
	name : ${member.name}<br>
	email : ${member.email}<br>
  ```
 > lomBok 설치 및 setting
   1. lombok.jar 파일 다운로드 (위에 주소 첨부)
   2. cmd(명령프롬프트) -> 다운한 경로 잡기 -> `java -jar lombok.jar` 실행
   3. sts.exe 경로 잡고 install
   4. sts.exe Reboot 한다.
   5. pom.xml에 아래와 같이 110번 라인 뒤에 추가한다.
 ```java
<!-- lombok -->
<dependency>
 <groupId>org.projectlombok</groupId>
  <artifactId>lombok</artifactId>
  <version>1.18.8</version>
 <scope>provided</scope>
</dependency>
 ```
  6. 아래와 같이 사용하면 된다.
    + 방법 1
  ```java
@Data
public class MemberDto {
	private String id;
	private String pwd;
	private String name;
	private String email;
}
  
  @RequestMapping("member/join") //데이터 커맨드 객체
	// 1. JSP에서 memberDto로 가져온다. (model.addAttribute("member",member))
	// 2. @ModelAttribute("member") 변수를 사용함
	public String join(@ModelAttribute("member") MemberDto member) {
		return "member/join";
	}
	// 아래와 같이 받으면 된다
	id : ${member.id}<br>
	pwd : ${member.pwd}<br>
	name : ${member.name}<br>
	email : ${member.email}<br>
  ```
   + 방법 2
  ```java
@Data
public class MemberDto {
	private String id;
	private String pwd;
	private String name;
	private String email;
}
  
  @RequestMapping("member/join") //데이터 커맨드 객체
	// 2. @ModelAttribute("member") 변수를 사용함
	public String join(@ModelAttribute("member") MemberDto member) {
		/* model.addAttribute("member", member); */
		return "member/join";
	}
	
	// 아래와 같이 받으면 된다
	id : ${member.id}<br>
	pwd : ${member.pwd}<br>
	name : ${member.name}<br>
	email : ${member.email}<br>
  ```
