package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Skill {
	
	@Id
	private int skillId;
	private String skillName;

}
