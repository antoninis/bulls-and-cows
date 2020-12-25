package com.example.bullsandcows.Controllers;

import com.example.bullsandcows.Entity.User;
import com.example.bullsandcows.Repo.UserRepo;
import com.example.bullsandcows.Services.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class GameController {
    private String result = null;
    private StringBuffer outputResult= new StringBuffer();
    private int step = 0;
    private String output =null;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String start(){
        return "start";
    }

    @GetMapping("/game")
    public String startGame(Model model){
        Game.setNumber();
        step = 0;
        outputResult.delete(0, outputResult.length());
        result=null;
        return "game";
    }

    @PostMapping("/game")
    public String Game(@RequestParam String userNumber, Model model, User username, Principal principal){
        result = Game.countBullsAndCows(userNumber);
        output = "Step "+ ++step + ":  " + userNumber +" - " +result;
        if (result =="4 bulls") {
            User userDB = userRepo.findByUsername(principal.getName());
            username.setId(userDB.getId());
            username.setUsername(userDB.getUsername());
            username.setPassword(userDB.getPassword());
            username.setSteps(userDB.getSteps()+step);
            username.setNumberAttempts(userDB.getNumberAttempts()+1);
            username.setStatistic(((double) (username.getSteps())/((double) username.getNumberAttempts())));
            username.setRoles(userDB.getRoles());
            userRepo.save(username);
            System.out.println("-------------------");
            return "redirect:/rating";
        }
        outputResult.append(output+ '\n');
        model.addAttribute("result", outputResult.toString());
        return "game";
    }

    @GetMapping("/rating")
    public String rating(Model model){
        model.addAttribute("number", Game.computerNumber);
        model.addAttribute("steps", step);
        List users = userRepo.findAll(Sort.by("statistic"));
        model.addAttribute("users", users);
        return "rating";
    }
}
