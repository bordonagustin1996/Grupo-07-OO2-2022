package com.unla.Grupo07OO22022.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unla.Grupo07OO22022.services.implementation.CareerService;

@Controller
@RequestMapping("/Career")
public class CareerController {
	
	@Autowired
	@Qualifier("careerService")
	private CareerService careerService;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	

}
