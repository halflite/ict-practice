package com.example.todo.app.controller;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

import com.example.todo.app.auth.AccountDetails;
import com.example.todo.app.form.ArticleForm;

@Controller
@SessionAttributes(names = "articleForm")
public class IndexController {

    private final MessageSource messageSource;

    @ModelAttribute
    ArticleForm articleForm() {
        return new ArticleForm();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String post(@Validated ArticleForm form, BindingResult result, RedirectAttributes attributes, 
            SessionStatus sessionStatus, @AuthenticationPrincipal AccountDetails accountDetails) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors()
                    .stream()
                    .map(ObjectError::toString)
                    .collect(Collectors.toList());
            attributes.addFlashAttribute("errors", errors);
            return "redirect:/";
        }
        
        // TODO ロジック
        String success = this.messageSource.getMessage("create.success", null, Locale.JAPANESE);
        attributes.addFlashAttribute("success", success);
        sessionStatus.setComplete();
        return "redirect:/";
    }

    @Autowired
    public IndexController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
