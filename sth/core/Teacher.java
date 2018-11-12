package sth.core;

import sth.core.exception.BadEntryException;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.ArrayList;

import sth.core.Course;
import sth.core.Discipline;
import sth.core.Submission;
import sth.core.Student;

public class Teacher extends Person implements java.io.Serializable {

	private static final long serialVersionUID = 201811111807L;

	private TreeMap<Course, TreeSet<Discipline>> _courseAndDisciplines = new TreeMap<Course, TreeSet<Discipline>>();

	public Teacher(int iD, int phoneNumber, String name) {
		super(iD, phoneNumber, name);
	}

	public void createProject(String name, String descricao) {
		// FIXME: Implementar entrega final
	}

	public void closeProject(Project p) {
		// FIXME: Implementar entrega final
	}

	TreeMap<Student, Submission> seeResults(Project p) {
		return p.getSubmissions();
	}

	ArrayList<Student> seeStudents(Discipline d) {
		return d.getStudents();
	}

	void addDiscipline(Discipline d, Course c) {
		_courseAndDisciplines.get(c).add(d);
	}

	Discipline getDiscipline(String name) {
		for (Map.Entry<Course, TreeSet<Discipline>> entry : _courseAndDisciplines.entrySet()) {
			for (Discipline discipline : entry.getValue()) {
				if (discipline.getName().equals(name))
					return discipline;
			}
		}
		return null;
	}

	ArrayList<Discipline> getDisciplines() {
		ArrayList<Discipline> disciplines = new ArrayList<>();
		for (Map.Entry<Course, TreeSet<Discipline>> entry : _courseAndDisciplines.entrySet()) {
			for (Discipline disc : entry.getValue()) {
				disciplines.add(disc);
			}
		}
		return disciplines;
	}

	@Override
	void parseContext(String lineContext, School school) throws BadEntryException {
		String components[] = lineContext.split("\\|");

		if (components.length != 2)
			throw new BadEntryException("Invalid line context " + lineContext);

		Course course = school.parseCourse(components[0]);
		Discipline discipline = course.parseDiscipline(components[1]);

		if (!_courseAndDisciplines.containsKey(course)) {
			_courseAndDisciplines.put(course, new TreeSet<Discipline>());
		}
		_courseAndDisciplines.get(course).add(discipline);
		discipline.addTeacher(this);
	}

	@Override
	public String printPerson() {
		String ret = "DOCENTE|" + super.getID() + "|" + super.getPhoneNumber() + "|" + super.getName() + "\n";

		for (Map.Entry<Course, TreeSet<Discipline>> entry : _courseAndDisciplines.entrySet()) {
			String courseName = entry.getKey().getName();
			for (Discipline disc : entry.getValue()) {
				String discName = disc.getName();
				ret += "* " + courseName + " - " + discName + "\n";
			}
		}

		return ret;
	}
}