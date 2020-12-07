package com.studentRequest.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentRequest.dao.FacultyDao;
import com.studentRequest.dao.RoleDao;
import com.studentRequest.dao.StaffDao;
import com.studentRequest.dao.StudentDao;
import com.studentRequest.dao.StudentInfoDao;
import com.studentRequest.dao.UserDao;
import com.studentRequest.dao.UserRoleDao;
import com.studentRequest.model.Faculty;
import com.studentRequest.model.Role;
import com.studentRequest.model.Staff;
import com.studentRequest.model.Student;
import com.studentRequest.model.StudentInfo;
import com.studentRequest.model.User;
import com.studentRequest.model.UserRole;
import com.studentRequest.services.SearchService;

@Service
public class SearchServiceImp implements SearchService {
	
	@Autowired
	private FacultyDao facultyDao;
	
	@Autowired
	private StaffDao staffDao;
	
	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private UserRoleDao userRoleDao;
	
	@Autowired
	private StudentInfoDao studInfoDao;

	@Override
	public List<Faculty> findByFaculty(Faculty faculty) {
		
		return facultyDao.findByFaculty(faculty);
	}

	@Override
	public List<Faculty> findByDepartment(int id) {
		Faculty findId = facultyDao.findById(id).get();
		return facultyDao.findByFaculty(findId);
	}

	@Override
	public Faculty facultyid(int id) {
		
		return facultyDao.findById(id).get();
	}

	@Override
	public List<Staff> findByIdList(String id) {
		
		return staffDao.findListById(id);
	}

	@Override
	public List<Staff> findByFacultyList(int faculty) {
		Faculty fa = facultyDao.findById(faculty).get();
		List<Faculty> f = facultyDao.findByFaculty(fa);
		List<Staff> sta = new ArrayList<Staff>();
		
		for(Faculty fac: f) {
			sta = staffDao.findByFaculty(fac);
		}
		return sta;
	}

	@Override
	public List<Student> studentFindById(String id) {
		
		return studentDao.findListById(id);
	}

	@Override
	public List<Student> studentFindByFacultyList(int faculty) {
		Faculty fa = facultyDao.findById(faculty).get();
		List<Faculty> f = facultyDao.findByFaculty(fa);
		List<Student> sta = new ArrayList<Student>();
		
		for(Faculty fac: f) {
			sta = studentDao.findByFaculty(fac);
		}
		return sta;
	}

	@Override
	public List<User> findAllStaffAccount() {
		
		return userDao.findByStudent(null);
	}

	@Override
	public List<User> findAllStudentAccount() {
		
		return userDao.findByStaff(null);
	}

	@Override
	public List<Role> roleList() {
		
		return roleDao.findAll();
	}

	@Override
	public List<UserRole> findByIdList(int id) {
		
		return userRoleDao.findById(id);
	}

	@Override
	public List<UserRole> findAllUserRole() {
		
		return userRoleDao.findAll();
	}

	@Override
	public User findById(int id) {
		
		return userDao.findById(id).get();
	}

	@Override
	public boolean findByuser(User user) {
		
		return userRoleDao.existsByUser(user);
	}

	@Override
	public boolean findByRole(Role role) {
		
		return userRoleDao.existsByRole(role);
	}

	@Override
	public boolean findByActive(boolean active) {
		
		return userRoleDao.existsByActive(active);
	}

	@Override
	public void deleteUserRole(int id) {
		 userRoleDao.deleteById(id);
	}

	@Override
	public UserRole findByUserRoleId(int id) {
		
		return userRoleDao.getOne(id);
	}

	@Override
	public UserRole findByuserAndRole(User user, Role role) {
		
		return userRoleDao.findByUserAndRole(user, role);
	}

	@Override
	public StudentInfo findById(String id) {
		return studInfoDao.findById(id).get();
	}

	@Override
	public StudentInfo saveStudent(StudentInfo info) {
		
		return studInfoDao.save(info);
	}

	@Override
	public List<Role> findAllRole() {
		
		return roleDao.findAll();
	}

	

}
