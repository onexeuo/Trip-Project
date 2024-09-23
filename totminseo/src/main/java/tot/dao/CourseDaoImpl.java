package tot.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tot.domain.LodgingDTO;
import tot.domain.RestaurantDTO;
import tot.domain.TourDTO;
import tot.domain.CourseDTO;

@Repository
public class CourseDaoImpl implements CourseDao {

    private final SqlSession sqlSession;

    @Autowired
    public CourseDaoImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public TourDTO getTour(String toId) {
        return sqlSession.selectOne("tot.dao.CourseDao.getTour", toId);
    }

    @Override
    public RestaurantDTO getRestaurant(int restId) {
        return sqlSession.selectOne("tot.dao.CourseDao.getRestaurant", restId);
    }

    @Override
    public LodgingDTO getLodging(int lodId) {
        return sqlSession.selectOne("tot.dao.CourseDao.getLodging", lodId);
    }

    @Override
    public List<CourseDTO> getCourse(int tripId) {
        return sqlSession.selectList("tot.dao.CourseDao.getCourse", tripId);
    }
}
