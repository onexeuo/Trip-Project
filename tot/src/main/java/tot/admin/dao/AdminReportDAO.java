package tot.admin.dao;

import java.util.List;

import tot.common.page.PageDTO;
import tot.domain.ReportDTO;

public interface AdminReportDAO {

	int selectTotalReportCount(PageDTO pageDTO);

	List<ReportDTO> findReportListWithPaging(PageDTO pageDTO);

	void updateReportStatus(String status, List<Integer> reportIds);

}
