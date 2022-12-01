package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.example.demo.dao.EmployeeRepository;
import com.example.demo.dao.PostRepository;
import com.example.demo.dao.ProjectRepository;
import com.example.demo.dao.RequestRepository;
import com.example.demo.dao.SkillRepository;

@SpringBootApplication
@EnableEurekaClient
public class MainClientApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(MainClientApplication.class, args);
	}
	
	@Autowired
	SkillRepository skillRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@Autowired
	ProjectRepository projRepo;
	
	@Autowired
	PostRepository postRepo;
	
	@Autowired
	RequestRepository reqRepo;

	@Override
	public void run(String... args) throws Exception {
		
		Skill s1 = new Skill();
		s1.setSkillId(1);
		s1.setSkillName("java");
//		skillRepo.save(s1);
//		
		Skill s2 = new Skill();
		s2.setSkillId(2);
		s2.setSkillName("python");
//		skillRepo.save(s2);
//		
		Skill s3 = new Skill();
		s3.setSkillId(3);
		s3.setSkillName("c++");
//		skillRepo.save(s3);
//		
		Skill s4 = new Skill();
		s4.setSkillId(4);
		s4.setSkillName("sql");
//		skillRepo.save(s4);
//		
		Skill s5  = new Skill();
		s5.setSkillId(5);
		s5.setSkillName("spring boot");
//		skillRepo.save(s5);
//		
		Skill s6 = new Skill();
		s6.setSkillId(6);
		s6.setSkillName("react");
//		skillRepo.save(s6);
//		
		Skill s7 = new Skill();
		s7.setSkillId(7);
		s7.setSkillName("angular");
//		skillRepo.save(s7);
////		
		List<Skill> sList= new ArrayList<Skill>();
		
		sList.add(s1);
		sList.add(s6);
		sList.add(s3);
		

		Employee e = new Employee();
		e.setEmpId(1000);
		e.setEmpName("admin");
		e.setEmpEmail("admin@gmail.com");
		e.setEmpExp(6);
		e.setEmpDesg("manager");
//		empRepo.save(e);
//		
//		
		Employee e1 = new Employee();
		e1.setEmpId(100);
		e1.setEmpName("user1");
		e1.setEmpEmail("user1@gmail.com");
		e1.setEmpExp(4);
		e1.setEmpSkills(sList);
		e1.setEmpDesg("employee");
//		empRepo.save(e1);
		
		Employee e2 = new Employee();
		e2.setEmpId(101);
		e2.setEmpName("user2");
		e2.setEmpEmail("user2@gmail.com");
		e2.setEmpDesg("employee");
		e2.setEmpExp(2);
		e2.setEmpSkills(sList);
//		empRepo.save(e2);
		
		
		Employee e3 = new Employee();
		e3.setEmpId(102);
		e3.setEmpName("user3");
		e3.setEmpEmail("user3@gmail.com");
		e3.setEmpDesg("employee");
		e3.setEmpExp(4);
		e3.setEmpSkills(sList);
//		empRepo.save(e3);
////		
//		List<Skill> rList= new ArrayList<Skill>();
//		rList.add(s1);
//		rList.add(s4);
//		rList.add(s5);
//		rList.add(s6);
//		rList.add(s7);
////		
//	    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
//	    Date date = formatter.parse("25/12/2022");  
//	    Date date1 = formatter.parse("05/02/2023");  
//
//		
//		Project p1 = new Project();
//		p1.setProjectId(1000);
//		p1.setProjectName("pf manager");
//		p1.setNoOfMembers(5);
//		p1.setReqSkills(rList);
//		p1.setDeadline(date);
//		projRepo.save(p1);
////		
//		List<Skill> rList1= new ArrayList<Skill>();
//		rList1.add(s1);
//		rList1.add(s4);
////		rList1.add(s5);
//		rList1.add(s6);
//		rList1.add(s7);
//		rList1.add(s3);
//		
//		Project p2 = new Project();
//		p2.setProjectId(1005);
//		p2.setProjectName("bill scanner");
//		p2.setNoOfMembers(8);
//		p2.setReqSkills(rList1);
//		p2.setDeadline(date1);
////		projRepo.save(p2);
//////		
//		Post post=new Post();
//		post.setProjectName(projRepo.findById(1005).get());
//		post.setDescription("post1");
//		post.setMinExp(2);
//		postRepo.save(post);
//		
//		Request r = new Request();
//		r.setProject(projRepo.findById(1005).get()); 
//		r.setEmployee(empRepo.findById(101).get());
//		r.setStatus(null);
//		reqRepo.save(r);
//		
//		
//		
//		
		
	}

}
