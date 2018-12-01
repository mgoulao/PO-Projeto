package sth.core;

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
	}

	void submit() {

	}

	void getResults() {
	}
}