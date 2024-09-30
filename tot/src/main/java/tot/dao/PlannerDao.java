package tot.dao;

import java.util.List;

import tot.domain.CourseDTO;
import tot.domain.Hotel;
import tot.domain.Restaurant;
import tot.domain.Tour;
import tot.domain.Trip;

public interface PlannerDao {

	List<Hotel> selectHotel(String areacode);

	List<Restaurant> selectRestaurant(String areacode);

	List<Tour> selectTour(String areacode);

	void insertTrip(Trip trip);

	void insertCourse(CourseDTO courseDTO);

	Integer selectLatestTripId();

	void updateMemberMbt(String memId, String newMbti);

}
