        $(document).ready(function() { 
            const requestData = JSON.parse(sessionStorage.getItem('requestData')); 
            let tramt = requestData.tramt.replace(/,/g, ''); 

            if (requestData) { 
                console.log("세션에서 불러온 데이터:", requestData); 
                $('.pdate').text(requestData.trstadate + "-" + requestData.trenddate);
                $('.datenav1').text(requestData.trstadate);
            } 

            let dataLoaded = { 
                hotels: false, 
                chatdata: false 
            }; 

            let ids = {}; 

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
                            courses: courses 
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
                            ids = extractIds(parsedContent); 
                            console.log("추출된 IDs:", ids); 

                            const timelineContainer = $('.timeline-container');
                            
                            for (const [day, activities] of Object.entries(parsedContent)) { 
                                for (const [meal, details] of Object.entries(activities)) { 
                                    const mealElement = $('<div class="timeline-item"></div>'); 
                                    const timeContent = $('<div class="time-content"></div>'); 

                                    details.forEach(info => {
                                        const infoElement = $('<div class="time-title"></div>').text(`${info.이름} - 예상 비용: ${info['예상 비용']}`); 
                                        timeContent.append(infoElement);
                                    });

                                    mealElement.append(timeContent);
                                    timelineContainer.append(mealElement);
                                }
                            } 

                            dataLoaded.chatdata = true; 
                            checkAllDataLoaded(); 
                        } else { 
                            console.error("파싱된 content가 없습니다."); 
                        } 
                    } else { 
                        console.error("잘못된 데이터 구조:", data); 
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

                let idsByDay = {}; 
                for (const day in data) { 
                    if (data.hasOwnProperty(day)) { 
                        idsByDay[day] = []; 
                        for (const meal in data[day]) { 
                            if (data[day].hasOwnProperty(meal)) { 
                                for (const type in data[day][meal]) { 
                                    if (data[day][meal].hasOwnProperty(type)) { 
                                        for (const key in data[day][meal][type]) { 
                                            if (data[day][meal][type].hasOwnProperty(key)) { 
                                                if (key.includes('ID') && data[day][meal][type][key] !== 'N/A') { 
                                                    idsByDay[day].push(data[day][meal][type][key]); 
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

            function createCourseDTO(day, description) { 
                return { 
                    tripid: null, 
                    areacode: requestData.areacode, 
                    dcourse: description, 
                    courregdate: null, 
                    courupdate: null 
                }; 
            } 
        }); 