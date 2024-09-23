package tot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import tot.domain.LodgingDTO;
import tot.domain.RestaurantDTO;
import tot.domain.TourDTO;
import tot.domain.CourseDTO;

public interface CourseDao {
    TourDTO getTour(String toid);
    RestaurantDTO getRestaurant(int restid);
    LodgingDTO getLodging(int lodid);
    List<CourseDTO> getCourse(int tripid);  
}
