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

	public Discipline(String name/* , int capacity */) {
		_name = name;
		/* _capacity = capacity; */
	}

	public String getName() {
		return _name;
	}

	void enrollStudent(Student a) {
		System.out.format("disc: %s, %d\n", _name, _students.size());
		_students.add(a);
	}

	void addTeacher(Teacher d) {
		_teachers.add(d);
	}

	ArrayList<Teacher> getTeachers() {
		return _teachers;
	}

	TreeSet<Student> getStudents() {
		System.out.format("size: %d\n", _teachers.size());
		TreeSet<Student> students = new TreeSet<>();
		for (Student student : _students) {
			students.add(student);
		}
		return students;
	}

	void addProject(Project p) {
		_projects.add(p);
	}

	ArrayList<Project> getProjects() {
		return _projects;
	}

	@Override
	public int compareTo(Discipline other) {
		return this._name.compareTo(other.getName());
	}
}