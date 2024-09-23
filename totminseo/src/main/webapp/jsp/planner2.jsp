<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>planner</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/planner.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/planer.css">

</head>
<body>

    <div class="container">
        <div class="pheader">
            <h1>제주</h1>
            <div>
                <div class="pdate">2024.8.26-2024.8.28</div>
            </div>
        </div>

        <div class="day-container">
            <div class="datanav1">
                <div class="date1">1일차</div>
                <div class="datenav1"></div>
            </div>

            <div class="timeline-container"></div>
        </div>
    </div>

    <div class="butdiv">
        <button class="backbtn">메인으로</button>
        <button class="okbtn" id="saveButton">저장하기</button>
    </div>
    
    

</body>
</html>