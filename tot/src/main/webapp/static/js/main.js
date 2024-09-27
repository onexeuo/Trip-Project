$(document).ready(function(){

    function closeModal() {
        $("#testModal").css("display", "none");
    }

    // TEST START 버튼 클릭 시 함수 호출
    $("#testStartBtn").on("click", checkTestStatus);

    // 모달 내 버튼 동작 정의
    $("#retestBtn").on("click", function() {
        window.location.href = "/tot/tendency";
    });

    $("#courseBtn").on("click", function() {
        window.location.href = "/tot/recommendCourseInput";
    });

    // 닫기 버튼
    $(".close").on("click", closeModal);
});


     function checkTestStatus() {
        const memberTT = "${sessionScope.member.member_tt}"; // JSP에서 sessionScope로 member_tt 값을 가져옴
		console.log(memberTT);
        if (memberTT === "TT02") {
            // TT02일 경우 바로 tendency 페이지로 이동
            window.location.href = "/tendency";
        } else {
            // TT02가 아니면 모달을 띄움
            $("#testModal").css("display", "block");
        }
    }
