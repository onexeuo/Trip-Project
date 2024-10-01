package tot.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import tot.common.page.PageDTO;
import tot.common.page.PageReqDTO;
import tot.domain.QnaCommentVO;
import tot.domain.QnaDTO;

@Repository
public class AdminQnaDaoImpl implements AdminQnaDao {

	private static final String NAMESPACE = "tot.admin.dao.AdminQnaDao";
	private final SqlSession sqlSession;

	public AdminQnaDaoImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<QnaDTO> qnaList() {
		return sqlSession.selectList(NAMESPACE + ".qnaList");
	}

	@Override
	public QnaDTO getQnaDetail(int QNAID) {
		return sqlSession.selectOne(NAMESPACE + ".getQnaDetail", QNAID);
	}

	@Override
	public QnaDTO getQna(int QNAID) {
		return sqlSession.selectOne(NAMESPACE + ".getQnaList", QNAID);
	}

	@Override
	public List<QnaDTO> qnaListWithPaging(PageDTO pageDTO) {
		return sqlSession.selectList(NAMESPACE + ".qnaListWithPaging", pageDTO);
	}

	@Override
	public List<QnaDTO> qnaListWithPagingByMemId(PageReqDTO dto, String memId) {
		Map<String, Object> params = new HashMap<>();
		params.put("dto", dto);
		params.put("memId", memId);
		return sqlSession.selectList(NAMESPACE + ".qnaListWithPagingByMemId", params); // memId와 함께 처리
	}

	@Override
	public int selectQnaTotalCount(PageDTO pageDTO) {
		return sqlSession.selectOne(NAMESPACE + ".selectQnaTotalCount", pageDTO);
	}

	@Override
	public List<QnaDTO> getMyQnaList(String memId) {
		return sqlSession.selectList(NAMESPACE + ".getMyQnaList", memId);
	}

	@Override
	public List<QnaCommentVO> getCommentsByQnaId(int qnaId) {
		return sqlSession.selectList(NAMESPACE + ".getCommentsByQnaId", qnaId);
	}

	@Override
	public int insertQnaComment(QnaCommentVO qnaComment) {
		return sqlSession.insert(NAMESPACE + ".insertQnaComment", qnaComment);
	}

	@Override
	public int updateQnaStatus(int qnaId, String newStatus) {
		Map<String, Object> params = new HashMap<>();
		params.put("qnaId", qnaId);
		params.put("newStatus", newStatus);

		return sqlSession.update(NAMESPACE + ".updateQnaStatus", params);
	}

}