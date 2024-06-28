package com.springboot_calling_extenal_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot_calling_extenal_api.entity.PostOffice;

@Repository
public interface PostOfficeRepository extends JpaRepository<PostOffice, Long> {

}
