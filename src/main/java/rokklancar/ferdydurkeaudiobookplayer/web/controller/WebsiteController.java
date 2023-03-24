package rokklancar.ferdydurkeaudiobookplayer.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import rokklancar.ferdydurkeaudiobookplayer.web.dto.UserDto;
import rokklancar.ferdydurkeaudiobookplayer.persistence.model.User;

import java.security.Principal;

@Controller
public class WebsiteController {

    @GetMapping("/")
    public String homepage(Principal principal) {
        if (principal == null) {
            return "redirect:/neprijavljeni";
        }
        return "redirect:/prijavljeni";
    }

    // Controllers for unauthenticated users
    @GetMapping("/neprijavljeni")
    public String homepageUnauthenticated() {
        return "index.html";
    }

    @GetMapping("/neprijavljeni/o_knjigi")
    public String aboutPageUnauthenticated() {
        return "about.html";
    }

    @GetMapping("/neprijavljeni/registracija")
    public String register(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "register.html";
    }

    @PostMapping("/neprijavljeni/registracija/submit")
    public String register(
            @ModelAttribute("user") @Valid UserDto userDto,
            HttpServletRequest request,
            Errors errors) {

        try {
            User registered = userService.registerNewUserAccount(userDto);
        }

    }

    // Controllers for authenticated users
    @GetMapping("/prijavljeni")
    public String homepageAuthenticated(Principal principal, Model model) {
        model.addAttribute("authenticatedUserName", principal.getName());
        return "index_authenticated.html";
    }

    @GetMapping("/prijavljeni/o_knjigi")
    public String aboutPageAuthenticated(Principal principal, Model model) {
        model.addAttribute("authenticatedUserName", principal.getName());
        return "about_authenticated.html";
    }
}
