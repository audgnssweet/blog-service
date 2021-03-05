<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@include file="../layout/header.jsp" %>

<div class="container d-flex flex-column align-items-center justify-content-center">
    <span style="display: none" id="boardId">${boardDetail.id}</span>
    <div class="w-50">
        <div class="d-flex justify-content-between align-items-end">
            <h1 style="opacity: 0.9;">${boardDetail.title}</h1>
            <c:if test="${principal.user.id == boardDetail.user.id}">
                <div>
                    <a href="/board/updateForm/${boardDetail.id}"
                       style="color: black" class="badge">수정</a>
                    <button style="border: none; background-color: white" id="btn-delete"
                            class="badge">삭제
                    </button>
                </div>
            </c:if>
        </div>
        <div style="background-color: darkgray; height: 1px; margin: 10px 0px;"></div>
        <div style="font-size: 21px">
            <p>${boardDetail.content}</p>
        </div>
    </div>
    <br/>
    <div class="card w-50">
        <div style="height: 35px; font-weight: bold; border-bottom: none"
             class="card-header d-flex align-items-center">
            <span>Comment</span></div>
        <div style="border-top: none" class="card-footer d-flex">
            <textarea id="reply-content" class="form-control shadow-none" rows="1"></textarea>
            <button id="btn-reply-save" class="btn btn-dark">Submit</button>
        </div>
    </div>
    <c:forEach var="reply" items="${boardDetail.replies}">
        <div class="card w-50">
            <span style="display: none" id="reply-user">${reply.user.id}</span>
            <span style="display: none" id="replyId">${reply.id}</span>
            <div class="card-body d-flex flex-column justify-content-between">
                <div>${reply.content}</div>
                <div style="height: 10px"></div>
                <div class="d-flex align-items-center justify-content-end">
                    <div style="font-size: 13px" class="font-italic">작성자 : ${reply.user.username}
                        &nbsp;
                    </div>
                    <c:if test="${reply.user.id == principal.user.id}">
                        <button onclick="boardDetail.replyDelete(${reply.id}, ${boardDetail.id})"
                                style="border: none; background-color: white" class="badge">삭제
                        </button>
                    </c:if>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<script src="/js/boarddetail.js"></script>
<%@include file="../layout/footer.jsp" %>