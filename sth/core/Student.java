package sth.core;

import sth.core.Discipline;
import sth.core.Submission;
import sth.core.Answer;

import java.util.List;
import java.util.ArrayList;

import sth.core.Discipline;
import sth.core.Survey;
import sth.core.Submission;
import sth.core.Answer;
import sth.core.Course;


public class Student extends Person{

	private Course _course;
	private List<Discipline> _disciplines = new ArrayList<Discipline>();
	private boolean _representative;

	public Student(Course course, boolean representative, String name, int iD, int phoneNumber,  ArrayList<Discipline> disciplines){
		super(iD, phoneNumber, name);
		_representative = representative;
		_course = course;
		_disciplines = disciplines;
	}

	public Course getCourse(){
		return _course;
	}

	public boolean getRepresentative(){
		return _representative;
	}

	public void setRepresentative(boolean representative){
		_representative = representative;
		_course.addRepresentative(super.getID());
	}
	
	public void submiteProject(String answer){
		Submission submission = new Submission(answer, super.getID());
	}

	public void submitAnswerToSurvey(int numberOfHours, String comment){
		Answer answer = new Answer(comment, numberOfHours);
	}

	/*public void SetSurveyState(Survey i,String state){
		if (_representative == true){
			i.setEstate(state);	
		}
	}*/

	public void addSurvey(){
		if(_representative == true){
			Survey i = new Survey();
		}
	}

	public void addDiscipline(Discipline d){
		_disciplines.add(d);
	}
}