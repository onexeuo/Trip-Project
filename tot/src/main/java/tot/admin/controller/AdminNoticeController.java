package tot.admin.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tot.admin.service.AdminNoticeService;
import tot.common.enums.SearchType;
import tot.common.page.PageReqDTO;
import tot.common.page.PageResDTO;
import tot.domain.NoticeVO;

@Controller
@RequestMapping("admin/notice")
public class AdminNoticeController {

	private final AdminNoticeService adminNoticeService;

	public AdminNoticeController(AdminNoticeService adminNoticeService) {
		this.adminNoticeService = adminNoticeService;
	}

	// 공지사항 목록 가져오기
	@GetMapping
	public String getNoticeList(@ModelAttribute PageReqDTO pageReqDTO, Model model) throws Exception {
		if (pageReqDTO.getPage() == 0) {
			pageReqDTO.setPage(1);
		}

		// 페이징 처리된 공지사항 목록 가져오기
		PageResDTO<NoticeVO> pagination = adminNoticeService.findNoticeListWithPaging(pageReqDTO);

		System.out.println("검색 결과: " + pagination);

		// 모델에 공지사항 목록과 페이징 정보를 담아서 JSP로 전달
		model.addAttribute("pagination", pagination);
		model.addAttribute("pageReqDTO", pageReqDTO);

		return "adminNotice"; // JSP 뷰로 이동
	}

	// 공지사항 상세보기 (GET)
	@GetMapping("/detail/{noid}")
	public String getNoticeDetail(@PathVariable int noid, Model model) throws Exception {
		NoticeVO notice = adminNoticeService.getNoticeById(noid);
		model.addAttribute("notice", notice); // 모델에 notice 객체 추가
		return "adminNoticeDetail"; // JSP 파일의 경로
	}

	@GetMapping("/regist")
	public String getNoticeregist(HttpSession session) {
		return "adminNoticeRegist";
	}

	// 공지사항 작성 (POST)
	@PostMapping("/registProc")
	public String insertNotice(@RequestBody NoticeVO notice) {
		adminNoticeService.insertNotice(notice);
		return "redirect:notice"; // JSP로 직접 가지 않고 공지사항 리스트를 로드하는 경로로 리다이렉트
	}

	// 공지사항 수정 페이지로 이동 (GET)
	@GetMapping("/update/{noid}")
	public String updateNoticeForm(@PathVariable int noid, Model model) {
		NoticeVO notice = adminNoticeService.getNoticeById(noid); // noid로 공지사항을 조회
		model.addAttribute("notice", notice); // 조회한 공지사항 데이터를 모델에 추가
		return "noticeUpdate"; // noticeUpdate.jsp로 이동
	}

	// 공지사항 수정 (POST 혹은 PUT)
	@PutMapping("/{noid}")
	public ResponseEntity<String> updateNotice(@PathVariable int noid, @RequestBody NoticeVO notice) {
		// notice.setNoid(noid);
		adminNoticeService.updateNotice(notice);
		return ResponseEntity.ok("공지사항 수정 완료");
	}

	// 공지사항 삭제 (DELETE)
	@DeleteMapping("/{noid}")
	public ResponseEntity<String> deleteNotice(@PathVariable int noid) {
		try {
			adminNoticeService.deleteNotice(noid);
			return ResponseEntity.ok("삭제되었습니다.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	// 공지사항 검색 (GET)
	@GetMapping("/search")
	@ResponseBody
	public List<NoticeVO> searchNotice(@RequestParam("searchType") String searchType,
			@RequestParam("keyword") String keyword, @RequestParam(value = "page", defaultValue = "1") int page)
			throws Exception {
		PageReqDTO pageReqDTO = new PageReqDTO();
		pageReqDTO.setSearchType(SearchType.valueOf(searchType)); // 검색 유형 설정
		pageReqDTO.setSearch(keyword); // 검색어 설정
		pageReqDTO.setPage(page); // 페이지 설정

		// 페이징 처리된 공지사항 목록 가져오기
		PageResDTO<NoticeVO> pagination = adminNoticeService.findNoticeListWithPaging(pageReqDTO);

		return pagination.getPostList(); // 공지사항 목록 반환
	}
}
