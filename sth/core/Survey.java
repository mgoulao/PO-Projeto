package sth.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Observable;
import java.util.TreeMap;

import sth.core.exception.*;

import sth.core.Answer;

public class Survey implements java.io.Serializable {

	private static final long serialVersionUID = 201811111809L;

	private SurveyState _state;
	private Collection<Student> _filledIn = new ArrayList<>();
	private Collection<Answer> _answers = new ArrayList<>();
	private Collection<Person> _observers;

	/**
	 * Survey constructer
	 */
	Survey(Collection<Person> observers) {
		_state = new SurveyCreated(this);
		_observers = new ArrayList<>(observers);
	}

	protected void setState(SurveyState state) {
		_state = state;
	}

	void cancel(String disciplineName, Project project) throws SurveyFinishedException, NonEmptySurveyException {
		_state.cancel(disciplineName, project);
	}

	void open(String disciplineName, Project project) throws SurveyFinishedException, OpeningSurveyException {
		_state.open(disciplineName, project);
	}

	void close(String disciplineName, Project project) throws SurveyFinishedException, ClosingSurveyException {
		_state.close(disciplineName, project);
	}

	void finalizeSurvey(String disciplineName, Project project) throws FinishingSurveyException {
		_state.finalizeSurvey(disciplineName, project);
	}

	void submitAnswer(String disciplineName, Project project, Student student, int time, String comment)
			throws NoSurveyException, NoSuchProjectException {
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
		if(min == Integer.MAX_VALUE)
			min = 0;
		return min;
	}

	void notifyObservers(String message) {
		for(Person person : _observers) {
			person.update(new Notification(message));
		}
	}
}