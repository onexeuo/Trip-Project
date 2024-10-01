package tot.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tot.dao.CourseDao;
import tot.domain.CourseDTO;
import tot.domain.CourseResDTO;
import tot.domain.LodgingDTO;
import tot.domain.RestaurantDTO;
import tot.domain.TourDTO;

@Service
public class CourseServiceImpl implements CourseService {

	private CourseDao courseDao;

	public CourseServiceImpl(CourseDao courseDao) {
		this.courseDao = courseDao;
	}

	@Override
	public Map<Integer, List<Object>> getDailyCourseByTripId(int tripId) {
		// 1. Trip ID에 맞는 코스 목록 가져오기 (courid 순으로 정렬됨)
		System.out.println("Fetching courses for tripId: " + tripId);

		List<CourseDTO> courses = courseDao.getCourse(tripId);
		System.out.println("Courses fetched: " + courses);
		// 2. DCOURSE 값을 파싱하여 각 코스를 일차별로 나누기
		Map<Integer, List<Object>> dailyCourses = new LinkedHashMap<>();

		int dayCounter = 1; // 일차 번호를 1부터 시작

		for (CourseDTO course : courses) {
			String dcourse = course.getdCourse();

			// DCOURSE 값에서 LODID, RESTID, TOID 추출
			List<String> idsList = extractIdsFromDcourse(dcourse);

			// 해당 일차에 코스를 추가
			dailyCourses.putIfAbsent(dayCounter, new ArrayList<>()); // 순차적으로 일차 할당
			List<Object> dailyList = dailyCourses.get(dayCounter);

			// 각 ID를 기반으로 데이터 조회 및 추가
			for (String id : idsList) {
				String[] parts = id.split(":");
				String type = parts[0];
				int idValue = Integer.parseInt(parts[1]);

				if ("TOID".equals(type)) {
					TourDTO tour = courseDao.getTour(String.valueOf(idValue));
					if (tour != null) {
						dailyList.add(tour);
					}
				} else if ("RESTID".equals(type)) {
					RestaurantDTO restaurant = courseDao.getRestaurant(idValue);
					if (restaurant != null) {
						dailyList.add(restaurant);
					}
				} else if ("LODID".equals(type)) {
					LodgingDTO lodging = courseDao.getLodging(idValue);
					if (lodging != null) {
						dailyList.add(lodging);
					}
				}
			}

			dayCounter++; // 다음 일차 번호로 증가
		}
		System.out.println(dailyCourses);
		return dailyCourses;
	}

	@Override
	// DCOURSE 파싱 메서드
	public List<String> extractIdsFromDcourse(String dcourse) {
		Pattern pattern = Pattern.compile("(TOID|RESTID|LODID):(\\d+)");
		Matcher matcher = pattern.matcher(dcourse);

		List<String> idsList = new ArrayList<>();
		while (matcher.find()) {
			String key = matcher.group(1);
			String value = matcher.group(2);
			idsList.add(key + ":" + value);
		}
		return idsList;
	}

	@Override
	public CourseDTO getCourseById(String courseId) {
		return courseDao.getCourseById(courseId);
	}

	@Override
	public List<CourseDTO> getCourseDetailsByTripId(int tripId) {
		List<CourseDTO> courseList = courseDao.getCourseByTripId(tripId);
		courseList.forEach(this::setCourseDetails);
		return courseList;
	}

	/**
	 * 주어진 코스에 대한 세부 정보를 설정합니다.
	 *
	 * @param course 세부 정보를 설정할 코스
	 */
	private void setCourseDetails(CourseDTO course) {
		List<CourseResDTO> courseDetails = new ArrayList<>();
		String[] courseTypes = course.getdCourse().split(",");

		for (String courseType : courseTypes) {
			CourseResDTO courseDetail = getCourseDetail(courseType);
			courseDetails.add(courseDetail);
		}

		course.setCourseDetail(courseDetails);
	}

	/**
	 * 유형과 ID에 따라 코스 세부 정보를 조회합니다.
	 *
	 * @param courseType 코스 유형과 ID를 포함하는 문자열
	 * @return 해당 코스의 세부 정보
	 */
	private CourseResDTO getCourseDetail(String courseType) {
		String[] typeAndId = courseType.split(":");
		String type = typeAndId[0];
		int id = Integer.parseInt(typeAndId[1]);
		return courseDao.getCourseDetailsById(type, id);
	}

	@Override
	public List<LodgingDTO> selectHotel(String areacode) throws Exception {
		return courseDao.selectHotel(areacode);
	}

	@Override
	public List<RestaurantDTO> selectRestaurant(String areacode) throws Exception {
		return courseDao.selectRestaurant(areacode);
	}

	@Override
	public List<TourDTO> selectTour(String areacode) throws Exception {
		return courseDao.selectTour(areacode);
	}

	@Transactional
	@Override
	public void updateDcourses(List<Integer> courIds, List<String> dcourses, Long tripId) {
		try {
			for (int i = 0; i < courIds.size(); i++) {
				Integer courId = courIds.get(i);
				String dcourse = dcourses.get(i);

				System.out.println("Updating courId: " + courId + ", dcourse: " + dcourse + ", tripId: " + tripId);

				courseDao.updateDcourse(courId, dcourse, tripId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e; // 예외를 다시 던져 트랜잭션을 롤백하도록 함
		}
	}
}
