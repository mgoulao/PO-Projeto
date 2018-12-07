package sth.core;

import sth.core.exception.NonEmptySurveyException;
import sth.core.exception.ProjectException;
import sth.core.exception.FinishingSurveyException;
import sth.core.exception.NoSuchProjectException;
import sth.core.exception.NoSurveyException;

import sth.core.Project;

public class SurveyOpen extends SurveyState implements java.io.Serializable {

	private static final long serialVersionUID = 201812012141L;

	/**
	 * @param survey
	 */
	SurveyOpen(Survey survey) {
		super(survey);
	}

	/**
	 * @param disciplineName
	 * @param project
	 * @throws NonEmptySurveyException
	 */
	void cancel(String disciplineName, Project project) throws NonEmptySurveyException {
		if (_survey.getNumberAnswers() > 0) {
			throw new NonEmptySurveyException(disciplineName, project.getName());
		}
		project.removeSurvey();
	}

	/**
	 * @param disciplineName
	 * @param project
	 */
	void open(String disciplineName, Project project) {
		// Open Project stays closed
	}

	/**
	 * @param disciplineName
	 * @param project
	 */
	void close(String disciplineName, Project project) {
		_survey.setState(new SurveyClosed(_survey));
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
	 * @throws NoSuchProjectException
	 */
	void submit(String disciplineName, Project project, Student student, int time, String comment)
			throws NoSuchProjectException {
		if (!project.studentSubmitedProject(student)) {
			throw new NoSuchProjectException(disciplineName, project.getName());
		}
		_survey.addStudent(student);
		_survey.addAnswer(time, comment);
	}

	/**
	 * @param person
	 * @param disciplineName
	 * @param project
	 * @param smallFormat
	 * @return survey results
	 */
	String getResults(Person person, String disciplineName, Project project, boolean smallFormat) {
		return super.getResults(person, disciplineName, project, smallFormat) + " (aberto)\n";
	}
}