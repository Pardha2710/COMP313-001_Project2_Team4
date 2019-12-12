package com.college;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

	@Autowired
	UserService userservice;

	@RequestMapping("/")
	public String home() {
		return "index";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin() {

		return "admin";
	}
	

	@RequestMapping(value = "/faculty", method = RequestMethod.GET)
	public String faculty(Model model) {

		List<Users> list = userservice.viewFaculties();
		model.addAttribute("facultyList", list);

		return "viewfaculties";
	}

	@RequestMapping(value = "/registration")
	public String registration() {

		return "registration";
	}

	@RequestMapping("/courses")
	public String courses() {
		return "courses";
	}

	@RequestMapping(value = "/registrationSuccess")
	public String registrationSuccess(@ModelAttribute("registrationForm") Users users, Model model) {

		userservice.insert(users);

		if (users.getRole().equalsIgnoreCase("Faculty")) {
			return "faculty";
		} else {
			return "admin";
		}

	}

	@RequestMapping(value = "/login")
	public @ResponseBody ModelAndView login(@RequestParam("username") String userName,
			@RequestParam("password") String pass) {
		ModelAndView mv = new ModelAndView();
		String role = "admin";
		boolean bool = userservice.fetchAdminByUserNamePass(userName, pass, role);
		if (bool == true) {
			mv.setViewName("adminLoginSuccess");
			return mv;

		} else {
			mv.setViewName("adminLoginUnSuccess");
			return mv;

		}

	}

	@PostMapping("/facultyLoginSuccess")
	public @ResponseBody String add(@RequestParam("username") String userName,
			@RequestParam("password") String password) {

		String role = "faculty";
		boolean bool = userservice.fetchByUserNamePass(userName, password, role);
		if (bool == true) {
			return "loginsuccess";
		} else {
			return "loginunsuccess";

		}

	}

	@RequestMapping(value = "/addCourse")
	public String addCourse(@ModelAttribute("courseForm") Courses courses, Model model) {

		userservice.insertCourses(courses);
		return "Course Added Successfully";

	}

	@RequestMapping(path = "/approve/{id}")
	public String approveFaculty(Model model, @PathVariable("id") int id)

	{
		ApprovedFaculty af=new ApprovedFaculty();
		Optional<Users> approvedFacultyData = userservice.approveFaculty(id);

		approvedFacultyData.ifPresent(user -> {
			af.setUserId(user.getUserId());
			af.setFirstName(user.getFirstName());
			af.setLastName(user.getLastName());
			af.setEmail(user.getEmail());
			af.setUsername(user.getUsername());
			af.setPassword(user.getPassword());
			af.setRole(user.getRole());
		        
		});
		userservice.insertApprovedFaculty(af);
		return "approvefaculty";
	}
	
	@RequestMapping(value="/assigncourse")
	public ModelAndView assignCourse(Model model) 
	{
		ModelAndView mv = new ModelAndView();
		List<Courses> courseList = userservice.viewCourses();
		model.addAttribute("courselist",courseList);
		for (Courses courses : courseList) {
			System.out.println(courses.courseName);
		};
		mv.setViewName("assigncourses");
		return mv;
	}
	@RequestMapping(value="/assign")
	public @ResponseBody String assignedData(@RequestParam("courseName") String courseName,@RequestParam("username") String facultyName) 
	{
		AssignedData ad = new AssignedData();
		
		
		ad.setCourseName(courseName);
		ad.setFacultyName(facultyName);
		System.out.println("Assigned faculty and Course : " + facultyName + " ," + courseName + " respectively");
		return "";
	}

}
