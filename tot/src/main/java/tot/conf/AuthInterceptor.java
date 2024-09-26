package tot.conf;

import static tot.common.Constants.*;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import tot.domain.MemberVO;

@Component
public class AuthInterceptor implements HandlerInterceptor {

	// 일반 URL 경로
	private static final List<String> EXCLUDED_PATHS = Arrays.asList(BASIC_URL, URL_MAIN, URL_LOGIN,
			BASIC_URL + URL_ALL_TREVIEW, URL_MEMBER_CHECKLOGIN);

	// ID값을 갖는 경로
	private static final List<Pattern> EXCLUDED_PATTERNS = Arrays.asList(PATTERN_ALL_TREVIEW,
			PATTERN_ALL_DETAIL_TREVIEW);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		MemberVO member = (MemberVO) request.getSession().getAttribute("member");

		if (member == null) {
			String requestURI = request.getRequestURI();
			boolean isExcluded = EXCLUDED_PATTERNS.stream().anyMatch(pattern -> pattern.matcher(requestURI).matches());

			// 예외처리할 경로
			if (EXCLUDED_PATHS.contains(requestURI) || isExcluded) {
				return true;
			}

			// 로그인 페이지로 리다이렉트
			response.sendRedirect(request.getContextPath() + "/login");
			return false;
		}

		// 로그인한 경우, 요청을 계속 진행
		return true;
	}

}
