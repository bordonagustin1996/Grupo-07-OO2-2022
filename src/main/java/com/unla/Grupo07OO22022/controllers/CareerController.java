package com.unla.Grupo07OO22022.controllers;

import java.util.ArrayList;
import java.util.List;

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

import com.unla.Grupo07OO22022.entities.Career;
import com.unla.Grupo07OO22022.entities.Department;
import com.unla.Grupo07OO22022.helpers.ViewRouteHelper;
import com.unla.Grupo07OO22022.models.CareerModel;
import com.unla.Grupo07OO22022.services.implementation.CareerService;
import com.unla.Grupo07OO22022.services.implementation.DepartmentService;

@Controller
@RequestMapping("/career")
public class CareerController {
	
	@Autowired
	@Qualifier("careerService")
	private CareerService careerService;
	
	@Autowired
	@Qualifier("departmentService")
	private DepartmentService departmentService;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@GetMapping("")
	public ModelAndView index(){
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.CAREER_INDEX);	
		mAV.addObject("careers", this.careerService.findByEnabled(true));
		return mAV;
	}
	
	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.CAREER_UPDATE);
		mAV.addObject("career", this.careerService.findById(id));	
		mAV.addObject("departments", this.departmentService.findByEnabled(true));
		return mAV;
	}
	
	@GetMapping("/new")
	public ModelAndView create() {		
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.CAREER_NEW);
		mAV.addObject("career", new CareerModel());
		mAV.addObject("departments", this.departmentService.findByEnabled(true));
		return mAV;
	}
	
	@PostMapping("/create")
	public RedirectView create(@ModelAttribute("career") CareerModel careerModel) {
		this.careerService.insertOrUpdate(this.modelMapper.map(careerModel, Career.class));
		return new RedirectView(ViewRouteHelper.CAREER_ROOT);
	}
	
	@GetMapping("/delete/{id}")
	public RedirectView delete(@PathVariable("id") int id) {		
		this.careerService.remove(id);
		return new RedirectView(ViewRouteHelper.CAREER_ROOT);
	}
	
	@PostMapping("/update")
	public RedirectView update(@ModelAttribute("career") CareerModel careerModel) {
		Career career = modelMapper.map(careerModel, Career.class);
		if(careerModel.getId() > 0) {
			Career careerOld = this.careerService.findById(careerModel.getId());
			career.setCreatedAt(careerOld.getCreatedAt());					
			
		}
		this.careerService.insertOrUpdate(career);
		return new RedirectView(ViewRouteHelper.CAREER_ROOT);
	}	
	

}
