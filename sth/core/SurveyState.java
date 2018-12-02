package sth.core;

import sth.app.exception.ProjectException;

import sth.core.Project;
import sth.core.Survey;

public abstract class SurveyState implements java.io.Serializable {

	private static final long serialVersionUID = 201812021448L;

	protected Survey _survey;

	SurveyState(Survey survey) {
		_survey = survey;
	}

	abstract void cancel(String disciplineName, Project project) throws ProjectException;

	abstract void open(String disciplineName, Project project) throws ProjectException;

	abstract void close(String disciplineName, Project project) throws ProjectException;

	abstract void finalizeSurvey(String disciplineName, Project project) throws ProjectException;

	abstract void submit(String disciplineName, Project project, Student student, int time, String comment)
			throws ProjectException;

	String getResults(Person person, String disciplineName, Project project, boolean smallFormat) {
		return disciplineName + " - " + project.getName();
	}

}