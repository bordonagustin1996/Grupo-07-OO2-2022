package com.unla.Grupo07OO22022.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.Grupo07OO22022.entities.UserRole;
import com.unla.Grupo07OO22022.helpers.ViewRouteHelper;
import com.unla.Grupo07OO22022.models.UserRoleModel;
import com.unla.Grupo07OO22022.services.IUserRoleService;

@Controller
@RequestMapping("/user-role")
public class UserRoleController {

	@Autowired
	@Qualifier("userRoleService")
	private IUserRoleService userRoleService;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_ROLE_INDEX);
		mAV.addObject("userRoles", userRoleService.findByEnabled(true));
		return mAV;
	}
	
	@GetMapping("/new")
	public ModelAndView create() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_ROLE_NEW);
		mAV.addObject("userRole", new UserRoleModel());
		return mAV;
	}
		
	@PostMapping("/create")
	public ModelAndView create(@Valid @ModelAttribute("userRole") UserRoleModel userRoleModel, BindingResult result) {
		ModelAndView mAV = new ModelAndView();
		if(result.hasErrors()) {
			mAV.setViewName(ViewRouteHelper.USER_ROLE_NEW);
			mAV.addObject("userRole", userRoleModel);			
		}else {
			this.userRoleService.insertOrUpdate(this.modelMapper.map(userRoleModel, UserRole.class));		
			mAV.setViewName("redirect:/user-role");
		}
		return mAV;
	}
	
	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_ROLE_UPDATE);
		mAV.addObject("userRole", userRoleService.findById(id));
		return mAV;
	}
	
	@PostMapping("/update")
	public ModelAndView update(@Valid @ModelAttribute("userRole") UserRoleModel userRoleModel, BindingResult result) {
		ModelAndView mAV = new ModelAndView();
		UserRole userRole = modelMapper.map(userRoleModel, UserRole.class);
		if(result.hasErrors()) {
			mAV.setViewName(ViewRouteHelper.USER_ROLE_UPDATE);
			mAV.addObject("userRole", userRoleModel);			
		}else {
			if (userRoleModel.getId() > 0) {
				UserRole userRoleOld = userRoleService.findById(userRoleModel.getId());
				userRole.setCreatedAt(userRoleOld.getCreatedAt());
			}
			this.userRoleService.insertOrUpdate(userRole);				
			mAV.setViewName("redirect:/user-role");
		}
		return mAV;	
	}	

	@GetMapping("/delete/{id}")
	public RedirectView delete(@PathVariable("id") int id) {
		userRoleService.remove(id);
		return new RedirectView(ViewRouteHelper.USER_ROLE_ROOT);
	}
	
}
