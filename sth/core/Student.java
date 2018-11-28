package sth.core;

import sth.core.exception.BadEntryException;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;
import sth.core.Discipline;
import sth.core.Submission;
import sth.core.Answer;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;

import sth.core.Discipline;
import sth.core.Survey;
import sth.core.Submission;
import sth.core.Answer;
import sth.core.Course;

public class Student extends Person implements java.io.Serializable {

	private static final long serialVersionUID = 201811111805L;

	private Course _course;
	private Set<Discipline> _disciplines = new TreeSet<>();
	private boolean _representative;

	/**
	 * @param id
	 * @param phone
	 * @param name
	 * @param representative
	 */
	public Student(int id, int phone, String name, boolean representative) {
		super(id, phone, name);
		_representative = representative;
	}

	/**
	 * @return student course
	 */
	Course getCourse() {
		return _course;
	}

	/**
	 * @return true if student is representative of his course
	 */
	boolean isRepresentative() {
		return _representative;
	}

	/**
	 * @param representative
	 */
	void setRepresentative(boolean representative) {
		if (_course.addRepresentative(this))
			_representative = representative;
	}

	/**
	 * @param disciplineName
	 * @param projectName
	 * @param answer
	 * @throws NoSuchDisciplineIdException
	 * @throws NoSuchProjectIdException
	 */
	void submiteProject(String disciplineName, String projectName, String answer)
			throws NoSuchDisciplineIdException, NoSuchProjectIdException {
		Discipline discipline = null;
		Project project = null;

		for (Discipline disc : getDisciplines()) {
			if (disc.getName().equals(disciplineName)) {
				discipline = disc;
			}
		}
		if(discipline == null)
			throw new NoSuchDisciplineIdException(disciplineName);

		if((project = discipline.getProjects().get(projectName)) == null) {
			throw new NoSuchProjectIdException(projectName);
		}

		if (!project.isClosed()) {
			project.addSubmission(answer, this);
		}
	}

	/**
	 * @param numberOfHours
	 * @param comment
	 */
	void submitAnswerToSurvey(int numberOfHours, String comment) {
		// FIXME: implement
	}

	/**
	 * @param survey
	 * @param state
	 */
	public void setSurveyState(Survey survey, String state) {
		if (_representative == true) {
			// FIXME: implement
		}
	}

	/**
	 * Add a survey
	 */
	void addSurvey() {
		if (_representative == true) {
			// FIXME: implement
		}
	}

	/**
	 * @param d
	 */
	void addDiscipline(Discipline d) {
		_disciplines.add(d);
	}

	Set<Discipline> getDisciplines() {
		return _disciplines;
	}

	/**
	 * @param lineContext
	 * @param school
	 */
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

	/**
	 * @return string that represents Student
	 */
	@Override
	public String toString() {
		String ret = "";
		String courseName = _course.getName();
		if (_representative) {
			ret = "DELEGADO|" + super.toString();
		} else {
			ret = "ALUNO|" + super.toString();
		}
		for (Discipline disc : _disciplines) {
			String discName = disc.getName();
			ret += "* " + courseName + " - " + discName + "\n";
		}

		return ret;
	}
}