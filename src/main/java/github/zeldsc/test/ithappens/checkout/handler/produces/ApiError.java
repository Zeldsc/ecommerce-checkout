package github.zeldsc.test.ithappens.checkout.handler.produces;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiError extends ResponseDetails{
	
	public ApiError(HttpStatus httpStatus, String message, String path) {
		super(httpStatus, message, path, Arrays.asList());
	}

	public ApiError(HttpStatus httpStatus, String message, String path, List<String> details) {
		super(httpStatus, message, path, details);
	}

}
