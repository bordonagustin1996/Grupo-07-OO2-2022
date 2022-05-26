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
		mAV.addObject("classrooms", classroomService.findByEnabled(true));
		return mAV;
	}
	
	@GetMapping("/new-laboratory")
	public ModelAndView createLaboratory() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.LABORATORY_NEW);
		mAV.addObject("laboratory", new LaboratoryModel());
		mAV.addObject("buildings", buildingService.findByEnabled(true));
		return mAV;
	}
	
	@PostMapping("/create-laboratory")
	public RedirectView create(@ModelAttribute("laboratory") LaboratoryModel laboratoryModel) {
		classroomService.insertOrUpdate(modelMapper.map(laboratoryModel, Laboratory.class));
		return new RedirectView(ViewRouteHelper.CLASSROOM_ROOT);
	}
	
	@GetMapping("/new-traditional")
	public ModelAndView createTraditional() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.TRADITIONAL_NEW);
		mAV.addObject("traditional", new TraditionalModel());
		mAV.addObject("buildings", buildingService.findByEnabled(true));
		return mAV;
	}
	
	@PostMapping("/create-traditional")
	public RedirectView create(@ModelAttribute("traditional") TraditionalModel traditionalModel) {
		classroomService.insertOrUpdate(modelMapper.map(traditionalModel, Traditional.class));
		return new RedirectView(ViewRouteHelper.CLASSROOM_ROOT);
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
		mAV.addObject("buildings", buildingService.findByEnabled(true));
		return mAV;
	}
	
	@PostMapping("/update-laboratory")
	public RedirectView update(@ModelAttribute("laboratory") LaboratoryModel laboratoryModel) {
		Laboratory laboratory = modelMapper.map(laboratoryModel, Laboratory.class);
		classroomService.insertOrUpdate(laboratory);
		return new RedirectView(ViewRouteHelper.CLASSROOM_ROOT);
	}
	
	@PostMapping("/update-traditional")
	public RedirectView update(@ModelAttribute("traditional") TraditionalModel traditionalModel) {
		Traditional traditional = modelMapper.map(traditionalModel, Traditional.class);
		classroomService.insertOrUpdate(traditional);
		return new RedirectView(ViewRouteHelper.CLASSROOM_ROOT);
	}
	
	@GetMapping("/delete/{id}")
	public RedirectView delete(@PathVariable("id") int id) {		
		classroomService.remove(id);
		return new RedirectView(ViewRouteHelper.CLASSROOM_ROOT);
	}
	
}
