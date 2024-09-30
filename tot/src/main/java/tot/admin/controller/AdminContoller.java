package tot.admin.controller;

import static tot.common.Constants.PAGE_ADMIN_LOGIN;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tot.common.enums.Flag;
import tot.common.page.PageReqDTO;
import tot.common.page.PageResDTO;
import tot.domain.MemBanHistoryDTO;
import tot.domain.MemberVO;
import tot.service.EmailService;
import tot.service.MemBanHistoryService;
import tot.service.MemberService;

@Controller
@RequestMapping("/admin")
public class AdminContoller {

	private final MemberService memberService;
	private final EmailService emailService;
	private final MemBanHistoryService memBanHistoryService;

	public AdminContoller(MemberService memberService, EmailService emailService,
			MemBanHistoryService memBanHistoryService) {
		this.memberService = memberService;
		this.emailService = emailService;
		this.memBanHistoryService = memBanHistoryService;
	}

	// 여행 목록 화면 호출
	@GetMapping("/adminLogin")
	public String getTripList(HttpSession session) {
		return PAGE_ADMIN_LOGIN;
	}

	@GetMapping("/adminUser")
	public String showAllMembers(@RequestParam(defaultValue = "1") int page,
			@RequestParam(required = false) String search, @RequestParam(required = false) String status, Model model) {
		System.out.println("페이지: " + page);
		System.out.println("검색어: " + search);
		System.out.println("상태: " + status);

		PageReqDTO pageReqDTO = new PageReqDTO();
		pageReqDTO.setPage(page);
		pageReqDTO.setSearch(search); // 검색어 설정

		// 상태가 주어지면 Flag로 변환
		if (status != null && !status.isEmpty()) {
			pageReqDTO.setActivateFlag(Flag.valueOf(status));
		}

		PageResDTO<MemberVO> pageResDTO = memberService.getAllMembers(pageReqDTO); // 수정된 메서드 호출
		model.addAttribute("members", pageResDTO.getPostList()); // postList 전달
		model.addAttribute("pageResDTO", pageResDTO); // 페이지 정보 전달
		model.addAttribute("search", search); // 검색어 전달
		model.addAttribute("status", status); // 상태 전달
		System.out.println(pageResDTO);
		return "adminUser"; // adminUser.jsp로 연결
	}

	// 제재 페이지 호출
	@GetMapping("/banUser")
	public String getBanHistory(@RequestParam String id, Model model) {
		System.out.println(id);

		// 회원 기본 정보 가져오기
		MemberVO member = memberService.findMemberByMemId(id);
		System.out.println("멤버: " + member);
		if (member == null) {
			// 로그에 에러 메시지 추가
			System.out.println("해당 ID의 회원을 찾을 수 없습니다: " + id);
			model.addAttribute("error", "해당 ID의 회원을 찾을 수 없습니다.");
			return "/tot/"; // 오류 페이지로 리다이렉트하거나 적절한 페이지 반환
		}

		model.addAttribute("member", member);
		System.out.println(model.addAttribute("member", member));

		List<MemBanHistoryDTO> banHistoryList = memBanHistoryService.getBanHistoryByMemId(id);
		System.out.println(banHistoryList);
		if (banHistoryList == null || banHistoryList.isEmpty()) {
			System.out.println("해당 ID의 제재 히스토리가 없습니다: " + id);
			model.addAttribute("banHistoryList", new ArrayList<>()); // 빈 리스트로 설정
		} else {
			model.addAttribute("banHistoryList", banHistoryList);
		}

		return "banUser"; // JSP 경로
	}

	@PostMapping("/banUserProc")
	public ResponseEntity<String> banUser(@RequestParam String id, @RequestParam String email,
			@RequestParam String reason) {
		// 회원 제재 상태 업데이트 및 MEMBAN_HISTORY에 기록 추가
		memberService.applyBan(id, "M02", reason); // 상태 업데이트와 히스토리 기록

		// 이메일 발송
		if (email != null) {
			emailService.sendBanEmail(email, reason);
		}

		return ResponseEntity.ok("제재 완료");
	}

	@PostMapping("/liftUserProc")
	public ResponseEntity<String> liftUser(@RequestParam String id, @RequestParam String reason) {
		// 회원 상태 업데이트 (M01로 변경)
		Map<String, Object> params = new HashMap<>();
		params.put("memId", id);
		params.put("memberStatus", "M01");
		params.put("banStart", null); // 시작 시간 제거
		params.put("banEnd", null); // 종료 시간 제거

		memberService.updateMemberStatus(params);

		// 제재 해제 사유와 시간 기록
		memBanHistoryService.updateLiftHistory(id, reason);

		return ResponseEntity.ok("제재 해제 완료");
	}

	// 로그인 처리
	@PostMapping("/adminLoginProc")
	public String login(@RequestParam("id") String id, @RequestParam("pass") String password, HttpSession session) {
		System.out.println("Received ID: " + id);
		System.out.println("Received Password: " + password);

		// 예시 계정 정보
		String correctId = "admin";
		String correctPassword = "1234";

		if (id.equals(correctId) && password.equals(correctPassword)) {
			System.out.println("Login successful for user: " + id);
			session.setAttribute("user", id);
			return "redirect:/admin/adminUser"; // 로그인 성공 후 리다이렉트 확인
		} else {
			System.out.println("Login failed for user: " + id + " with password: " + password);
			return "redirect:/admin/adminLogin?error=true"; // 실패 시 리다이렉트 확인
		}

	}

	// 로그아웃 처리
	@GetMapping("/adminLogout")
	public String logout(HttpSession session) {
		session.invalidate(); // 세션 무효화
		return "redirect:/admin/adminLogin"; // 로그인 페이지로 리다이렉트
	}

}
