package com.example.demo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
public class Request {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
	generator="req_seq")
	@SequenceGenerator(name="req_seq",initialValue=100)
	private int requestId;
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("cuurentProj")
	private Project project;
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("members")
	private Employee employee;
	private int status;
 
}
