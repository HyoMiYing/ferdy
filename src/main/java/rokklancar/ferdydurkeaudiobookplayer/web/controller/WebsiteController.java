package rokklancar.ferdydurkeaudiobookplayer.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import rokklancar.ferdydurkeaudiobookplayer.persistence.dao.UserRepository;
import rokklancar.ferdydurkeaudiobookplayer.service.IUserService;
import rokklancar.ferdydurkeaudiobookplayer.service.MediaStreamLoader;
import rokklancar.ferdydurkeaudiobookplayer.web.dto.UserDto;
import rokklancar.ferdydurkeaudiobookplayer.persistence.model.User;
import rokklancar.ferdydurkeaudiobookplayer.web.error.UserAlreadyExistsException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
public class WebsiteController {

    private static final Logger log = LoggerFactory.getLogger(WebsiteController.class);

    private IUserService userService;

    private MediaStreamLoader mediaLoaderService;

    private UserRepository repository;

    public WebsiteController(IUserService userService, UserRepository userRepository, MediaStreamLoader mediaLoaderService) {
        this.userService = userService;
        this.repository = userRepository;
        this.mediaLoaderService = mediaLoaderService;
    }

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
    public String register(Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "register.html";
    }

    @PostMapping("/neprijavljeni/registracija")
    public ModelAndView register(
            @ModelAttribute("user") @Valid UserDto userDto,
            HttpServletRequest request
    ) {
        try {
            final User registered = userService.registerNewUserAccount(userDto);
            return new ModelAndView("index.html");
        } catch (final UserAlreadyExistsException exception) {
            return new ModelAndView("register").addObject("userExistsException", exception);
        }
    }

    @GetMapping("/neprijavljeni/ferdydurke_stream")
    @ResponseBody
    public ResponseEntity<StreamingResponseBody> ferdydurke_stream(
            @RequestHeader(value = "Range", required = false)
            String rangeHeader,
            @RequestParam String chapter,
            HttpServletRequest request)
    {
        try
        {
            String filePathString = "src/main/resources/static/" + chapter;
            ResponseEntity<StreamingResponseBody> returnVal = mediaLoaderService.loadPartialMediaFile(filePathString, rangeHeader);

            return returnVal;
        }
        catch (FileNotFoundException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (IOException e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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

    @ExceptionHandler(BindException.class)
    public ModelAndView validationError(BindException exception) {
        BindingResult result = exception.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        final List<ObjectError> globalErrors = result.getGlobalErrors();
        final List<ObjectError> allErrors = result.getAllErrors();

        UserDto user = new UserDto();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("fieldErrors", fieldErrors);
        modelAndView.addObject("globalErrors", globalErrors);
        modelAndView.addObject("user", user);

        modelAndView.setViewName("register.html");

        return modelAndView;
    }
}
