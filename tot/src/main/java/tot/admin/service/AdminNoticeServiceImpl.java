package tot.admin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import tot.admin.dao.AdminNoticeDao;
import tot.common.page.PageDTO;
import tot.common.page.PageReqDTO;
import tot.common.page.PageResDTO;
import tot.domain.NoticeVO;

@Service
public class AdminNoticeServiceImpl implements AdminNoticeService {

	private static final Logger logger = LoggerFactory.getLogger(AdminNoticeServiceImpl.class);

	@Autowired
	private DataSourceTransactionManager transactionManager;

	@Autowired
	private AdminNoticeDao adminNoticeDao;

	@Override
	public List<NoticeVO> noticeList() {
		return adminNoticeDao.noticeList();
	}

	@Override
	public PageResDTO<NoticeVO> findNoticeListWithPaging(PageReqDTO pageReqDTO) {

		PageDTO pageDTO = new PageDTO(pageReqDTO); // PageReqDTO 기반의 DTO 생성

		int totalNoticeCount = adminNoticeDao.selectNoticeTotalCount(pageDTO); // 전체 공지사항 수 조회

		// 검색 및 페이징 처리된 공지사항 목록 조회
		List<NoticeVO> postList = adminNoticeDao.noticeListWithPaging(pageDTO);

		// PageResDTO에 데이터와 페이징 정보 포함해서 반환
		return new PageResDTO<>(totalNoticeCount, pageReqDTO.getPage(), postList);
	}

	@Override
	public NoticeVO getNoticeById(int noid) {
		return adminNoticeDao.getNoticeById(noid);
	}

	@Override
	public void insertNotice(NoticeVO notice) {
		adminNoticeDao.insertNotice(notice); // DAO 계층으로 데이터 전송
	}

	@Override
	public void deleteNotice(int noid) {
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			int result = adminNoticeDao.deleteNotice(noid);
			if (result == 0) {
				throw new Exception("공지사항을 찾을 수 없습니다."); // 삭제 실패
			}
			transactionManager.commit(status);
		} catch (Exception e) {
			transactionManager.rollback(status);
			logger.error("공지사항 삭제 실패: " + e.getMessage(), e);
		}
	}

	@Override
	public void updateNotice(NoticeVO notice) {
		adminNoticeDao.updateNotice(notice);
	}

}
