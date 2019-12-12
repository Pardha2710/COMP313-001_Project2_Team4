package com.college;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private ApprovedFacultyRepository approvedFacultyRepository;

	@Override
	public Optional<Users> findById(int id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id);
	}

	@Override
	public void insert(Users users) {

		userRepository.save(users);
	}

	@Override
	public boolean fetchByUserNamePass(String userName, String pass,String role) {
		boolean b = false;
		List<ApprovedFaculty> usersList = approvedFacultyRepository.findAll();
		for (ApprovedFaculty users : usersList) {
			if(users.getUsername().equalsIgnoreCase(userName) && users.getPassword().equalsIgnoreCase(pass) && users.getRole().equalsIgnoreCase(role)) 
			{
				b = true;
			}else 
			{
				b = false;
			}
			

		}
		return b;
	}

	@Override
	public boolean fetchAdminByUserNamePass(String userName, String pass,String role) {
		boolean b = false;
		List<Users> usersList = userRepository.findAll();
		for (Users users : usersList) {
			if(users.getUsername().equalsIgnoreCase(userName) && users.getPassword().equalsIgnoreCase(pass) && users.getRole().equalsIgnoreCase(role)) 
			{
				b = true;
			}else 
			{
				b = false;
			}
			

		}
		return b;

	}

	@Override
	public void insertCourses(Courses courses) {
		courseRepository.save(courses);
		
	}

	@SuppressWarnings("null")
	@Override
	public List<Users> viewFaculties() {
		
		List<Users> list = userRepository.findAll();
		try {
		for (Users users : list) {
			if(users.getRole().equalsIgnoreCase("faculty")) 
			{
				list.add(users);
				
			}
			
			
		}
		}catch(Exception e) {}
		return list;
	}

	@Override
	public Optional<Users> approveFaculty(int userId) {
		//long uId=(long) userId;
		Optional<Users> approvedData = userRepository.findById(userId);
		return approvedData;
		
		
		
	}

	@Override
	public void insertApprovedFaculty(ApprovedFaculty users) {
		
		approvedFacultyRepository.save(users);
		userRepository.deleteById(users.getUserId());

		
	}

	@Override
	public List<Courses> viewCourses() {
		List<Courses> l = courseRepository.findAll();
		return l;
	}

}
