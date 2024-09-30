package tot.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tot.admin.dao.AdminReportDao;
import tot.common.page.PageDTO;
import tot.common.page.PageReqDTO;
import tot.common.page.PageResDTO;
import tot.domain.ReportDTO;

@Service
public class AdminReportServiceImpl implements AdminReportService {

	private final AdminReportDao adminReportDAO;

	public AdminReportServiceImpl(AdminReportDao adminReportDAO) {
		this.adminReportDAO = adminReportDAO;
	}

	/**
	 * 페이지네이션된 신고 목록을 조회합니다.
	 *
	 * @param pageReqDTO 페이지 요청 데이터 전송 객체
	 * @param boardId    게시판 ID
	 * @return 페이지네이션된 신고 응답 객체
	 */
	@Override
	public PageResDTO<ReportDTO> findReportListWithPaging(PageReqDTO pageReqDTO, int boardId) {
		PageDTO pageDTO = new PageDTO(pageReqDTO, boardId);
		int totalTReviewCount = adminReportDAO.selectTotalReportCount(pageDTO);

		List<ReportDTO> reportList = adminReportDAO.findReportListWithPaging(pageDTO);

		return new PageResDTO<>(totalTReviewCount, pageReqDTO.getPage(), reportList);
	}

	/**
	 * 신고 상태를 업데이트합니다.
	 *
	 * @param status    상태 (예: 활성화/비활성화)
	 * @param reportIds 업데이트할 신고 ID 목록
	 */
	@Override
	public void updateReportStatus(String status, List<Integer> reportIds) {
		adminReportDAO.updateReportStatus(status, reportIds);
	}

}
