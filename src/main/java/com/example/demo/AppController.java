package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.AppDao;
import com.example.demo.dao.PostRepository;
import com.example.demo.exception.AddProjectError;
import com.example.demo.exception.DeleteProjectError;
import com.example.demo.exception.EmployeeFetchError;
import com.example.demo.exception.Error;

@RestController
@CrossOrigin({"http://localhost:4200/","*"})
public class AppController {
	
	@Autowired
	AppDao appdao;
	
	@Autowired
	PostRepository postRepo;
	
	//employee end points
	@GetMapping("getemp/{email}")
	public ResponseEntity<Employee> getEmployee(@PathVariable String email) {
		return new ResponseEntity<Employee>(appdao.getEmployee(email),HttpStatus.OK);
	}
	
	@GetMapping("getallemp")
	public ResponseEntity<List<Employee>> getallEmp(){
		List<Employee> l =  appdao.getAllEmp();
		return new ResponseEntity<List<Employee>>(l.stream().filter(t ->!t.getEmpDesg().equals("manager")).toList(),HttpStatus.OK);
	}
	
		@PostMapping("acceptEmp")
		public ResponseEntity<String> acceptEmployee(@RequestBody Request req) {
			return new ResponseEntity(appdao.AcceptEmp(req),HttpStatus.OK);
		}
		
	
	//project end points
	@PostMapping("addProject")
	public void addProject(@RequestBody Project project) {
		try {
		appdao.addProject(project);
		}catch(Exception e) {
			throw new AddProjectError("Error while creating project");
		}
	}
	
	@GetMapping("getAllProject")
	public ResponseEntity<List<Project>>  getAllProject(){
		return new ResponseEntity<List<Project>>(appdao.getallProjects(),HttpStatus.OK);
	}
	
	@DeleteMapping("deleteProject/{id}")
	public ResponseEntity deleteProject(@PathVariable int id) {
		appdao.deleteProject(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("findProject/{id}")
	public ResponseEntity<Project> getProject(@PathVariable int id){
		return new ResponseEntity<Project>(appdao.getProject(id),HttpStatus.OK);
	}
	
	@GetMapping("getUnPosted")
	public ResponseEntity<List<Project>> getUnposted(){
		System.out.println("in controller");
		return new ResponseEntity<List<Project>>(appdao.getUnpostedProject(),HttpStatus.OK);
	}
	
	//post end points
	
	@GetMapping("getPost/{id}")
	public ResponseEntity<Post> getPost(@PathVariable int id) {
		return new ResponseEntity<Post>( appdao.getPost(id),HttpStatus.OK);
	}
	
	@PostMapping("addPost")
	public ResponseEntity addPost(@RequestBody Post post) {
		
		try {
		appdao.addPost(post);
		return new ResponseEntity("New project added",HttpStatus.OK);
	}catch(Exception e) {
		System.out.println(e.getMessage());
		return new ResponseEntity("error while inserting, redirecting to projects",HttpStatus.OK);
	}
	}
	
	@GetMapping("getAllPost")
	public List<Post> getAllPost(){
		return appdao.getAllPost();
	}
	

	@GetMapping("getPostForEmp")
	public ResponseEntity getPostNotInReq() {
		return new ResponseEntity(appdao.getAllPostNotIdReq(101),HttpStatus.OK);
	}

	
	@DeleteMapping("deletepost/{id}")
	public ResponseEntity deletePost(@PathVariable int id) {
		System.out.println("came in delete");
		appdao.deletePost(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("updatePost")
	public void updatePost(@RequestBody Post post) {
		appdao.updatePost(post);
	}
	
	//request end points
	
	@GetMapping("getAllReqs")
	public ResponseEntity<List<Request>> getAllReqs(){
		return new ResponseEntity<List<Request>>(appdao.getAllReqs(),HttpStatus.OK);
	}
	
	@DeleteMapping("deleteReq/{id}")
	public ResponseEntity<String> deleteReq(@PathVariable int id) {
		return new ResponseEntity<String>(appdao.deleteRequest(id),HttpStatus.OK);
	}
	
	@PostMapping("apply")
	public void applyReq(@RequestBody Request req) {

		appdao.apply(req);
	}
	
	// skill endpoints
	@GetMapping("getAllSkills")
	public ResponseEntity<List<Skill>> getAllSkills(){
		return new ResponseEntity<List<Skill>>(appdao.getAllSkill(),HttpStatus.OK);
	}
	
	@ExceptionHandler(AddProjectError.class)
	public ResponseEntity addProjectHandler(Exception e) {
		Error er=new Error();
		er.setErrorCode(HttpStatus.NOT_FOUND.toString());
		er.setErrorMsg(e.getMessage());
		return new ResponseEntity(er,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DeleteProjectError.class)
	public ResponseEntity deleteProjectError(Exception e) {
		

		Error er=new Error();
		er.setErrorCode(HttpStatus.NOT_FOUND.toString());
		er.setErrorMsg(e.getMessage());
		return new ResponseEntity(er,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EmployeeFetchError.class)
	public ResponseEntity employeeFetchHandler(Exception e) {
		Error er=new Error();
		er.setErrorCode(HttpStatus.NOT_FOUND.toString());
		er.setErrorMsg(e.getMessage());
		return new ResponseEntity(er,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity commonHandler(Exception e) {
		Error er=new Error();
		er.setErrorCode(HttpStatus.NOT_FOUND.toString());
		er.setErrorMsg(e.getMessage());
		return new ResponseEntity(er,HttpStatus.NOT_FOUND);
	}

	
	

	
}
