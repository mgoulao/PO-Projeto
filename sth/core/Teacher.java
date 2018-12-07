package sth.core;

import sth.core.exception.*;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
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
	public void createProject(String disciplineName, String projectName)
			throws NoSuchDisciplineIdException, DuplicateProjectException {
		Discipline discipline = getDisciplines().get(disciplineName);

		if (discipline == null)
			throw new NoSuchDisciplineIdException(disciplineName);

		if (discipline.getProjects().get(projectName) != null)
			throw new DuplicateProjectException(disciplineName, projectName);

		discipline.addProject(new Project(projectName));
	}

	/**
	 * @param p
	 */
	public void closeProject(String disciplineName, String projectName)
			throws NoSuchDisciplineIdException, NoSuchProjectIdException {
		Discipline discipline = null;
		Project project = null;

		discipline = getDisciplines().get(disciplineName);

		if (discipline == null)
			throw new NoSuchDisciplineIdException(disciplineName);

		project = discipline.getProjects().get(projectName);

		if (project == null)
			throw new NoSuchProjectIdException(projectName);

		project.close(disciplineName);
	}

	/**
	 * @param p
	 * @return Collection with submissions
	 */
	Collection<Submission> seeResults(Project p) {
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
	@Override
	Discipline getDiscipline(String name) {
		for (Map.Entry<Course, TreeMap<String, Discipline>> entry : _courseAndDisciplines.entrySet()) {
			Discipline discipline = entry.getValue().get(name);
			if (discipline != null)
				return discipline;
		}
		return null;
	}

	/**
	 * @return Map with all discipline teached
	 */
	Map<String, Discipline> getDisciplines() {
		Map<String, Discipline> disciplines = new HashMap<>();
		for (Map.Entry<Course, TreeMap<String, Discipline>> entry : _courseAndDisciplines.entrySet()) {
			disciplines.putAll(entry.getValue());
		}
		return disciplines;
	}

	/**
	 * @param numberSubmission
	 * @param numberAnswers
	 * @param min
	 * @param max
	 * @param avg
	 * @return String with survey results
	 */
	@Override
	String surveyResultsFormat(int numberSubmission, int numberAnswers, int min, int max, int avg) {
		String res = "";
		res += "\n * Número de submissões: " + numberSubmission + "\n";
		res += " * Número de respostas: " + numberAnswers + "\n";
		res += " * Tempos de resolução (horas) (mínimo, médio, máximo): " + min + ", " + avg + ", " + max + "\n";
		return res;
	}

	/**
	 * @param disciplineName
	 * @param projectName
	 * @return survey results
	 * @throws NoSuchDisciplineIdException
	 * @throws NoSuchProjectIdException
	 * @throws NoSurveyException
	 */
	String showSurveyResults(String disciplineName, String projectName)
			throws NoSuchDisciplineIdException, NoSuchProjectIdException, NoSurveyException {
		Project project = getProject(disciplineName, projectName);
		Survey survey = project.getSurvey();
		if (survey == null)
			throw new NoSurveyException(disciplineName, projectName);
		return survey.getResultsFor(this, disciplineName, project, true);
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

	/**
	 * @return person type
	 */
	@Override
	protected String getPersonType() {
		return "DOCENTE";
	}

	/**
	 * @return string with disciplines
	 */
	@Override
	protected String getPersonDisciplines() {
		String res = "";
		for (Map.Entry<Course, TreeMap<String, Discipline>> entry : _courseAndDisciplines.entrySet()) {
			String courseName = entry.getKey().getName();
			for (Map.Entry<String, Discipline> discEntry : entry.getValue().entrySet()) {
				String discName = discEntry.getKey();
				res += "* " + courseName + " - " + discName + "\n";
			}
		}
		return res;
	}

}