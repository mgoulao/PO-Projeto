package sth.core;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Collections;

import sth.core.Student;
import sth.core.Teacher;
import sth.core.Project;


public class Discipline implements Comparable<Discipline>, java.io.Serializable {

	private static final long serialVersionUID = 201811111808L;

	private String _name;
	private int _capacity;
	private List<Student> _students = new ArrayList<Student>();
	private List<Teacher> _teachers = new ArrayList<Teacher>();
	private Map<String, Project> _projects = new HashMap<>();

	/**
	 * @param name
	 */
	public Discipline(String name) {
		_name = name;
		_capacity = 200;
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
		if (_students.size() < _capacity)
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
	List<Teacher> getTeachers() {
		return _teachers;
	}

	/**
	 * @return List with discipline students
	 */
	List<Student> getStudents() {
		return _students;
	}

	/**
	 * @param p
	 */
	void addProject(Project p) {
		_projects.put(p.getName(), p);
	}

	/**
	 * @return Map with discipline projects
	 */
	Map<String, Project> getProjects() {
		return _projects;
	}

	@Override
	public int compareTo(Discipline other) {
		return this._name.compareTo(other.getName());
	}
}