package com.nexos.test.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MessageResponse {

    private boolean error;
    private String message;
    
    public MessageResponse() { }
    
   	public MessageResponse(boolean error, String message) {
   		super();
   		this.error = error;
   		this.message = message;
   	}
   	
}
