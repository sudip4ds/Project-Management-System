package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Request;

public interface RequestRepository extends JpaRepository<Request, Integer>{

}
