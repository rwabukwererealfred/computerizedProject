package com.studentRequest.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studentRequest.model.ERole;
import com.studentRequest.model.Faculty;
import com.studentRequest.model.Role;
import com.studentRequest.model.Staff;
import com.studentRequest.model.Student;
import com.studentRequest.model.StudentInfo;
import com.studentRequest.model.User;
import com.studentRequest.model.UserRole;
import com.studentRequest.response.ResponseMessage;
import com.studentRequest.services.SearchService;
import com.studentRequest.services.UserAccessService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "user/search/")
public class SearchController {

	@Autowired
	private SearchService searchService;

	@Autowired
	private UserAccessService userAccess;

	@GetMapping("faculty")
	@ApiOperation("get all faculties")
	public List<Faculty> getFaculty() {
		return searchService.findByFaculty(null);
	}

	@GetMapping("department/{id}")
	@ApiOperation("find department by faculty")
	public List<Faculty> getDeparmtnent(@PathVariable("id") int id) {
		List<Faculty> list = new ArrayList<Faculty>();
		if (id == 0) {
			list = new ArrayList<Faculty>();
			return list;
		} else {
			list = searchService.findByDepartment(id);
			return list;
		}

	}

	@GetMapping("getRoles")
	@ApiOperation("find all user roles")
	public List<Role> returnRole() {
		List<Role> list = searchService.findAllRole();
		return list;
	}

	@GetMapping("searchById/{id}")
	@ApiOperation("search staff by id")
	public List<Staff> searchById(@PathVariable("id") String id) {
		return searchService.findByIdList(id);
	}

	@GetMapping("searchByFaculty/{faculty}")
	@ApiOperation("search all staffs by faculty")
	public List<Staff> searchByFaculty(@PathVariable("faculty") int faculty) {
		List<Staff> list = new ArrayList<Staff>();
		if (faculty == 0) {
			list = userAccess.getAllStaff();
		} else {
			list = searchService.findByFacultyList(faculty);
		}
		return list;
	}

	@GetMapping("searchByStudentId/{id}")
	@ApiOperation("find student by id")
	public List<Student> searchByStudentId(@PathVariable("id") String id) {
		return searchService.studentFindById(id);
	}

	@GetMapping("searchByStudentFaculty/{faculty}")
	@ApiOperation("search students by faculty")
	public List<Student> searchByStudentFaculty(@PathVariable("faculty") int faculty) {
		List<Student> list = new ArrayList<Student>();
		if (faculty == 0) {
			list = userAccess.getAllStudent();
		} else {
			list = searchService.studentFindByFacultyList(faculty);
		}
		return list;
	}

	@GetMapping("studentAccount")
	public List<User> findStudentAccount() {
		List<User> list = searchService.findAllStudentAccount().stream().filter(i -> i.getStudent() != null)
				.collect(Collectors.toList());
		return list;
	}

	@GetMapping("staffAccount")
	public List<User> findStaffAccount() {
		List<User> list = searchService.findAllStaffAccount().stream().filter(i -> i.getStaff() != null)
				.collect(Collectors.toList());
		return list;
	}

	@GetMapping("roleList")
	public List<Role> roleList() {
		return searchService.roleList();
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("addNewRole")
	@ApiOperation("update user role")
	public ResponseEntity updateUserRole(@RequestBody HashMap<String, Object> map) {
		ObjectMapper mapper = new ObjectMapper();
		UserRole userRole = new UserRole();
		User user = mapper.convertValue(map.get("user"), User.class);
		Role role = mapper.convertValue(map.get("role"), Role.class);
		UserRole userRo = searchService.findByuserAndRole(user, role);
		if (userRo != null) {
			 if(userRo.isActive() == true) {
				 return ResponseEntity.badRequest().body(new ResponseMessage("this user is already having this role"));
			
			 }else {
				 userRo.setActive(true);
				 userRo.setRoleCreatedDate(new Date());
				 userAccess.saveUserRole(userRole);
				 return ResponseEntity.ok().body(new ResponseMessage("new role user is well successfull added")); 
			 }
		} else {
			userRole.setRole(role);
			userRole.setUser(user);
			userRole.setActive(true);
			userRole.setRoleCreatedDate(new Date());
			userAccess.saveUserRole(userRole);
			return ResponseEntity.ok().body(new ResponseMessage("new role user is well successfull added"));
		}
	}

	@GetMapping("userRoleList/{id}")
	public List<UserRole> findUserRoleIdList(@PathVariable("id") int id) {
		User user = searchService.findById(id);
		return user.getUserRole().stream().filter(i->i.isActive() ==true).collect(Collectors.toList());
		
	}

	@GetMapping("findAllUserRole")
	public List<UserRole> findUserRoleIdList() {
		return searchService.findAllUserRole();
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("removeRole")
	public ResponseEntity removeUserRole(@RequestBody UserRole userRole) {
		UserRole userRo = searchService.findByUserRoleId(userRole.getId());
		userRo.setActive(false);
		userAccess.saveUserRole(userRo);
		return ResponseEntity.ok().body(new ResponseMessage("well successfull removed"));
	}
	
	@GetMapping(value="studentInfo/{id}")
	public StudentInfo findStudent(@PathVariable("id")String id) {
		return searchService.findById(id);
	}
	

}
