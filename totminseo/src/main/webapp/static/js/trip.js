$(document).ready(function() {
    let allDailyCourses; // 모든 코스 데이터를 저장할 변수
    let mapVisible = true;
    let map;
    let dailyPaths = {};
    let dailyMarkers = {};
    let currentPolylines = [];

    function getQueryParam(param) {
        const urlParams = new URLSearchParams(window.location.search);
        return urlParams.get(param);
    }

    function initializeMap() {
    const tripId = getQueryParam('tripId');
    if (!tripId) {
        console.error('tripId not found in the URL');
        return;
    }

    map = new kakao.maps.Map(document.getElementById('map'), {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 10
    });

    fetch('/tot/session/id')
        .then(response => response.json())
        .then(data => {
            const memId = data.memId;
            console.log('Session memId:', memId);
            return fetch(`/tot/triplist/trip/${tripId}`); // Trip 정보를 조회하는 API 호출
        })
        .then(response => response.json())
        .then(trip => {
            if (trip) {
                console.log('Trip data:', trip); // 여행 정보 로그
                // 여행 정보를 추가로 처리하거나 화면에 표시할 수 있습니다.
                return fetch(`/tot/triplist/locations/${tripId}`);
            } else {
                throw new Error('Trip not found');
            }
        })
        .then(response => response.json())
        .then(data => {
            allDailyCourses = data;
            return createAllMarkers(allDailyCourses);
        })
        .then(() => {
            displayCourses(allDailyCourses);
            updateMapDisplay();
        })
        .catch(error => console.error('Error:', error));
}

   function fetchImage(name, address) {
	    // 전체 이름으로 먼저 검색
	    return searchImage(name)
	        .then(url => {
	            if (url) return url;
	            
	            // 전체 이름으로 검색 실패 시, 이름의 첫 단어로 검색
	            const firstWord = name.split(' ')[0];
	            return searchImage(firstWord);
	        })
	        .then(url => {
	            if (url) return url;
	            return searchImage(address);
	        })
	        .then(url => {
	            if (url) return url;
	            return 'default-image.jpg';
	        })
	        .catch(error => {
	            console.error('Error in image search:', error);
	            return 'default-image.jpg';
	        });
	}

	function searchImage(query) {
	    return fetch(`https://dapi.kakao.com/v2/search/image?query=${encodeURIComponent(query)}&size=1`, {
	        headers: {
	            'Authorization': `KakaoAK b46016897768df03faee0171297891f2`
	        }
	    })
	    .then(response => response.json())
	    .then(data => {
	        if (data.documents && data.documents.length > 0) {
	            console.log(`Image found for query: ${query}`);
	            return data.documents[0].thumbnail_url;
	        }
	        console.log(`No image found for query: ${query}`);
	        return null;
	    })
	    .catch(error => {
	        console.error(`Error searching image for query ${query}:`, error);
	        return null;
	    });
	}
	
        // 코스 데이터를 화면에 표시하는 함수
    async function displayCourses(dailyCourses) {
        const coursesContainer = $('#courses-container');
        const courseButtons = $('#course-buttons');
        const festivalsContainer = $('#festivals-container');
        
        coursesContainer.empty();
        courseButtons.empty();
        festivalsContainer.empty();

        let dayNumber = 1;
        for (const day of Object.keys(dailyCourses)) {
            const dailyList = dailyCourses[day];
            let timelineHtml = `
                <div class="day-container active" data-day="${dayNumber}">
                    <div class="datanav1">
                        <div class="date1">${dayNumber}일차</div>
                    </div>
                    <div class="timeline-container">
            `;
            
            for (let index = 0; index < dailyList.length; index++) {
                const item = dailyList[index];
                let itemHtml = '';
                let imageUrl = 'default-image.jpg';

                if (item.toId) {
                    if (item.toImgPath) {
                        imageUrl = item.toImgPath;
                    } else {
                        imageUrl = await fetchImage(item.toName, item.toAddress);
                    }
                    itemHtml = `
                        <div class="timeline-item">
                            <div class="number1">${index + 1}</div>
                            <div class="time-content">
                                <div class="time-date">12:07-12:07</div>
                                <div class="asd123">
                                    <div class="datanav3">
                                        <div class="pcolor">명소</div>
                                        <div class="time-title">${item.toName}</div>
                                    </div>
                                    <div><img class="imgdiv1" src="${imageUrl}" alt=""></div>
                                    <div>${item.toAddress}</div>
                                    <div>${item.toHomePage}</div>
                                </div>
                            </div>
                        </div>
                    `;
                } else if (item.restId) {
                    if (item.restImgPath) {
                        imageUrl = item.restImgPath;
                    } else {
                        imageUrl = await fetchImage(item.restName, item.restAddress);
                    }
                    itemHtml = `
                    		<div class="timeline-item">
                            <div class="number1">${index + 1}</div>
                            <div class="time-content">
                                <div class="time-date">12:07-12:07</div>
                                <div class="asd123">
                                    <div class="datanav3">
                                        <div class="pcolor">식당</div>
                                        <div class="time-title">${item.restName}</div>
                                    </div>
                                    <div><img class="imgdiv1" src="${imageUrl}" alt=""></div>
                                    <div>${item.restAddress}</div>
                                </div>
                            </div>
                        </div>
                    `;
                } else if (item.lodId) {
                    itemHtml = `
                        <div class="timeline-item">
                            <div class="number1">${index + 1}</div>
                            <div class="time-content">
                                <div class="time-date">12:07-12:07</div>
                                <div class="asd123">
                                    <div class="datanav3">
                                        <div class="pcolor">숙소</div>
                                        <div class="time-title">${item.lodName}</div>
                                    </div>
                                    <div><img class="imgdiv1" src="${item.lodImgPath || 'default-image.jpg'}" alt=""></div>
                                    <div>${item.lodAddress}</div>
                                    <div><a href="${item.lodUrl}" target="_blank">예약하기</a></div>
                                    <div>가격: ${item.lodPrice}</div>
                                </div>
                            </div>
                        </div>
                    `;
                }
                timelineHtml += itemHtml;
            }
            
            timelineHtml += `</div></div>`;
            coursesContainer.append(timelineHtml);
            courseButtons.append(`<button class="day-button" data-day="${dayNumber}">Day ${dayNumber}</button>`);
            dayNumber++;
        }

        // 축제 정보 추가
        const tripMonth = getQueryParam('month');
        const areaCode = getQueryParam('areacode');
        const tripStartDate = getQueryParam('tripStartDate');
        const tripEndDate = getQueryParam('tripEndDate');

        console.log(tripMonth, areaCode, tripStartDate, tripEndDate);

        let festivalsUrl = `/tot/festivals?month=${tripMonth}&areacode=${areaCode}`;

        if (tripStartDate && tripEndDate) {
            festivalsUrl += `&tripStartDate=${tripStartDate}&tripEndDate=${tripEndDate}`;
        }

        fetch(festivalsUrl)
            .then(response => response.json())
            .then(festivals => {
                if (festivals.length > 0) {
                    let festivalsHtml = '<h2>추천 축제</h2><ul>';
                    festivals.forEach(festival => {
                        festivalsHtml += `
                            <li>
                                <h3>${festival.title}</h3>
                                <img src="${festival.firstimage || 'default-image.jpg'}" alt="${festival.title}">
                                <p>${festival.addr1} ${festival.addr2}</p>
                                <p>${festival.eventstartdate} ~ ${festival.eventenddate}</p>
                                <p>${festival.tel}</p>
                                <p>${festival.overviewYN}</p>
                            </li>
                        `;
                    });
                    festivalsHtml += '</ul>';
                    festivalsContainer.append(festivalsHtml);
                } else {
                    festivalsContainer.append('<p>이 달에는 추천할 만한 축제가 없습니다.</p>');
                }
            })
            .catch(error => console.error('Error fetching festivals:', error));
    }


    function createAllMarkers(dailyCourses) {
        const geocoder = new kakao.maps.services.Geocoder();
        const geocodingPromises = [];

        Object.keys(dailyCourses).forEach((day) => {
            const courseList = dailyCourses[day];
            dailyPaths[day] = [];
            dailyMarkers[day] = [];

            courseList.forEach((item, index) => {
                const address = item.restAddress || item.lodAddress || item.toAddress;
                
                const promise = new Promise((resolve) => {
                    if (item.toX && item.toY) {
                        const position = new kakao.maps.LatLng(item.toY, item.toX);
                        createMarker(position, item, index, day);
                        dailyPaths[day][index] = position;
                        resolve();
                    } else if (address) {
                        geocoder.addressSearch(address, function(result, status) {
                            if (status === kakao.maps.services.Status.OK) {
                                const position = new kakao.maps.LatLng(result[0].y, result[0].x);
                                createMarker(position, item, index, day);
                                dailyPaths[day][index] = position;
                            }
                            resolve();
                        });
                    } else {
                        resolve();
                    }
                });
                geocodingPromises.push(promise);
            });
        });

        return Promise.all(geocodingPromises);
    }

    function createMarker(position, item, index, day) {
        const markerImageUrl = `static/image/mapMarker${index + 1}.png`;
        const markerImage = new kakao.maps.MarkerImage(markerImageUrl, new kakao.maps.Size(36, 36));
        const marker = new kakao.maps.Marker({
            position,
            title: item.toName || item.restName || item.lodName,
            image: markerImage
        });
        
        const content = `
            <div style="padding: 5px; width: 200px;">
                <strong>${index + 1}. ${item.toName || item.restName || item.lodName}</strong><br>
                ${item.toAddress || item.restAddress || item.lodAddress}<br>
                ${item.lodPrice ? `가격: ${item.lodPrice}` : ''}
            </div>
        `;
        
        const infowindow = new kakao.maps.InfoWindow({
            content: content,
            zIndex: 1
        });
        
        kakao.maps.event.addListener(marker, 'mouseover', function() {
            infowindow.open(map, marker);
        });
        
        kakao.maps.event.addListener(marker, 'mouseout', function() {
            infowindow.close();
        });

        dailyMarkers[day][index] = marker;
    }

    function updateMapDisplay(selectedDay = null) {
        Object.values(dailyMarkers).flat().forEach(marker => marker.setMap(null));
        currentPolylines.forEach(polyline => polyline.setMap(null));
        currentPolylines = [];

        let bounds = new kakao.maps.LatLngBounds();

        Object.keys(dailyPaths).forEach((day) => {
            if (!selectedDay || selectedDay === parseInt(day)) {
                const path = dailyPaths[day].filter(Boolean); // null 값 제거
                Object.values(dailyMarkers[day]).forEach(marker => {
                    marker.setMap(map);
                    bounds.extend(marker.getPosition());
                });
                
                if (path.length > 1) {
                    const polyline = new kakao.maps.Polyline({
                        path: path,
                        strokeWeight: 5,
                        strokeColor: getColor(parseInt(day) - 1),
                        strokeOpacity: 0.7,
                        strokeStyle: 'solid'
                    });
                    polyline.setMap(map);
                    currentPolylines.push(polyline);
                }
            }
        });

        map.setBounds(bounds);
    }

    function getColor(index) {
        const colors = ['#FF0000', '#00FF00', '#0000FF', '#FFFF00', '#FF00FF', '#00FFFF'];
        return colors[index % colors.length];
    }

    $('#toggle-course-btn').click(function() {
        if (mapVisible) {
            $('#map').addClass('hidden');
            $('.day-button').addClass('hidden');
            $('#courses-container').css('width', '100%');
            $(this).text('지도 보기');
        } else {
            $('#map').removeClass('hidden');
            $('.day-button').removeClass('hidden');
            $('#courses-container').css('width', 'calc(100% - 800px)');
            $(this).text('코스만 보기');
        }
        mapVisible = !mapVisible;
    });

    $('#course-buttons').on('click', '.day-button', function() {
        const selectedDay = $(this).data('day');
        $('.day-button').removeClass('active');
        $(this).addClass('active');
        updateMapDisplay(selectedDay);
    });

    initializeMap();
});
