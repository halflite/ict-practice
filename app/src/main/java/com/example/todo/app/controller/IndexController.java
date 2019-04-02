package com.example.todo.app.controller;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.todo.app.auth.AccountDetails;
import com.example.todo.app.entity.Article;
import com.example.todo.app.entity.DisplayArticle;
import com.example.todo.app.exception.NotFoundArticleException;
import com.example.todo.app.form.ArticleForm;
import com.example.todo.app.service.ArticleService;

@Controller
@SessionAttributes(names = "articleForm")
public class IndexController {

    private final ArticleService articleService;
    private final MessageSource messageSource;

    @ModelAttribute
    ArticleForm articleForm() {
        return new ArticleForm();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        List<DisplayArticle> articles = this.articleService.findAll();
        model.addAttribute("articles", articles);
        return "index";
    }

    @RequestMapping(value = "/article/{id:\\d+}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> article(@PathVariable("id") Long id) {
        return this.articleService.findById(id);

    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Validated ArticleForm form, BindingResult result, RedirectAttributes attributes,
            SessionStatus sessionStatus, @AuthenticationPrincipal AccountDetails accountDetails) {
        if (result.hasErrors()) {
            return "redirect:/";
        }

        Article article = this.articleService.create(accountDetails.getId(), form.getName(), form.getDescription());
        String success = this.messageSource.getMessage("create.success", new Object[] { article.getName() }, Locale.JAPANESE);
        attributes.addFlashAttribute("success", success);
        sessionStatus.setComplete();
        return "redirect:/";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@Validated ArticleForm form, BindingResult result, RedirectAttributes attributes,
            SessionStatus sessionStatus, @AuthenticationPrincipal AccountDetails accountDetails) {
        try {
            if (result.hasErrors()) {
                return "redirect:/";
            }

            Article article = this.articleService.update(form.getId(), accountDetails.getId(), form.getName(), form.getDescription());
            String success = this.messageSource.getMessage("update.success", new Object[] { article.getName() }, Locale.JAPANESE);
            attributes.addFlashAttribute("success", success);
            sessionStatus.setComplete();
            return "redirect:/";
        } catch (NotFoundArticleException e) {
            String error = this.messageSource.getMessage("error.throws", null, Locale.JAPANESE);
            attributes.addFlashAttribute("errors", Collections.singleton(error));
            sessionStatus.setComplete();
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/complete", method = RequestMethod.POST)
    public String complete(@RequestParam("id") Long id, RedirectAttributes attributes,
            SessionStatus sessionStatus, @AuthenticationPrincipal AccountDetails accountDetails) {
        try {
            Article article = this.articleService.complete(id, accountDetails.getId());
            String success = this.messageSource.getMessage("complete.success", new Object[] { article.getName() }, Locale.JAPANESE);
            attributes.addFlashAttribute("success", success);
            sessionStatus.setComplete();
            return "redirect:/";
        } catch (NotFoundArticleException e) {
            String error = this.messageSource.getMessage("error.throws", null, Locale.JAPANESE);
            attributes.addFlashAttribute("errors", Collections.singleton(error));
            sessionStatus.setComplete();
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Long id, RedirectAttributes attributes,
            SessionStatus sessionStatus, @AuthenticationPrincipal AccountDetails accountDetails) {
        try {
            Article article = this.articleService.delete(id, accountDetails.getId());
            String success = this.messageSource.getMessage("delete.success", new Object[] { article.getName() }, Locale.JAPANESE);
            attributes.addFlashAttribute("success", success);
            sessionStatus.setComplete();
            return "redirect:/";
        } catch (NotFoundArticleException e) {
            String error = this.messageSource.getMessage("error.throws", null, Locale.JAPANESE);
            attributes.addFlashAttribute("errors", Collections.singleton(error));
            sessionStatus.setComplete();
            return "redirect:/";
        }
    }

    @Autowired
    public IndexController(ArticleService articleService, MessageSource messageSource) {
        this.articleService = articleService;
        this.messageSource = messageSource;
    }
}
