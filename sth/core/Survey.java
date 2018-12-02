package sth.core;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import sth.app.exception.NoSuchProjectException;
import sth.app.exception.ProjectException;

import sth.core.Answer;

public class Survey implements java.io.Serializable {

	private static final long serialVersionUID = 201811111809L;

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

	void open(String disciplineName, Project project) throws ProjectException {
		_state.open(disciplineName, project);
	}

	void close(String disciplineName, Project project) throws ProjectException {
		_state.close(disciplineName, project);
	}

	void finalizeSurvey(String disciplineName, Project project) throws ProjectException {
		_state.finalizeSurvey(disciplineName, project);
	}

	void submitAnswer(String disciplineName, Project project, Student student, int time, String comment)
			throws ProjectException {
		if (!_filledIn.contains(student)) {
			_state.submit(disciplineName, project, student, time, comment);
		}
	}

	void addAnswer(int time, String comment) {
		_answers.add(new Answer(comment, time));
	}

	void addStudent(Student student) {
		_filledIn.add(student);
	}

	int getNumberAnswers() {
		return _answers.size();
	}

	String getResultsFor(Person person, String disciplineName, Project project, boolean smallFormat) {
		return _state.getResults(person, disciplineName, project, smallFormat);
	}

	int getMaxTime() {
		int max = 0;
		for(Answer answer : _answers) {
			int time = answer.getHours();
			if (time > max)
				max = time;
		}
		return max;
	}

	int getAvgTime() {
		double total = 0;
		double avg;
		for(Answer answer : _answers) {
			int time = answer.getHours();
			total += time;
		}
		avg = total / (double) getNumberAnswers();
		return (int) Math.floor(avg);
	}

	int getMinTime() {
		int min = Integer.MAX_VALUE;
		for(Answer answer : _answers) {
			int time = answer.getHours();
			if(time < min)
				min = time;
		}
		return min;
	}

}