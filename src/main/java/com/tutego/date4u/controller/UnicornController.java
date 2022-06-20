package com.tutego.date4u.controller;

import com.tutego.date4u.entities.Unicorn;
import com.tutego.date4u.repositories.PhotoRepository;
import com.tutego.date4u.repositories.ProfileRepository;
import com.tutego.date4u.repositories.UnicornRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class UnicornController {

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    UnicornRepository unicornRepository;

    @Autowired
    PhotoRepository photoRepository;

    //Register a new unicorn
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registration(Principal principal, Unicorn unicorn) {
        if (principal != null) {
            return "/index";
        }

        return "register";
    }

    @Autowired
    protected AuthenticationManager authenticationManager;

    //Save the unicorn
    @RequestMapping(value = "/saveUnicorn", method = RequestMethod.POST)
    public String unicornCompleted(@ModelAttribute("unicorn") Unicorn unicorn, HttpServletRequest request) {
        unicorn.setPassword("{noop}" + unicorn.getPassword());
        unicornRepository.save(unicorn);

        authenticateUserAndSetSession(unicorn, request);

        return "redirect:/createProfile";
    }

    private void authenticateUserAndSetSession(Unicorn unicorn, HttpServletRequest request) {
        String username = unicorn.getEmail();
        String password = unicorn.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        request.getSession();
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
