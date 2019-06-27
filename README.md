# SpringFrm (스프링 공부중)

Spring Download URl : https://spring.io/tools3/sts/all <br>
lombok Download URl : https://projectlombok.org/download <br>
------------

### 개발 IDE : Spring 3 (version 3)

---
- 배운내용을 통해 복습한 것들
  + [JSDL을사용함] <https://github.com/oohitoo/SpringFrm/blob/master/SpringBoard.md>
  + [myBatis 글쓰기/수정/삭제] <https://github.com/oohitoo/SpringFrm/blob/master/maBatis.md>
  + [글쓰기, 수정, 삭제 (JDBC)] <https://github.com/oohitoo/SpringFrm/blob/master/movieticket.md>
  + [JSDL을사용함](https://google.com)
---

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
  6. 아래와 같이 사용하면 된다.<br>
   + 방법 1 (modelDto로 받아야됨.)
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
	public String join(MemberDto member) {
		return "member/join";
	}
	// 아래와 같이 받으면 된다
	id : ${memberDto.id}<br>
	pwd : ${memberDto.pwd}<br>
	name : ${memberDto.name}<br>
	email : ${memberDto.email}<br>
  ```
   + 방법 2 (변수를 사용해서 member로 받을수 있음.)
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
		return "member/join";
	}
	
	// 아래와 같이 받으면 된다
	id : ${member.id}<br>
	pwd : ${member.pwd}<br>
	name : ${member.name}<br>
	email : ${member.email}<br>
  ```
 > @PathVariable 사용하여 값을 전달 가능하다.
   + member/student/{넘어갈 값 변수}/{넘어갈 값 변수}
     * 메게변수에도 Mapping에 선언한거 똑같이 변수로 주어야함.
 ```java
 -controller
 @RequestMapping(value = "member/student/{studentId}/{no}")
	public String student(@PathVariable String studentId,@PathVariable int no, Model model) {
		model.addAttribute("studentId", studentId);
		model.addAttribute("no", no);
		return "member/student";
	}

-view
	// 받을때
	student : ${studentId}<br>
	no : ${no}<br>
```

 > GET, POST
   + HttpServletRequest 을 이용할 것
   + ModelAndView : Model(데이터)과 view를 동시에 설정 가능
 ```java
 -controller
 	// form에서 get방식
	@RequestMapping(value = "member/goGet" , method = RequestMethod.GET)
	public String goStudent(HttpServletRequest req, Model model) {
		String id = req.getParameter("id");
		model.addAttribute("id", id);
		return "member/goGet";
	}
	
	// form에서 post방식
	@RequestMapping(value = "member/goPost", method = RequestMethod.POST)
	public ModelAndView goStudent(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		String id = req.getParameter("id");
		mv.addObject("id",id);
		//넘어가는페이지
		mv.setViewName("member/goGet");
		return mv;
	}

-view
<!-- GET -->
	<form action="goGet" method="get">
		<input type="text" name="id" value="aaa1">
		<input type="submit" value="get">
	</form>
	<!-- POST -->
	<form action="goPost" method="post">
		<input type="text" name="id" value="aaa2">
		<input type="submit" value="post">
	</form>
	
	//받을때
	id : ${id}<br>
```
 > redirect 
   + return 에 `redirect`를 선언하여 `studenOK`를 실행 시키도록한다.
 ```java
 	@RequestMapping(value = "/studentConfirm")
	public String studentRedirect(HttpServletRequest req) {
		String id = req.getParameter("id");
		if(id.equals("aaa")) {
			return "redirect:studentOK";
		}
		return "redirect:studentNG";
	}
	
	@RequestMapping(value = "/studentOK")
	public String studentOK(Model model) {		
		return "student/studentOK";
	}
	
	@RequestMapping(value = "/studentNG")
	public String studenNG(Model model) {
		return "student/studentNG";
	}
 ```
  + URL을 호출 할 수 도있다.
```java
	@RequestMapping(value = "/studentURL")
	public String studentURL(Model model) {
		return "redirect:http://localhost/ex01/form.jsp";
	}
```
