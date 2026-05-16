package com.example.March6Assignment.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.March6Assignment.Pojo.User;
import com.example.March6Assignment.Service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

@Autowired
UserService service;

@GetMapping("/display")
public String display(@RequestParam(required=false) String city,
                      @RequestParam(required=false) String bloodgroup,
                      @RequestParam(required=false) String gender,
                      Model model){

    List<User> users;
    model.addAttribute("role","user");

    if(city == null && bloodgroup == null && gender == null){
        users = service.getAllUsers();   // login ke baad full data
    } else {
        users = service.searchUser(city, bloodgroup, gender);  // search result
    }

    model.addAttribute("users", users);

    return "user_display";
}




@GetMapping("/search")
public String searchPage(Model model){
    model.addAttribute("role","user");
    return "search";
}

@GetMapping("/edit")
public String edit(@RequestParam int id, Model model){
    model.addAttribute("user", service.getUserById(id));
    model.addAttribute("role","user");
    return "edit";
}

@PostMapping("/update")
public String update(@ModelAttribute User user, Model model){

    User existingUser = service.getUserById(user.getId());

    existingUser.setCity(user.getCity());
    existingUser.setBloodgroup(user.getBloodgroup());
    existingUser.setGender(user.getGender());

    service.saveUser(existingUser);
    
    model.addAttribute("role","user");

    return "redirect:/user/display";
}

}
