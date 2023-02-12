package com.greatlearning.registration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.registration.model.Student;
import com.greatlearning.registration.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentrepo;
	@Override
	public List<Student> findAll() {
		// TODO Auto-generated method stub
		return studentrepo.findAll();
	}

	@Override
	public Student findByid(int studentId) {
		// TODO Auto-generated method stub
		return studentrepo.findById(studentId).get();
	}

	@Override
	public void save(Student student) {
		// TODO Auto-generated method stub
		studentrepo.save(student);	
	}

	@Override
	public void deleteById(int studentId) {
		// TODO Auto-generated method stub
		studentrepo.deleteById(studentId);
	}

	
}
