package com.cos.blog.controller.apicontroller;

import com.cos.blog.common.Response;
import com.cos.blog.model.dto.request.UserJoinRequestDto;
import com.cos.blog.model.entity.User;
import com.cos.blog.service.impl.UserJoinServiceImpl;
import com.cos.blog.service.impl.UserService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    private final UserJoinServiceImpl userJoinService;

//    강제 세션 초기화에 필요
//    private final PrincipalDetailsService principalDetailsService;

//    @DeleteMapping("/user/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public Response<String> deleteUser(@PathVariable Integer id) {
//        final String resultString = userService.deleteUser(id);
//        return new Response<>(HttpStatus.OK, resultString);
//    }

    @PutMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response<String> updateUser(
        @PathVariable Integer id,
        @RequestBody User user
//        HttpSession httpSession
    ) {
        final String resultString = userService.updateUser(id, user);

//        강제 세션 초기화로 로그아웃 없이 수정
//        UserDetails userDetails = principalDetailsService.loadUserByUsername(user.getUsername());
//        Authentication authentication =
//            new UsernamePasswordAuthenticationToken(userDetails, null,
//                userDetails.getAuthorities());
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        securityContext.setAuthentication(authentication);
//        httpSession.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

        return new Response<>(HttpStatus.OK, resultString);
    }

//    //그냥 전체 다 가져오기
//    @GetMapping("/users")
//    @ResponseStatus(HttpStatus.OK)
//    public Response<List<User>> getUsers() {
//        final List<User> allUsers = userService.getAllUsers();
//        return new Response<>(HttpStatus.OK, allUsers);
//    }


//    @GetMapping("/user/page")
//    @ResponseStatus(HttpStatus.OK)
//    public Response<List<User>> getUsersPage(
//        //페이지에 기본적으로 데이터 2개, id를 기준으로 내림차순 sort.
//        //뒤에 querystring으로 ?page=숫자 이렇게 붙여주면 paging 쉽게 된다.
//        //페이지 인덱스는 0부터 시작
//        //그리고 json형식으로 페이징 할 떄 거의 필수적인 데이터들을 자동으로 채워서 함꼐 보내준다.
//        @PageableDefault(size = 2, sort = "id", direction = Direction.DESC) Pageable pageable
//    ) {
//        final List<User> users = userService.getUserPage(pageable);
//        return new Response<>(HttpStatus.OK, users);
//    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response<User> getUserDetail(@PathVariable Integer id) {
        final User userDetail = userService.getUserDetail(id);
        return new Response<>(HttpStatus.OK, userDetail);
    }

    @PostMapping("/auth/joinProc")
    @ResponseStatus(HttpStatus.CREATED)
    public Response<String> join(
        @Valid @RequestBody UserJoinRequestDto userLoginRequestDto,
        BindingResult bindingResult
    ) {
        String resultMessage = userJoinService.joinUser(userLoginRequestDto);
        return new Response<>(HttpStatus.CREATED, resultMessage);
    }

//    //이건 전통적인 로그인방식. spring security를 이용하지 않았음.
//    @PostMapping("/user/login")
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    //httpsession은 spring container가 가지고있음. 필드로 di 해서도 사용가능.
//    public Response<String> login(@RequestBody User user, HttpSession httpSession) {
//        final User principal = userService.login(user);
//        //유저가 로그인한 정보를 기억하기 위해 세션 생성. 기본지속 30분.
//        //서버가 꺼지면 session도 초기화.
//        //session - client정보 - browser 기억.
//        httpSession.setAttribute("principal", principal);
//        return new Response<>(HttpStatus.ACCEPTED, "로그인이 완료되었습니다");
//    }

}
