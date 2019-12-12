package com.college;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "approvedfaculty")
public class ApprovedFaculty {

	@Id
	@Column(name = "faculty_id")
	private int userId;

	@NotNull
	@Column(name = "first_name")
	private String firstName;

	@NotNull
	@Column(name = "last_name")
	private String lastName;

	@NotNull
	@Column(name = "email")
	private String email;

	@NotNull
	@Column(name = "username")
	private String username;

	@NotNull
	@Column(name = "password")
	private String password;

	@NotNull
	@Column(name = "user_role")
	private String role;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public ApprovedFaculty(int userId, @NotNull String firstName, @NotNull String lastName, @NotNull String email,
			@NotNull String username, @NotNull String password, @NotNull String role) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public ApprovedFaculty() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
