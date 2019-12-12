package com.college;

import java.util.List;
import java.util.Optional;

public interface IUserService {

	Optional<Users> findById(int id);

	public void insert(Users users);

	public boolean fetchByUserNamePass(String userName, String pass, String role);

	public boolean fetchAdminByUserNamePass(String userName, String pass, String role);

	public void insertCourses(Courses courses);

	public List<Users> viewFaculties();

	public Optional<Users> approveFaculty(int userId);

	public void insertApprovedFaculty(ApprovedFaculty users);

	public List<Courses> viewCourses();
}
