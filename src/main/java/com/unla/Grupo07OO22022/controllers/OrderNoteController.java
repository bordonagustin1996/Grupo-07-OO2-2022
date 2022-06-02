package com.unla.Grupo07OO22022.controllers;

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
	public RedirectView createCourse(@ModelAttribute("orderNote") CourseModel courseModel) {
		orderNoteService.insertOrUpdateCourse(modelMapper.map(courseModel, Course.class));
		return new RedirectView(ViewRouteHelper.COURSE_ROOT);
	}

	@PostMapping("/create-final")
	public RedirectView createFinal(@ModelAttribute("orderNote") FinalModel finalModel) {
		orderNoteService.insertOrUpdateFinal(modelMapper.map(finalModel, Final.class));
		return new RedirectView(ViewRouteHelper.FINAL_ROOT);
	}

	@GetMapping("/new-final")
	public ModelAndView createFinal() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.FINAL_NEW);
		mAV.addObject("orderNote", new Final());
		mAV.addObject("matters", matterService.findByEnabled(true));
		mAV.addObject("classrooms", classroomService.findByEnabled(true));
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		mAV.addObject("users", userService.findByUsername(user.getUsername()));
		return mAV;
	}

	@GetMapping("/new-course")
	public ModelAndView createCourse() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.COURSE_NEW);
		mAV.addObject("orderNote", new Course());
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

	@PostMapping("/update-final")
	public RedirectView updateFinal(@ModelAttribute("orderNote") FinalModel finalModel) {
		Final finalr = modelMapper.map(finalModel, Final.class);
		if (finalModel.getId() > 0) {
			Final finalOld = (Final) this.orderNoteService.findById(finalModel.getId());
			finalr.setCreatedAt(finalOld.getCreatedAt());

		}
		this.orderNoteService.insertOrUpdateFinal(finalr);
		return new RedirectView(ViewRouteHelper.FINAL_ROOT);
	}

	@PostMapping("/confirm-final")
	public ModelAndView confirm(@ModelAttribute("orderNote") FinalModel finalModel, BindingResult result) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.FINAL_UPDATE);
		mAV.addObject("orderNote", finalModel);
		mAV.addObject("matters", matterService.findByEnabled(true));
		mAV.addObject("classrooms", classroomService.findByEnabled(true));
		mAV.addObject("users", userService.findByEnabled(true));
		Space space = spaceService.findByDateAndTurnAndClassroomAndFree(finalModel.getExamDate(), finalModel.getTurn(), finalModel.getClassroom(), true);
		if (space == null) {
			result.addError(new ObjectError("error", "No hay espacios disponibles para esta fecha"));
		} else {
			Final finalmap = modelMapper.map(finalModel, Final.class);
			space.setOderNote(finalmap);
			space.setFree(false);
			spaceService.insertOrUpdate(space);
			finalModel.setConfirmed(true);
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
		Course course = modelMapper.map(courseModel, Course.class);
		List<Space> spaces = spaceService.getSpace(courseModel.getStartDate(), courseModel.getClassroom(), courseModel.getTurn());
		if (spaces.size() < 10) {
			result.addError(new ObjectError("error", "No se puede confirmar este pedido ya que no hay suficientes espacios"));
		} else {
			spaces.stream().forEach(space -> space.setOderNote(course)); 
			spaceService.saveAll(spaces);
			courseModel.setConfirmed(true);
			orderNoteService.insertOrUpdateCourse(course);
		}
		return mAV;
	}

	@PostMapping("/update-course")
	public RedirectView updateCourse(@ModelAttribute("orderNote") CourseModel courseModel) {
		Course course = modelMapper.map(courseModel, Course.class);
		if (courseModel.getId() > 0) {
			Course courseOld = (Course) this.orderNoteService.findById(courseModel.getId());
			course.setCreatedAt(courseOld.getCreatedAt());

		}
		this.orderNoteService.insertOrUpdateCourse(course);
		return new RedirectView(ViewRouteHelper.COURSE_ROOT);
	}
}
