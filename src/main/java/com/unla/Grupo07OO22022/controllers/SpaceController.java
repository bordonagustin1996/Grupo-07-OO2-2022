package com.unla.Grupo07OO22022.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.Grupo07OO22022.entities.Space;
import com.unla.Grupo07OO22022.helpers.ViewRouteHelper;
import com.unla.Grupo07OO22022.models.AddSpaceModel;
import com.unla.Grupo07OO22022.models.SpaceModel;
import com.unla.Grupo07OO22022.services.IClassroomService;
import com.unla.Grupo07OO22022.services.ISpaceService;

@Controller
@RequestMapping("/space")
public class SpaceController {
	
	@Autowired
	@Qualifier("spaceService")
	private ISpaceService spaceService;
	
	@Autowired
	@Qualifier("classroomService")
	private IClassroomService classroomService;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@GetMapping("")
	public ModelAndView index(){
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.SPACE_INDEX);		
		mAV.addObject("spaces", this.spaceService.findByEnabled(true));
		return mAV;
	}
	
	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.SPACE_UPDATE);
		mAV.addObject("space", this.spaceService.findById(id));
		mAV.addObject("classrooms", classroomService.findByEnabled(true));
		return mAV;
	}
	
	@GetMapping("/new")
	public ModelAndView create() {		
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.SPACE_NEW);
		mAV.addObject("space", new SpaceModel());
		mAV.addObject("classrooms", classroomService.findByEnabled(true));
		return mAV;
	}
	
	@PostMapping("/create")
	public ModelAndView create(@Valid @ModelAttribute("space") SpaceModel spaceModel, BindingResult bindingResult) {
		ModelAndView mAV = new ModelAndView();
		if (spaceService.findByDateAndTurnAndClassroom(spaceModel.getDate(), spaceModel.getTurn(), spaceModel.getClassroom()) != null) {
			bindingResult.addError(new ObjectError("error", "Ya existe un espacio con la misma fecha, el mismo turno y la misma aula"));
		}		
		if (bindingResult.hasErrors()) {
			mAV.setViewName(ViewRouteHelper.SPACE_NEW);
			mAV.addObject("space", spaceModel);
			mAV.addObject("classrooms", classroomService.findByEnabled(true));
		} else {
			spaceService.insertOrUpdate(modelMapper.map(spaceModel, Space.class));
			mAV.setViewName("redirect:/space");
		}
		return mAV;
	}
	
	@GetMapping("/delete/{id}")
	public RedirectView delete(@PathVariable("id") int id) {		
		this.spaceService.remove(id);
		return new RedirectView(ViewRouteHelper.SPACE_ROOT);
	}
	
	@PostMapping("/update")
	public ModelAndView update(@Valid @ModelAttribute("space") SpaceModel spaceModel, BindingResult bindingResult) {
		ModelAndView mAV = new ModelAndView();
		Space space = spaceService.findByDateAndTurnAndClassroom(spaceModel.getDate(), spaceModel.getTurn(), spaceModel.getClassroom());		
		if (space != null && space.getId() != spaceModel.getId()) {
			bindingResult.addError(new ObjectError("error", "Ya existe un espacio con la misma fecha, el mismo turno y la misma aula"));
		}
		if (bindingResult.hasErrors()) {
			mAV.setViewName(ViewRouteHelper.SPACE_UPDATE);
			mAV.addObject("space", spaceModel);
			mAV.addObject("classrooms", classroomService.findByEnabled(true));
		} else {
			spaceService.insertOrUpdate(modelMapper.map(spaceModel, Space.class));
			mAV.setViewName("redirect:/space");
		}
		return mAV;
	}

	@GetMapping("/add/form-by-dates")
	public ModelAndView addByDates() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.ADD_SPACE_FORM);
		mAV.addObject("addSpace", new AddSpaceModel());
		return mAV;
	}
	
	@PostMapping("/add/by-dates")
	public ModelAndView addByDates(@Valid @ModelAttribute("addSpace") AddSpaceModel addSpaceModel, BindingResult bindingResult) {
		ModelAndView mAV = new ModelAndView();
		if (addSpaceModel.getEndDate().isBefore(addSpaceModel.getStartDate())) {
			bindingResult.addError(new FieldError("error", "endDate", "La fecha de fin debe ser mayor a la fecha de inicio"));
		}
		if (bindingResult.hasErrors()) {
			mAV.setViewName(ViewRouteHelper.ADD_SPACE_FORM);
			mAV.addObject("addSpace", addSpaceModel);
		} else {
			spaceService.addByDates(classroomService.findByEnabled(true), addSpaceModel.getStartDate(), addSpaceModel.getEndDate());
			mAV.setViewName("redirect:/space");
		}
		return mAV;
	}
	
}
