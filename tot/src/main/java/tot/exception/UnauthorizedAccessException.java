package tot.exception;

public class UnauthorizedAccessException extends CustomException {

	public UnauthorizedAccessException() {
		super(ErrorCode.UNAUTHORIZED_ACCESS);
	}

}
