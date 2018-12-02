package sth.core;

import sth.app.exception.ProjectException;
import sth.app.exception.SurveyFinishedException;
import sth.app.exception.NoSurveyException;

import sth.core.Student;

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
		// Finalized Project stays finalized
	}

	void submit(String disciplineName, Project project, Student student, int time, String comment)
			throws ProjectException {
		throw new NoSurveyException(disciplineName, project.getName());
	}

	String getResults(Person person, String disciplineName, Project project, boolean smallFormat) {
		String res = super.getResults(person, disciplineName, project, smallFormat);
		if (smallFormat) {
			res += " - " + _survey.getNumberAnswers() + " respostas - " + _survey.getAvgTime() + " horas\n";
		} else {
			if (person instanceof Student) {
				res += "\n * Número de respostas: " + _survey.getNumberAnswers() + "\n";
				res += " * Tempo médio (horas): " + _survey.getAvgTime() + "\n";
			} else {
				res += "\n * Número de submissões: " + project.getNumberSubmissions() + "\n";
				res += " * Número de respostas: " + _survey.getNumberAnswers() + "\n";
				res += " * Tempos de resolução (horas) (mínimo, médio, máximo): " + _survey.getMinTime() + ", "
						+ _survey.getAvgTime() + ", " + _survey.getMaxTime() + "\n";
			}
		}
		return res;
	}
}