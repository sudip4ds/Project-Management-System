package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;


@Entity
@Data
public class Employee {
	@Id
	private int empId;
	private String empName;
	private String empEmail;
	private int empExp;
	private String empDesg;
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Skill> empSkills= new ArrayList<Skill>();
	@ManyToMany(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("members")
	private List<Project> currentProj = new ArrayList<Project>();
	
}
