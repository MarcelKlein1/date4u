package com.tutego.date4u.controller;

import com.tutego.date4u.entities.Photo;
import com.tutego.date4u.entities.Profile;
import com.tutego.date4u.repositories.PhotoRepository;
import com.tutego.date4u.repositories.ProfileRepository;
import com.tutego.date4u.repositories.UnicornRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    UnicornRepository unicornRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    PhotoRepository photoRepository;

    @RequestMapping("/search")
    public String searchPage(Model model, Principal principal) {
        model.addAttribute("user", unicornRepository.findByEmail(principal.getName()).getProfile().getId());
        return "search";
    }

    @RequestMapping("/results")
    public String searching(Model model, Principal principal, int minAge, int maxAge, byte gender, short minHorn, short maxHorn) {
        model.addAttribute("user", unicornRepository.findByEmail(principal.getName()).getProfile().getId());

        LocalDate currentDate = LocalDate.now();

        //filtering Profiles by hornlength and gender
        Iterable<Profile> profiles = profileRepository.findProfileByGenderAndHornlength(gender, minHorn, maxHorn);
        List<Profile> profileListWithMatchingAge = new ArrayList<>();

        //calculating the age of profiles and filter them
        for (Profile profile : profiles) {
            if (Period.between(profile.getBirthdate(), currentDate).getYears() >= minAge &&
                    Period.between(profile.getBirthdate(), currentDate).getYears() <= maxAge) {
                profileListWithMatchingAge.add(profile);
            }
        }

        List<Photo> profilePhotos = new ArrayList<>();
        for(Profile profile : profileListWithMatchingAge) {
            profilePhotos.add(photoRepository.findByProfilePhoto(profile));
        }

        model.addAttribute("photoResultList", profilePhotos);
        model.addAttribute("resultList", profileListWithMatchingAge);

        return "search";
    }
}
