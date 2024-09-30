package tot.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import tot.common.page.PageDTO;
import tot.domain.NoticeVO;

@Repository
public class AdminNoticeDaoImpl implements AdminNoticeDao {

	private static final String NAMESPACE = "tot.admin.dao.AdminNoticeDao";
	private final SqlSession sqlSession;

	public AdminNoticeDaoImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<NoticeVO> noticeList() {
		return sqlSession.selectList("NoticeDao.NoticeList");
	}

	@Override
	public NoticeVO getNoticeById(int noid) {
		return sqlSession.selectOne("NoticeDao.getNoticeById", noid);
	}

	@Override
	public void insertNotice(NoticeVO notice) {
		sqlSession.insert("NoticeDao.insertNotice", notice);
	}

	@Override
	public int deleteNotice(int noid) {
		return sqlSession.delete("NoticeDao.deleteNotice", noid);
	}

	@Override
	public void updateNotice(NoticeVO notice) {
		sqlSession.update("NoticeDao.updateNotice", notice);
	}

	@Override
	public List<NoticeVO> noticeListWithPaging(PageDTO pageDTO) {
		return sqlSession.selectList(NAMESPACE + ".noticeListWithPaging", pageDTO);
	}

	@Override
	public int selectNoticeTotalCount(PageDTO pageDTO) {
		return sqlSession.selectOne(NAMESPACE + ".selectNoticeTotalCount", pageDTO);
	}

}
