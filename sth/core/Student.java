package sth.core;

import sth.core.exception.BadEntryException;

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

public class Student extends Person {

	private Course _course;
	private List<Discipline> _disciplines = new ArrayList<Discipline>();
	private boolean _representative;

	public Student(int id, int phone, String name, boolean representative) {
		super(id, phone, name);
		_representative = representative;
	}

	public Course getCourse() {
		return _course;
	}

	public boolean getRepresentative() {
		return _representative;
	}

	public void setRepresentative(boolean representative) {
		_representative = representative;
		_course.addRepresentative(this);
	}

	public void submiteProject(String answer) {
		Submission submission = new Submission(answer, super.getID());
	}

	public void submitAnswerToSurvey(int numberOfHours, String comment) {
		Answer answer = new Answer(comment, numberOfHours);
	}

	/*
	 * public void SetSurveyState(Survey i,String state){ if (_representative ==
	 * true){ i.setEstate(state); } }
	 */

	public void addSurvey() {
		if (_representative == true) {
			Survey i = new Survey();
		}
	}

	public void addDiscipline(Discipline d) {
		_disciplines.add(d);
	}

	@Override
	void parseContext(String lineContext, School school) throws BadEntryException {
		String components[] = lineContext.split("\\|");

		if (components.length != 2)
			throw new BadEntryException("Invalid line context " + lineContext);

		if (_course == null) {
			_course = school.parseCourse(components[0]);
			_course.addStudent(this);
		}

		Discipline disc = _course.parseDiscipline(components[1]);
		_disciplines.add(disc);
		disc.enrollStudent(this);
		if (_representative) {
			_course.addRepresentative(this);
		}
	}

	public String printStudent() {
		if (_representative) {
			return ("Delegado | " + super.getID() + " " + super.getPhoneNumber() + " " + super.getName());
		}
		return ("Aluno | " + super.getID() + " " + super.getPhoneNumber() + " " + super.getName());
	}
}