package com.springboot_calling_extenal_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(name = "post_office")
public class PostOffice {

	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;

	  private String name;
	  private String description;
	  private String branchType;
	  private String deliveryStatus;
	  private String circle;
	  private String district;
	  private String division;
	  private String region;
	  private String state;
	  private String country;
	  private String pincode;


}
