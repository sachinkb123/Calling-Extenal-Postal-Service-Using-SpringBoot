package com.springboot_calling_extenal_api.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.springboot_calling_extenal_api.entity.PostOffice;
import com.springboot_calling_extenal_api.output.PostOfficeDetailsBean;
import com.springboot_calling_extenal_api.output.PostOfficeResponseBean;
import com.springboot_calling_extenal_api.repository.PostOfficeRepository;


@Service
public class PostServiceImpl implements IPostService {
	
	private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	PostOfficeRepository postOfficeRepository;
	
	 
	
//	Fetches post office details from an external API based on the city name and saves them into the database.
	@Override
	public PostOfficeResponseBean fetchAndSavePostOfficeDetailsByCity(String city) {
		// Construct the URL with the city parameter
		String url = UriComponentsBuilder.fromUriString("https://api.postalpincode.in/postoffice/{city}")
				.buildAndExpand(city).toString();

		logger.info("Constructed URL: {}", url);

		// Send GET request to the external API and retrieve the response as an array of PostOfficeResponseBean objects
		ResponseEntity<PostOfficeResponseBean[]> postalResponseEntity = restTemplate.getForEntity(url,
				PostOfficeResponseBean[].class);

		logger.info("Response Status Code: {}", postalResponseEntity.getStatusCodeValue());
		
		 // Retrieve the body of the HTTP response
		PostOfficeResponseBean[] responseBeanArray = postalResponseEntity.getBody();
		
		Gson gson = new Gson();
		String responseBeanArray2 = gson.toJson(postalResponseEntity.getBody());
		logger.info(responseBeanArray2);


		// If responseBeanArray is not null, iterate through each PostOfficeResponseBean object
		if (responseBeanArray != null) {
			for (PostOfficeResponseBean responseBean : responseBeanArray) {
				// Retrieve the list of PostOfficeDetailsBean objects from each PostOfficeResponseBean
				List<PostOfficeDetailsBean> postOfficeList = responseBean.getPostOffice();
				if (postOfficeList != null) {
					// Iterate through each PostOfficeDetailsBean object
					for (PostOfficeDetailsBean pod : postOfficeList) {
						try {
							PostOffice postOffice = new PostOffice();
							postOffice.setName(pod.getName());
							postOffice.setDescription(pod.getDescription());
							postOffice.setBranchType(pod.getBranchType());
							postOffice.setDeliveryStatus(pod.getDeliveryStatus());
							postOffice.setCircle(pod.getCircle());
							postOffice.setDistrict(pod.getDistrict());
							postOffice.setDivision(pod.getDivision());
							postOffice.setRegion(pod.getRegion());
							postOffice.setState(pod.getState());
							postOffice.setCountry(pod.getCountry());
							postOffice.setPincode(pod.getPincode());

							// Save the PostOffice entity to the database
							postOfficeRepository.save(postOffice);
							logger.info("Saved Post Office: {}", postOffice.getName());
						} catch (Exception e) {
							logger.error("Error saving Post Office: {}", pod.getName(), e);
						}
					}
				}
			}
		}
		 // Return the first element of the responseBeanArray as the method result
		return responseBeanArray[0];
	}

	@Override
	  public PostOfficeResponseBean fetchPostOfficeDetailsByCity(String city) {
	    String url = UriComponentsBuilder.fromUriString("https://api.postalpincode.in/postoffice/{city}")
	      .buildAndExpand(city)
	      .toString();

	   logger.info("url is " + url);

	    ResponseEntity<PostOfficeResponseBean[]> postalResponseEntity = restTemplate.getForEntity(url, PostOfficeResponseBean[].class);

	    logger.info("ResponseStatus code is " + postalResponseEntity.getStatusCodeValue());

	    PostOfficeResponseBean[] responseBeanArray = postalResponseEntity.getBody();
	    
	    Gson gson = new Gson();
		String responseBeanArray2 = gson.toJson(postalResponseEntity.getBody());
		logger.info(responseBeanArray2);

	    for (PostOfficeResponseBean responseBean : responseBeanArray) {
	      List<PostOfficeDetailsBean> postOfficeListBean = responseBean.getPostOffice();
//	      for (PostOfficeDetailsBean pod : postOfficeListBean) {
//	        System.out.println(pod.getName() + "  " + pod.getCountry() + " " + pod.getPincode());
//	      }
	    }
	    return responseBeanArray[0];
	  }

}
