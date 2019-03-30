package com.example.todo.app.controller;

import java.util.List;

import org.pac4j.core.config.Config;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.core.profile.ProfileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	@Autowired
	private Config config;
	@Autowired
	private ProfileManager<CommonProfile> profileManager;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		List<CommonProfile> profiles = profileManager.getAll(true);
		return "index";
	}
}
