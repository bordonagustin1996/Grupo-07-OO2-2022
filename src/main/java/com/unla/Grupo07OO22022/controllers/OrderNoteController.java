package com.unla.Grupo07OO22022.controllers;

import javax.validation.Valid;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.Grupo07OO22022.entities.Course;
import com.unla.Grupo07OO22022.entities.Final;
import com.unla.Grupo07OO22022.entities.Space;
import com.unla.Grupo07OO22022.helpers.ViewRouteHelper;
import com.unla.Grupo07OO22022.models.CourseModel;
import com.unla.Grupo07OO22022.models.FinalModel;
import com.unla.Grupo07OO22022.services.implementation.ClassroomService;
import com.unla.Grupo07OO22022.services.implementation.MatterService;
import com.unla.Grupo07OO22022.services.implementation.OrderNoteService;
import com.unla.Grupo07OO22022.services.implementation.SpaceService;
import com.unla.Grupo07OO22022.services.implementation.UserService;

@Controller
@RequestMapping("/order-note")
public class OrderNoteController {

	@Autowired
	@Qualifier("orderNoteService")
	private OrderNoteService orderNoteService;

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Autowired
	@Qualifier("matterService")
	private MatterService matterService;

	@Autowired
	@Qualifier("classroomService")
	private ClassroomService classroomService;

	@Autowired
	@Qualifier("spaceService")
	private SpaceService spaceService;

	private ModelMapper modelMapper = new ModelMapper();

