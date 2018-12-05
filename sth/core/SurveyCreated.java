package sth.core;

import sth.core.exception.OpeningSurveyException;
import sth.core.exception.ProjectException;
import sth.core.exception.ClosingSurveyException;
import sth.core.exception.FinishingSurveyException;
import sth.core.exception.NoSurveyException;

import sth.core.Project;

public class SurveyCreated extends SurveyState implements java.io.Serializable {

	private static final long serialVersionUID = 201812012140L;

	SurveyCreated(Survey survey) {
		super(survey);
	}

	void cancel(String disciplineName, Project project) {
		project.removeSurvey();
	}

	void open(String disciplineName, Project project) throws OpeningSurveyException {
		if (!project.isClosed()) {
			throw new OpeningSurveyException(disciplineName, project.getName());
		}
		_survey.setState(new SurveyOpen(_survey));
		_survey.notifyObservers("Pode preencher inquérito do projecto "+ project.getName() +" da disciplina "+ disciplineName+ "\n");
	}

	void close(String disciplineName, Project project) throws ClosingSurveyException {
		throw new ClosingSurveyException(disciplineName, project.getName());
	}

	void finalizeSurvey(String disciplineName, Project project) throws FinishingSurveyException {
		throw new FinishingSurveyException(disciplineName, project.getName());
	}

	void submit(String disciplineName, Project project, Student student, int time, String comment) throws NoSurveyException {
		throw new NoSurveyException(disciplineName, project.getName());
	}

	String getResults(Person person, String disciplineName, Project project, boolean smallFormat) {
		return super.getResults(person, disciplineName, project, smallFormat) + " (por abrir)\n";
	}
}