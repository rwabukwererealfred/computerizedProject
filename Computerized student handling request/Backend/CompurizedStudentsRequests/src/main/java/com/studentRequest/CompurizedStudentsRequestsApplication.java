package com.studentRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.studentRequest.model.ERole;
import com.studentRequest.model.Role;
import com.studentRequest.model.StudentInfo;
import com.studentRequest.model.User;
import com.studentRequest.model.UserRole;
import com.studentRequest.services.SearchService;
import com.studentRequest.services.UserAccessService;


@SpringBootApplication
public class CompurizedStudentsRequestsApplication implements CommandLineRunner {

//	@Autowired
//	private UserAccessService service;
//	
//	@Autowired
//	private PasswordEncoder encoder;
	
	@Autowired
	private SearchService searchService;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CompurizedStudentsRequestsApplication.class, args);
	}

	
	@Override
	public void run(String... args) throws Exception {
//		List<Role>roleList =  Arrays.asList(new Role(1,ERole.ROLE_Accountant),new Role(2,ERole.ROLE_Dean),
//				new Role(3,ERole.ROLE_HeadOfDepartment),new Role(4,ERole.ROLE_Admin),new Role(5,ERole.ROLE_Lecturer),new Role(6,ERole.ROLE_Registrar),
//				new Role(7,ERole.ROLE_Student));
//		service.saveRole(roleList);
//		
//		User user = new User();
//		user.setId(1);
//		user.setActive(true);
//		user.setUsername("admin");
//		user.setPassword(encoder.encode("123"));
//		service.saverUser(user);
//		UserRole userRole = new UserRole();
//		Role role = service.findByName(ERole.ROLE_Admin).get();
//		userRole.setId(1);
//		userRole.setRole(role);
//		userRole.setActive(true);
//		userRole.setRoleCreatedDate(new Date());
//		userRole.setUser(user);
//		service.saveUserRole(userRole);
		
//		StudentInfo st = new StudentInfo("L124", "Alpha", "Fred", "fred@gmail.com", 90, "0788283828", "Male");
//		searchService.saveStudent(st);
//		st = new StudentInfo("L120", "IRadukunda", "Jado", "jado@gmail.com", 30, "0783083828", "Male");
//		searchService.saveStudent(st);
//		st = new StudentInfo("L121", "Benimana", "Agnes", "agnes@gmail.com", 100, "0788283828", "Female");
//		searchService.saveStudent(st);
	}
	
	

}
