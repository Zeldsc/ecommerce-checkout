package github.zeldsc.test.ithappens.checkout.handler.resolver;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import github.zeldsc.test.ithappens.checkout.handler.produces.ApiError;

public class MethodArgumentNotValidExceptionResolver {
	
	private MethodArgumentNotValidExceptionResolver() {
	}
	
	public static final ApiError reolver(MethodArgumentNotValidException ex, String path) {
		List<String> errorList = ex
			.getBindingResult()
			.getFieldErrors()
			.stream()
			.map(MethodArgumentNotValidExceptionResolver::getFieldError)
			.collect(Collectors.toList());
		return new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), path, errorList);
	}
	
	private static String getFieldError(FieldError error) {
		return error.getField().concat(" ").concat(error.getDefaultMessage());
	}
}
