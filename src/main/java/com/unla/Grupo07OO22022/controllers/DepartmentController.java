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

import com.unla.Grupo07OO22022.entities.Department;
import com.unla.Grupo07OO22022.helpers.ViewRouteHelper;
import com.unla.Grupo07OO22022.models.DepartmentModel;
import com.unla.Grupo07OO22022.services.implementation.DepartmentService;

@Controller
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	@Qualifier("departmentService")
	private DepartmentService departmentService;
	
	private ModelMapper modelMapper = new ModelMapper();
  	
	@GetMapping("")
	public ModelAndView index(){
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.DEPARTMENT_INDEX);		
		mAV.addObject("departments", this.departmentService.findByEnabled(true));
		return mAV;
	}
	
	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.DEPARTMENT_UPDATE);
		mAV.addObject("department", this.departmentService.findById(id));		
		return mAV;
	}
	
	@GetMapping("/new")
	public ModelAndView create() {		
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.DEPARTMENT_NEW);
		mAV.addObject("department", new DepartmentModel());		
		return mAV;
	}
	
	@PostMapping("/create")
	public RedirectView create(@ModelAttribute("department") DepartmentModel departmentModel) {
		this.departmentService.insertOrUpdate(this.modelMapper.map(departmentModel, Department.class));
		return new RedirectView(ViewRouteHelper.DEPARTMENT_ROOT);
	}
	
	@GetMapping("/delete/{id}")
	public RedirectView delete(@PathVariable("id") int id) {		
		this.departmentService.remove(id);
		return new RedirectView(ViewRouteHelper.DEPARTMENT_ROOT);
	}
	
	@PostMapping("/update")
	public RedirectView update(@ModelAttribute("department") DepartmentModel departmentModel) {
		Department department = modelMapper.map(departmentModel, Department.class);
		if(departmentModel.getId() > 0) {
			Department departmentOld = this.departmentService.findById(departmentModel.getId());
			department.setCreatedAt(departmentOld.getCreatedAt());					
			
		}
		this.departmentService.insertOrUpdate(department);
		return new RedirectView(ViewRouteHelper.DEPARTMENT_ROOT);
	}

}