package tot.admin.service;

import java.util.List;

import tot.common.page.PageReqDTO;
import tot.common.page.PageResDTO;
import tot.domain.ReportDTO;

public interface AdminReportService {

	void updateReportStatus(String status, List<Integer> reportIds);

	PageResDTO<ReportDTO> findReportListWithPaging(PageReqDTO dto, String boardId);

}
