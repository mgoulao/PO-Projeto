package sth.core;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.TreeSet;

import sth.core.exception.*;
import sth.core.Student;
import sth.core.Submission;

public class Project implements java.io.Serializable {

	private static final long serialVersionUID = 201811111800L;

	private String _name;
	private String _description;
	private boolean _closed;
	private Collection<Submission> _submissions;
	private Survey _survey;

	/**
	 * @param name
	 */
	public Project(String name) {
		_name = name;
		_description = "Projeto - " + name;
		_submissions = new TreeSet<>();
	}

	/**
	 * @return name
	 */
	public String getName() {
		return _name;
	}

	/**
	 * Closes project
	 */
	void close(String disciplineName) {
		if (!isClosed()) {
			_closed = true;
			if (_survey != null) {
				try {
					_survey.open(disciplineName, this);
				} catch (Exception e) {
					// Nenhuma das excessoes ocorre
				}
			}
		}
	}

	/**
	 * @return true if project is closed
	 */
	boolean isClosed() {
		return _closed;
	}

	/**
	 * @param answer
	 * @param student
	 */
	void addSubmission(String answer, Student student) {
		Iterator<Submission> iterator = _submissions.iterator();
		while (iterator.hasNext()) {
			Submission subm = iterator.next();
			if (subm.getStudentID() == student.getID()) {
				iterator.remove();
				break;
			}
		}
		_submissions.add(new Submission(student, answer));
	}

	/**
	 * @return All submissions
	 */
	Collection<Submission> getSubmissions() {
		return _submissions;
	}

	/**
	 * @return number of submissions
	 */
	int getNumberSubmissions() {
		return _submissions.size();
	}

	/**
	 * @return Survey
	 */
	Survey getSurvey() {
		return _survey;
	}

	/**
	 * Remove survey from Project
	 */
	void removeSurvey() {
		_survey = null;
	}

	/**
	 * @param disciplineName
	 * @param observers
	 * @throws DuplicateSurveyException
	 * @throws SurveyFinishedException
	 * @throws OpeningSurveyException
	 */
	void addSurvey(String disciplineName, Collection<Observer> observers)
			throws DuplicateSurveyException, SurveyFinishedException, OpeningSurveyException {
		if (_survey != null) {
			throw new DuplicateSurveyException(disciplineName, _name);
		}
		_survey = new Survey(observers);
		if (isClosed())
			_survey.open(disciplineName, this);
	}

	/**
	 * @param student
	 * @return true if student has submited
	 */
	boolean studentSubmitedProject(Student student) {
		for (Submission subm : _submissions) {
			if (subm.getStudentID() == student.getID()) {
				return true;
			}
		}
		return false;
	}
}