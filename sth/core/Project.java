package sth.core;

import java.util.Map;
import java.util.TreeMap;

import sth.core.Student;
import sth.core.Submission;

public class Project implements java.io.Serializable {

	private static final long serialVersionUID = 201811111800L;

	private String _name;
	private String _description;
	private boolean _closed;
	private TreeMap<Student, Submission> _submissions = new TreeMap<Student, Submission>();

	public Project(String name, String description) {
		_name = name;
		_description = description;
	}

	public Project(String name) {
		_name = name;
	}

	public String getName() {
		return _name;
	}

	void close() {
		_closed = true;
	}

	void addSubmission(Submission s, Student a) {
		_submissions.replace(a, s);
	}

	TreeMap<Student, Submission> getSubmissions() {
		return _submissions;
	}
}