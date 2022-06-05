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

import com.unla.Grupo07OO22022.entities.Classroom;
import com.unla.Grupo07OO22022.entities.Laboratory;
import com.unla.Grupo07OO22022.entities.Traditional;
import com.unla.Grupo07OO22022.helpers.ViewRouteHelper;
import com.unla.Grupo07OO22022.models.LaboratoryModel;
import com.unla.Grupo07OO22022.models.TraditionalModel;
import com.unla.Grupo07OO22022.services.IBuildingService;
import com.unla.Grupo07OO22022.services.IClassroomService;

@Controller
@RequestMapping("/classroom")
public class ClassroomController {

	@Autowired
	@Qualifier("classroomService")
	private IClassroomService classroomService;
	
	@Autowired
	@Qualifier("buildingService")
	private IBuildingService buildingService;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.CLASSROOM_INDEX);
		mAV.addObject("classrooms", classroomService.getAll());
		return mAV;
	}
	
	@GetMapping("/new-laboratory")
	public ModelAndView createLaboratory() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.LABORATORY_NEW);
		mAV.addObject("laboratory", new LaboratoryModel());
		mAV.addObject("buildings", buildingService.getAll());
		return mAV;
	}
	
	@PostMapping("/create-laboratory")
	public ModelAndView create(@Valid @ModelAttribute("laboratory") LaboratoryModel laboratoryModel, BindingResult bindingResult) {
		ModelAndView mAV = new ModelAndView();
		if (bindingResult.hasErrors()) {
			mAV.setViewName(ViewRouteHelper.LABORATORY_NEW);
			mAV.addObject("laboratory", laboratoryModel);
			mAV.addObject("buildings", buildingService.getAll());
		} else {
			mAV.setViewName("redirect:/classroom");
			classroomService.insertOrUpdate(modelMapper.map(laboratoryModel, Laboratory.class));
		}
		return mAV;
	}
	
	@GetMapping("/new-traditional")
	public ModelAndView createTraditional() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.TRADITIONAL_NEW);
		mAV.addObject("traditional", new TraditionalModel());
		mAV.addObject("buildings", buildingService.getAll());
		return mAV;
	}
	
	@PostMapping("/create-traditional")
	public ModelAndView create(@Valid @ModelAttribute("traditional") TraditionalModel traditionalModel, BindingResult bindingResult) {
		ModelAndView mAV = new ModelAndView();
		if (bindingResult.hasErrors()) {
			mAV.setViewName(ViewRouteHelper.TRADITIONAL_NEW);
			mAV.addObject("traditional", traditionalModel);
			mAV.addObject("buildings", buildingService.getAll());
		} else {
			mAV.setViewName("redirect:/classroom");
			classroomService.insertOrUpdate(modelMapper.map(traditionalModel, Traditional.class));
		}
		return mAV;
	}
	
	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView();
		Classroom classroom = classroomService.findById(id);		
		if (classroom instanceof Laboratory) {
			mAV.setViewName(ViewRouteHelper.LABORATORY_UPDATE);
			mAV.addObject("laboratory", classroom);
		} else {
			mAV.setViewName(ViewRouteHelper.TRADITIONAL_UPDATE);
			mAV.addObject("traditional", classroom);
		}
		mAV.addObject("buildings", buildingService.getAll());
		return mAV;
	}
	
	@PostMapping("/update-laboratory")
	public ModelAndView update(@Valid @ModelAttribute("laboratory") LaboratoryModel laboratoryModel, BindingResult bindingResult) {
		ModelAndView mAV = new ModelAndView();
		if (bindingResult.hasErrors()) {
			mAV.setViewName(ViewRouteHelper.LABORATORY_UPDATE);
			mAV.addObject("laboratory", laboratoryModel);
			mAV.addObject("buildings", buildingService.getAll());
		} else {
			mAV.setViewName("redirect:/classroom");
			classroomService.insertOrUpdate(modelMapper.map(laboratoryModel, Laboratory.class));
		}		
		return mAV;
	}
	
	@PostMapping("/update-traditional")
	public ModelAndView update(@Valid @ModelAttribute("traditional") TraditionalModel traditionalModel, BindingResult bindingResult) {
		ModelAndView mAV = new ModelAndView();
		if (bindingResult.hasErrors()) {
			mAV.setViewName(ViewRouteHelper.TRADITIONAL_UPDATE);
			mAV.addObject("laboratory", traditionalModel);
			mAV.addObject("buildings", buildingService.getAll());
		} else {
			mAV.setViewName("redirect:/classroom");
			classroomService.insertOrUpdate(modelMapper.map(traditionalModel, Traditional.class));
		}
		return mAV;
	}
	
	@GetMapping("/delete/{id}")
	public RedirectView delete(@PathVariable("id") int id) {		
		classroomService.remove(id);
		return new RedirectView(ViewRouteHelper.CLASSROOM_ROOT);
	}
	
}
