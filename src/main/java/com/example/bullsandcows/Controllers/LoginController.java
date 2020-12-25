package com.example.bullsandcows.Controllers;

import com.example.bullsandcows.Entity.Role;
import com.example.bullsandcows.Entity.User;
import com.example.bullsandcows.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){
        User userFromDB = userRepo.findByUsername(user.getUsername());
        if(userFromDB !=null){
            model.put("message", "User exists!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setSteps(0);
        user.setNumberAttempts(0);
        user.setStatistic(0.0);
        userRepo.save(user);
        return "redirect:/login";
    }
}
