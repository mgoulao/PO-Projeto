package sth.core;

import sth.app.exception.NonEmptySurveyException;
import sth.app.exception.ProjectException;
import sth.app.exception.FinishingSurveyException;

import sth.core.Project;

public class SurveyOpen extends SurveyState implements java.io.Serializable {

	private static final long serialVersionUID = 201812012141L;

	SurveyOpen(Survey survey) {
		super(survey);
	}

	void cancel(String disciplineName, Project project) throws ProjectException {
		if (_survey.getNumberAnswers() > 0) {
			throw new NonEmptySurveyException(disciplineName, project.getName());
		}
		project.removeSurvey();
	}

	void open(String disciplineName, Project project) {
		//Open Project stays closed
	}

	void close(String disciplineName, Project project) {
		_survey.setState(new SurveyClosed(_survey));
	}

	void finalizeSurvey(String disciplineName, Project project) throws ProjectException {
		throw new FinishingSurveyException(disciplineName, project.getName());
	}

	void submit() {

	}

	void getResults() {
	}
}