<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@include file="../layout/header.jsp" %>

<div class="container d-flex justify-content-center">
    <div class="w-50">
        <form action="/auth/signinProc" method="post">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" class="form-control" placeholder="Enter username" name="username"
                       id="username">
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" placeholder="Enter password"
                       name="password" id="password">
            </div>
            <div class="form-group form-check">
                <label class="form-check-label">
                    <input class="form-check-input" type="checkbox" name="remember"> Remember me
                </label>
            </div>
            <div>
                <a href="https://kauth.kakao.com/oauth/authorize?client_id=3a62ed60cbaca789517ca5fd0e6a909c&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code"><img
                        height="37.68" src="/image/kakao_login_button.png"></a>
                <button id="btn-login" class="btn btn-dark float-right">Submit</button>
            </div>
        </form>
    </div>
</div>

<%@include file="../layout/footer.jsp" %>