package sth.core;

import sth.core.exception.NoSurveyException;
import sth.core.exception.ProjectException;

public class SurveyClosed extends SurveyState implements java.io.Serializable {

	private static final long serialVersionUID = 201812012142L;

	SurveyClosed(Survey survey) {
		super(survey);
	}
	
	void cancel(String disciplineName, Project project) {
		_survey.setState(new SurveyOpen(_survey));
	}

	void open(String disciplineName, Project project) {
		_survey.setState(new SurveyOpen(_survey));
	}

	void close(String disciplineName, Project project) {
		//Closed Project stays closed
	}

	void finalizeSurvey(String disciplineName, Project project) {
		_survey.setState(new SurveyFinalized(_survey));
		_survey.notifyObservers("Resultados do inquérito do projecto "+ project.getName() +" da disciplina "+ disciplineName+ "\n");
	}

	void submit(String disciplineName, Project project, Student student, int time, String comment) throws NoSurveyException {
		throw new NoSurveyException(disciplineName, project.getName());
	}

	String getResults(Person person, String disciplineName, Project project, boolean smallFormat) {
		return super.getResults(person, disciplineName, project, smallFormat) + " (fechado)\n";
	}
}