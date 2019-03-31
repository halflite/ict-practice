package com.example.todo.app.controller;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.todo.app.entity.Account;
import com.example.todo.app.exception.AuthenticateException;
import com.example.todo.app.form.SigninForm;
import com.example.todo.app.service.SigninService;

@Controller
@SessionAttributes(names="signinForm")
public class SigninController {

    
    private final SigninService signinService;
    private final MessageSource messageSource;

    @ModelAttribute
    SigninForm signinForm() {
        return new SigninForm();
    }

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String index(Model model) {
        return "signin";
    }

    @RequestMapping(value = "/signin/post", method = RequestMethod.POST)
    public String post(@Validated SigninForm form, BindingResult result, RedirectAttributes attributes, SessionStatus sessionStatus) {
        try {
            if (result.hasErrors()) {
                List<String> errors = result.getAllErrors()
                        .stream()
                        .map(ObjectError::toString)
                        .collect(Collectors.toList());
                attributes.addFlashAttribute("errors", errors);
                return "redirect:/signin";
            }
            Account account = signinService.authenticate(form.getUsername(), form.getPassword());
            String success = this.messageSource.getMessage("auth.success", null, Locale.JAPANESE);
            attributes.addFlashAttribute("success", Collections.singletonList(success));
            sessionStatus.setComplete();
            return "redirect:/";
        } catch (AuthenticateException e) {
            String error = this.messageSource.getMessage(e.getMessageKey(), null, Locale.JAPANESE);
            attributes.addFlashAttribute("errors", Collections.singletonList(error));
            return "redirect:/signin";
        }
    }

    @Autowired
    public SigninController(SigninService signinService, MessageSource messageSource) {
        this.signinService = signinService;
        this.messageSource = messageSource;
    }

}
