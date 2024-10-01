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
</head>
<body>
    <h1>여행 계획</h1>

    <!-- 세션에서 불러온 데이터 표시 -->
    <div>
        <h2>MBTI</h2>
        <p id="mbti"></p>
        <h2>총 예산</h2>
        <p id="tramt"></p>
    </div>

    <!-- 호텔 데이터 표시 -->
    <h2>추천 호텔</h2>
    <ul id="hotels"></ul>

    <!-- 일차별 추천 코스 데이터 표시 -->
    <h2>추천 코스</h2>
    <div id="dailyData"></div>
    
    
    
    <button id="saveButton">저장하기</button>
    <div id="recommendationName"></div>

</body>
</html>