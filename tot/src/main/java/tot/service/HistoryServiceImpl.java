package tot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import tot.dao.HistoryDAO;
import tot.domain.HistoryVO;
import tot.exception.ServerException;

@Service
public class HistoryServiceImpl implements HistoryService {

	@Autowired
	HistoryDAO historyDAO;

	@Override
	public void insertTReviewHistory(HistoryVO historyVO) {
		try {
			historyDAO.insertTReviewHistory(historyVO);
		} catch (DataAccessException e) {
			throw new ServerException("여행 후기 글 기록 중 오류 발생", e);
		}
	}

	@Override
	public void insertCommentHistory(HistoryVO historyVO) {
		try {
			historyDAO.insertCommentHistory(historyVO);
		} catch (DataAccessException e) {
			throw new ServerException("여행 후기 댓글 기록 중 오류 발생", e);
		}
	}

	@Override
	public List<HistoryVO> getTReviewHistorysById(int trevId) {
		try {
			return historyDAO.getTReviewHistorysById(trevId);
		} catch (DataAccessException e) {
			throw new ServerException("여행 후기 기록 가져오던 중 오류 발생", e);
		}
	}

}
