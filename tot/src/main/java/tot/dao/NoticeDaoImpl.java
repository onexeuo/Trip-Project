package tot.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tot.common.page.PageDTO;
import tot.domain.Notice;

@Repository
public class NoticeDaoImpl implements NoticeDao {
	
	@Autowired
	SqlSession sqlSession;
	
	private static final String NAMESPACE = "tot.dao.NoticeDao";

	@Override
	public List<Notice> noticeList() throws Exception {
	    return sqlSession.selectList("tot.dao.NoticeDao.NoticeList");
	}
	
    @Override
    public Notice getNoticeById(int noid) throws Exception {
        return sqlSession.selectOne("tot.dao.NoticeDao.getNoticeById", noid);
    }
    
    @Override
    public void insertNotice(Notice notice) throws Exception {
        sqlSession.insert("tot.dao.NoticeDao.insertNotice", notice); // MyBatis 쿼리 호출
    }
    
    @Override
    public int deleteNotice(int noid) throws Exception {
    	 return sqlSession.delete("tot.dao.NoticeDao.deleteNotice", noid);
    }
    
    @Override
    public void updateNotice(Notice notice) throws Exception {
        sqlSession.update("tot.dao.NoticeDao.updateNotice", notice);
    }
    
    
    @Override
    public List<Notice> noticeListWithPaging(PageDTO pageDTO) throws Exception {
        return sqlSession.selectList(NAMESPACE + ".noticeListWithPaging", pageDTO);
    }

    @Override
    public int selectNoticeTotalCount(PageDTO pageDTO) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".selectNoticeTotalCount", pageDTO);
    }

}
