package tot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tot.domain.FestivalDTO;
import tot.domain.FestivalVO;
import tot.domain.LodgingDTO;
import tot.domain.RestaurantDTO;
import tot.domain.TourDTO;
import tot.domain.TripDTO;
import tot.domain.UpdateDcourseRequestDTO;
import tot.service.CourseService;
import tot.service.FestivalService;
import tot.service.TripService;

@RestController
@RequestMapping("/triplist")
public class TripRestController {

	private TripService tripService;
	private CourseService courseService;
	private FestivalService festivalService;

	public TripRestController(TripService tripService, CourseService courseService, FestivalService festivalService) {
		this.tripService = tripService;
		this.courseService = courseService;
		this.festivalService = festivalService;
	}

	@GetMapping("/locations/{tripId}")
	public ResponseEntity<Map<Integer, List<Object>>> getCourseLocations(@PathVariable int tripId) {
		Map<Integer, List<Object>> dailyCourses = courseService.getDailyCourseByTripId(tripId);
		return ResponseEntity.ok(dailyCourses);
	}

	@GetMapping("/trip/{tripId}")
	public ResponseEntity<TripDTO> getTripById(@PathVariable int tripId) {
		TripDTO trip = tripService.getTripById(tripId);

		if (trip != null) {
			return ResponseEntity.ok(trip);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/festivals")
	public ResponseEntity<List<FestivalVO>> getFestivals(@RequestParam(required = false) String areacode,
			@RequestParam(required = false) String tripStartDate, @RequestParam(required = false) String tripEndDate) {

		Map<String, Object> params = new HashMap<>();
		params.put("areacode", areacode);

		// 날짜 범위가 주어졌을 때
		if (tripStartDate != null && tripEndDate != null) {
			params.put("tripStartDate", tripStartDate);
			params.put("tripEndDate", tripEndDate);
			List<FestivalDTO> festivalDTOs = festivalService.findFestivalsByDateRange(params);
			List<FestivalVO> festivalVOs = convertDTOsToVOs(festivalDTOs);
			return ResponseEntity.ok(festivalVOs);
		} else if (tripStartDate != null) { // 월 단위 조회
			params.put("tripStartDate", tripStartDate);
			List<FestivalDTO> festivalDTOs = festivalService.findFestivalsByMonth(params);
			List<FestivalVO> festivalVOs = convertDTOsToVOs(festivalDTOs);
			return ResponseEntity.ok(festivalVOs);
		} else {
			return ResponseEntity.badRequest().build(); // 필수 파라미터 부족
		}
	}

	@GetMapping("/locations")
	public ResponseEntity<Map<String, List<?>>> getLocationsByAreaCode(@RequestParam String areacode) {
		try {
			List<LodgingDTO> hotels = courseService.selectHotel(areacode);
			List<RestaurantDTO> restaurants = courseService.selectRestaurant(areacode);
			List<TourDTO> tours = courseService.selectTour(areacode);

			Map<String, List<?>> response = new HashMap<>();
			response.put("hotels", hotels);
			response.put("restaurants", restaurants);
			response.put("tours", tours);

			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).build(); // 예외가 발생하면 500 에러 반환
		}
	}

	@PostMapping("/updateDcourse")
	public ResponseEntity<?> updateDcourse(@RequestBody UpdateDcourseRequestDTO request) {
		List<Integer> courIds = request.getCourIds();
		List<String> dcourses = request.getDcourses();
		Long tripId = request.getTripId();

		if (courIds.size() != dcourses.size()) {
			return ResponseEntity.status(500).build();
		}

		try {
			// 서비스 호출하여 업데이트 수행
			courseService.updateDcourses(courIds, dcourses, tripId);
			return ResponseEntity.ok("ok");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).build();
		}
	}

	// DTO를 VO로 변환하는 메서드
	private List<FestivalVO> convertDTOsToVOs(List<FestivalDTO> dtoList) {
		return dtoList.stream()
				.map(dto -> new FestivalVO(dto.getContentid(), dto.getContenttypeid(), dto.getAreacode(),
						dto.getTitle(), dto.getAddr1(), dto.getAddr2(), dto.getMapx(), dto.getMapy(),
						dto.getFirstimage(), dto.getFirstimage2(), dto.getEventstartdate(), dto.getEventenddate(),
						dto.getTel(), dto.getOverviewYN(), dto.getPlaytime(), dto.getUsetimefestival()))
				.collect(Collectors.toList());
	}
}
