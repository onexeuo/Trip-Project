package tot.util;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import tot.domain.MemberVO;
import tot.exception.ErrorCode;
import tot.exception.ValidationException;

public class MemberUtil {

	public static MemberVO getAuthenticatedMember() {
		// RequestContextHolder에서 현재 RequestAttributes 가져오기
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

		// RequestAttributes에서 HttpSession 가져오기
		HttpSession session = attr.getRequest().getSession();

		MemberVO member = (MemberVO) session.getAttribute("member");

		// member가 null인 경우 빈 MemberVO 반환
		if (member == null) {
			return new MemberVO(); // 빈 MemberVO 객체 반환
		}

		return member;
	}

	// 인증된 회원이 존재하지 않으면 예외를 던지는 메서드
	public static MemberVO isAuthenticatedMember() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();

		MemberVO member = (MemberVO) session.getAttribute("member");

		if (member == null) {
			throw new ValidationException(ErrorCode.NOT_FOUND_MEMBERID);
		}

		return member;
	}

	// 인증된 회원이 존재하는지 여부를 boolean으로 반환하는 메서드
	public static boolean isMemberLoggedIn() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();

		MemberVO member = (MemberVO) session.getAttribute("member");
		if (member == null) {
			return false;
		}

		return true;
	}

}
