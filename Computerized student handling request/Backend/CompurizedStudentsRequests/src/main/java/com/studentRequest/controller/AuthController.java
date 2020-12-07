package com.studentRequest.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studentRequest.config.JwtUtils;
import com.studentRequest.model.ERole;
import com.studentRequest.model.Faculty;
import com.studentRequest.model.Role;
import com.studentRequest.model.Staff;
import com.studentRequest.model.Student;
import com.studentRequest.model.User;
import com.studentRequest.model.UserRole;
import com.studentRequest.request.LoginForm;
import com.studentRequest.request.SignUpForm;
import com.studentRequest.response.JwtResponse;
import com.studentRequest.response.ResponseMessage;
import com.studentRequest.services.SearchService;
import com.studentRequest.services.UserAccessService;
import com.studentRequest.services.impl.UserDetailsImp;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "user/authentication/")
public class AuthController {

	@Autowired
	private UserAccessService userAccess;
	
	@Autowired
	private SearchService searchService;

	@Autowired
	private AuthenticationManager authonticationManager;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtUtils jwtutils;

	@PostMapping("signin")
	@ApiOperation("user account login")
	public ResponseEntity<?> SignIn(@Valid @RequestBody LoginForm login) {
		System.out.print("username: "+login.getUsername()+" password: "+login.getPassword());
		Authentication auth = authonticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(auth);
		String jwt = jwtutils.generateJwtToken(auth);
		UserDetailsImp us = (UserDetailsImp) auth.getPrincipal();
		 List<String> role = us.getAuthorities().stream().map(item ->
		 item.getAuthority()).collect(Collectors.toList());
		System.out.print("out put: " + us.getUsername()+ " role "+ role);
		return ResponseEntity.ok(new JwtResponse(us.getId(), us.getUsername(), us.getPassword(), us.getStaff(),
				us.getStudent(), us.isActive(), role, jwt));
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("studentSignup")
	@ApiOperation("Student sign up on the system")
	public ResponseEntity signUp(@RequestBody SignUpForm signup) {
		User user = new User();
		try {
			
			Student student = userAccess.findStudentId(signup.getUserId());
			if (userAccess.existStudent(student)) {
				return ResponseEntity.badRequest()
						.body(new ResponseMessage("this user id is arleady having account!!"));
			} else {
				if (userAccess.studentExist(signup.getUserId())) {
					UserRole userRole = new UserRole();
					Role rol = userAccess.findByName(ERole.ROLE_Student).orElseThrow(()->new RuntimeException("user role not found"));
					
					user = new User(signup.getUsername(), encoder.encode(signup.getPassword()), true, student, null);
					userAccess.saverUser(user);
					userRole.setRole(rol);
					userRole.setUser(user);
					userRole.setRoleCreatedDate(new Date());
					userRole.setActive(true);
					userAccess.saveUserRole(userRole);
					return ResponseEntity.ok(new ResponseMessage("well successfull created!!"));

				} else {
					return ResponseEntity.badRequest().body(new ResponseMessage("this user id is not found"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ResponseMessage(e.getMessage()));
		}

	}

	@SuppressWarnings("rawtypes")
	@PostMapping("staffSignup")
	@ApiOperation("Staff sign up on the system")
	public ResponseEntity staffSignup(@RequestBody SignUpForm signup) {
		User user = new User();
		try {
			Staff staff = userAccess.findStaffId(signup.getUserId());
			if (userAccess.existsStaff(staff)) {
				return ResponseEntity.badRequest()
						.body(new ResponseMessage("this user id is arleady having account!!"));
			} else {
				
				if (userAccess.staffExist(signup.getUserId())) {
				UserRole userRole = new UserRole();
					Role rol = userAccess.findByName(ERole.ROLE_Lecturer).orElseThrow(()->new RuntimeException("user role not found"));
					
					user = new User(signup.getUsername(), encoder.encode(signup.getPassword()), true,
							null, staff);
					userAccess.saverUser(user);
					userRole.setRole(rol);
					userRole.setRoleCreatedDate(new Date());
					userRole.setActive(true);
					userRole.setUser(user);
					userAccess.saveUserRole(userRole);
					return ResponseEntity.ok(new ResponseMessage("well successfull created!!"));
				} else {
					return ResponseEntity.badRequest().body(new ResponseMessage("this user id is not found"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ResponseMessage(e.getMessage()));
		}
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("postStaff")
	@ApiOperation("admin register staff on the system")
	public ResponseEntity createStaff(@RequestBody HashMap<String, Object>map) {
		ObjectMapper obj = new ObjectMapper();
		Staff staff = obj.convertValue(map.get("staff"), Staff.class);
		String fac = (String)map.get("facultyId");
		Faculty faculty = searchService.facultyid(Integer.valueOf(fac));
		System.out.print("faculty id: "+fac);
		Staff st = new Staff(staff.getFirstName(), staff.getLastName(), staff.getGender(), staff.getEmail(),
				staff.getPhoneNumber(), staff.getId(), "Employee", faculty);
		userAccess.createStaff(st);
		return ResponseEntity.ok(new ResponseMessage("well successfull created!!"));
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("postStudent")
	@ApiOperation("admin register student on the system")
	public ResponseEntity createStudent(@RequestBody HashMap<String, Object>map) {
		ObjectMapper obj = new ObjectMapper();
		Student student = obj.convertValue(map.get("student"), Student.class);
		String fac = (String)map.get("facultyId");
		Faculty faculty = searchService.facultyid(Integer.valueOf(fac));
		System.out.print("faculty id: "+fac);
		Student st = new Student(student.getFirstName(), student.getLastName(), student.getGender(), student.getEmail(),
				student.getPhoneNumber(), student.getId(), faculty);
		userAccess.createStudent(st);
		return ResponseEntity.ok(new ResponseMessage("well successfull created!!"));
	}
	
	@GetMapping("getAllStudent")
	@ApiOperation("find all student registered")
	public List<Student> getAllStudent() {
			return userAccess.getAllStudent();
		
	}
	@GetMapping("getAllStaff")
	@ApiOperation("find all staff registered")
	public List<Staff>getAllStaff(){
		return userAccess.getAllStaff();
	}
	

}
