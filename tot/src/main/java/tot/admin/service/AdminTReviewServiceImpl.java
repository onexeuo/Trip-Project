package tot.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tot.admin.dao.AdminTReviewDao;
import tot.common.page.PageDTO;
import tot.common.page.PageReqDTO;
import tot.common.page.PageResDTO;
import tot.domain.TReviewResDTO;

@Service
public class AdminTReviewServiceImpl implements AdminTReviewService {

	private final AdminTReviewDao adminTReviewDAO;

	public AdminTReviewServiceImpl(AdminTReviewDao adminTReviewDAO) {
		this.adminTReviewDAO = adminTReviewDAO;
	}

	/**
	 * 페이지네이션된 여행 후기 목록을 조회합니다.
	 *
	 * @param pageReqDTO 페이지 요청 데이터 전송 객체
	 * @param boardId    게시판 ID
	 * @return 페이지네이션된 여행 후기 응답 객체
	 */
	@Override
	public PageResDTO<TReviewResDTO> findTReviewListWithPaging(PageReqDTO pageReqDTO, int boardId) {
		PageDTO pageDTO = new PageDTO(pageReqDTO, boardId);
		int totalTReviewCount = adminTReviewDAO.selectTotalTReviewCount(pageDTO);

		List<TReviewResDTO> postList = adminTReviewDAO.selectTReviewListWithPaging(pageDTO);
		return new PageResDTO<>(totalTReviewCount, pageReqDTO.getPage(), postList);
	}

	/**
	 * 여행 후기 상태를 업데이트합니다.
	 *
	 * @param status  상태 (예: 활성화/비활성화)
	 * @param trevIds 업데이트할 여행 후기 ID 목록
	 */
	@Override
	public void updateTReviewStatus(String status, List<Integer> trevIds) {
		adminTReviewDAO.updateTReviewStatus(status, trevIds);
	}

	/**
	 * 특정 여행 후기를 ID로 조회합니다.
	 *
	 * @param trevId 여행 후기 ID
	 * @return 여행 후기 응답 데이터 전송 객체
	 */
	@Override
	public TReviewResDTO getTReviewById(int trevId) {
		return adminTReviewDAO.getTReviewById(trevId);
	}

}
