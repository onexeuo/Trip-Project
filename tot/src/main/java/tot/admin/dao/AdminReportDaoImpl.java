package tot.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import tot.common.page.PageDTO;
import tot.domain.ReportDTO;

@Repository
public class AdminReportDaoImpl implements AdminReportDao {

	private static final String NAMESPACE = "tot.dao.AdminReportDao";
	private final SqlSession sqlSession;

	public AdminReportDaoImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int selectTotalReportCount(PageDTO pageDTO) {
		return sqlSession.selectOne(NAMESPACE + ".selectTotalReportCount", pageDTO);
	}

	@Override
	public List<ReportDTO> findReportListWithPaging(PageDTO pageDTO) {
		return sqlSession.selectList(NAMESPACE + ".findReportListWithPaging", pageDTO);
	}

	@Override
	public void updateReportStatus(String status, List<Integer> reportIds) {
		Map<String, Object> params = new HashMap<>();
		params.put("status", status);
		params.put("reportIds", reportIds);

		sqlSession.update(NAMESPACE + ".updateReportStatus", params);
	}

}
