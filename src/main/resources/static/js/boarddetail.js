const boardDetail = {
  init: function () {
    $("#btn-update").on("click", () => {
      this.update();
    });
    $("#btn-delete").on("click", () => {
      this.delete();
    })
    $("#btn-reply-save").on("click", () => {
      this.replySave();
    })
  },

  delete: function () {
    const boardId = $("#boardId").text();

    $.ajax({
      type: "DELETE",
      url: "/board/" + boardId,
      dataType: "json"
    }).done(function (resp) {
      alert("글을 삭제했습니다");
      location.href = "/";
    }).fail(function (error) {
      alert("글 삭제에 실패했습니다");
      console.log(JSON.stringify(error));
    });
  },

  update: function () {
    const boardId = $("#boardId").text();

    const data = {
      title: $("#title").val(),
      content: $("#content").val()
    };

    $.ajax({
      type: "PUT",
      url: "/board/" + boardId,
      data: JSON.stringify(data),
      contentType: "application/json; charset=utf-8",
      dataType: "json"
    }).done(function (resp) {
      alert("수정이 완료되었습니다");
      location.href = "/board/" + boardId;
    }).fail(function (error) {
      alert("수정에 실패했습니다");
      console.log(error);
    })
  },

  replySave: function () {
    const data = {
      content: $("#reply-content").val(),
      boardId: $("#boardId").text(),
      userId: $("#userId").text()
    };

    $.ajax({
      type: "POST",
      url: `/board/${data.boardId}/reply`,
      data: JSON.stringify(data),
      contentType: "application/json; charset=utf-8",
      dataType: "json"
    }).done(function (resp) {
      location.href = `/board/${data.boardId}`;
    }).fail(function (error) {
      alert("댓글 작성에 실패했습니다");
      console.log(error);
    });
  },

  replyDelete: function (replyId, boardId) {

    $.ajax({
      type: "DELETE",
      url: `/board/${boardId}/reply/${replyId}`,
      dataType: "json"
    }).done(function (resp) {
      location.href = `/board/${boardId}`;
    }).fail(function (error) {
      alert("댓글 삭제에 실패했습니다");
      console.log(error);
    });
  }

};

boardDetail.init();