package com.project.freelance.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseMessage {
  private String message;
  private String code;

  public ResponseMessage responseMessageSuccess() {
    ResponseMessage responseMessage = new ResponseMessage();
    responseMessage.setMessage("SUCCESS");
    responseMessage.setCode("200");
    return  responseMessage;
  }
}
