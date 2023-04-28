package com.example;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class AppController {

  private final UserRepository userRepository;

  public AppController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("/")
  public String viewHomePage() {
    System.out.println("home page");
    return "index";
  }

  @GetMapping("/register")
  public String showRegistrationForm(Model model) {
    model.addAttribute("user", new User());
    return "signup_form";
  }

  @PostMapping("/process_register")
  public String processRegister(User user) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String encodedPassword = passwordEncoder.encode(user.getPassword());
    user.setPassword(encodedPassword);
    userRepository.save(user);
    return "register_success";
  }

  @GetMapping("/users")
  public String listUsers(Model model , Principal principal, Authentication authentication){
    List<User> list = userRepository.findAll();
    model.addAttribute("listUsers",list);
    model.addAttribute("principal",principal.getName());
    return "users";
  }
}
