package sth.core;

import sth.app.exception.OpeningSurveyException;
import sth.app.exception.ProjectException;
import sth.app.exception.ClosingSurveyException;
import sth.app.exception.FinishingSurveyException;
import sth.app.exception.NoSurveyException;

import sth.core.Project;

public class SurveyCreated extends SurveyState implements java.io.Serializable {

	private static final long serialVersionUID = 201812012140L;

	SurveyCreated(Survey survey) {
		super(survey);
	}

	void cancel(String disciplineName, Project project) {
		project.removeSurvey();
	}

	void open(String disciplineName, Project project) throws ProjectException {
		if (!project.isClosed()) {
			throw new OpeningSurveyException(disciplineName, project.getName());
		}
		_survey.setState(new SurveyOpen(_survey));
	}

	void close(String disciplineName, Project project) throws ProjectException {
		throw new ClosingSurveyException(disciplineName, project.getName());
	}

	void finalizeSurvey(String disciplineName, Project project) throws ProjectException {
		throw new FinishingSurveyException(disciplineName, project.getName());
	}

	void submit(String disciplineName, Project project, Student student, int time, String comment) throws ProjectException {
		throw new NoSurveyException(disciplineName, project.getName());
	}

	String getResults(Person person, String disciplineName, Project project, boolean smallFormat) {
		return super.getResults(person, disciplineName, project, smallFormat) + " por abrir\n";
	}
}