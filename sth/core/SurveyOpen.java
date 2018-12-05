package sth.core;

import sth.core.exception.NonEmptySurveyException;
import sth.core.exception.ProjectException;
import sth.core.exception.FinishingSurveyException;
import sth.core.exception.NoSuchProjectException;
import sth.core.exception.NoSurveyException;

import sth.core.Project;

public class SurveyOpen extends SurveyState implements java.io.Serializable {

	private static final long serialVersionUID = 201812012141L;

	SurveyOpen(Survey survey) {
		super(survey);
	}

	void cancel(String disciplineName, Project project) throws NonEmptySurveyException {
		if (_survey.getNumberAnswers() > 0) {
			throw new NonEmptySurveyException(disciplineName, project.getName());
		}
		project.removeSurvey();
	}

	void open(String disciplineName, Project project) {
		// Open Project stays closed
	}

	void close(String disciplineName, Project project) {
		_survey.setState(new SurveyClosed(_survey));
	}

	void finalizeSurvey(String disciplineName, Project project) throws FinishingSurveyException {
		throw new FinishingSurveyException(disciplineName, project.getName());
	}

	void submit(String disciplineName, Project project, Student student, int time, String comment)
			throws NoSuchProjectException {
		if (!project.studentSubmitedProject(student)) {
			throw new NoSuchProjectException(disciplineName, project.getName());
		}
		_survey.addStudent(student);
		_survey.addAnswer(time, comment);
	}

	String getResults(Person person, String disciplineName, Project project, boolean smallFormat) {
		return super.getResults(person, disciplineName, project, smallFormat) + " (aberto)\n";
	}
}