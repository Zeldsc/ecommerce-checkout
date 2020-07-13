package github.zeldsc.test.ithappens.checkout.exception;

import org.springframework.http.HttpStatus;

public class RuleExceprion extends Exception {

	private static final long serialVersionUID = 1L;
	
	private final HttpStatus httpStatus;

	public RuleExceprion(String message) {
		super(message);
		this.httpStatus = HttpStatus.BAD_REQUEST;
	}
	
	public RuleExceprion(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
}
