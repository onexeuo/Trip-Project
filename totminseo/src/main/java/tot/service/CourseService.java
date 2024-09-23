package tot.service;

import java.util.List;
import java.util.Map;

public interface CourseService {

	public abstract Map<Integer, List<Object>> getDailyCourseByTripId(int tripId);

	public abstract List<String> extractIdsFromDcourse(String dcourse);

}