	@GetMapping("/final")
	public ModelAndView indexFinal() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.FINAL_INDEX);
		mAV.addObject("orderNotes", orderNoteService.findByEnabled(true, false));
		mAV.addObject("matters", matterService.findByEnabled(true));
		mAV.addObject("users", userService.findByEnabled(true));
		mAV.addObject("classrooms", classroomService.findByEnabled(true));
		return mAV;
	}

	@GetMapping("/course")
	public ModelAndView indexCourse() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.COURSE_INDEX);
		mAV.addObject("orderNotes", orderNoteService.findByEnabled(true, true));
		mAV.addObject("matters", matterService.findByEnabled(true));
		mAV.addObject("users", userService.findByEnabled(true));
		mAV.addObject("classrooms", classroomService.findByEnabled(true));
		return mAV;
	}

	@GetMapping("/final/{id}")
	public ModelAndView getFinal(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.FINAL_UPDATE);
		mAV.addObject("orderNote", this.orderNoteService.findById(id));
		mAV.addObject("matters", matterService.findByEnabled(true));
		mAV.addObject("classrooms", classroomService.findByEnabled(true));
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		mAV.addObject("users", userService.findByUsername(user.getUsername()));
		return mAV;
	}

	@GetMapping("/course/{id}")
	public ModelAndView getCourse(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.COURSE_UPDATE);
		mAV.addObject("orderNote", this.orderNoteService.findById(id));
		mAV.addObject("matters", matterService.findByEnabled(true));
		mAV.addObject("classrooms", classroomService.findByEnabled(true));
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		mAV.addObject("users", userService.findByUsername(user.getUsername()));
		return mAV;
	}

	@PostMapping("/create-course")
	public ModelAndView createCourse(@Valid @ModelAttribute("orderNote") CourseModel courseModel,
			BindingResult bidingResul) {
		ModelAndView mAV = new ModelAndView();
		if (bidingResul.hasErrors()) {
			mAV.setViewName(ViewRouteHelper.COURSE_NEW);
			mAV.addObject("orderNote", courseModel);
			mAV.addObject("matters", matterService.findByEnabled(true));
			mAV.addObject("classrooms", classroomService.findByEnabled(true));
		} else {
			orderNoteService.insertOrUpdateCourse(modelMapper.map(courseModel, Course.class));
			mAV.setViewName("redirect:/order-note/course");
		}
		return mAV;
	}

	@PostMapping("/create-final")
	public ModelAndView createFinal(@Valid @ModelAttribute("orderNote") FinalModel finalModel,
			BindingResult bidingResult) {
		ModelAndView mAV = new ModelAndView();
		if (bidingResult.hasErrors()) {
			mAV.setViewName(ViewRouteHelper.FINAL_NEW);
			mAV.addObject("orderNote", finalModel);
			mAV.addObject("matters", matterService.findByEnabled(true));
			mAV.addObject("classrooms", classroomService.findByEnabled(true));
			UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			mAV.addObject("users", userService.findByUsername(user.getUsername()));
		} else {
			orderNoteService.insertOrUpdateFinal(modelMapper.map(finalModel, Final.class));
			mAV.setViewName("redirect:/order-note/final");
		}
		return mAV;
	}

	@GetMapping("/new-final")
	public ModelAndView createFinal() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.FINAL_NEW);
		mAV.addObject("orderNote", new FinalModel());
		mAV.addObject("matters", matterService.findByEnabled(true));
		mAV.addObject("classrooms", classroomService.findByEnabled(true));
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		mAV.addObject("users", userService.findByUsername(user.getUsername()));
		return mAV;
	}

	@GetMapping("/new-course")
	public ModelAndView createCourse() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.COURSE_NEW);
		CourseModel course = new CourseModel();
		course.setDate(LocalDate.now());
		mAV.addObject("orderNote", course);
		mAV.addObject("matters", matterService.findByEnabled(true));
		mAV.addObject("classrooms", classroomService.findByEnabled(true));
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		mAV.addObject("users", userService.findByUsername(user.getUsername()));
		return mAV;
	}

	@GetMapping("/delete/final/{id}")
	public RedirectView deleteFinal(@PathVariable("id") int id) {
		this.orderNoteService.remove(id);
		return new RedirectView(ViewRouteHelper.FINAL_ROOT);
	}

	@GetMapping("/delete/course/{id}")
	public RedirectView deleteCourse(@PathVariable("id") int id) {
		this.orderNoteService.remove(id);
		return new RedirectView(ViewRouteHelper.COURSE_ROOT);
	}

	@PostMapping("/confirm-final")
	public ModelAndView confirm(@ModelAttribute("orderNote") FinalModel finalModel, BindingResult result) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.FINAL_UPDATE);
		mAV.addObject("orderNote", finalModel);
		mAV.addObject("matters", matterService.findByEnabled(true));
		mAV.addObject("classrooms", classroomService.findByEnabled(true));
		mAV.addObject("users", userService.findByEnabled(true));
		Space space = spaceService.findByDateAndTurnAndClassroomAndFree(finalModel.getExamDate(), finalModel.getTurn(),
				finalModel.getClassroom(), true);
		if (space == null) {
			result.addError(new ObjectError("error", "No hay espacios disponibles para esta fecha"));
		} else {
			space.setFree(false);
			spaceService.insertOrUpdate(space);
			finalModel.setConfirmed(true);
			Final finalmap = modelMapper.map(finalModel, Final.class);
			space.setOrderNote(finalmap);
			orderNoteService.insertOrUpdateFinal(finalmap);
		}
		return mAV;
	}

	@PostMapping("/confirm-course")
	public ModelAndView confirm(@ModelAttribute("orderNote") CourseModel courseModel, BindingResult result) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.COURSE_UPDATE);
		mAV.addObject("orderNote", courseModel);
		mAV.addObject("matters", matterService.findByEnabled(true));
		mAV.addObject("classrooms", classroomService.findByEnabled(true));
		mAV.addObject("users", userService.findByEnabled(true));
		List<Space> spaces = spaceService.getSpace(courseModel.getStartDate(), courseModel.getClassroom(),
				courseModel.getTurn(), courseModel.getFtfPercentage(), courseModel.isEvenWeek());
		if ((spaces.size() < 15 && courseModel.getFtfPercentage() == 100)
				|| (spaces.size() < 7 && courseModel.getFtfPercentage() == 50)) {
			result.addError(
					new ObjectError("error", "No se puede confirmar este pedido ya que no hay suficientes espacios"));
		} else {
			courseModel.setConfirmed(true);
			Course course = modelMapper.map(courseModel, Course.class);
			orderNoteService.insertOrUpdateCourse(course);
			spaces.stream().forEach(space -> space.setOrderNote(course));
			spaceService.saveAll(spaces);
		}
		return mAV;
	}

	@PostMapping("/course-get-space")
	public ModelAndView getSpace(@ModelAttribute("orderNote") CourseModel courseModel) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.SPACE_INDEX);
		mAV.addObject("spaces", spaceService.findByOrderNoteOrderByDateAsc(modelMapper.map(courseModel, Course.class)));
		return mAV;
	}

	@PostMapping("/final-get-space")
	public ModelAndView getSpace(@ModelAttribute("orderNote") FinalModel finalModel) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.SPACE_INDEX);
		mAV.addObject("spaces", spaceService.findByOrderNoteOrderByDateAsc(modelMapper.map(finalModel, Final.class)));
		return mAV;
	}

	@PostMapping("/update-final")
	public ModelAndView updateFinal(@Valid @ModelAttribute("orderNote") FinalModel finalModel, BindingResult bindingResult) {
		ModelAndView mAV = new ModelAndView();				
		if (bindingResult.hasErrors()) {
			mAV.setViewName(ViewRouteHelper.FINAL_UPDATE);
			mAV.addObject("orderNote", finalModel);
			mAV.addObject("classrooms", classroomService.findByEnabled(true));
			mAV.addObject("matters", matterService.findByEnabled(true));		
			mAV.addObject("users", userService.findByEnabled(true));
		} else {
			orderNoteService.insertOrUpdate(modelMapper.map(finalModel, Final.class));
			mAV.setViewName("redirect:/order-note/final");
		}
		return mAV;
	}
	
	@PostMapping("/update-course")
	public ModelAndView updateFinal(@Valid @ModelAttribute("orderNote") CourseModel courseModel, BindingResult bindingResult) {
		ModelAndView mAV = new ModelAndView();				
		if (bindingResult.hasErrors()) {
			mAV.setViewName(ViewRouteHelper.COURSE_UPDATE);
			mAV.addObject("orderNote", courseModel);
			mAV.addObject("classrooms", classroomService.findByEnabled(true));
			mAV.addObject("matters", matterService.findByEnabled(true));		
			mAV.addObject("users", userService.findByEnabled(true));
		} else {
			orderNoteService.insertOrUpdate(modelMapper.map(courseModel, Course.class));
			mAV.setViewName("redirect:/order-note/course");
		}
		return mAV;
	}
}
