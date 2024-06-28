package com.springboot_calling_extenal_api.service;

import com.springboot_calling_extenal_api.output.PostOfficeResponseBean;

public interface IPostService {

	public PostOfficeResponseBean fetchAndSavePostOfficeDetailsByCity(String city);

	public PostOfficeResponseBean fetchPostOfficeDetailsByCity(String city);

}
