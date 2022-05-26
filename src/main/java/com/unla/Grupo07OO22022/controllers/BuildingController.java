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

import com.unla.Grupo07OO22022.entities.Building;
import com.unla.Grupo07OO22022.helpers.ViewRouteHelper;
import com.unla.Grupo07OO22022.models.BuildingModel;
import com.unla.Grupo07OO22022.services.IBuildingService;

@Controller
@RequestMapping("/building")
public class BuildingController {

	@Autowired
	@Qualifier("buildingService")
	private IBuildingService buildingService;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.BUILDING_INDEX);
		mAV.addObject("buildings", buildingService.findByEnabled(true));
		return mAV;
	}
	
	@GetMapping("/new")
	public ModelAndView create() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.BUILDING_NEW);
		mAV.addObject("building", new BuildingModel());
		return mAV;
	}
	
	@PostMapping("/create")
	public RedirectView create(@ModelAttribute("building") BuildingModel buildingModel) {
		buildingService.insertOrUpdate(modelMapper.map(buildingModel, Building.class));
		return new RedirectView(ViewRouteHelper.BUILDING_ROOT);
	}
	
	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.BUILDING_UPDATE);
		mAV.addObject("building", buildingService.findById(id));
		return mAV;
	}
	
	@PostMapping("/update")
	public RedirectView update(@ModelAttribute("building") BuildingModel buildingModel) {
		Building building = modelMapper.map(buildingModel, Building.class);
		buildingService.insertOrUpdate(building);
		return new RedirectView(ViewRouteHelper.BUILDING_ROOT);
	}

	@GetMapping("/delete/{id}")
	public RedirectView delete(@PathVariable("id") int id) {
		buildingService.remove(id);
		return new RedirectView(ViewRouteHelper.BUILDING_ROOT);
	}
	
}
