const user = {
  init: function () {
    $("#btn-update").on("click", () => {
      this.update();
    })
  },

  update: function () {
    const userId = $("#userId").text();

    const data = {
      password: $("#password").val()
    };

    $.ajax({
      type: "PUT",
      url: "/user/" + userId,
      data: JSON.stringify(data),
      contentType: "application/json; charset=utf-8",
      dataType: "json"
    }).done(function (resp) {
      alert("회원정보 수정이 완료되었습니다. 다시 로그인해주세요");
      location.href = "/logout";
    }).fail(function (error) {
      alert("회원정보 수정에 실패했습니다");
      console.log(error);
    })
  }

}

user.init();