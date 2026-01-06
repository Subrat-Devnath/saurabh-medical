package com.common.service.dtos;

import java.io.Serializable;

import lombok.Data;

@Data
public class ResponseDTO implements Serializable {

	private static final long serialVersionUID = -4112436951057744456L;

	private boolean isSuccess;
	private Object data;
	private String sucessMessage;

	public ResponseDTO(boolean isSuccess, Object data, String sucessMessage) {
		this.isSuccess = isSuccess;
		this.data = data;
		this.sucessMessage = sucessMessage;
	}

}
