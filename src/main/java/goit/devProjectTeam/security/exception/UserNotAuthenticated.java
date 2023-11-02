package goit.devProjectTeam.security.exception;

public class UserNotAuthenticated extends Exception{
	public UserNotAuthenticated(String message) {
		super(message);
	}
}
