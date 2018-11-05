package sth.core;

import java.util.List;
import java.util.ArrayList;

import sth.core.Student;
import sth.core.Teacher;
import sth.core.Project;

public class Discipline{
	
	private String _name;
	//private int _capacity; 
	private ArrayList<Student> _students = new ArrayList<Student>();
	private ArrayList<Teacher> _teachers = new ArrayList<Teacher>();
	private ArrayList<Project> _projects = new ArrayList<Project>();

	public Discipline(String name/*, int capacity*/){
		_name = name;
		/*_capacity = capacity;*/
	}

	public void addStudent(Student a){
		_students.add(a);
	}

	public void addTeacher(Teacher d){
		_teachers.add(d);
	}

	public ArrayList<Teacher> getTeacher(){
		return _teachers;
	}

	public ArrayList<Student> getStudent(){
		return _students;
	}

	public void addProjeto(Project p){
		_projects.add(p);
	}

	public ArrayList<Project> getProjects(){
		return _projects;
	}
}