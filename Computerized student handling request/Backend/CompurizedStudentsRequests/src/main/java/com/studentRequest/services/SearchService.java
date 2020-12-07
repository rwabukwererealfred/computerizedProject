package com.studentRequest.services;

import java.util.List;
import java.util.Optional;

import com.studentRequest.model.Faculty;
import com.studentRequest.model.Role;
import com.studentRequest.model.Staff;
import com.studentRequest.model.Student;
import com.studentRequest.model.StudentInfo;
import com.studentRequest.model.User;
import com.studentRequest.model.UserRole;

public interface SearchService {

	List<Faculty>findByFaculty(Faculty faculty);
	
	List<Faculty>findByDepartment(int id);
	Faculty facultyid(int id);
	
	List<Staff>findByIdList(String id);
	List<Staff>findByFacultyList(int faculty);
	List<Student>studentFindById(String id);
	List<Student>studentFindByFacultyList(int faculty);
	
	List<User>findAllStaffAccount();
	List<User>findAllStudentAccount();
	List<Role>roleList();
	List<UserRole>findByIdList(int id);
	List<UserRole>findAllUserRole();
	User findById(int id);
	boolean findByuser(User user);
	boolean findByRole(Role role);
	boolean findByActive(boolean active);
	void deleteUserRole(int id);
	UserRole findByUserRoleId(int id);
	UserRole findByuserAndRole(User user, Role role);
	StudentInfo findById(String id);
	StudentInfo saveStudent(StudentInfo info);
	List<Role>findAllRole();
	
}
