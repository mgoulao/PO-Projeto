package sth.core;

import sth.app.exception.ProjectException;

import sth.core.Project;
import sth.core.Survey;

public abstract class SurveyState {

	protected Survey _survey;
	
	SurveyState(Survey survey) {
		_survey = survey;
	}

	abstract void cancel(String disciplineName, Project project) throws ProjectException;
	
	abstract void open(String disciplineName, Project project) throws ProjectException;

	abstract void close(String disciplineName, Project project) throws ProjectException;

	abstract void finalizeSurvey(String disciplineName, Project project) throws ProjectException;

	abstract void submit();

	abstract void getResults();
}