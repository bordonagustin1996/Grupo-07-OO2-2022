package com.unla.Grupo07OO22022.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.lowagie.text.DocumentException;
import com.unla.Grupo07OO22022.entities.User;
import com.unla.Grupo07OO22022.generatePDF.UserPDFExporter;
import com.unla.Grupo07OO22022.helpers.ViewRouteHelper;
import com.unla.Grupo07OO22022.models.UserModel;
import com.unla.Grupo07OO22022.services.implementation.UserRoleService;
import com.unla.Grupo07OO22022.services.implementation.UserService;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
		mAV.addObject("users", this.userService.getAll());
		return mAV;
	}
	
	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_UPDATE);
		mAV.addObject("user", this.userService.findById(id));
		mAV.addObject("userRoles", userRoleService.getAll());
		return mAV;
	}
	
	@GetMapping("/new")
	public ModelAndView create() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_NEW);
		mAV.addObject("user", new UserModel());
		mAV.addObject("userRoles", userRoleService.getAll());
		return mAV;
	}
	
	@PostMapping("/create")
	public ModelAndView create(@Valid @ModelAttribute("user") UserModel userModel, BindingResult result) {
		ModelAndView mAV = new ModelAndView();
		if (userService.findByUsername(userModel.getUsername()) != null) {			
			result.addError(new FieldError("error", "username", "Ya existe un usuario en este username"));
		}
		if(result.hasErrors()) {
			mAV.setViewName(ViewRouteHelper.USER_NEW);
			mAV.addObject("user", userModel);
			mAV.addObject("userRoles", userRoleService.getAll());
		}else {
			this.userService.insertOrUpdate(this.modelMapper.map(userModel, User.class));					
			mAV.setViewName("redirect:/user");
		}
		return mAV;
	}
	
	@GetMapping("/delete/{id}")
	public RedirectView delete(@PathVariable("id") int id) {		
		userService.remove(id);
		return new RedirectView(ViewRouteHelper.USER_ROOT);
	}
	
	@GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateTime = dateFormatter.format(Date.valueOf(LocalDate.now()));         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=usuarios_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);         
        List<User> users = userService.getAll();
        UserPDFExporter exporter = new UserPDFExporter(users);
        exporter.export(response);         
    }
	
	@PostMapping("/update")
	public ModelAndView update(@Valid @ModelAttribute("user") UserModel userModel, BindingResult result) {
		ModelAndView mAV = new ModelAndView();
		User user = modelMapper.map(userModel, User.class);
		if (userService.findByUsername(user.getUsername()) != null) {			
			result.addError(new FieldError("error", "username", "Ya existe un usuario en este username"));
		}
		if(result.hasErrors()) {
			mAV.setViewName(ViewRouteHelper.USER_UPDATE);
			mAV.addObject("user", userModel);
			mAV.addObject("userRoles", userRoleService.getAll());
		}else {			
			this.userService.insertOrUpdate(user);				
			mAV.setViewName("redirect:/user");
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getName().equals(userModel.getUsername())) {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority(userModel.getUserRole().getName()));
			Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), authorities);
			SecurityContextHolder.getContext().setAuthentication(newAuth);
		}
		return mAV;
	}	
}
