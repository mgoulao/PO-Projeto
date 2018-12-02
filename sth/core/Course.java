package sth.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;

import sth.core.Discipline;
import sth.core.Student;

public class Course implements Comparable<Course>, java.io.Serializable {

	private static final long serialVersionUID = 201811111810L;

	private String _name;
	private Map<String, Discipline> _disciplines = new HashMap<>();
	private List<Student> _representatives = new ArrayList<>();
	private List<Student> _students = new ArrayList<>();

	/**
	 * @param name
	 */
	public Course(String name) {
		_name = name;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return _name;
	}

	/**
	 * @param disciplines
	 */
	void addDiscipline(Discipline discipline) {
		_disciplines.put(discipline.getName(), discipline);
	}

	
	/**
	 * @param s
	 * @return true if the student can be a representative
	 */
	boolean addRepresentative(Student s) {
		if (_representatives.size() < 6 && !_representatives.contains(s)) {
			_representatives.add(s);
			return true;
		}
		return false;
	}
	
	/**
	 * @param s
	 */
	void removeRepresentative(Student s) {
		if (_representatives.contains(s)) {
			_representatives.remove(s);
		}
	}
	
	/**
	 * @param student
	 */
	void addStudent(Student student) {
		_students.add(student);
	}

	Discipline getDiscipline(String disciplineName) {
		return _disciplines.get(disciplineName);
	}
	
	Map<String, Discipline> getDisciplines() {
		return _disciplines;
	}

	/**
	 * @param name
	 * @return Discipline with the name
	 */
	Discipline parseDiscipline(String name) {
		Discipline d;
		if(_disciplines.containsKey(name))
			return _disciplines.get(name);
		d = new Discipline(name);
		addDiscipline(d);
		return d;
	}
	
	@Override
	public int compareTo(Course other) {
		return this._name.compareTo(other.getName());
	}
}