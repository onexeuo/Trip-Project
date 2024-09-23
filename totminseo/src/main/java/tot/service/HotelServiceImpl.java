package tot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tot.domain.Accommodation;
import tot.dao.HotelDao;

@Service("HotelService")
public class HotelServiceImpl implements HotelService {


	@Autowired
	private HotelDao hotelDao;
	
	@Override
	public void insertHotel(Accommodation accommodation) throws Exception {
		hotelDao.insertHotel(accommodation);
	}

}
