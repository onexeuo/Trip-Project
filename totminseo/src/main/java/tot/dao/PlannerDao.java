package tot.dao;

import java.util.List;

import tot.domain.CourseDTO;
import tot.domain.Hotel;
import tot.domain.Restaurant;
import tot.domain.Tour;
import tot.domain.Trip;

public interface PlannerDao {

	public abstract List<Hotel> selectHotel(String areacode) throws Exception;

	public abstract List<Restaurant> selectRestaurant(String areacode) throws Exception;
	
	public abstract List<Tour> selectTour(String areacode) throws Exception;
	
	public abstract void insertTrip(Trip trip) throws Exception;
	
	public abstract void insertCourse(CourseDTO courseDTO) throws Exception;
	
	public abstract Integer selectLatestTripId() throws Exception;
	
	public abstract void updateMemberMbt(String memId, String newMbti) throws Exception;
	
}
