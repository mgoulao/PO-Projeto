package sth.core;

public class Answer extends Survey implements java.io.Serializable{

	private static final long serialVersionUID = 201811111813L;
	
	private String _comment;
	private int _numberOfHours;

	/**
	 * @param comment
	 * @param hours
	 */
	public Answer(String comment, int hours){
		_comment = comment;
		_numberOfHours = hours;
	}

	/**
	 * @return Answer comment
	 */
	public String getMessage(){
		return _comment;
	}

	/**
	 * @return number of hours
	 */
	public int getHours(){
		return _numberOfHours; 
	}
}