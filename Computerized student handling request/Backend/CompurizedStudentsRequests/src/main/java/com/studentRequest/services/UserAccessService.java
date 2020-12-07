package com.studentRequest.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.studentRequest.model.ERole;
import com.studentRequest.model.Role;
import com.studentRequest.model.Staff;
import com.studentRequest.model.Student;
import com.studentRequest.model.User;
import com.studentRequest.model.UserRole;

public interface UserAccessService {

	Optional<User> findUsername(String username);
	boolean existStudent (Student id);
	boolean existsStaff(Staff id);
	Staff findStaffId(String id);
	Student findStudentId(String id);
	User saverUser(User user);
	Staff createStaff(Staff staff);
	Student createStudent(Student student);
	boolean studentExist(String student);
	boolean staffExist(String staff);
	List<Student>getAllStudent();
	List<Staff>getAllStaff();
	List<User>getAllUsers();
	Optional<Role>findByName(ERole role);
	void saveRole(List<Role>role);
	Role findID(int role);
	UserRole saveUserRole(UserRole userRole);
	
}
