package es.codeurjc.gamepost.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.codeurjc.gamepost.objects.CustomList;
import es.codeurjc.gamepost.objects.Game;
import es.codeurjc.gamepost.objects.User;
import es.codeurjc.gamepost.repositories.CustomListRepository;
import es.codeurjc.gamepost.repositories.ForumEntryRepository;
import es.codeurjc.gamepost.repositories.GameRepository;
import es.codeurjc.gamepost.repositories.UserRepository;

@Controller
public class IndexController {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ForumEntryRepository forumEntryRepository;

    @Autowired
    CustomListRepository customListRepository;
    
    @GetMapping("/")
    public String enlace(Model model){
        List<Game> games = gameRepository.findAll();
        Optional<User> user = userRepository.findByName("Mariam");
        //TODO: model.addAttribute("user", user);
        model.addAttribute("games", games);
        if(user.isPresent()){
            List<CustomList> customLists = customListRepository.findByUser(user.get()); 
            model.addAttribute("list", customLists);
            model.addAttribute("user", user.get());
        }

        //Show forum entries
        model.addAttribute("latestForumEntries", forumEntryRepository.findAll(Sort.by("lastUpdatedOn")));

        return "index";
    }
    
    @GetMapping("/signin")
    public String signin(Model model){
        return "signin";
    }

    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }

    @GetMapping("/profile")
    public String profile(Model model){
        Optional<User> user = userRepository.findByName("Mariam");
        if(user.isPresent()){
            model.addAttribute("user", user.get());
        }
        //TODO: model.addAttribute("user", user);
        return "profile";
    }


}
