package com.gazbygaz.exceptions;

import com.gazbygaz.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	 
	@Autowired
    private MessageSource messageSource;

	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public Response handleException(HttpServletRequest request, Exception ex){
		log.error("Exception Occurred: " , ex);
		log.error("Invalid Exception Occurred Error Message: {}", ex.getMessage());
		log.error("Invalid Exception Occurred Error: ", ex);
		ex.printStackTrace();
		Response response = new Response();
		String message = messageSource.getMessage(HttpStatus.INTERNAL_SERVER_ERROR.name(), null, "server error occurred!!.", null);
		
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
        response.setMessage(message);
		response.setCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        response.setMessageDescription(ex.getMessage());
		return response;
	}

	@ExceptionHandler(InvalidRequestException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public Response handleException(HttpServletRequest request, InvalidRequestException ex){
		log.error("Invalid Exception : " , ex);
		log.error("Invalid Exception - ErrorDescription: {}" , ex.getMessageDescription());

		Response response = new Response();
		response.setStatus(HttpStatus.BAD_REQUEST.name());
		response.setMessage(ex.getMessage());
		response.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
		response.setMessageDescription(ex.getMessageDescription());
		return response;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public Response handleExceptionMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException ex){
		log.error("Validation Exception Occurred Error: ", ex);
		ex.printStackTrace();
		GolbalValidationErrorResponse response = new GolbalValidationErrorResponse();

		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			response.addValidationError(fieldName, errorMessage);
		});

		response.setMessage(ex.getMessage());
		response.setStatus(HttpStatus.BAD_REQUEST.name());
		response.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
		return response;
	}
}
