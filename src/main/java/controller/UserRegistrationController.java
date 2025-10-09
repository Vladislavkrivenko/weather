package controller;

import dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.UserRegistrationService;

@Slf4j
@Controller
@RequestMapping("/register")
public class UserRegistrationController {
    @Autowired
    private UserRegistrationService userRegistrationService;

    @GetMapping("/register")
    public String registerForm(Model model, UserDto userDto) {
        model.addAttribute("userDto", userDto);
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("userDto") UserDto userDto) {
        userRegistrationService.saveUser(userDto);
        log.debug("User registered successfully: {}", userDto.login());
        return "redirect:/login";
    }
}
