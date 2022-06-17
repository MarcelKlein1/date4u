package com.tutego.date4u.controller;


import com.tutego.date4u.repositories.PhotoRepository;
import com.tutego.date4u.repositories.ProfileRepository;

import com.tutego.date4u.repositories.UnicornRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;


@Controller
public class Date4uWebController {
    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    UnicornRepository unicornRepository;

    @Autowired
    PhotoRepository photoRepository;

    //Startpage
    @RequestMapping("/*")
    public String indexPage(Model model, Principal principal) {
        //How many Profiles registered
        model.addAttribute("profileNumber", profileRepository.count());
        //Does the User have an Account
        if (principal != null) {
            if (unicornRepository.findByEmail(principal.getName()).getProfile() != null) {
                model.addAttribute("user", unicornRepository.findByEmail(principal.getName()).getProfile().getId());
            }
            //Does the User have a Profile
            if (unicornRepository.findByEmail(principal.getName()).getProfile() == null) {
                model.addAttribute("noProfileSet", true);
            }
        } else {
            model.addAttribute("noUser", true);
        }
        return "index";
    }

}
