package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
	
	@Query(value = "select p from Project p where p.projectId not in (select pp.projectName.projectId from Post pp)")
	public List<Project> findUnpostedProjects();

}
