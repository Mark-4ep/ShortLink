package goit.devProjectTeam.user;


import goit.devProjectTeam.user.dto.LoginModel;

import goit.devProjectTeam.user.dto.RegistrationModel;
import goit.devProjectTeam.user.exception.IncorrectEmailException;
import goit.devProjectTeam.user.exception.IncorrectPasswordException;
import goit.devProjectTeam.user.exception.UserAlreadyExistException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginModel loginModel) {
		try {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.loginUser(loginModel));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("/registration")
	public ResponseEntity<?> registration(@RequestBody RegistrationModel registrationModel) {
		try {
			userService.registerNewCustomer(registrationModel);
			return ResponseEntity.status(HttpStatus.CREATED).body("User created");
		} catch (UserAlreadyExistException | IncorrectPasswordException | IncorrectEmailException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
