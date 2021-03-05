//package com.cos.blog.controller.testcontroller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
////# 스프링부트 파일 리턴 기본경로 ? src/main/resources/static 기본경로
////    # 그런데 static 이하에는 browser가 인식할 수 있는 정적파일 (html, css, javascript, img, video, ... (MIME))만 놓아야함.
////    # jsp는 동적페이지. tomcat이 컴파일을 통해서 html로 바꿔줘야하는데, static은 웹서버가 관리 -> 처리해야 하므로 static에 놓으면 안된다.
////    # viewResolver 설정해주는 것.
//
//
//@Controller
//public class SpringController {
//
//    @GetMapping("/spring/get")
//    public String home() {
//        return "/home.html";
//    }
//
//    @GetMapping("/spring/jsp")
//    public String jsp() {
//        return "home";
//    }
//
//}
