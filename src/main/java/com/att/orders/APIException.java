package com.att.orders;

import org.springframework.stereotype.Component;

@Component
public class APIException extends Exception {

	ApiError apiError;

	public ApiError getApiError() {
		return apiError;
	}

	public void setApiError(ApiError apiError) {
		this.apiError = apiError;
	}
	
	
}
