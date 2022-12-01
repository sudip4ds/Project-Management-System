package com.example.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
public class Project {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
	generator="proj_seq")
	@SequenceGenerator(name="proj_seq",initialValue=1004)
	private int projectId;
	private String projectName;
	@ManyToMany
	private List<Skill> reqSkills= new ArrayList<Skill>();
	private int noOfMembers;
	@ManyToMany(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("currentProj")
	private List<Employee> members = new ArrayList<Employee>();
	private Date deadline;
	
	
	

}


