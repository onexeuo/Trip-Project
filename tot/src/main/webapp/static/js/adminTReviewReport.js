//  URL 선언
const BASE_TREVIEW_URL = '/tot/admin/report'; // 여행 후기 기본 URL
const ALL_ADMIN_ACTIVE_TREVIEW_REPORT_URL = `${BASE_TREVIEW_URL}/1/1`; // 활성화 게시물 조회 URL
const ALL_ADMIN_DEACTIVE_TREVIEW_REPORT_URL = `${BASE_TREVIEW_URL}/2/1`; // 비활성화 게시물 조회 URL

// 에러 메시지 선언
const ERROR_MESSAGES = {
    NOT_SELECT_REPORT: '하나 이상의 후기 신고 내역을 선택해야 합니다.',
    FAIL_UPDATE_STATUS: '상태 변경 중 오류가 발생했습니다'
};

$(document).ready(() => {
    // 현재 경로에 따라 버튼 활성화
    let path = window.location.pathname;

    if (path.includes(ALL_ADMIN_ACTIVE_TREVIEW_REPORT_URL)) {
        $('#activeBtn').addClass('active');
        $('#deactiveBtn').removeClass('active');
    } else if (path.includes(ALL_ADMIN_DEACTIVE_TREVIEW_REPORT_URL)) {
        $('#deactiveBtn').addClass('active');
        $('#activeBtn').removeClass('active');
    }

    // 활성화 버튼 클릭 시 활성화 댓글 목록 처리
    $('#activeBtn').on('click', () => {
        window.location.href = ALL_ADMIN_ACTIVE_TREVIEW_REPORT_URL;
    });

    // 비활성화 버튼 클릭 시 비활성화 댓글 목록 처리
    $('#deactiveBtn').on('click', () => {
        window.location.href = ALL_ADMIN_DEACTIVE_TREVIEW_REPORT_URL;
    });

    // 신고 내역 관리 전체 선택 및 해제
    $("#selectAll").change(function () {
        $("input[name='reportSelect']").prop("checked", this.checked);
    });

    // 행 전체 클릭 시 체크박스 선택
    $('tbody tr').on('click', function() {
        const checkbox = $(this).find('input[name="reportSelect"]');
        checkbox.prop('checked', !checkbox.prop('checked'));
    });
    
    // 체크박스를 클릭시 체크박스 선택
    $('input[name="reportSelect"]').on('click', function(event) {
        const checkbox = $(this);
        checkbox.prop('checked', !checkbox.prop('checked'));
    });

	// 게시글/댓글 내용 최대 길이 조정
    handleCommentMaxLength();
    
    // 신고 내역 목록에서 체크한 댓글에 대한 활성화, 비활성화 처리
    $('.activeButton').on('click', function (e) {
        e.preventDefault();
        e.stopPropagation();

        const selectedReports = $("input[name='reportSelect']:checked").map(function () {
            return $(this).val();
        }).get();

        if (selectedReports.length === 0) {
            alert(ERROR_MESSAGES.NOT_SELECT_REPORT);
            return;
        }
        
        // 모달창 띄우기
		$('#statusModal').show();
		
		// 확인 버튼 클릭 시 신고 처리 요청
		$('#confirmUpdateBtn').off('click').on('click', function() {
	        const status = $('#reportStatus').val();
	        const url = $(e.currentTarget).attr('href');
	
	        // 단순 ID 배열을 JSON.stringify로 변환하여 전송
	        handleActiveStatus(url.replace("{status}", status), JSON.stringify(selectedReports.map(id => parseInt(id))));
	        $('#statusModal').hide();
    	});
    });
     
    // 모달 닫기 버튼 클릭 시 모달 닫기
    $('.close2').on('click', function() {
        $('#statusModal').hide();
    });

	// 모달 외부 클릭 시 모달 닫기
    $(window).on('click', function(event) {
        if ($(event.target).is('#statusModal')) {
            $('#statusModal').hide();
        }
    });
    
});

// 활성화, 비활성화 처리 함수
const handleActiveStatus = (url, data) => {
    $.ajax({
        type: 'POST',
        url: url,
        contentType: 'application/json',
        data: data,
        success: function (response) {
            alert(response.message);
            location.reload();
        },
        error: function (xhr) {
            // 서버에서 전달한 오류 메시지를 확인
            const errorResponse = xhr.responseJSON;
            if (errorResponse && errorResponse.message) {
                alert(errorResponse.message);
            } else {
                alert(ERROR_MESSAGES.FAIL_UPDATE_STATUS);
            }
        }
    });
}

// 댓글 내용 최대 길이 조정 함수
const handleCommentMaxLength = () => {
	const maxLength = 20; // 최대 글자 수

    $('.comment-content').each(function() {
        const content = $(this).text().trim();

        if (content.length > maxLength) {
            $(this).text(content.slice(0, maxLength) + '...');
        } else {
            $(this).text(content);
        }
    });
}

