package sth.core;

import sth.app.exception.ProjectException;
import sth.app.exception.SurveyFinishedException;;

public class SurveyFinalized extends SurveyState implements java.io.Serializable {

	private static final long serialVersionUID = 201812012143L;

	SurveyFinalized(Survey survey) {
		super(survey);
	}

	void cancel(String disciplineName, Project project) throws ProjectException {
		throw new SurveyFinishedException(disciplineName, project.getName());
	}

	void open(String disciplineName, Project project) throws ProjectException {
		throw new SurveyFinishedException(disciplineName, project.getName());
	}

	void close(String disciplineName, Project project) throws ProjectException {
		throw new SurveyFinishedException(disciplineName, project.getName());
	}

	void finalizeSurvey(String disciplineName, Project project) {
		//Finalized Project stays finalized
	}

	void submit() {

	}

	void getResults() {
	}
}