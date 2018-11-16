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

	/**
	 * @param name
	 */
	public Project(String name) {
		_name = name;
		_description = "Projeto - " + name;
	}


	/** 
	 * @return name
	 */
	public String getName() {
		return _name;
	}

	/**
	 * Closes project
	 */
	void close() {
		_closed = true;
	}

	/**
	 * @param s
	 * @param a
	 */
	void addSubmission(Submission s, Student a) {
		_submissions.replace(a, s);
	}

	/**
	 * @return All submissions
	 */
	TreeMap<Student, Submission> getSubmissions() {
		return _submissions;
	}
}