$(document).ready(function() { 
    const requestData = JSON.parse(sessionStorage.getItem('requestData')); 
    let tramt = requestData.tramt.replace(/,/g, ''); 

	       fetch('/tot/session/id')
            .then(response => response.json())
            .then(data => {
                const memId = data.memId || 'user123';  // memId가 없으면 'guest'로 기본값 설정
                sessionStorage.setItem('memId', memId); // 세션에 memId 저장
                
            });

    if (requestData) { 
        console.log("세션에서 불러온 데이터:", requestData); 
        $('#mbti').text(requestData.mbti); 
        $('#tramt').text(requestData.tramt); 
    } 

    let dataLoaded = { 
        hotels: false, 
        chatdata: false 
    }; 

    let ids = {}; // IDs를 저장할 객체
    let recommendationName = " "; 

    function checkAllDataLoaded() { 
        if (dataLoaded.hotels && dataLoaded.chatdata) { 
            $('#saveButton').on('click', function() { 
                let courses = []; 
                for (const [day, idList] of Object.entries(ids)) { 
					const description = idList.join(','); 
                    const courseDTO = createCourseDTO(day, description); 
                    courses.push(courseDTO); 
                } 

                const tripData = { 
                    memId: memid, 
                    tripName: recommendationName,  // Use extracted recommendation name
                    mbti: requestData.mbti, 
                    trAmt: parseInt(tramt), 
                    trstaDate: requestData.trstadate, 
                    trendDate: requestData.trenddate, 
                    trPeople: parseInt(requestData.trpeople), 
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
    fetch('/tot/planner/data') 
        .then(response => response.json()) 
        .then(data => { 
            if (data && data.hotels) { 
                console.log("추천 데이터:", data); 

                const hotelsContainer = $('#hotels'); 
                data.hotels.forEach(hotel => { 
                    const hotelElement = $('<li>').text(`${hotel.name} - ${hotel.type}`); 
                    hotelsContainer.append(hotelElement); 
                }); 

                dataLoaded.hotels = true; 
                checkAllDataLoaded(); 
            } 
        }) 
        .catch(error => console.error('데이터 가져오기 에러:', error)); 

    // 채팅 데이터 가져오기 
    fetch('/tot/planner/chatdata') 
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
                    // 추천 이름 추출
                    if (parsedContent.추천이름) {
                        recommendationName = parsedContent.추천이름;  // Store the recommendation name
                        $('#recommendationName').text(recommendationName); // HTML 요소에 표시
                    }

                    ids = extractIds(parsedContent); // IDs를 추출하여 할당 
                    console.log("추출된 IDs:", ids); 

                    const dailyContainer = $('#dailyData'); 
                    for (const [day, activities] of Object.entries(parsedContent)) { 
                        const dayElement = $('<div>').addClass('day').append(`<h2>${day}</h2>`); 
                        const mealsList = $('<ul>'); 

                        for (const [meal, details] of Object.entries(activities)) { 
                            const mealElement = $('<li>').addClass('meal').append(`<strong>${meal}:</strong>`); 
                            const detailsList = $('<ul>'); 

                            for (const [type, info] of Object.entries(details)) { 
                                const infoElement = $('<li>').text(`${info.이름} - 예상 비용: ${info['예상 비용']}`); 
                                detailsList.append(infoElement); 
                            } 

                            mealElement.append(detailsList); 
                            mealsList.append(mealElement); 
                        } 

                        dayElement.append(mealsList); 
                        dailyContainer.append(dayElement); 
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
                                                                formattedId = `TOID:${obj[day][meal][type][key]}`; 
                                                            } else if (key.startsWith('호텔')) { 
                                                                formattedId = `LODID:${obj[day][meal][type][key]}`; 
                                                            } else if (key.startsWith('식당')) { 
                                                                formattedId = `RESTID:${obj[day][meal][type][key]}`; 
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
            tripId: null, // tripid는 서버에서 설정할 값 
            areacode: requestData.areacode, // 지역코드 
            dcourse: description, // 하루 코스 설명 
            courRegdate: null, // 서버에서 SYSDATE로 설정 
            courUpdate: null  // 서버에서 SYSDATE로 설정 
        }; 
    } 
});
