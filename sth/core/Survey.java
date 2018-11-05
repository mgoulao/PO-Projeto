package sth.core;

import java.util.Map;
import java.util.TreeMap;

import sth.core.Answer;

public class Survey{
	
	private String _state;
	private TreeMap<Integer,Answer>  _surveys = new TreeMap<Integer,Answer>();

	public void addSurvey(Answer i, int idStudent){
		_surveys.put(idStudent, i); 
	}

	public TreeMap<Integer,Answer> getSurveys(){
		return _surveys;
	}
}