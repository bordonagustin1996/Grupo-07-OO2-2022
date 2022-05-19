package com.unla.Grupo07OO22022.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.Grupo07OO22022.entities.User;
import com.unla.Grupo07OO22022.helpers.ViewRouteHelper;
import com.unla.Grupo07OO22022.models.UserModel;
import com.unla.Grupo07OO22022.services.implementation.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	private ModelMapper modelMapper = new ModelMapper();
		
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView("user/index");	
		//mAV.addObject("users", this.userService.getAll());
		mAV.addObject("users", this.userService.findByEnabled(true));
		return mAV;
	}
	
	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_UPDATE);
		mAV.addObject("user", this.userService.findById(id));
		return mAV;
	}
	
	@GetMapping("/new")
	public ModelAndView create() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_NEW);
		mAV.addObject("user", new UserModel());
		return mAV;
	}
	
	@PostMapping("/create")
	public RedirectView create(@ModelAttribute("user") UserModel userModel) {
		this.userService.insertOrUpdate(this.modelMapper.map(userModel, User.class));
		return new RedirectView(ViewRouteHelper.USER_ROOT);
	}
	
	@PostMapping("/delete/{id}")
	public RedirectView delete(@PathVariable("id") int id) {		
		this.userService.remove(id);
		return new RedirectView(ViewRouteHelper.USER_ROOT);
	}
	
	@PostMapping("/update")
	public RedirectView update(@ModelAttribute("person") UserModel userModel) {
		User user = modelMapper.map(userModel, User.class);
		if(userModel.getId() > 0) {
			User userOld = this.userService.findById(userModel.getId());
			user.setCreatedAt(userOld.getCreatedAt());
			user.setUsername(userOld.getUsername());
			user.setPassword(userOld.getPassword());
			user.setEmail(userOld.getEmail());
		}
		this.userService.insertOrUpdate(user);
		return new RedirectView(ViewRouteHelper.USER_ROOT);
	}
	
}
