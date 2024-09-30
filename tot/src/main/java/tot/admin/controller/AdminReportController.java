package tot.admin.controller;

import static tot.common.Constants.PAGE_ADMIN_TREVIEW_REPORT;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import tot.admin.service.AdminReportService;
import tot.common.page.PageReqDTO;
import tot.common.page.PageResDTO;
import tot.domain.ReportDTO;
import tot.util.ResponseUtil;

@Controller
@RequestMapping("/admin/report/{boardId}")
public class AdminReportController {

	private final AdminReportService adminReportService;

	public AdminReportController(AdminReportService adminReportService) {
		this.adminReportService = adminReportService;
	}

	// 신고 관리 화면 이동
	@GetMapping("/{page}")
	public String showAdminReport(@PathVariable int boardId, @ModelAttribute PageReqDTO pageReqDTO, Model model) {
		PageResDTO<ReportDTO> pagination = adminReportService.findReportListWithPaging(pageReqDTO, boardId);

		model.addAttribute("boardId", boardId);
		model.addAttribute("pagination", pagination);

		return PAGE_ADMIN_TREVIEW_REPORT;
	}

	// 신고 상태 업데이트 처리
	@PostMapping("/{status}")
	public ResponseEntity<Map<String, String>> updateReportStatus(@PathVariable String status,
			@RequestBody List<Integer> reportIds) {
		try {
			adminReportService.updateReportStatus(status, reportIds);

			return ResponseUtil.createTReviewResponse("신고가 처리되었습니다.");
		} catch (IllegalStateException e) {
			return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
		}

	}

}
