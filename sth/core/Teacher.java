package sth.core;

import sth.core.exception.BadEntryException;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sth.core.Course;
import sth.core.Discipline;
import sth.core.Submission;
import sth.core.Student;

public class Teacher extends Person implements java.io.Serializable {

	private static final long serialVersionUID = 201811111807L;

	private TreeMap<Course, TreeMap<String, Discipline>> _courseAndDisciplines = new TreeMap<Course, TreeMap<String, Discipline>>();

	/**
	 * @param iD
	 * @param phoneNumber
	 * @param name
	 */
	public Teacher(int iD, int phoneNumber, String name) {
		super(iD, phoneNumber, name);
	}

	/**
	 * @param name
	 * @param descricao
	 */
	public void createProject(String name, String descricao) {
		// FIXME: Implementar entrega final
	}

	/**
	 * @param p
	 */
	public void closeProject(Project p) {
		// FIXME: Implementar entrega final
	}

	/**
	 * @param p
	 * @return Map with submissions
	 */
	Map<Student, Submission> seeResults(Project p) {
		return p.getSubmissions();
	}

	/**
	 * @param d
	 * @return List with all discipline students
	 */
	List<Student> seeStudents(Discipline d) {
		return d.getStudents();
	}

	/**
	 * @param d
	 * @param c
	 */
	void addDiscipline(Discipline d, Course c) {
		_courseAndDisciplines.get(c).put(d.getName(), d);
	}

	/**
	 * 
	 * @param name
	 * @return discipline with the unique name
	 */
	Discipline getDiscipline(String name) {
		for (Map.Entry<Course, TreeMap<String, Discipline>> entry : _courseAndDisciplines.entrySet()) {
			Discipline discipline = entry.getValue().get(name);
			if (discipline != null)
				return discipline;
		}
		return null;
	}

	/**
	 * @return List with all discipline teached
	 */
	Map<String, Discipline> getDisciplines() {
		Map<String, Discipline> disciplines = new HashMap<>();
		for(Map.Entry<Course, TreeMap<String, Discipline>> entry : _courseAndDisciplines.entrySet()) {
			disciplines.putAll(entry.getValue());
		}
		return disciplines;
	}

	/**
	 * @param lineContext
	 * @param school
	 * @throws BadEntryException
	 */
	@Override
	void parseContext(String lineContext, School school) throws BadEntryException {
		String components[] = lineContext.split("\\|");

		if (components.length != 2)
			throw new BadEntryException("Invalid line context " + lineContext);

		Course course = school.parseCourse(components[0]);
		Discipline discipline = course.parseDiscipline(components[1]);

		if (!_courseAndDisciplines.containsKey(course)) {
			_courseAndDisciplines.put(course, new TreeMap<String, Discipline>());
		}
		_courseAndDisciplines.get(course).put(discipline.getName(), discipline);
		discipline.addTeacher(this);
	}

	@Override
	public String toString() {
		String ret = "DOCENTE|" + super.toString();

		for (Map.Entry<Course, TreeMap<String, Discipline>> entry : _courseAndDisciplines.entrySet()) {
			String courseName = entry.getKey().getName();
			for (Map.Entry<String, Discipline> discEntry : entry.getValue().entrySet()) {
				String discName = discEntry.getKey();
				ret += "* " + courseName + " - " + discName + "\n";
			}
		}

		return ret;
	}
}