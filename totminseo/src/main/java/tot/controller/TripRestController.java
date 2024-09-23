package tot.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tot.domain.TripVO;
import tot.service.TripService;
import tot.service.CourseService;

@RestController
@RequestMapping("/triplist")
public class TripRestController {

    @Autowired
    private TripService tripService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/trips")
    public ResponseEntity<List<TripVO>> getTripsByMemId(HttpSession session) {
        String memId = (String) session.getAttribute("memId");
        if (memId != null) {
            List<TripVO> trips = tripService.getTripsByMemId(memId);
            return ResponseEntity.ok(trips);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/locations/{tripId}")
    public ResponseEntity<Map<Integer, List<Object>>> getCourseLocations(@PathVariable int tripId) {
        Map<Integer, List<Object>> dailyCourses = courseService.getDailyCourseByTripId(tripId);
        return ResponseEntity.ok(dailyCourses);
    }

    // 새로운 메소드 추가
    @GetMapping("/trip/{tripId}")
    public ResponseEntity<TripVO> getTripById(@PathVariable int tripId) {
        TripVO trip = tripService.getTripById(tripId);
        if (trip != null) {
            return ResponseEntity.ok(trip);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
