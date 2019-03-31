package com.example.todo.app.controller;

import org.pac4j.core.profile.CommonProfile;
import org.pac4j.core.profile.ProfileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.todo.app.form.SigninForm;
import com.example.todo.app.service.SigninService;

@Controller
public class SigninController {

    @Autowired
    private SigninService signinService;
    @Autowired
    private ProfileManager<CommonProfile> profileManager;

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
        return "redirect:/";
    }

    //	@RequestMapping(value = "/signin/post", method = RequestMethod.POST)
    //	public String post(@Validated SigninForm form, BindingResult result, Model model) {
    //		try {
    //            if (result.hasErrors()) {
    //            	List<ObjectError> allErrors = result.getAllErrors();
    //            	return "redirect:/signin";
    //            }
    //            Account account = signinService.authenticate(form.getUsername(), form.getPassword());
    //            return "redirect:/";
    //        } catch (AuthenticateException e) {
    //            return "redirect:/signin";
    //        }
    //	}

//    @Autowired
//    public SigninController(SigninService signinService, ProfileManager<CommonProfile> profileManager) {
//        this.signinService = signinService;
//        this.profileManager = profileManager;
//    }
}
