<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@include file="../layout/header.jsp" %>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css"
      rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>

<div class="container d-flex justify-content-center">

    <!--  썸머노트 사용  -->
    <!--  bootswatch도 괜찮  -->
    <div class="w-75">
        <span style="display: none" id="boardId">${boardDetail.id}</span>
        <form>
            <div class="form-group">
                <label for="title">Title</label>
                <input value="${boardDetail.title}" type="text" class="form-control"
                       placeholder="Enter Title" id="title"/>
            </div>
            <div class="form-group">
                <label for="content">Content</label>
                <textarea class="form-control summernote"
                          id="content">${boardDetail.content}</textarea>
            </div>
        </form>
        <button id="btn-update" class="btn btn-dark float-right">Modify</button>
    </div>

    <script>
      $('.summernote').summernote({
        placeholder: 'Input Contents',
        tabsize: 2,
        height: 400
      });
    </script>

</div>

<script src="/js/boarddetail.js"></script>
<%@include file="../layout/footer.jsp" %>