package sth.core;

import sth.core.Project;
import sth.core.exception.ClosingSurveyException;
import sth.core.exception.FinishingSurveyException;
import sth.core.exception.NoSurveyException;
import sth.core.exception.OpeningSurveyException;

public class SurveyCreated extends SurveyState implements java.io.Serializable {

	private static final long serialVersionUID = 201812012140L;

	/**
	 * @param survey
	 */
	SurveyCreated(Survey survey) {
		super(survey);
	}

	/**
	 * @param disciplineName
	 * @param project
	 */
	void cancel(String disciplineName, Project project) {
		project.removeSurvey();
	}

	/**
	 * @param disciplineName
	 * @param project
	 * @throws OpeningSurveyException
	 */
	void open(String disciplineName, Project project) throws OpeningSurveyException {
		if (!project.isClosed()) {
			throw new OpeningSurveyException(disciplineName, project.getName());
		}
		_survey.setState(new SurveyOpen(_survey));
		_survey.createMessage("Pode preencher inquérito do projecto "+ project.getName() +" da disciplina "+ disciplineName+ "\n");
	}

	/**
	 * @param disciplineName
	 * @param project
	 * @throws ClosingSurveyException
	 */
	void close(String disciplineName, Project project) throws ClosingSurveyException {
		throw new ClosingSurveyException(disciplineName, project.getName());
	}

	/**
	 * @param disciplineName
	 * @param project
	 * @throws FinishingSurveyException
	 */
	void finalizeSurvey(String disciplineName, Project project) throws FinishingSurveyException {
		throw new FinishingSurveyException(disciplineName, project.getName());
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
		return super.getResults(person, disciplineName, project, smallFormat) + " (por abrir)\n";
	}
}