package com.example.todo.app.controller;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.todo.app.form.SignupForm;
import com.example.todo.app.service.SignupService;

@Controller
@SessionAttributes(names="signupForm")
public class SignupController {

    private final SignupService signupService;
    private final MessageSource messageSource;
    
	@ModelAttribute
	SignupForm signupForm() {
		return new SignupForm();
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String index() {
		return "signup";
	}

	@RequestMapping(value = "/signup/post", method = RequestMethod.POST)
	public String post(@Validated SignupForm form, BindingResult result, RedirectAttributes attributes, SessionStatus sessionStatus) {
		if (result.hasErrors()) {
            List<String> errors = result.getAllErrors()
                    .stream()
                    .map(ObjectError::toString)
                    .collect(Collectors.toList());
            attributes.addFlashAttribute("errors", errors);
			return "redirect:/signup";
		}
		if (this.signupService.isDuplicatedUsername(form.getUsername())) {
		    String error = this.messageSource.getMessage("signup.username.duplicated", null, Locale.JAPANESE);
		    attributes.addFlashAttribute("errors", Collections.singletonList(error));
		    return "redirect:/signup";
		}
		
		signupService.register(form.getUsername(), form.getPassword(), form.getSavingDisplayName());
		sessionStatus.setComplete();
		return "redirect:/";
	}

	@Autowired
    public SignupController(SignupService signupService, MessageSource messageSource) {
        this.signupService = signupService;
        this.messageSource = messageSource;
    }
}
