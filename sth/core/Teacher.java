package sth.core;

import sth.core.exception.BadEntryException;

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

	public Teacher(int iD, int phoneNumber, String name){
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

	@Override
	void parseContext(String lineContext, School school) throws BadEntryException {
		String components[] =  lineContext.split("\\|");

		if (components.length != 2)
			throw new BadEntryException("Invalid line context " + lineContext);

		Course course = school.parseCourse(components[0]);
		Discipline discipline = course.parseDiscipline(components[1]);

		discipline.addTeacher(this);
	}

	public String printTeacher(){
		return("DOCENTE | " + super.getID() + " " + super.getPhoneNumber() + " " + super.getName()); 
	}
}