package com.example.demo.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	public Employee findByempEmail(String empEmail);
	
}
