package sth.core;

import java.util.List;
import java.util.TreeSet;
import java.util.ArrayList;

import sth.core.Student;
import sth.core.Teacher;
import sth.core.Project;

public class Discipline implements Comparable<Discipline>, java.io.Serializable {

	private static final long serialVersionUID = 201811111808L;

	private String _name;
	// private int _capacity;
	private ArrayList<Student> _students = new ArrayList<Student>();
	private ArrayList<Teacher> _teachers = new ArrayList<Teacher>();
	private ArrayList<Project> _projects = new ArrayList<Project>();

	/**
	 * @param name
	 */
	public Discipline(String name/* , int capacity */) {
		_name = name;
		/* _capacity = capacity; */
	}

	/**
	 * @return name
	 */
	public String getName() {
		return _name;
	}

	/**
	 * @param student
	 */
	void enrollStudent(Student student) {
		_students.add(student);
	}

	/**
	 * @param teacher
	 */
	void addTeacher(Teacher teacher) {
		_teachers.add(teacher);
	}

	/**
	 * @return List with discipline teachers
	 */
	ArrayList<Teacher> getTeachers() {
		return _teachers;
	}

	/**
	 * @return List with discipline students
	 */
	ArrayList<Student> getStudents() {
		return _students;
	}

	/**
	 * @param p
	 */
	void addProject(Project p) {
		_projects.add(p);
	}

	/**
	 * @return List with discipline projects
	 */
	ArrayList<Project> getProjects() {
		return _projects;
	}

	@Override
	public int compareTo(Discipline other) {
		return this._name.compareTo(other.getName());
	}
}