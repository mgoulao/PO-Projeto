package sth.core;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import sth.core.Answer;

public class Survey implements java.io.Serializable {

	private static final long serialVersionUID = 201811111809L;

	private enum SurveySate {
		ABERTO, FECHADO
	};

	private SurveySate _state;
	private ArrayList<Student> _filledIn = new ArrayList<>();
	private ArrayList<Answer> _answers = new ArrayList<>();

	/**
	 * Survey constructer
	 */
	Survey() {
		_state = SurveySate.ABERTO;
	}

	/**
	 * open survey
	 */
	void open() {
		// FIXME: implement
	}

	/**
	 * close survey
	 */
	void close() {
		// FIXME: implement
	}

	/**
	 * finalize survey
	 */
	void finalizeSurvey() {
		// FIXME: implement
	}

	/**
	 * @param student
	 * @param time
	 * @param comment
	 */
	void addAnswer(Student student, int time, String comment) {
		// FIXME: implement
	}

	/**
	 * @param person
	 * @return survey results
	 */
	String getResultsFor(Person person) {
		// FIXME: implement
		return "";
	}

}