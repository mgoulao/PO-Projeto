package sth.core;

import sth.core.Answer;
import sth.core.exception.*;

import java.util.ArrayList;
import java.util.Collection;

public class Survey extends Observable implements java.io.Serializable {

	private static final long serialVersionUID = 201811111809L;

	private SurveyState _state;
	private Collection<Student> _filledIn = new ArrayList<>();
	private Collection<Answer> _answers = new ArrayList<>();

	/**
	 * Survey constructer
	 * @param observers
	 */
	Survey(Collection<Observer> observers) {
		super(observers);
		_state = new SurveyCreated(this);
	}

	/**
	 * @param state
	 */
	protected void setState(SurveyState state) {
		_state = state;
	}

	/**
	 * @param disciplineName
	 * @param project
	 * @throws SurveyFinishedException
	 * @throws NonEmptySurveyException
	 */
	void cancel(String disciplineName, Project project) throws SurveyFinishedException, NonEmptySurveyException {
		_state.cancel(disciplineName, project);
	}

	/**
	 * @param disciplineName
	 * @param project
	 * @throws OpeningSurveyException
	 */
	void open(String disciplineName, Project project) throws OpeningSurveyException {
		_state.open(disciplineName, project);
	}

	/**
	 * @param disciplineName
	 * @param project
	 * @throws ClosingSurveyException
	 */
	void close(String disciplineName, Project project) throws ClosingSurveyException {
		_state.close(disciplineName, project);
	}

	/**
	 * @param disciplineName
	 * @param project
	 * @throws FinishingSurveyException
	 */
	void finalizeSurvey(String disciplineName, Project project) throws FinishingSurveyException {
		_state.finalizeSurvey(disciplineName, project);
	}

	/**
	 * @param disciplineName
	 * @param project
	 * @param student
	 * @param time
	 * @param comment
	 * @throws NoSurveyException
	 * @throws NoSuchProjectException
	 */
	void submitAnswer(String disciplineName, Project project, Student student, int time, String comment)
			throws NoSurveyException, NoSuchProjectException {
		if (!_filledIn.contains(student)) {
			_state.submit(disciplineName, project, student, time, comment);
		}
	}

	/**
	 * @param time
	 * @param comment
	 */
	void addAnswer(int time, String comment) {
		_answers.add(new Answer(comment, time));
	}

	/**
	 * @param student
	 */
	void addStudent(Student student) {
		_filledIn.add(student);
	}

	/**
	 * @return
	 */
	int getNumberAnswers() {
		return _answers.size();
	}

	/**
	 * @param person
	 * @param disciplineName
	 * @param project
	 * @param smallFormat
	 * @return survey results
	 */
	String getResultsFor(Person person, String disciplineName, Project project, boolean smallFormat) {
		return _state.getResults(person, disciplineName, project, smallFormat);
	}

	/**
	 * @return survey max time
	 */
	int getMaxTime() {
		int max = 0;
		for(Answer answer : _answers) {
			int time = answer.getHours();
			if (time > max)
				max = time;
		}
		return max;
	}

	/**
	 * @return survey avg time
	 */
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

	/**
	 * @return survey min time
	 */
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

	/**
	 * @param message
	 */
	void createMessage(String message) {
		notifyObservers(new Notification(message));
	}
}