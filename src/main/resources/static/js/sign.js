const sign = {
  init: function () {
    // jquery문법
    $("#btn-save").on("click", () => {  //function() {} 을 안쓰고 () => 쓰는 것은 this를 바인딩하기 위해서
      this.save();
    });
    // $("#btn-login").on("click", () => {
    //   this.login();
    // });
  },

  save: function () {
    const data = {
      username: $("#username").val(),
      password: $("#password").val(),
      email: $("#email").val()
    };

    //ajax로 3개의 data를 json으로 변경 후 서버에 insert 요청.
    //ajax는 호출시 기본적으로 비동기. 현재 회원가입을 요청해도 아래 코드가 있다면 요청해서 결과값 받아올 동안 아래 코드 실행하다가 callback
    $.ajax({
      type: "POST", //ajax 요청은 post
      url: "/auth/joinProc", //요청 주소
      data: JSON.stringify(data), //javascrip object -> json
      contentType: "application/json; charset=utf-8", //request data type
      dataType: "json"  //response data type. json -> javascript object로 바꿔서 아래 done에 함수로 넣어줌.
      //근데 얘 없어도 ajax 버전이 올라가서 자동으로 json을 javascript object로 변환해줌.
    }).done(function (resp) {
      //정상실행된경우
      console.log("정상적으로 가입되었습니다. 로그인해주세요");
      location.href = "/auth/signinForm";  //로그인 페이지로 재요청
    }).fail(function (error) {
      //실패한 경우
      alert("회원가입에 실패했습니다.");
      console.log(JSON.stringify(error)); //에러객체 반환받은 것을 object로 error에 넣어주는데 이걸 확인.
    });
    //ajax사용 이유?
    //1. data를 return 하기 위해서. -> web app 등에서 서버가 api만으로 연동 가능. (html돌려주면 웹에 종속)
    //2. 비동기 통신을 위해서.

  }
  //
  // login: function () {
  //   const data = {
  //     userName: $("#userName").val(),
  //     password: $("#password").val()
  //   };
  //
  //   $.ajax({
  //     type: "POST", //get은 주소창에 로그가 남으므로, get방식의 로그인 요청은 위험함.
  //     url: "/api/user/login",
  //     data: JSON.stringify(data),
  //     contentType: "application/json; charset=utf-8",
  //     dataType: "json"
  //   }).done(function (resp) {
  //     alert("로그인에 성공했습니다");
  //     console.log(resp);
  //     location.href = "/";  // 로그인 성공시 메인페이지로 이동.
  //   }).fail(function (error) {
  //     alert("로그인에 실패했습니다");
  //     console.log(JSON.stringify(error));
  //   });
  // }

};

sign.init();