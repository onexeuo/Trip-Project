package tot.admin.controller;

import static tot.common.Constants.PAGE_ADMIN_QNA;
import static tot.common.Constants.PAGE_ADMIN_QNA_DETAIL;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tot.admin.service.AdminQnaService;
import tot.common.page.PageReqDTO;
import tot.common.page.PageResDTO;
import tot.domain.MemberVO;
import tot.domain.QnaCommentVO;
import tot.domain.QnaDTO;

@Controller
@RequestMapping("/admin/qna")
public class AdminQnaController {

	private final AdminQnaService adminQnaService;

	public AdminQnaController(AdminQnaService adminQnaService) {
		this.adminQnaService = adminQnaService;
	}

	// 문의글 화면 이동
	@GetMapping("/{page}")
	public String getQnaListWithPaging(@PathVariable int boardId, @ModelAttribute PageReqDTO pageReqDTO, Model model) {
		PageResDTO<QnaDTO> pagination = adminQnaService.findQnaListWithPaging(pageReqDTO, boardId);

		model.addAttribute("boardId", boardId);
		model.addAttribute("pagination", pagination);
		return PAGE_ADMIN_QNA;
	}

	// 문의글 상세 페이지 이동
	@GetMapping("/detail/{qnaId}")
	public String getQnaDetail(@PathVariable("qnaId") int qnaId, Model model) {
		QnaDTO qnaDetail = adminQnaService.getQnaDetail(qnaId);
		List<QnaCommentVO> comments = adminQnaService.getCommentsByQnaId(qnaId);

		model.addAttribute("qnaDetail", qnaDetail);
		model.addAttribute("comments", comments);
		return PAGE_ADMIN_QNA_DETAIL;
	}

	@PostMapping("/addComment")
	public ResponseEntity<String> addComment(@RequestParam("qnaId") Integer qnaId,
			@RequestParam("commentText") String commentText, HttpSession session) {
		MemberVO member = (MemberVO) session.getAttribute("member");
		if (member != null) {
			QnaCommentVO comment = new QnaCommentVO(qnaId, commentText, new Timestamp(System.currentTimeMillis()),
					new Timestamp(System.currentTimeMillis()));

			adminQnaService.insertQnaComment(comment);
			return ResponseEntity.ok("Comment added successfully");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You must be logged in to add a comment.");
		}
	}

	@GetMapping("/comments")
	public ResponseEntity<List<QnaCommentVO>> getQnaComments(@RequestParam("QNAID") int qnaId) {
		List<QnaCommentVO> comments = adminQnaService.getCommentsByQnaId(qnaId);
		return ResponseEntity.ok(comments);
	}

	@PostMapping("/changeStatus")
	public ResponseEntity<Map<String, Object>> changeQnaStatus(@RequestBody Map<String, Object> requestData) {
		int qnaId = (int) requestData.get("qnaId");
		String newStatus = (String) requestData.get("qna_002");

		// 상태 변경을 서비스에 요청
		int result = adminQnaService.updateQnaStatus(qnaId, newStatus);

		Map<String, Object> response = new HashMap<>();
		if (result > 0) {
			response.put("success", true);
		} else {
			response.put("success", false);
		}

		return ResponseEntity.ok(response);
	}

}