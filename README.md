# tomcat-log
Simple demo for enable access log for Spring Boot
Spring Boot의 web module에 있는 내장 톰캣은 기본적으로 access log를 출력하지 않는다.

다양한 방법으로 access log 혹은 그와 유사한 로그를 남길수 있지만 여기서는 전통적인 설치형 tomcat에서 access log를 남긴 것과 동일한 log 출력 방법을 알아 보도록 한다.



예제를 위한 tomcat 프로젝트 생성
Hands on을 위해 https://start.spring.io 로 접속해 Spring Web Dependency를 추가 후 Generate 버튼을 클릭한다.


다운로드 받은 tomcat-log 프로젝트의 압축을 풀고 사용하는 IDE를 통해 프로젝트 파일을 오픈한다.



TomcatLogApplication.java 파일을 다음과 같이 작성한다.

package com.example.tomcatlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class TomcatLogApplication {

   public static void main(String[] args) {
      SpringApplication.run(TomcatLogApplication.class, args);
   }

   @GetMapping("/hello/{name}")
   public String sayHello(@PathVariable String name) {
      return "Hello " + name;
   }

   @GetMapping("/goodbye/{name}")
   public String sayGoodBye(@PathVariable String name) {
      return "Good bye " + name;
   }
}


application.properties 파일을 오픈해 다음과 같이 편집한다.

server.tomcat.accesslog.enabled=true #로그를 사용하겠다고 지정
server.tomcat.basedir=. #spring boot가 실행되는 디렉토리를 기본 디렉토리로 설정 
server.tomcat.accesslog.directory=logs  #앞서 지정한 base디렉토리 아래 ./logs 디렉토리에 로그 디렉토리 설정
server.tomcat.accesslog.suffix=.log
server.tomcat.accesslog.prefix=access_log
server.tomcat.accesslog.file-date-format=.yyyy-MM-dd
#server.tomcat.accesslog.pattern=common  #일반적인 access log 포맷 적용시
server.tomcat.accesslog.pattern=%{yyyy-MM-dd HH:mm:ss}t %s %r %{User-Agent}i %{Referer}i %a %b %D
일반적인 로그 포맷을 사용하고 싶으면 server.tomcat.accesslog.pattern=common 으로 설정하면 된다.
만약 기본적인 로그 포맷을 확장하고 싶으면 아래의 패턴을 참조해 로그 포맷을 커스터마이징 할수 있다.

%h – 요청을 보낸 클라이언트 IP
%l – 사용자 식별 정보
%u – HTTP 인증에 의해 지정된 사용자 이름
%t – 요청 시간
%r – 사용자 요청 URL
%s – HTTP 응답 코드 
%b – 응답 사이즈




Spring Boot 실행 및 로그 확인
작성한 응용프로그램을 실행시킨 후 웹브라우저에 http://localhost:8080/hello/yourname 및 http://localhost:8080/goodbay/yourname 등을 다양하게 입력하면서 호출해본다.



Spring Boot가 실행된 디렉토리 하위에 /logs/  디렉토리에 access_log 파일이 생성된 것을 확인한다.





이상 ~
