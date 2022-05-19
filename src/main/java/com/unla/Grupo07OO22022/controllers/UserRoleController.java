package com.unla.Grupo07OO22022.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
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
	public RedirectView create(@ModelAttribute("userRole") UserRoleModel userRoleModel) {
		userRoleService.insertOrUpdate(modelMapper.map(userRoleModel, UserRole.class));
		return new RedirectView(ViewRouteHelper.USER_ROLE_ROOT);
	}
	
	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_ROLE_UPDATE);
		mAV.addObject("userRole", userRoleService.findById(id));
		return mAV;
	}
	
	@PostMapping("/update")
	public RedirectView update(@ModelAttribute("userRole") UserRoleModel userRoleModel) {
		UserRole userRole = modelMapper.map(userRoleModel, UserRole.class);
		if (userRoleModel.getId() > 0) {
			UserRole userRoleOld = userRoleService.findById(userRoleModel.getId());
			userRole.setCreatedAt(userRoleOld.getCreatedAt());
		}
		userRoleService.insertOrUpdate(userRole);
		return new RedirectView(ViewRouteHelper.USER_ROLE_ROOT);
	}
	
}
