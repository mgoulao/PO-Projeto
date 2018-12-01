package sth.core;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import sth.app.exception.DuplicateSurveyException;
import sth.app.exception.ProjectException;
import sth.core.Student;
import sth.core.Submission;

public class Project implements java.io.Serializable {

	private static final long serialVersionUID = 201811111800L;

	private String _name;
	private String _description;
	private boolean _closed;
	private List<Submission> _submissions;
	private Survey _survey;

	/**
	 * @param name
	 */
	public Project(String name) {
		_name = name;
		_description = "Projeto - " + name;
		_submissions = new ArrayList<>();
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
		_closed = true;
		if (_survey != null) {
			try {
				_survey.open(disciplineName, this);
			} catch (ProjectException e) {
				//Esta excessao nunca ocorre
			}
		}
	}

	boolean isClosed() {
		return _closed;
	}

	/**
	 * @param answer
	 * @param student
	 */
	void addSubmission(String answer, Student student) {
		for (Submission subm : _submissions) {
			if (subm.getStudentID() == student.getID()) {
				_submissions.remove(subm);
				break;
			}
		}
		_submissions.add(new Submission(student, answer));
	}

	/**
	 * @return All submissions
	 */
	List<Submission> getSubmissions() {
		return _submissions;
	}

	Survey getSurvey() {
		return _survey;
	}

	void removeSurvey() {
		_survey = null;
	}

	void addSurvey(String disciplineName) throws ProjectException {
		if (_survey != null) {
			throw new DuplicateSurveyException(disciplineName, _name);
		}
		_survey = new Survey();
		if (isClosed())
			_survey.open(disciplineName, this);
	}
}