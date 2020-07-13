package github.zeldsc.test.ithappens.checkout.handler.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import github.zeldsc.test.ithappens.checkout.exception.RuleExceprion;
import github.zeldsc.test.ithappens.checkout.handler.produces.ApiError;
import github.zeldsc.test.ithappens.checkout.handler.resolver.MethodArgumentNotValidExceptionResolver;

@RestController
@RestControllerAdvice(basePackages = "github.zeldsc.test.ithappens.checkout", annotations = RestController.class)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
		HttpHeaders headers, HttpStatus status, WebRequest request) {
		ApiError errorDetails = MethodArgumentNotValidExceptionResolver.reolver(ex, getPath(request));
		return handleExceptionInternal(ex, errorDetails, headers, errorDetails.getHttpStatus(), request);
	}
	
	@ExceptionHandler(
		value = {
			RuleExceprion.class
		}
	)
	protected ResponseEntity<Object> handleBusinessException(
		RuleExceprion ex,
		WebRequest request
	) {
		ApiError errorDetails = new ApiError(ex.getHttpStatus(),ex.getMessage(), getPath(request));
		return handleExceptionInternal(
			ex,
			errorDetails,
			new HttpHeaders(),
			ex.getHttpStatus(),
			request
		);
	}
	
	private String getPath(WebRequest request) {
		ServletWebRequest rq = (ServletWebRequest) request;
		return rq.getRequest().getRequestURI();
	}
}
