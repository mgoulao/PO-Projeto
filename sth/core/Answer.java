package sth.core;

public class Answer extends Survey implements java.io.Serializable{

	private static final long serialVersionUID = 201811111813L;
	
	private String _comment;
	private int _numberOfHours;

	public Answer(String comment, int hours){
		_comment = comment;
		_numberOfHours = hours;
	}

	public String getMessage(){
		return _comment;
	}

	public int getHours(){
		return _numberOfHours; 
	}
}