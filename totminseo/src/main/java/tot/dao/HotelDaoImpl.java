package tot.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tot.domain.Accommodation;

@Repository("HotelDao")
public class HotelDaoImpl implements HotelDao {
	
	private final SqlSession sqlSession;
	
    @Autowired
    public HotelDaoImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
	
	@Override
	public void insertHotel(Accommodation accommodation) throws Exception {
		sqlSession.insert("tot.dao.HotelDao.insertHotel",accommodation);
	}

}
