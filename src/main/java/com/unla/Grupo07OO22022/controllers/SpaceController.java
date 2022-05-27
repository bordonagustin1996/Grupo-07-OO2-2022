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

import com.unla.Grupo07OO22022.entities.Space;
import com.unla.Grupo07OO22022.helpers.ViewRouteHelper;
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
	public RedirectView create(@ModelAttribute("space") SpaceModel spaceModel) {
		this.spaceService.insertOrUpdate(this.modelMapper.map(spaceModel, Space.class));
		return new RedirectView(ViewRouteHelper.SPACE_ROOT);
	}
	
	@GetMapping("/delete/{id}")
	public RedirectView delete(@PathVariable("id") int id) {		
		this.spaceService.remove(id);
		return new RedirectView(ViewRouteHelper.SPACE_ROOT);
	}
	
	@PostMapping("/update")
	public RedirectView update(@ModelAttribute("space") SpaceModel spaceModel) {
		Space space = modelMapper.map(spaceModel, Space.class);
		if(spaceModel.getId() > 0) {
			Space spaceOld = this.spaceService.findById(spaceModel.getId());
			space.setCreatedAt(spaceOld.getCreatedAt());					
			
		}
		this.spaceService.insertOrUpdate(space);
		return new RedirectView(ViewRouteHelper.SPACE_ROOT);
	}

}
