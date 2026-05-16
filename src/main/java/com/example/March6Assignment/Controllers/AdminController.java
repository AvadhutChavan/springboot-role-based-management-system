package com.example.March6Assignment.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.March6Assignment.Pojo.User;
import com.example.March6Assignment.Service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

@Autowired
UserService service;
@GetMapping("/display")
public String display(@RequestParam(required=false) String city,
                      @RequestParam(required=false) String bloodgroup,
                      @RequestParam(required=false) String gender,
                      Model model){

    List<User> users;
    model.addAttribute("role","admin");
    
    System.out.println("AdminController.display()");

    if(city == null && bloodgroup == null && gender == null){
        users = service.getAllUsers();   // login ke baad full data
    } else {
        users = service.searchUser(city, bloodgroup, gender);  // search result
    }

    model.addAttribute("users", users);

    return "admin_display";
}

@GetMapping("/delete")
public String delete(@RequestParam int id){
    service.deleteUser(id);
    return "redirect:/admin/display";
}

@GetMapping("/search")
public String searchPage(Model model){
    model.addAttribute("role","admin");
    return "search";
}

@GetMapping("/edit")
public String editPage(@RequestParam int id, Model model){
    model.addAttribute("user", service.getUserById(id));
    model.addAttribute("role","admin");
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

    return "redirect:/admin/display";
}


@GetMapping("/insert")
public String insertPage(Model model){
    model.addAttribute("user", new User());
    return "signup";
}

}