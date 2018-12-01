package sth.core;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import sth.app.exception.ProjectException;

import sth.core.Answer;

public class Survey implements java.io.Serializable {

	private static final long serialVersionUID = 201811111809L;

	public final SurveyState CREATED = new SurveyCreated(this);
	public final SurveyState OPEN = new SurveyOpen(this);
	public final SurveyState CLOSED = new SurveyClosed(this);
	public final SurveyState FINALIZED = new SurveyFinalized(this);

	private SurveyState _state;
	private ArrayList<Student> _filledIn = new ArrayList<>();
	private ArrayList<Answer> _answers = new ArrayList<>();

	/**
	 * Survey constructer
	 */
	Survey() {
		_state = new SurveyCreated(this);
	}

	protected void setState(SurveyState state) {
		_state = state;
	}

	void cancel(String disciplineName, Project project) throws ProjectException {
		_state.cancel(disciplineName, project);
	}

	/**
	 * open survey
	 */
	void open(String disciplineName, Project project) throws ProjectException  {
		_state.open(disciplineName, project);
	}

	/**
	 * close survey
	 */
	void close(String disciplineName, Project project) throws ProjectException {
		_state.close(disciplineName, project);
	}

	/**
	 * finalize survey
	 */
	void finalizeSurvey(String disciplineName, Project project) throws ProjectException {
		_state.finalizeSurvey(disciplineName, project);
	}

	/**
	 * @param student
	 * @param time
	 * @param comment
	 */
	void addAnswer(Student student, int time, String comment) {
		if(_filledIn.contains(student)) {
			//TODO: erro
		}
	}

	int getNumberAnswers() {
		return _answers.size();
	}

	/**
	 * @param person
	 * @return survey results
	 */
	String getResultsFor(Person person) {
		_state.getResults();
		return "";
	}

}