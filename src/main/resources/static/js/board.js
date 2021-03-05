const board = {
  init: function () {
    $("#btn-write").on("click", () => {
      this.save();
    });
  },

  save: function () {
    const data = {
      title: $("#title").val(),
      content: $("#content").val(),
      userId: $("#userId").text()
    };

    $.ajax({
      type: "POST",
      url: "/board",     //apicontroller 만들어서 넣어줘야함.
      data: JSON.stringify(data),
      contentType: "application/json; charset=utf-8",
      dataType: "json"
    }).done(function (resp) {
      alert("글 쓰기를 완료했습니다");
      location.href = "/";
    }).fail(function (error) {
      alert("글 쓰기에 실패했습니다");
      console.log(JSON.stringify(error));
    });
  }

};

board.init();