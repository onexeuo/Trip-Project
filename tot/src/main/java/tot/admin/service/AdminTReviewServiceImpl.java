package tot.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import tot.admin.dao.AdminTReviewDAO;
import tot.common.page.PageDTO;
import tot.common.page.PageReqDTO;
import tot.common.page.PageResDTO;
import tot.domain.TReviewResDTO;
import tot.exception.ServerException;

@Service
public class AdminTReviewServiceImpl implements AdminTReviewService {

	@Autowired
	private AdminTReviewDAO adminTReviewDAO;

	@Override
	public PageResDTO<TReviewResDTO> findTReviewListWithPaging(PageReqDTO pageReqDTO, String boardId) {
		try {
			PageDTO pageDTO = new PageDTO(pageReqDTO, boardId);

			int totalTReviewCount = adminTReviewDAO.selectTotalTReviewCount(pageDTO);

			List<TReviewResDTO> postList = adminTReviewDAO.selectTReviewListWithPaging(pageDTO);
			return new PageResDTO<>(totalTReviewCount, pageReqDTO.getPage(), postList);
		} catch (DataAccessException e) {
			throw new ServerException("여행 게시물 목록 정보 가져오던 중 데이터베이스 오류 발생", e);
		}
	}

	@Override
	public void updateTReviewStatus(String status, List<Integer> trevIds) {
		try {
			adminTReviewDAO.updateTReviewStatus(status, trevIds);
		} catch (DataAccessException e) {
			throw new ServerException("여행 게시물 상태 업데이트 중 오류 발생", e);
		}
	}

	@Override
	public TReviewResDTO getTReviewById(int trevId) {
		try {
			return adminTReviewDAO.getTReviewById(trevId);
		} catch (DataAccessException e) {
			throw new ServerException("여행 게시물 상세 정보 가져오던 중 오류 발생", e);
		}
	}

}
