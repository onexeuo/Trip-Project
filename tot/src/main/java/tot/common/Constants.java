package tot.common;

import java.util.regex.Pattern;

public class Constants {

	public static final int PAGE_ROW_COUNT = 8; // 페이지당 리뷰 개수
	public static final int PAGE_BLOCK_COUNT = 5; // 블록당 페이지 개수

	// 기본 URL
	public static final String BASIC_URL = "/tot";

	// 메인 페이지 및 URL
	public static final String MAIN = "main";
	public static final String URL_MAIN = BASIC_URL + "/";

	// 로그인 페이지 및 URL
	public static final String PAGE_LOGIN = "login";
	public static final String URL_LOGIN = BASIC_URL + "/login";

	// 회원 페이지 및 URL
	public static final String URL_MEMBER_CHECKLOGIN = BASIC_URL + "/member/checkLogin";

	// 여행 코스 페이지
	public static final String PAGE_TRIP = "trip";

	// 여행 후기 페이지 및 URL
	public static final String PAGE_TREVIEW = "treview";
	public static final String PAGE_WRITE_TREVIEW = "treviewWrite";
	public static final String PAGE_EDIT_TREVIEW = "treviewEdit";
	public static final String PAGE_DETAIL_TREVIEW = "treviewDetail";

	public static final String TREVIEW_BASE_URL = "/review";
	public static final String URL_ALL_TREVIEW = TREVIEW_BASE_URL + "/all/1";
	public static final String URL_MY_TREVIEW = TREVIEW_BASE_URL + "/my/1";

	public static final Pattern PATTERN_ALL_TREVIEW = Pattern.compile("^" + BASIC_URL + "/review/all/\\d+$");
	public static final Pattern PATTERN_ALL_DETAIL_TREVIEW = Pattern
			.compile("^" + BASIC_URL + "/review/all/detail/\\d+$");

	// 관리자 페이지

	// 관리자 후기 게시물 페이지
	public static final String PAGE_ADMIN_TREVIEW = "adminTReview";
	public static final String PAGE_ADMIN_EDIT_TREVIEW = "adminTReviewEdit";
	public static final String PAGE_ADMIN_DETAIL_TREVIEW = "adminTReviewDetail";
	
	// 관리자 후기 댓글 페이지
	public static final String PAGE_ADMIN_TREVIEW_COMMENT = "adminTReviewComment";
	
	// 관리자 후기 신고 페이지
	public static final String PAGE_ADMIN_TREVIEW_REPORT = "adminTReviewReport";
	
	// 생성자를 private으로 설정하여 인스턴스화 방지
	private Constants() {
	}

}
