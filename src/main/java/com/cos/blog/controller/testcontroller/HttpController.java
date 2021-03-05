//package com.cos.blog.controller.testcontroller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;

//HTML의 FORM 태그 - GET, POST 요청밖에 못함.
//- SPRING이 제공하는 FORM:FORM 태그 - 모든 요청 가능.
//JAVASCRIPT로 AJAX 요청 + 데이터는 JSON형식으로 통일 - 모든 요청 가능 - 이게 가장 많이쓰고 좋음.


//
//@RestController
//public class HttpController {
//
//    @GetMapping("/http/get")    //GET방식은 parameter을 querystring 방식으로밖에 전달할 수 없음.
//    public String doGet(
////        @RequestParam int id
//        UserTest user           //자동으로 객체와 매핑시켜줌.
//        //Get방식은 @RequestBody가 필요 없음. 쓰지 않기 때문에 당연.
//    ) {
//        return "doGet " + "id = " + user.getId() + "name = " + user.getName();
//    }
//
//    //springboot의 messageconverter가 들어오는 데이터를 매핑해준다.
//    @PostMapping("/http/post")  //POST 요청은 Data를 querystring말고 body에 담아서 보낸다.
//    public String doPost(
//        @RequestBody UserTest user) {   //raw방식의 기본데이터, form방식, @RequestBody를 통한 json
//        return "doPost " + "id = " + user.getId() + "name = " + user.getName();
//    }
//
//    @PutMapping("/http/put")
//    public String doPut(@RequestBody UserTest user) {   //얘는 post와 마찬가지
//        return "doPost " + "id = " + user.getId() + "name = " + user.getName();
//    }
//
//}
