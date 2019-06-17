# SpringFrm
스프링 공부중

Spring Download Url : https://spring.io/tools3/sts/all
------------

### 개발 IDE : Eclipe EE

> 다운받은 sts.exe 실행
 + 경로는 C:\Spring\sts-bundle\sts-3.9.8.RELEASE\sts.exe 과 같다.
 
> Project 만들기 & setting
 
 1. new -> Legacy Project -> mvcProject 선택 후 (프로젝트명) -> Plugin (com.spring.아무거나)

pom.xml 수정
11 ~ 14, 139 라인
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
