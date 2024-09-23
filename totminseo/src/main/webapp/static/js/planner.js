$(document).ready(function() { 
    const requestData = JSON.parse(sessionStorage.getItem('requestData')); 
    let tramt = requestData.tramt.replace(/,/g, ''); 

	let dateElements = [];
	let dayCounter = 0;
    if (requestData) { 
        console.log("세션에서 불러온 데이터:", requestData); 
        $('#mbti').text(requestData.mbti); 
        $('#tramt').text(requestData.tramt); 
		$('.pdate').text(requestData.trstadate + "-" + requestData.trenddate);
		const trstadate = new Date(requestData.trstadate);
		const trenddate = new Date(requestData.trenddate);
		
		let currentDate = trstadate;
		
		const daysWeek = ['일', '월', '화', '수', '목', '금', '토'];

        while(currentDate <= trenddate){
            const formattedDate = currentDate.toISOString().split('T')[0];
            const dayOfWeek = daysWeek[currentDate.getDay()];
            const dateWithDay = `${formattedDate} (${dayOfWeek})`;
            dateElements.push(dateWithDay); // 배열에 추가
            currentDate.setDate(currentDate.getDate() + 1);
        }

        let dayCounter = 0;
		
    } 

    let dataLoaded = { 
        hotels: false, 
        chatdata: false 
    }; 

    let ids = {}; // IDs를 저장할 객체

    function checkAllDataLoaded() { 
        if (dataLoaded.hotels && dataLoaded.chatdata) { 
            $('#saveButton').on('click', function() { 
                let courses = []; 
                for (const [day, idList] of Object.entries(ids)) { 
                    const description = idList.join(', ');
                    const courseDTO = createCourseDTO(day, description); 
                    courses.push(courseDTO); 
                } 

                const tripData = { 
                    memid: "user123", 
                    tripname: "제주도", 
                    mbti: requestData.mbti, 
                    tramt: parseInt(tramt), 
                    trstadate: requestData.trstadate, 
                    trenddate: requestData.trenddate, 
                    trpeople: parseInt(requestData.trpeople), 
                    areaCode: requestData.areacode, 
                    courses: courses // CourseDTO 객체 포함 
                }; 

                console.log(tripData); 

                $.ajax({ 
                    url: '/tot/recommendCourse/create', 
                    type: 'POST', 
                    contentType: 'application/json', 
                    data: JSON.stringify(tripData), 
                    success: function(response) { 
                        console.log('성공', response); 
                    }, 
                    error: function(error) { 
                        console.error('에러', error); 
                    } 
                }); 
            }); 
        } 
    } 

    // 호텔 데이터 가져오기 
    fetch('/totminseo/planner/data') 
        .then(response => response.json()) 
        .then(data => { 
            if (data && data.hotels) { 
                console.log("추천 데이터:", data); 

                const dailyContainer = $('.container'); 
				const dayContainer = $('.day-container');
                data.hotels.forEach(hotel => { 
                    const hotelElement = $('<div class="pcolor">').text(`${hotel.name} - ${hotel.type}`); 
                    dayContainer.append(hotelElement); 
                    dailyContainer.append(dayContainer); 
                }); 

                dataLoaded.hotels = true; 
                checkAllDataLoaded(); 
            } 
        }) 
        .catch(error => console.error('데이터 가져오기 에러:', error)); 

    // 채팅 데이터 가져오기 
    fetch('/totminseo/planner/chatdata') 
        .then(response => response.json()) 
        .then(data => { 
            console.log("받은 채팅 데이터:", data); 

            if (data && data.content && data.content.choices) { 
                const content = data.content.choices[0].message.content; 

                let parsedContent; 
                try { 
                    parsedContent = JSON.parse(content); 
                } catch (e) { 
                    console.error("Content는 유효한 JSON이 아닙니다:", e); 
                    parsedContent = null; 
                } 

                if (parsedContent) { 
                    console.log("파싱된 Content:", parsedContent); 

                    ids = extractIds(parsedContent); // IDs를 추출하여 할당 
                    console.log("추출된 IDs:", ids); 

                    const dailyContainer = $('.container'); 
					let number = 1;

					
                    for (const [day, activities] of Object.entries(parsedContent)) { 
                    	const dayContainer = $('<div class="day-container">');
						const datanav1 = $('<div>').addClass('datanav1');
						const dayElement = $('<div>').addClass('date1').text(`${day}`); 
						
						const date = $('<div>').text(dateElements[dayCounter++]); 
						datanav1.append(dayElement);
						datanav1.append(date);
	                    dayContainer.append(datanav1);           

						const timelineContainer = $('<div>').addClass('timeline-container');


                        for (const [meal, details] of Object.entries(activities)) { 
                            const mealElement = $('<div>').addClass('pcolor').append(`${meal}`); 
							const timelineItem = $('<div class="timeline-item">'); 	
		                    const numberElement = $('<div class="number1">').text(number++);

                            const timeContent = $('<div class="time-content">'); 
                            const detailsList = $('<div class="datanav3">'); 


                            detailsList.append(mealElement); 
                            for (const [type, info] of Object.entries(details)) { 
                                const infoElement = $('<div class="time-title">').text(`${info.이름} - 예상 비용: ${info['예상 비용']}`); 
                                detailsList.append(infoElement); 
                            } 
                            timeContent.append(detailsList);
							
                            timelineItem.append(numberElement);
							timelineItem.append(timeContent);
				
							timelineContainer.append(timelineItem);

                        } 
						 timelineContainer.append($('<div class="endnumber">'));

	                    dayContainer.append(timelineContainer);
	                    dailyContainer.append(dayContainer);
                    } 


                    dataLoaded.chatdata = true; 
                    checkAllDataLoaded(); 
                } else { 
                    console.error("파싱된 content가 없습니다."); 
                } 
            } else { 
                console.error("잘못된 데이터 구조:", data); 
                if (data && data.content) { 
                    console.log("Content 존재:", data.content); 
                } 
            } 
        }) 
        .catch(error => { 
            console.error("채팅 데이터 가져오기 에러:", error); 
        }); 

    function extractIds(data) { 
        if (typeof data !== 'object' || data === null) { 
            console.error("잘못된 데이터 형식:", data); 
            return {}; 
        } 

        function extractIdsFromObject(obj) { 
            let idsByDay = {}; 
             
            for (const day in obj) { 
                if (obj.hasOwnProperty(day)) { 
                    if (typeof obj[day] === 'object' && obj[day] !== null) { 
                        idsByDay[day] = []; 
                        for (const meal in obj[day]) { 
                            if (obj[day].hasOwnProperty(meal)) { 
                                if (typeof obj[day][meal] === 'object' && obj[day][meal] !== null) { 
                                    for (const type in obj[day][meal]) { 
                                        if (obj[day][meal].hasOwnProperty(type)) { 
                                            if (typeof obj[day][meal][type] === 'object' && obj[day][meal][type] !== null) { 
                                                for (const key in obj[day][meal][type]) { 
                                                    if (obj[day][meal][type].hasOwnProperty(key)) { 
                                                        if (key.includes('ID') && obj[day][meal][type][key] !== 'N/A') { 
                                                            let formattedId = ''; 
                                                            if (key.startsWith('관광지')) { 
                                                                formattedId = `toid:${obj[day][meal][type][key]}`; 
                                                            } else if (key.startsWith('호텔')) { 
                                                                formattedId = `lodid:${obj[day][meal][type][key]}`; 
                                                            } else if (key.startsWith('식당')) { 
                                                                formattedId = `restid:${obj[day][meal][type][key]}`; 
                                                            } 
                                                            if (formattedId) { 
                                                                idsByDay[day].push(formattedId); 
                                                            } 
                                                        } 
                                                    } 
                                                } 
                                            } 
                                        } 
                                    } 
                                } 
                            } 
                        } 
                    } 
                } 
            } 
            return idsByDay; 
        } 

        return extractIdsFromObject(data); 
    } 

    function createCourseDTO(day, description) { 
        return { 
            tripid: null, // tripid는 서버에서 설정할 값 
            areacode: requestData.areacode, // 지역코드 
            dcourse: description, // 하루 코스 설명 
            courregdate: null, // 서버에서 SYSDATE로 설정 
            courupdate: null  // 서버에서 SYSDATE로 설정 
        }; 
    } 
}); 
