package sth.core;

import sth.app.exception.NonEmptySurveyException;
import sth.app.exception.ProjectException;
import sth.app.exception.FinishingSurveyException;
import sth.app.exception.NoSuchProjectException;
import sth.app.exception.NoSurveyException;

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
		// Open Project stays closed
	}

	void close(String disciplineName, Project project) {
		_survey.setState(new SurveyClosed(_survey));
	}

	void finalizeSurvey(String disciplineName, Project project) throws ProjectException {
		throw new FinishingSurveyException(disciplineName, project.getName());
	}

	void submit(String disciplineName, Project project, Student student, int time, String comment)
			throws ProjectException {
		if (!project.studentSubmitedProject(student)) {
			throw new NoSuchProjectException(disciplineName, project.getName());
		}
		_survey.addStudent(student);
		_survey.addAnswer(time, comment);
	}

	String getResults(Person person, String disciplineName, Project project, boolean smallFormat) {
		return super.getResults(person, disciplineName, project, smallFormat) + " aberto\n";
	}
}