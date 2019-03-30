package com.example.todo.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.todo.app.form.SignupForm;
import com.example.todo.app.service.SignupService;

@Controller
public class SignupController {

    private final SignupService signupService;
    
	@ModelAttribute
	SignupForm setUpForm() {
		return new SignupForm();
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String index(Model model) {
		return "signup";
	}

	@RequestMapping(value = "/signup/post", method = RequestMethod.POST)
	public String post(@Validated SignupForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<ObjectError> allErrors = result.getAllErrors();
			return "redirect:/signup";
		}
		if (this.signupService.isDuplicatedUsername(form.getUsername())) {
		    // TODO 何か
		    return "redirect:/signup";
		}
		
		signupService.register(form.getUsername(), form.getPassword(), form.getSavingDisplayName());
		return "redirect:/";
	}

	@Autowired
    public SignupController(SignupService signupService) {
        this.signupService = signupService;
    }
}
