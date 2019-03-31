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

import com.example.todo.app.entity.Account;
import com.example.todo.app.exception.AuthenticateException;
import com.example.todo.app.form.SigninForm;
import com.example.todo.app.form.SignupForm;
import com.example.todo.app.service.SigninService;
import com.example.todo.app.service.SignupService;

@Controller
public class SigninController {

    private final SigninService signinService;
    
	@ModelAttribute
	SigninForm setinForm() {
		return new SigninForm();
	}

	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String index(Model model) {
		return "signin";
	}

	@RequestMapping(value = "/signin/post", method = RequestMethod.POST)
	public String post(@Validated SigninForm form, BindingResult result, Model model) {
		try {
            if (result.hasErrors()) {
            	List<ObjectError> allErrors = result.getAllErrors();
            	return "redirect:/signin";
            }
            Account account = signinService.authenticate(form.getUsername(), form.getPassword());
            return "redirect:/";
        } catch (AuthenticateException e) {
            return "redirect:/signin";
        }
	}

	@Autowired
    public SigninController(SigninService signinService) {
        this.signinService = signinService;
    }
}
