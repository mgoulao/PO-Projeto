package sth.core;

import sth.core.exception.*;

import sth.core.Project;
import sth.core.Survey;

public abstract class SurveyState implements java.io.Serializable {

	private static final long serialVersionUID = 201812021448L;

	protected Survey _survey;

	/**
	 * @param survey
	 */
	SurveyState(Survey survey) {
		_survey = survey;
	}

	abstract void cancel(String disciplineName, Project project) throws SurveyFinishedException, NonEmptySurveyException;

	abstract void open(String disciplineName, Project project) throws SurveyFinishedException, OpeningSurveyException;

	abstract void close(String disciplineName, Project project) throws SurveyFinishedException, ClosingSurveyException;

	abstract void finalizeSurvey(String disciplineName, Project project) throws FinishingSurveyException;

	abstract void submit(String disciplineName, Project project, Student student, int time, String comment)
			throws NoSurveyException, NoSuchProjectException;

	/**
	 * @param person
	 * @param disciplineName
	 * @param project
	 * @param smallFormat
	 * @return survey results
	 */
	String getResults(Person person, String disciplineName, Project project, boolean smallFormat) {
		return disciplineName + " - " + project.getName();
	}

}