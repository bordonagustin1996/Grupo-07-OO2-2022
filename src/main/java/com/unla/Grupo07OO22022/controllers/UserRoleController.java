package com.unla.Grupo07OO22022.controllers;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
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

import com.lowagie.text.DocumentException;
import com.unla.Grupo07OO22022.entities.UserRole;
import com.unla.Grupo07OO22022.generatePDF.UserRolePDFExporter;
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
		mAV.addObject("userRoles", userRoleService.getAll());
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
	
	@GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateTime = dateFormatter.format(Date.valueOf(LocalDate.now()));         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=roles_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);         
        List<UserRole> userRoles = userRoleService.getAll();
        UserRolePDFExporter exporter = new UserRolePDFExporter(userRoles);
        exporter.export(response);         
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
