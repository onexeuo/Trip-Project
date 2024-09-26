package tot.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tot.common.page.PageDTO;
import tot.domain.TReviewResDTO;

@Repository
public class AdminTReviewDAOImpl implements AdminTReviewDAO {

	private static final String NAMESPACE = "tot.mapper.AdminTReviewMapper";

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int selectTotalTReviewCount(PageDTO pageDTO) {
		return sqlSession.selectOne(NAMESPACE + ".selectTotalTReviewCount", pageDTO);
	}

	@Override
	public List<TReviewResDTO> selectTReviewListWithPaging(PageDTO pageDTO) {
		return sqlSession.selectList(NAMESPACE + ".selectTReviewListWithPaging", pageDTO);
	}

	@Override
	public void updateTReviewStatus(String status, List<Integer> trevIds) {
		Map<String, Object> params = new HashMap<>();
		params.put("status", status);
		params.put("trevIds", trevIds);

		sqlSession.update(NAMESPACE + ".updateTReviewStatus", params);
	}

	@Override
	public TReviewResDTO getTReviewById(int trevId) {
		return sqlSession.selectOne(NAMESPACE + ".getTReviewById", trevId);
	}

}
