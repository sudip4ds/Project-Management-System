package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
	
	@Query(value = "select p from Post p")
	public List<Post> getAllPost();
	
	@Query(value = "select p from Post p where p.projectName.projectId=:id")
	public Post getp(int id);
	

	
	

}
