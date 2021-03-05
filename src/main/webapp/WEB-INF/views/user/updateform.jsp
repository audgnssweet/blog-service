<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@include file="../layout/header.jsp" %>

<div class="container d-flex justify-content-center">
    <div class="w-50">
        <form>
            <div class="form-group">
                <label for="username">Username:</label>
                <input readonly value="${principal.user.username}" type="text" class="form-control"
                       id="username" placeholder="Enter username"
                       name="username" required>
            </div>
            <c:if test="${principal.user.social == 'BLOG'}">
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" class="form-control" id="password"
                           placeholder="Enter password"
                           name="password" required>
                </div>
            </c:if>
            <div class="form-group">
                <label for="email">Email:</label>
                <input readonly value="${principal.user.email}" type="text" class="form-control"
                       id="email" placeholder="Enter email"
                       name="email" required>
            </div>
        </form>
        <c:if test="${principal.user.social == 'BLOG'}">
            <button id="btn-update" class="btn btn-dark float-right">Update</button>
        </c:if>
    </div>
</div>

<script src="/js/user.js"></script>
<%@include file="../layout/footer.jsp" %>