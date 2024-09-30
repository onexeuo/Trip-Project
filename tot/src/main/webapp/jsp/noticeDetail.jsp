<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="initial-scale=1, width=device-width" />
    <title>공지사항 상세보기</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/global.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/noticeDetail.css" />
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
    <section class="container">   
        <h2 class="row1Text">NOTICE</h2> 
        <div class="fromHeaderParent">
            <div class="fromHeader">
                <div class="row1">
                    <div class="row1Text1">공지사항</div>
                </div>
                <div class="instruction"></div>
            </div>

            <div class="row2">
                <div class="noticeTitle">
                    <b>제목</b>
                    <p>${notice.noTitle}</p>
                </div>
                <div class="noticeTitleContent">
                    <b>내용</b>
                    <p>${notice.noText}</p>
                </div>
            </div>
        </div>  
        <div class="buttons">
            <button type="button" class="toListBtn" onclick="location.href='${pageContext.request.contextPath}/notice/1'">목록으로</button>
        </div>
    </section>
    <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
