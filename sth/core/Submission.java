package sth.core;

public class Submission{
	private String _project;
	private int _studentID;

	public Submission(String proj, int id){
		_studentID = id;
		_project = proj;
	}

	public int getID(){
		return _studentID;
	}
}

 