package com.springboot_calling_extenal_api.output;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data

public class PostOfficeResponseBean {
	
	@JsonProperty("Message")
	private String message;
	
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("PostOffice")
	private List<PostOfficeDetailsBean> postOffice;

}
