package sth.core;

import sth.core.exception.NoSurveyException;

public class SurveyClosed extends SurveyState implements java.io.Serializable {

	private static final long serialVersionUID = 201812012142L;

	/**
	 * @param survey
	 */
	SurveyClosed(Survey survey) {
		super(survey);
	}

	/**
	 * @param disciplineName
	 * @param project
	 */
	void cancel(String disciplineName, Project project) {
		_survey.setState(new SurveyOpen(_survey));
	}

	/**
	 * @param disciplineName
	 * @param project
	 */
	void open(String disciplineName, Project project) {
		_survey.setState(new SurveyOpen(_survey));
		_survey.createMessage("Pode preencher inquérito do projecto "+ project.getName() +" da disciplina "+ disciplineName+ "\n");
	}

	/**
	 * @param disciplineName
	 * @param project
	 */
	void close(String disciplineName, Project project) {
		//Closed Project stays closed
	}

	/**
	 * @param disciplineName
	 * @param project
	 */
	void finalizeSurvey(String disciplineName, Project project) {
		_survey.setState(new SurveyFinalized(_survey));
		_survey.createMessage("Resultados do inquérito do projecto "+ project.getName() +" da disciplina "+ disciplineName+ "\n");
	}

	/**
	 * @param disciplineName
	 * @param project
	 * @param student
	 * @param time
	 * @param comment
	 * @throws NoSurveyException
	 */
	void submit(String disciplineName, Project project, Student student, int time, String comment) throws NoSurveyException {
		throw new NoSurveyException(disciplineName, project.getName());
	}

	/**
	 * @param person
	 * @param disciplineName
	 * @param project
	 * @param smallFormat
	 * @return survey results
	 */
	String getResults(Person person, String disciplineName, Project project, boolean smallFormat) {
		return super.getResults(person, disciplineName, project, smallFormat) + " (fechado)\n";
	}
}