$(document).ready(function() {
    // 날짜 필드 초기화
    const $startDate = $('#trstadate');
    const $endDate = $('#trenddate');
  
    // 출발일 변경 시 도착일의 min 값을 업데이트
    $startDate.on('change', function() {
        const startDateValue = $(this).val();
        if (startDateValue) {
            $endDate.attr('min', startDateValue);
        } else {
            $endDate.removeAttr('min'); // 출발일이 비어 있으면 min 속성 제거
        }
    });
    
    // 도착일 변경 시 유효성 검사
    $endDate.on('change', function() {
        const endDateValue = $(this).val();
        const startDateValue = $startDate.val();
        if (endDateValue && startDateValue && new Date(endDateValue) < new Date(startDateValue)) {
            alert('도착일은 출발일 이후여야 합니다.');
            $(this).val(''); // 잘못된 날짜를 선택하면 입력값을 지웁니다.
        }
    });

    // 지역 버튼 클릭 시 선택 상태 변경
    $('.region').on('click', function() {
        $('.region').removeClass('selected'); // 모든 지역 버튼 선택 해제
        $(this).addClass('selected'); // 현재 버튼 선택
    });

    // 음식 버튼 클릭 시 선택 상태 변경
    $('.food').on('click', function() {
        $('.food').removeClass('selected'); // 모든 음식 버튼 선택 해제
        $(this).addClass('selected'); // 현재 버튼 선택
    });

	$('.submit-button').on('click', function(event) {
	    event.preventDefault();
		
		/*fetch('session/id')
	        .then(response => response.json())
	        .then(data => {
	            const memId = data.memId || 'guest';  // memId가 없으면 'guest'로 기본값 설정
	            sessionStorage.setItem('memId', memId); // 세션에 memId 저장
	            
	            // 페이지 로드 후 결과 업데이트
	            const MemberMbit = $('#mbti').val();
	            updateMbit(MemberMbit);
	     });
  */
	    // 폼 데이터 수집
	    const mbti = $('#mbti').val();
	    const tramt = $('#tramt').val();
	    const trpeople = $('#trpeople').val();
	    const trstadate = $('#trstadate').val();
	    const trenddate = $('#trenddate').val();
	    const trperiod = $('#trperiod').val();
	    const areacode = $('.region.selected').attr('id'); // 선택된 지역 버튼의 값
	    const restaurant_001 = $('.food.selected').attr('id'); // 선택된 음식 버튼의 값
	    const resultType = sessionStorage.getItem('resultType'); // 세션에서 resultType 가져오기
	
	    // JSON 데이터 구조화
	    const requestData = {
	        mbti: mbti,
	        tramt: tramt,
	        trpeople: trpeople,
	        trstadate: trstadate,
	        trenddate: trenddate,
	        trperiod: trperiod,
	        areacode: areacode,
	        restaurant_001: restaurant_001,
	        resultType: resultType // resultType 추가
	    };
	
	    // 데이터를 sessionStorage에 저장
	    sessionStorage.setItem('requestData', JSON.stringify(requestData));
	
	    // AJAX 요청
	    fetch('/totminseo/recommendCourse', {
	        method: 'POST',
	        headers: {
	            'Content-Type': 'application/json'
	        },
	        body: JSON.stringify(requestData)
	    })
	    .then(response => response.json())
	    .then(data => {
	        if (data.success) {
	            window.location.href = '/totminseo/planner';
	        } else {
	            alert('Error');
	        }
	        // 변환된 JSON 데이터를 콘솔에 출력
	        console.log("변환된 데이터:", data);
	    })
	    .catch(error => console.error('Error:', error));
	});
	
	/*function updateMbit(MemberMbit) {
	    const memId = sessionStorage.getItem('memId');
	
	    // memId가 'guest'일 경우 로그인 필요 알림
	    if (memId === 'guest') {
			console.log("guest");
	    } else {
	        // memId가 'guest'가 아닐 경우, 결과를 서버에 저장합니다.
	        fetch('/tot/recommendCourse/updateMbti', {
	            method: 'PATCH',
	            headers: {
	                'Content-Type': 'application/json'
	            },
	            body: JSON.stringify({
	                newMbti: MemberMbit,
	                memId: memId
	            })
	        }).then(response => response.json())
	          .then(data => {
	              if (data.success) {
	                  alert('성향 결과가 저장되었습니다.');
	              } else {
	                  alert('오류 발생: ' + data.error);
	              }
	          }).catch(error => {
	              console.error('Fetch error:', error);
	              alert('서버와의 통신 중 오류가 발생했습니다.');
	          });
	    }
	}
*/

    // 예산 입력 필드 숫자만 허용
    $('#tramt').on('input', function() {
        let value = $(this).val().replace(/[^0-9]/g, ''); // 숫자만 추출
        $(this).val(formatNumber(value)); // 숫자 포맷팅
    });

    $('#tramt').on('focusout', function() {
        let value = $(this).val().replace(/[^0-9]/g, '');
        $(this).val(formatNumber(value)); // 숫자 포맷팅
    });

    $('#tramt').on('focus', function() {
        $(this).val($(this).val().replace(/[^0-9]/g, ''));
    });

    // 숫자 천 단위로 포맷팅
    function formatNumber(value) {
        return value.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }

    // 페이지 로드 시 resultType 출력
    const resultType = sessionStorage.getItem('resultType');
    if (resultType) {
        console.log('세션에서 가져온 resultType:', resultType);
    } else {
        console.log('세션에 resultType이 없습니다.');
    }
});
