package rokklancar.ferdydurkeaudiobookplayer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class WebsiteController {

    @GetMapping("/")
    public String homepage(Principal principal, Model model) {
        model.addAttribute("authenticatedUserName", principal.getName());
        return "index.html";
    }
}
