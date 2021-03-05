<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>

<%@include file="layout/header.jsp" %>


<div class="container d-flex flex-column align-items-center">

    <c:forEach var="board" items="${boards.content}">
        <div class="card mb-4 w-75">
            <div class="card-body">
                <h4 class="card-title">${board.title}</h4>
                <a href="/board/${board.id}" class="btn btn-dark">board detail</a>
            </div>
        </div>
    </c:forEach>

    <div class="w-75 d-flex justify-content-end">
        <c:choose>
            <c:when test="${boards.first}">
                <ul class="pagination">
                    </li>
                    <li class="page-item"><a class="page-link"
                                             href="?page=${boards.number+1}">Next</a></li>
                </ul>
            </c:when>
            <c:when test="${boards.last}">
                <ul class="pagination">
                    <li class="page-item"><a class="page-link" href="?page=${boards.number-1}">Previous</a>
                    </li>
                </ul>
            </c:when>
            <c:otherwise>
                <ul class="pagination">
                    <li class="page-item"><a class="page-link" href="?page=${boards.number-1}">Previous</a>
                    </li>
                    <li class="page-item"><a class="page-link"
                                             href="?page=${boards.number+1}">Next</a></li>
                </ul>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<%@include file="layout/footer.jsp" %>

