package com.example.March6Assignment.Controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.March6Assignment.Pojo.User;
import com.example.March6Assignment.Service.UserService;


@Controller
@RequestMapping("/guest")
public class GuestController {

@Autowired
UserService service;

@GetMapping("/display")
public String display(@RequestParam(required=false) String city,
                      @RequestParam(required=false) String bloodgroup,
                      @RequestParam(required=false) String gender,
                      Model model){

    List<User> users;
    model.addAttribute("role","guest");

    if(city == null && bloodgroup == null && gender == null){
        users = service.getAllUsers();   // login ke baad full data
    } else {
        users = service.searchUser(city, bloodgroup, gender);  // search result
    }

    model.addAttribute("users", users);

    return "guest_display";
}

@GetMapping("/search")
public String searchPage(Model model){
    model.addAttribute("role","guest");
    return "search";
}

}
