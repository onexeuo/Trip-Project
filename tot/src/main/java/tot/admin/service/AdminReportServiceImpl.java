package tot.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import tot.admin.dao.AdminReportDAO;
import tot.common.page.PageDTO;
import tot.common.page.PageReqDTO;
import tot.common.page.PageResDTO;
import tot.domain.ReportDTO;
import tot.exception.ServerException;

@Service
public class AdminReportServiceImpl implements AdminReportService {

	@Autowired
	private AdminReportDAO adminReportDAO;

	@Override
	public PageResDTO<ReportDTO> findReportListWithPaging(PageReqDTO pageReqDTO, String boardId) {
		try {
			PageDTO pageDTO = new PageDTO(pageReqDTO, boardId);
			int totalTReviewCount = adminReportDAO.selectTotalReportCount(pageDTO);

			List<ReportDTO> reportList = adminReportDAO.findReportListWithPaging(pageDTO);

			return new PageResDTO<>(totalTReviewCount, pageReqDTO.getPage(), reportList);
		} catch (DataAccessException e) {
			throw new ServerException("여행 게시물 신고 목록 정보 가져오던 중 데이터베이스 오류 발생", e);
		}
	}

	@Override
	public void updateReportStatus(String status, List<Integer> reportIds) {
		try {
			adminReportDAO.updateReportStatus(status, reportIds);
		} catch (DataAccessException e) {
			throw new ServerException("여행 신고 상태 업데이트 중 오류 발생", e);
		}

	}

}
