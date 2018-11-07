package sth.core;

public class Answer extends Survey implements java.io.Serializable{
	
	private String _comment;
	private int _numberOfHours;

	public Answer(String coment, int hours){
		_comment = coment;
		_numberOfHours = hours;
	}

	public String getComment(){
		return _comment;
	}

	public int getNumberOfHours(){
		return _numberOfHours; 
	}
}