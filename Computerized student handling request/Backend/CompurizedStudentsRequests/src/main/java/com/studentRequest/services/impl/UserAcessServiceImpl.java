package com.studentRequest.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentRequest.dao.RoleDao;
import com.studentRequest.dao.StaffDao;
import com.studentRequest.dao.StudentDao;
import com.studentRequest.dao.UserDao;
import com.studentRequest.dao.UserRoleDao;
import com.studentRequest.model.ERole;
import com.studentRequest.model.Role;
import com.studentRequest.model.Staff;
import com.studentRequest.model.Student;
import com.studentRequest.model.User;
import com.studentRequest.model.UserRole;
import com.studentRequest.services.UserAccessService;

@Service
public class UserAcessServiceImpl implements UserAccessService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private StaffDao staffDao;
	
	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private RoleDao roleDao;

	@Autowired
	private UserRoleDao userRoleDao;
	
	
	@Override
	public Optional<User> findUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public Staff findStaffId(String id) {
		
		return staffDao.getOne(id);
	}

	@Override
	public Student findStudentId(String id) {
		
		return studentDao.getOne(id);
	}

	@Override
	public User saverUser(User user) {
		return userDao.save(user);
	}

	@Override
	public boolean existStudent(Student id) {
		
		return userDao.existsByStudent(id);
	}

	@Override
	public boolean existsStaff(Staff id) {
		
		return userDao.existsByStaff(id);
	}
	
	@Override
	public Staff createStaff(Staff staff) {
		
		return staffDao.save(staff);
	}

	@Override
	public Student createStudent(Student student) {
		
		return studentDao.save(student);
	}

	@Override
	public boolean studentExist(String student) {
		
		return studentDao.existsById(student);
	}

	@Override
	public boolean staffExist(String staff) {
		
		return staffDao.existsById(staff);
	}

	@Override
	public List<Student> getAllStudent() {
		
		return studentDao.findAll();
	}

	@Override
	public List<Staff> getAllStaff() {
		
		return staffDao.findAll();
	}

	@Override
	public List<User> getAllUsers() {
		
		return userDao.findAll();
	}

	@Override
	public Optional<Role> findByName(ERole role) {
		
		return roleDao.findByName(role);
	}

	@Override
	public void saveRole(List<Role> role) {
		 roleDao.saveAll(role);
	}

	@Override
	public Role findID(int role) {
		
		return roleDao.findById(role).get();
	}

	@Override
	public UserRole saveUserRole(UserRole userRole) {
		
		return userRoleDao.save(userRole);
	}

	

}
