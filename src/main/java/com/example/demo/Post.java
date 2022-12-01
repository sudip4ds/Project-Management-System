package com.example.demo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class Post {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
	generator="post_seq")
	@SequenceGenerator(name="post_seq",initialValue=100)
	private int postId; 
	@OneToOne
	@JoinColumn(name = "projectName")
	@JsonIgnoreProperties("currentProj")
	private Project projectName;
	private String description;
	private int minExp;

}
