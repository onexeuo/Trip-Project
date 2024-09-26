package tot.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

	// 댓글 공통 응답 메소드
	public static ResponseEntity<Map<String, String>> createCommentResponse(String message, String updatedDate) {

		Map<String, String> response = new HashMap<>();
		response.put("message", message);
		response.put("updatedDate", updatedDate);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	// 여행 후기 공통 응답 메소드
	public static ResponseEntity<Map<String, String>> createTReviewResponse(String message) {

		Map<String, String> response = new HashMap<>();
		response.put("message", message);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
