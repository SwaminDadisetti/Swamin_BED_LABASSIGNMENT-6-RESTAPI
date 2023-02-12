package com.greatlearning.registration.controller;

import java.io.ByteArrayInputStream;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greatlearning.registration.model.Student;
import com.greatlearning.registration.repository.StudentRepository;
import com.greatlearning.registration.service.StudentServiceImpl;

import StudentDebateRegistrationApplication.reporting.ExportPdf;

@Controller
@RequestMapping("/student") // endpoint
public class StudentController {
	@Autowired
	StudentServiceImpl studentservice;
	
	@Autowired
	StudentRepository repository;

	@RequestMapping("/list")
	public String listStudents(Model model) {
		List<Student> mystudents = studentservice.findAll();
		model.addAttribute("studentjsp", mystudents);
		return "list-Students";
	}
	
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		Student student = new Student();
		model.addAttribute("StudentUI", student);
		return "Student-form";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int id, Model model) {
		Student studentformdb = studentservice.findByid(id);
		model.addAttribute("StudentUI", studentformdb);
		return "Student-form";
	}

	@PostMapping("/save")
	public String saveStudent(@RequestParam("id") int id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("course") String course,
			@RequestParam("country") String country) {
		Student student = new Student();
		student.setId(id);
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setCourse(course);
		student.setCountry(country);

		studentservice.save(student);
		return "redirect:/student/list";
	}

	@RequestMapping("/delete")
	public String deleteById(@RequestParam("studentId") int id) {
		studentservice.deleteById(id);
		return "redirect:/student/list";
	}

	@RequestMapping(value = "/403")
	public ModelAndView accessDenied(Principal user) {
		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() + ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", "You do not have permission to access this page");
		}

		model.setViewName("403");
		return model;
	}
	
@GetMapping(value = "/print", produces = MediaType.APPLICATION_PDF_VALUE)
public ResponseEntity<InputStreamResource> printTable(HttpServletResponse response) {
		List<Student> mystudents = repository.findAll();
		ByteArrayInputStream bis = ExportPdf.printTable(mystudents);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment;filename=students.pdf");
		
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
		
	}


}
