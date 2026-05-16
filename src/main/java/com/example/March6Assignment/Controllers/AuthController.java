package com.example.March6Assignment.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.March6Assignment.Pojo.User;
import com.example.March6Assignment.Service.UserService;

@Controller
public class AuthController {

	@Autowired
	private UserService service;

	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/signup")
	public String signupPage(Model model) {
		(model).addAttribute("user", new User());
		return "signup";
	}

	@PostMapping("/signup")
	public String registerUser(@ModelAttribute User user,Model model) {

		System.out.println(user.getName());
		System.out.println(user.getDob());
		System.out.println(user.getCity());
		System.out.println(user.getGender());
		System.out.println(user.getBloodgroup());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getRole());
		
		
		Optional<User> existingUser = service.findByUsername(user.getUsername());

		if(existingUser.isPresent()) {
			
			model.addAttribute("error", "Username already exists...");
			return "signup";
		}
		else {
			service.saveUser(user);
			return "redirect:/login";
		}
		
	}

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

	@PostMapping("/loginUser")
	public String login(@RequestParam String username, @RequestParam String password, Model model) {

		System.out.println("Login method called");

		Optional<User> user = service.findByUsername(username);

		System.out.println("username is = " + username);
		System.out.println("password is = " + password);

		if (user.isPresent()) {

			User dbUser = user.get();

			System.out.println("db username is = " + dbUser.getUsername());
			System.out.println("db password is = " + dbUser.getPassword());

			if (dbUser.getPassword().equals(password)) {

				System.out.println("User found: " + dbUser.getRole());

				if (dbUser.getRole().equals("ADMIN")) {
					return "redirect:/admin/display";
				}

				if (dbUser.getRole().equals("USER")) {
					return "redirect:/user/display";
				}

				if (dbUser.getRole().equals("GUEST")) {
					return "redirect:/guest/display";
				}
			}

			if (!user.get().getPassword().equals(password)) {
				model.addAttribute("error", "Wrong password...");
//				return "login";
			}

		} else {
			model.addAttribute("error", "Username not found...");
//			return "login";
		}

		
		return "login";
	}
}