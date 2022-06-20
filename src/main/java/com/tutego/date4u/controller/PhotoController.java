package com.tutego.date4u.controller;

import com.tutego.date4u.entities.Photo;
import com.tutego.date4u.entities.Profile;
import com.tutego.date4u.repositories.PhotoRepository;
import com.tutego.date4u.repositories.ProfileRepository;
import com.tutego.date4u.repositories.UnicornRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class PhotoController {

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    UnicornRepository unicornRepository;

    @Autowired
    PhotoRepository photoRepository;

    @PostMapping("/uploadPhoto")
    public String uploadPhoto(Photo photo, Principal principal, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        String filename = UUID.randomUUID().toString();

        Profile profile = unicornRepository.findByEmail(principal.getName()).getProfile();

        //Saving Photo; Saving as profile photo if there was none before
        photo.setName(filename);
        photo.setProfile(profile);
        photo.setProfilePhoto(photoRepository.findByProfilePhoto(profile) == null);

        photoRepository.save(photo);

        String uploadDir = "src/main/resources/static/img/user/";

        FileUploadUtil.saveFile(uploadDir, filename + ".jpg", multipartFile);

        return "redirect:/profile/" + unicornRepository.findByEmail(principal.getName()).getProfile().getId();
    }

    @RequestMapping("/editProfilePhoto")
    public String editProfilePhoto(Principal principal, Model model) {
        List<String> allPhotoListNames = new ArrayList<>();

        Profile profile = unicornRepository.findByEmail(principal.getName()).getProfile();

        model.addAttribute("user", profile.getId());

        if (photoRepository.findByProfile(profile) != null) {
            photoRepository.findByProfile(profile).forEach(photo -> allPhotoListNames.add(photo.getName()));
            //Find all pictures associated with profile
            model.addAttribute("allPhotos", allPhotoListNames);
            model.addAttribute("allPhotoObjects", photoRepository.findByProfile(profile));
            model.addAttribute("numberOfPhotos", allPhotoListNames.size());
        } else {
            model.addAttribute("noPhoto", true);
        }

        return "/editProfilePhoto";
    }

    @PostMapping("/saveNewProfilePhoto")
    public String saveNewProfilePhoto(Principal principal, String photoName) {
        Profile profile = unicornRepository.findByEmail(principal.getName()).getProfile();

        //The old profile photo is no longer a profile photo
        Photo oldProfilePhoto = photoRepository.findByProfilePhoto(profile);
        oldProfilePhoto.setProfile(oldProfilePhoto.getProfile());
        oldProfilePhoto.setProfilePhoto(false);
        oldProfilePhoto.setName(oldProfilePhoto.getName());
        oldProfilePhoto.setCreated(oldProfilePhoto.getCreated());
        photoRepository.save(oldProfilePhoto);

        //saving the new profile photo as a profile photo
        Photo newProfilePhoto = photoRepository.findPhotoByName(photoName);
        newProfilePhoto.setProfilePhoto(true);
        newProfilePhoto.setCreated(newProfilePhoto.getCreated());
        newProfilePhoto.setProfile(newProfilePhoto.getProfile());
        newProfilePhoto.setName(newProfilePhoto.getName());
        photoRepository.save(newProfilePhoto);

        return "redirect:/profile/" + unicornRepository.findByEmail(principal.getName()).getProfile().getId();
    }
}
