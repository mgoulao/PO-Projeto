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

	Survey() {
		_state = SurveySate.ABERTO;
	}

	void open() {
		// FIXME: implement
	}

	void close() {
		// FIXME: implement
	}

	void finalizeSurvey() {
		// FIXME: implement
	}

	void addAnswer(Student student, int time, String comment) {
		// FIXME: implement
	}

	String getResultsFor(Person person) {
		// FIXME: implement
	}

}