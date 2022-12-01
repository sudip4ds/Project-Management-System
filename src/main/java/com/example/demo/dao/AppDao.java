package com.example.demo.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.Employee;
import com.example.demo.Post;
import com.example.demo.Project;
import com.example.demo.Request;
import com.example.demo.Skill;
import com.example.demo.exception.DeleteProjectError;
import com.example.demo.exception.EmployeeFetchError;
import com.example.demo.exception.PostFetchError;



@Repository
public class AppDao {
	
	@Autowired
	EmployeeRepository empRepo;
	
	@Autowired
	ProjectRepository projRepo;
	
	@Autowired
	PostRepository postRepo;
	
	@Autowired
	RequestRepository reqRepo;
	
	@Autowired
	SkillRepository skillRepo;
	
	//employee methods
	
	public Employee getEmployee(String email) {
		return empRepo.findByempEmail(email);
	}
	
	public List<Employee> getAllEmp(){
		try {
		return empRepo.findAll();
		}catch(Exception e){
			throw new EmployeeFetchError("Error while fetching employee details!");
		}
	}
	
	@Transactional
	public String AcceptEmp(Request req) {
		System.out.println(req);
		try {
		
		System.out.println(req);
		boolean flag = true;
		Project p = req.getProject();
		Employee e = req.getEmployee();
		
		
		
		
		Project project = projRepo.findById(p.getProjectId()).get();
		
		Employee employee = empRepo.findById(e.getEmpId()).get();

		
		
		
		List<Employee> EinProject= project.getMembers();
		List<Project> PinEmployee= employee.getCurrentProj();
		
		
		
		if(PinEmployee.size()<3 && EinProject.size()<project.getNoOfMembers()) {
		
		if(EinProject.isEmpty() ) {
			
			//changing projects and employee
			System.out.println("not in project list is empty");
			EinProject.add(employee);
			PinEmployee.add(project);
			project.setMembers(EinProject);
			employee.setCurrentProj(PinEmployee);
			projRepo.save(project);
			empRepo.save(employee);
			//changing request
			Request r = reqRepo.findById(req.getRequestId()).get();
			r.setStatus(1);
			reqRepo.save(r);
			return "employee assigned to "+project.getProjectName();
			
		}else {

			for(Employee i:EinProject) {
				if(i.getEmpId()==employee.getEmpId()) {
					flag = true;
					break;
				}else {
					flag=false;
				}
				
			}
			if(flag) {
				System.out.println("in project");
				return "employee is already working is "+project.getProjectName();
			}else {
				
				System.out.println("not in project");
				EinProject.add(employee);
				PinEmployee.add(project);
				project.setMembers(EinProject);
				employee.setCurrentProj(PinEmployee);
				projRepo.save(project);
				empRepo.save(employee);
				//changing request
				Request r = reqRepo.findById(req.getRequestId()).get();
				r.setStatus(1);
				reqRepo.save(r);
				
				return "Employee assigned to "+project.getProjectName();
			}
		}
		}else {
			return "employee already assigned to 3 projects";
		}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return "error while assigning employee";
		}
	
	}
	
	public void addProject(Project project) {
		projRepo.save(project);
	}
	
	public void addPost(Post post) {
		postRepo.save(post);
	}
	
	public List<Post> getAllPost(){
		
		try {
		return postRepo.getAllPost();
		}catch(Exception e) {
			throw new PostFetchError("Error, No Post Found");
		}
	}
	
	public void deletePost(int postId) {
		postRepo.deleteById(postId);
	}
	
	public List<Project> getallProjects(){
		return projRepo.findAll();
	}
	
	public void deleteProject(int projectId) {
		try {
		try {
		Post post=postRepo.getp(projectId);
		postRepo.deleteById(post.getPostId());
		projRepo.deleteById(projectId);
		}catch(Exception e) {
			projRepo.deleteById(projectId);
		}
		}catch(Exception e){
			throw new DeleteProjectError("Error while deleting project");
		}
	}
	 
	public List<Request> getAllReqs(){
		return reqRepo.findAll();
	}
	
	public Project getProject(int id) {
		return projRepo.findById(id).get();
	}
	
	public List<Project> getUnpostedProject(){
		return projRepo.findUnpostedProjects();
		
	}
	
	public List<Skill> getAllSkill(){
		return skillRepo.findAll();
	}
	
	public void updatePost(Post p) {
		Post post = postRepo.findById(p.getPostId()).get();
		post.setDescription(p.getDescription());
		post.setMinExp(p.getMinExp());
		postRepo.save(post);
		
	}
	
	public Post getPost(int id) {
		return postRepo.findById(id).get();
	}
	
	public void apply(Request request) {
		Project p = projRepo.findById(request.getProject().getProjectId()).get();
		
		Employee e = empRepo.findById(request.getEmployee().getEmpId()).get();
		
//		System.out.println(p.getProjectName());
//		System.out.println(e);
//		System.out.println(request);
		
		
		Request r = new Request();
		r.setProject(p);
		r.setEmployee(e);
		r.setStatus(0);
		
		reqRepo.save(r);
	}
	
	
	public List<Post> getAllPostNotIdReq(int empId){
		TreeSet<Post> posts = new TreeSet<Post>();
		 for(Post p:postRepo.getAllPost()) {
			 for(Employee e:p.getProjectName().getMembers()) {
				 if(empId!=e.getEmpId()) {
					 posts.add(p);
				 }
			 }
		 }
		 System.out.println(posts);
		 return new ArrayList<Post>(posts);
	}
	
	public String deleteRequest(int reqId) {
		reqRepo.deleteById(reqId);
		return "Rejected";
	}
	
	

}
