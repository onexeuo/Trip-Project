package tot.dao;

import java.util.List;

import tot.domain.TripDTO;

public interface TripDao {

	// 특정 사용자의 모든 여행 목록 조회
	List<TripDTO> getTripsByMemId(String memId);

	// 특정 여행 아이디에 대한 여행 정보 조회
	TripDTO getTripById(int tripId);
}
