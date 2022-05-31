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

import com.unla.Grupo07OO22022.entities.Matter;
import com.unla.Grupo07OO22022.helpers.ViewRouteHelper;
import com.unla.Grupo07OO22022.models.MatterModel;
import com.unla.Grupo07OO22022.services.implementation.CareerService;
import com.unla.Grupo07OO22022.services.implementation.MatterService;


@Controller
@RequestMapping("/matter")
public class MatterController {
	
	@Autowired
	@Qualifier("matterService")
	private MatterService matterService;
	
	@Autowired
	@Qualifier("careerService")
	private CareerService careerService;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@GetMapping("")
	public ModelAndView index(){
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.MATTER_INDEX);		
		mAV.addObject("matters", this.matterService.findByEnabled(true));
		return mAV;
	}
	
	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.MATTER_UPDATE);
		mAV.addObject("matter", this.matterService.findById(id));	
		mAV.addObject("careers", this.careerService.findByEnabled(true));
		return mAV;
	}
	
	@GetMapping("/new")
	public ModelAndView create() {		
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.MATTER_NEW);
		mAV.addObject("matter", new MatterModel());	
		mAV.addObject("careers", this.careerService.findByEnabled(true));
		return mAV;
	}
	
	@PostMapping("/create")
	public ModelAndView create(@Valid @ModelAttribute("matter") MatterModel matterModel, BindingResult result) {
		ModelAndView mAV = new ModelAndView();
		if(result.hasErrors()) {
			mAV.setViewName(ViewRouteHelper.MATTER_NEW);
			mAV.addObject("matter", matterModel);
			mAV.addObject("careers", careerService.findByEnabled(true));
		}else {
			this.matterService.insertOrUpdate(this.modelMapper.map(matterModel, Matter.class));					
			mAV.setViewName("redirect:/matter");
		}
		return mAV;
	}
	
	@GetMapping("/delete/{id}")
	public RedirectView delete(@PathVariable("id") int id) {		
		this.matterService.remove(id);
		return new RedirectView(ViewRouteHelper.MATTER_ROOT);
	}
		
	@PostMapping("/update")
	public ModelAndView update(@Valid @ModelAttribute("matter") MatterModel matterModel, BindingResult result) {
		ModelAndView mAV = new ModelAndView();
		Matter matter = modelMapper.map(matterModel, Matter.class);
		if(result.hasErrors()) {
			mAV.setViewName(ViewRouteHelper.CAREER_UPDATE);
			mAV.addObject("career", matterModel);
			mAV.addObject("careers", careerService.findByEnabled(true));
		}else {			
			this.matterService.insertOrUpdate(matter);				
			mAV.setViewName("redirect:/matter");
		}
		return mAV;
	}

}
