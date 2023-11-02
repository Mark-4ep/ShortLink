package goit.devProjectTeam.user;

import goit.devProjectTeam.user.dto.RegistrationModel;
import goit.devProjectTeam.user.exception.IncorrectEmailException;
import goit.devProjectTeam.user.exception.IncorrectPasswordException;
import goit.devProjectTeam.user.exception.UserAlreadyExistException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserUiController {

    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        return new ModelAndView("login");
    }

    @GetMapping("/registration")
    public ModelAndView getRegistrationPage(RegistrationModel registrationModel) {
        ModelAndView modelAndView = new ModelAndView("registration");
        return modelAndView.addObject("user", registrationModel);
    }

    @PostMapping("/registration")
    public ModelAndView createUser(@Valid @ModelAttribute("registrationModel") RegistrationModel registrationModel)
            throws IncorrectEmailException, UserAlreadyExistException, IncorrectPasswordException {
        ModelAndView modelAndView = new ModelAndView("redirect:/login");
        userService.registerNewCustomer(registrationModel);
        return modelAndView;
    }

}
