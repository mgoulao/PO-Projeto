package sth.core;

import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;

import sth.core.Course;
import sth.core.Discipline;
import sth.core.Submission;
import sth.core.Student;

public class Teacher extends Person{

	private TreeMap<Course,ArrayList<Discipline>>  _courseAndDisciplines = new TreeMap<Course,ArrayList<Discipline>>();

	public Teacher(String name, int iD, int phoneNumber,  TreeMap<Course,ArrayList<Discipline>> courseAndDisciplines){
		super(iD, phoneNumber, name);
		_courseAndDisciplines = courseAndDisciplines;
	}
	
	public void addProject(String name, String descricao){
		Project p = new Project(name, descricao);
	}

	public void closeProject(Project p){
		p.close();
	}

	public TreeMap<Student,Submission> seeResults(Project p){
		return p.getSubmissions();
	}

	public ArrayList<Student> seeStudents(Discipline d){
		return d.getStudent();
	}

	public void addDiscipline(Discipline d, Course c){
		_courseAndDisciplines.get(c).add(d);	
	}
}