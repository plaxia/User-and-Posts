package com.rest.webservices.resftul_web_services.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.webservices.resftul_web_services.user.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
	

}
