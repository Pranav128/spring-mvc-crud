package com.app.mvc.entity;

import com.app.mvc.validators.ConfirmFields;
import com.app.mvc.validators.DateValidator;
import com.app.mvc.validators.PasswordValidator;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "employee")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

@ConfirmFields.list(@ConfirmFields(match1 = "password", match2 = "confirmPass", message = "password not matches with confirm pass"))

public class Employee extends BaseEntity {
	// define fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "first_name")
	@NotEmpty(message = "Firstname can't be empty")
	@Pattern(regexp = "^[A-Za-z ]{3,15}$", message = "Only characters are allowed")
	private String firstName;

	@Column(name = "last_name")
	@NotEmpty(message = "Lastname can't be empty")
	@Pattern(regexp = "^[A-Za-z ]{3,15}$", message = "Only characters are allowed")
	private String lastName;

	@Column(unique = true)
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid Email")
	@NotEmpty(message = "Email can't be empty")
	private String email;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past(message = "DOB not in future")
	@DateValidator
	private LocalDate dob;

	@NotEmpty(message = "Password can't be empty")
//    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()\\-+=_<>?]).{6,}$",
//            message = "Password must contain at least one upper,one lower, one digit and one special char")
	@PasswordValidator(regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()\\-+=_<>?]).{6,}$", message = "please give a strong password and password must contain at least one upper,one lower, one digit and one special char")
	private String password;

	@NotEmpty(message = "confirm password can't be empty")
	private String confirmPass;

	@Column(nullable = false)
	@NotEmpty(message = "Country must be given")
	private String country;

	@NotEmpty(message = "Gender can't empty")
	private String gender;



}