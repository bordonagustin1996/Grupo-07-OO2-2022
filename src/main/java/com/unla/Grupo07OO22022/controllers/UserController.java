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
import com.unla.Grupo07OO22022.services.implementation.UserRoleService;
import com.unla.Grupo07OO22022.services.implementation.UserService;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	@Qualifier("userRoleService")
	private UserRoleService userRoleService;
	
	private ModelMapper modelMapper = new ModelMapper();
		
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView("user/index");
		mAV.addObject("users", this.userService.findByEnabled(true));
		return mAV;
	}
	
	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_UPDATE);
		mAV.addObject("user", this.userService.findById(id));
		mAV.addObject("userRoles", userRoleService.findByEnabled(true));
		return mAV;
	}
	
	@GetMapping("/new")
	public ModelAndView create() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_NEW);
		mAV.addObject("user", new UserModel());
		mAV.addObject("userRoles", userRoleService.findByEnabled(true));
		return mAV;
	}
	
	@PostMapping("/create")
	public RedirectView create(@ModelAttribute("user") UserModel userModel) {
		this.userService.insertOrUpdate(this.modelMapper.map(userModel, User.class));
		return new RedirectView(ViewRouteHelper.USER_ROOT);
	}
	
	@GetMapping("/delete/{id}")
	public RedirectView delete(@PathVariable("id") int id) {		
		userService.remove(id);
		return new RedirectView(ViewRouteHelper.USER_ROOT);
	}
	
	@PostMapping("/update")
	public RedirectView update(@ModelAttribute("user") UserModel userModel) {
		User user = modelMapper.map(userModel, User.class);
		if(userModel.getId() > 0) {
			User userOld = this.userService.findById(userModel.getId());
			user.setCreatedAt(userOld.getCreatedAt());
			user.setUsername(userOld.getUsername());
			user.setPassword(userOld.getPassword());
			user.setEmail(userOld.getEmail());
		}
		this.userService.insertOrUpdate(user);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getName().equals(userModel.getUsername())) {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority(userModel.getUserRole().getName()));
			Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), authorities);
			SecurityContextHolder.getContext().setAuthentication(newAuth);
		}
		return new RedirectView(ViewRouteHelper.USER_ROOT);
	}
	
}
