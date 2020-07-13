package github.zeldsc.test.ithappens.checkout.handler.produces;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

class ResponseDetails {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime timestamp;

	@JsonIgnore
	private HttpStatus httpStatus;

	private String message;

	private String path;

	private List<String> details;

	private ResponseDetails() {
		this.timestamp = LocalDateTime.now();
	}

	public ResponseDetails(HttpStatus httpStatus, String message, String path,
			List<String> details) {
		this();
		this.httpStatus = httpStatus;
		this.message = message;
		this.path = path;
		this.details = details;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	@JsonProperty("status")
	public Integer getHttpStatusValue() {
		return httpStatus.value();
	}

	public String getMessage() {
		return message;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public List<String> getDetails() {
		return details;
	}

}
