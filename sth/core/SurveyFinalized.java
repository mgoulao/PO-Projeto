package sth.core;

import sth.core.exception.ProjectException;
import sth.core.exception.SurveyFinishedException;
import sth.core.exception.NoSurveyException;

import sth.core.Student;

public class SurveyFinalized extends SurveyState implements java.io.Serializable {

	private static final long serialVersionUID = 201812012143L;

	SurveyFinalized(Survey survey) {
		super(survey);
	}

	void cancel(String disciplineName, Project project) throws SurveyFinishedException {
		throw new SurveyFinishedException(disciplineName, project.getName());
	}

	void open(String disciplineName, Project project) throws SurveyFinishedException {
		throw new SurveyFinishedException(disciplineName, project.getName());
	}

	void close(String disciplineName, Project project) throws SurveyFinishedException {
		throw new SurveyFinishedException(disciplineName, project.getName());
	}

	void finalizeSurvey(String disciplineName, Project project) {
		// Finalized Project stays finalized
	}

	void submit(String disciplineName, Project project, Student student, int time, String comment)
			throws NoSurveyException {
		throw new NoSurveyException(disciplineName, project.getName());
	}

	String getResults(Person person, String disciplineName, Project project, boolean smallFormat) {
		String res = super.getResults(person, disciplineName, project, smallFormat);
		if (smallFormat) {
			res += " - " + _survey.getNumberAnswers() + " respostas - " + _survey.getAvgTime() + " horas\n";
		} else {
			person.surveyResultsFormat(project.getNumberSubmissions(), _survey.getNumberAnswers(), _survey.getMinTime(),
					_survey.getMaxTime(), _survey.getAvgTime());
		}
		return res;
	}
}