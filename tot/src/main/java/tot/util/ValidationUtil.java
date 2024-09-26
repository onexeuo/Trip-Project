package tot.util;

import tot.exception.ErrorCode;
import tot.exception.ValidationException;

public class ValidationUtil {

	public static void validateNotEmpty(String value, ErrorCode errorCode) {
		if (value == null || value.trim().isEmpty()) {
			throw new ValidationException(errorCode);
		}
	}

	public static void validateLength(String value, int maxLength, ErrorCode errorCode) {
		if (value != null && value.length() > maxLength) {
			throw new ValidationException(errorCode);
		}
	}
	
	public static void validateCheck(String value, ErrorCode errorCode) {
		if (value != null && value.equals("disagree")) {
			throw new ValidationException(errorCode);
		}
	}
}
