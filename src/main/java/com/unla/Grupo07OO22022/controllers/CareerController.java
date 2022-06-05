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

import com.unla.Grupo07OO22022.entities.Career;
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
		mAV.addObject("careers", careerService.findByEnabled(true));
		return mAV;
	}
	
	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.CAREER_UPDATE);
		mAV.addObject("career", careerService.findById(id));	
		mAV.addObject("departments", departmentService.findByEnabled(true));
		return mAV;
	}
	
	@GetMapping("/new")
	public ModelAndView create() {		
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.CAREER_NEW);
		mAV.addObject("career", new CareerModel());
		mAV.addObject("departments", departmentService.findByEnabled(true));
		return mAV;
	}

	@PostMapping("/create")
	public ModelAndView create(@Valid @ModelAttribute("career") CareerModel careerModel, BindingResult bindingResult) {
		ModelAndView mAV = new ModelAndView();
		if (bindingResult.hasErrors()) {
			mAV.setViewName(ViewRouteHelper.CAREER_NEW);
			mAV.addObject("career", careerModel);
			mAV.addObject("departments", departmentService.findByEnabled(true));
		} else {
			careerService.insertOrUpdate(modelMapper.map(careerModel, Career.class));
			mAV.setViewName("redirect:/career");
		}
		return mAV;
	}
	
	@GetMapping("/delete/{id}")
	public RedirectView delete(@PathVariable("id") int id) {		
		careerService.remove(id);
		return new RedirectView(ViewRouteHelper.CAREER_ROOT);
	}
	
	@PostMapping("/update")
	public ModelAndView update(@Valid @ModelAttribute("career") CareerModel careerModel, BindingResult bindingResult) {
		ModelAndView mAV = new ModelAndView();
		if (bindingResult.hasErrors()) {
			mAV.setViewName(ViewRouteHelper.CAREER_UPDATE);
			mAV.addObject("career", careerModel);
			mAV.addObject("departments", departmentService.findByEnabled(true));
		} else {
			mAV.setViewName("redirect:/career");
			careerService.insertOrUpdate(modelMapper.map(careerModel, Career.class));
		}
		return mAV;
	}

}
