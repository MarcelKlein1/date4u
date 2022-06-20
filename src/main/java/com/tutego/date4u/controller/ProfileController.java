package com.tutego.date4u.controller;

import com.tutego.date4u.entities.Profile;
import com.tutego.date4u.entities.ProfileFormData;
import com.tutego.date4u.entities.Unicorn;
import com.tutego.date4u.repositories.PhotoRepository;
import com.tutego.date4u.repositories.ProfileRepository;
import com.tutego.date4u.repositories.UnicornRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ProfileController {
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    UnicornRepository unicornRepository;
    @Autowired
    PhotoRepository photoRepository;

    //Profilepage
    @RequestMapping("/profile/{id}")
    public String profilePage(@PathVariable long id, Model model, Principal principal) {
        Optional<Profile> maybeProfile = profileRepository.findById(id);
        if (!maybeProfile.isPresent())
            return "redirect:/";

        model.addAttribute("user", unicornRepository.findByEmail(principal.getName()).getProfile().getId());

        //Profile is the users own profile
        if (id == unicornRepository.findByEmail(principal.getName()).getProfile().getId()) {
            model.addAttribute("isOwnProfile", true);
        }

        Profile profile = maybeProfile.get();
        model.addAttribute("unicornEmail", unicornRepository.findByProfile(profile).getEmail());
        model.addAttribute("profile",
                new ProfileFormData(profile.getId(),
                        profile.getBirthdate(),
                        profile.getNickname(),
                        (short) profile.getHornlength(),
                        (short) profile.getGender(),
                        profile.getAttractedToGender(),
                        profile.getDescription(),
                        profile.getLastseen()));

        List<String> allPhotoListNames = new ArrayList<>();

        if (photoRepository.findByProfile(profile) != null) {
            photoRepository.findByProfile(profile).forEach(photo -> allPhotoListNames.add(photo.getName()));
            if (photoRepository.findByProfilePhoto(profile) != null) {
                //Find profilepicture
                model.addAttribute("profilePhoto", photoRepository.findByProfilePhoto(profile).getName());
            }
            //Find all pictures associated with profile
            model.addAttribute("allPhotos", allPhotoListNames);
            model.addAttribute("numberOfPhotos", allPhotoListNames.size());
        } else {
            model.addAttribute("noPhoto", true);
        }

        return "profile";
    }

    //Create a new Profile
    @RequestMapping(value = "/createProfile", method = RequestMethod.GET)
    public String profileCreation(Principal principal, Unicorn unicorn, Profile profile) {
        if (principal != null) {
            if (unicornRepository.findByEmail(principal.getName()).getProfile() != null) {
                return "/index";
            }
            return "createProfile";
        }
        return "/index";
    }

    //Save the new Profile
    @RequestMapping(value = "/saveProfile", method = RequestMethod.POST)
    public String profileCompleted(@ModelAttribute("profile") Profile profile, Principal principal) {

        unicornRepository.findByEmail(principal.getName()).setProfile(profile);
        profileRepository.save(profile);


        return "redirect:/profile/" + profile.getId();
    }

    @RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
    public String updateProfile(@ModelAttribute("profile") Profile profile) {

        profile.setId(profile.getId());
        profile.setNickname(profile.getNickname());
        profile.setBirthdate(profile.getBirthdate());
        profile.setHornlength(profile.getHornlength());
        profile.setGender(profile.getGender());
        profile.setAttractedToGender(profile.getAttractedToGender());
        profile.setDescription(profile.getDescription());
        profile.setLastseen(profile.getLastseen());
        profileRepository.save(profile);

        return "redirect:/profile/" + profile.getId();
    }
}
