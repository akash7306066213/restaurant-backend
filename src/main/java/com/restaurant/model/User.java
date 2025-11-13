package com.restaurant.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Primary key
    
    @Column(nullable = false)
    private String name;  // User name

    @Column(unique = true, nullable = false)
    private String email; // Unique email ID

    @Column(nullable = false)
    private String password; // Encrypted password

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;// "ROLE_CUSTOMER" or "ROLE_ADMIN"

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
				+ "]";
	}

	public void setRole(Role role) {
		this.role = role;
	}

	
}
