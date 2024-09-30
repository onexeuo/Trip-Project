package tot.admin.controller;

import static tot.common.Constants.PAGE_ADMIN_DETAIL_TREVIEW;
import static tot.common.Constants.PAGE_ADMIN_TREVIEW;

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

import tot.admin.service.AdminCommentService;
import tot.admin.service.AdminTReviewService;
import tot.common.page.PageReqDTO;
import tot.common.page.PageResDTO;
import tot.domain.CommentVO;
import tot.domain.CourseDTO;
import tot.domain.HistoryVO;
import tot.domain.MemberVO;
import tot.domain.TReviewResDTO;
import tot.service.CourseService;
import tot.service.HistoryService;
import tot.util.MemberUtil;
import tot.util.ResponseUtil;

@Controller
@RequestMapping("/admin/review/{boardId}")
public class AdminTReviewController {

	private final AdminTReviewService adminTreviewService;
	private final CourseService courseService;
	private final AdminCommentService adminCommentService;
	private final HistoryService historyService;

	public AdminTReviewController(AdminTReviewService adminTreviewService, CourseService courseService,
			AdminCommentService adminCommentService, HistoryService historyService) {
		this.adminTreviewService = adminTreviewService;
		this.courseService = courseService;
		this.adminCommentService = adminCommentService;
		this.historyService = historyService;
	}

	// 게시물 관리 화면 이동
	@GetMapping("/{page}")
	public String showAdminTReview(@PathVariable int boardId, @ModelAttribute PageReqDTO pageReqDTO, Model model) {
		PageResDTO<TReviewResDTO> pagination = adminTreviewService.findTReviewListWithPaging(pageReqDTO, boardId);

		model.addAttribute("boardId", boardId);
		model.addAttribute("pagination", pagination);

		return PAGE_ADMIN_TREVIEW;
	}

	// 게시물 상태 업데이트 처리
	@PostMapping("/{status}")
	public ResponseEntity<Map<String, String>> updateTReviewStatus(@PathVariable String status,
			@RequestBody List<Integer> trevIds) {
		adminTreviewService.updateTReviewStatus(status, trevIds);

		return ResponseUtil.createTReviewResponse("게시물 상태가 업데이트되었습니다.");
	}

	// 게시물 상세 화면 이동
	@GetMapping("/detail/{trevId}/{page}")
	public String showTourReviewDetail(@PathVariable("boardId") int boardId, @ModelAttribute PageReqDTO pageReqDTO,
			@PathVariable("trevId") int trevId, Model model) {
		MemberVO member = MemberUtil.getAuthenticatedMember();

		TReviewResDTO review = adminTreviewService.getTReviewById(trevId);
		List<CourseDTO> courses = courseService.getCourseDetailsByTripId(review.getTripId());
		PageResDTO<CommentVO> comments = adminCommentService.findCommentListWithPaging(pageReqDTO, boardId, trevId);
		List<HistoryVO> historys = historyService.getTReviewHistorysById(trevId);

		model.addAttribute("boardId", boardId);
		model.addAttribute("member", member);
		model.addAttribute("review", review);
		model.addAttribute("courses", courses);
		model.addAttribute("comments", comments);
		model.addAttribute("historys", historys);
		model.addAttribute("historyCount", historys.size());

		return PAGE_ADMIN_DETAIL_TREVIEW;
	}

}
