<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=380ae83d33ff0006270a1f7b2d257ebf&libraries=services"></script>
    <script defer src="${pageContext.request.contextPath}/static/js/trip.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/trip.css">
    <title>여행코스경로</title>
</head>
<body>
    <div class="container">
        <h1>여행 경로 표시 Map</h1>
        <div class="pheader">
            <h1></h1>
            <div>
                <div class="pdate"></div>
            </div>
		<button id="toggle-course-btn" class="toggle-course-btn">코스만 보기</button>
        </div>
        <div class="main-content">
            <div id="courses-container"></div>
            
            <div class="courseDiv">
                <div class="courseBtnDiv" id="course-buttons"></div>
                <div class="courseMapDiv">
                    <div id="map" style="width:800px;height:600px;position:absolute;"></div>
                    <div id="clickLatlng"></div>
                </div>
            </div>
        </div>
        
        <div class="butdiv">
            <button class="backbtn" onclick="window.location.href='${pageContext.request.contextPath}/trip/list'">목록으로</button>
            <button class="okbtn">저장하기</button>
        </div>
    </div>
    
</body>
</html>
