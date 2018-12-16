package sth.core;

import sth.core.Course;
import sth.core.Discipline;
import sth.core.Survey;
import sth.core.exception.*;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class Student extends Person implements java.io.Serializable, Observer {

	private static final long serialVersionUID = 201811111805L;

	private Course _course;
	private Map<String, Discipline> _disciplines = new TreeMap<>();
	private boolean _representative;

	/**
	 * @param id
	 * @param phone
	 * @param name
	 * @param representative
	 */
	public Student(int id, int phone, String name, boolean representative) {
		super(id, phone, name);
		_representative = representative;
	}

	/**
	 * @return student course
	 */
	Course getCourse() {
		return _course;
	}

	/**
	 * @return true if student is representative of his course
	 */
	boolean isRepresentative() {
		return _representative;
	}

	/**
	 * @param representative
	 */
	void setRepresentative(boolean representative) {
		if (_course.addRepresentative(this))
			_representative = representative;
	}

	/**
	 * @param disciplineName
	 * @param projectName
	 * @param answer
	 * @throws NoSuchDisciplineIdException
	 * @throws NoSuchProjectIdException
	 */
	void submitProject(String disciplineName, String projectName, String answer)
			throws NoSuchDisciplineIdException, NoSuchProjectIdException {
		Discipline discipline = null;
		Project project = null;

		for (Map.Entry<String, Discipline> entry : getDisciplines().entrySet()) {
			Discipline disc = entry.getValue();
			if (disc.getName().equals(disciplineName)) {
				discipline = disc;
			}
		}
		if (discipline == null)
			throw new NoSuchDisciplineIdException(disciplineName);

		if ((project = discipline.getProjects().get(projectName)) == null)
			throw new NoSuchProjectIdException(projectName);

		if (!project.isClosed())
			project.addSubmission(answer, this);
		else
			throw new NoSuchProjectIdException(projectName);
	}

	/**
	 * @param disciplineName
	 * @param projectName
	 * @return Project
	 * @throws NoSuchDisciplineIdException
	 * @throws NoSuchProjectIdException
	 */
	private Project getCourseProject(String disciplineName, String projectName)
			throws NoSuchDisciplineIdException, NoSuchProjectIdException {
		Course course = getCourse();
		Discipline discipline = course.getDiscipline(disciplineName);
		if (discipline == null) {
			throw new NoSuchDisciplineIdException(disciplineName);
		}

		Project project = discipline.getProjects().get(projectName);
		if (project == null) {
			throw new NoSuchProjectIdException(projectName);
		}

		return project;
	}

	/**
	 * @param numberOfHours
	 * @param comment
	 */
	void submitAnswerToSurvey(String disciplineName, String projectName, int time, String comment)
			throws NoSuchDisciplineIdException, NoSuchProjectIdException, NoSurveyException, NoSuchProjectException {
		Project project = getProject(disciplineName, projectName);
		Survey survey = project.getSurvey();
		if (survey == null)
			throw new NoSurveyException(disciplineName, projectName);
		survey.submitAnswer(disciplineName, project, this, time, comment);
	}

	/**
	 * @param disciplineName
	 * @param projectName
	 * @throws NoSuchDisciplineIdException
	 * @throws NoSuchProjectIdException
	 * @throws DuplicateSurveyException
	 * @throws SurveyFinishedException
	 * @throws OpeningSurveyException
	 */
	void createSurvey(String disciplineName, String projectName) throws NoSuchDisciplineIdException,
			NoSuchProjectIdException, DuplicateSurveyException, SurveyFinishedException, OpeningSurveyException {
		if (isRepresentative()) {
			Course course = getCourse();
			Discipline discipline = course.getDiscipline(disciplineName);
			if (discipline == null) {
				throw new NoSuchDisciplineIdException(disciplineName);
			}

			Project project = discipline.getProjects().get(projectName);
			if (project == null || project.isClosed()) {
				throw new NoSuchProjectIdException(projectName);
			}

			Collection<Observer> observers = new TreeSet<>();
			observers.addAll(discipline.getStudents());
			observers.addAll(discipline.getTeachers());
			observers.addAll(course.getRepresentatives());

			project.addSurvey(disciplineName, observers);
		}
	}

	/**
	 * @param disciplineName
	 * @param projectName
	 * @throws NoSuchDisciplineIdException
	 * @throws NoSuchProjectIdException
	 * @throws SurveyFinishedException
	 * @throws NonEmptySurveyException
	 * @throws NoSurveyException
	 */
	public void cancelSurvey(String disciplineName, String projectName) throws NoSuchDisciplineIdException,
			NoSuchProjectIdException, SurveyFinishedException, NonEmptySurveyException, NoSurveyException {
		if (isRepresentative()) {
			Project project = getCourseProject(disciplineName, projectName);
			Survey survey = project.getSurvey();
			if (survey == null)
				throw new NoSurveyException(disciplineName, projectName);
			survey.cancel(disciplineName, project);
		}
	}

	/**
	 * @param disciplineName
	 * @param projectName
	 * @throws NoSuchDisciplineIdException
	 * @throws NoSuchProjectIdException
	 * @throws SurveyFinishedException
	 * @throws OpeningSurveyException
	 * @throws NoSurveyException
	 */
	public void openSurvey(String disciplineName, String projectName) throws NoSuchDisciplineIdException,
			NoSuchProjectIdException, OpeningSurveyException, NoSurveyException {
		if (isRepresentative()) {
			Project project = getCourseProject(disciplineName, projectName);
			Survey survey = project.getSurvey();
			if (survey == null)
				throw new NoSurveyException(disciplineName, projectName);
			survey.open(disciplineName, project);
		}
	}

	/**
	 * @param disciplineName
	 * @param projectName
	 * @throws NoSuchDisciplineIdException
	 * @throws NoSuchProjectIdException
	 * @throws SurveyFinishedException
	 * @throws ClosingSurveyException
	 * @throws NoSurveyException
	 */
	public void closeSurvey(String disciplineName, String projectName) throws NoSuchDisciplineIdException,
			NoSuchProjectIdException, ClosingSurveyException, NoSurveyException {
		if (isRepresentative()) {
			Project project = getCourseProject(disciplineName, projectName);
			Survey survey = project.getSurvey();
			if (survey == null)
				throw new NoSurveyException(disciplineName, projectName);
			survey.close(disciplineName, project);
		}
	}

	/**
	 * @param disciplineName
	 * @param projectName
	 * @throws NoSuchDisciplineIdException
	 * @throws NoSuchProjectIdException
	 * @throws FinishingSurveyException
	 * @throws NoSurveyException
	 */
	public void finalizeSurvey(String disciplineName, String projectName)
			throws NoSuchDisciplineIdException, NoSuchProjectIdException, FinishingSurveyException, NoSurveyException {
		if (isRepresentative()) {
			Project project = getCourseProject(disciplineName, projectName);
			Survey survey = project.getSurvey();
			if (survey == null)
				throw new NoSurveyException(disciplineName, projectName);
			survey.finalizeSurvey(disciplineName, project);
		}
	}

	/**
	 * @param disciplineName
	 * @param projectName
	 * @return
	 * @throws NoSuchDisciplineIdException
	 * @throws NoSuchProjectIdException
	 * @throws NoSurveyException
	 */
	public String showSurveyResults(String disciplineName, String projectName)
			throws NoSuchDisciplineIdException, NoSuchProjectIdException, NoSurveyException {
		Project project = getProject(disciplineName, projectName);
		Survey survey = project.getSurvey();
		if (survey == null)
			throw new NoSurveyException(disciplineName, projectName);
		return survey.getResultsFor(this, disciplineName, project, false);
	}

	/**
	 * @param disciplineName
	 * @return
	 * @throws NoSuchDisciplineIdException
	 */
	public String showDisciplineSurveyResults(String disciplineName) throws NoSuchDisciplineIdException {
		String res = "";
		if (isRepresentative()) {
			Course course = getCourse();
			Discipline discipline = course.getDiscipline(disciplineName);
			if (discipline == null)
				throw new NoSuchDisciplineIdException(disciplineName);
			for (Map.Entry<String, Project> entry : discipline.getProjects().entrySet()) {
				Project project = entry.getValue();
				Survey survey = project.getSurvey();
				if (survey != null) {
					res += survey.getResultsFor(this, disciplineName, project, true);
				}
			}
		}
		return res;
	}

	/**
	 * @param d
	 */
	void addDiscipline(Discipline d) {
		_disciplines.put(d.getName(), d);
	}

	/**
	 * @return Map with disciplines
	 */
	Map<String, Discipline> getDisciplines() {
		return _disciplines;
	}

	/**
	 * @param disciplineName
	 * @return Discipline
	 */
	@Override
	Discipline getDiscipline(String disciplineName) {
		return getDisciplines().get(disciplineName);
	}

	/**
	 * @param numberSubmission
	 * @param numberAnswers
	 * @param min
	 * @param max
	 * @param avg
	 * @return survey results
	 */
	@Override
	String surveyResultsFormat(int numberSubmission, int numberAnswers, int min, int max, int avg) {
		String res = "";
		res += "\n * Número de respostas: " + numberAnswers + "\n";
		res += " * Tempo médio (horas): " + avg + "\n";
		return res;
	}

	/**
	 * @param lineContext
	 * @param school
	 */
	@Override
	void parseContext(String lineContext, School school) throws BadEntryException {
		String components[] = lineContext.split("\\|");

		if (components.length != 2)
			throw new BadEntryException("Invalid line context " + lineContext);

		if (_course == null) {
			_course = school.parseCourse(components[0]);
			_course.addStudent(this);
		}

		Discipline disc = _course.parseDiscipline(components[1]);
		_disciplines.put(disc.getName(), disc);
		disc.enrollStudent(this);
		if (_representative) {
			_course.addRepresentative(this);
		}
	}

	/**
	 * @return person type
	 */
	@Override
	protected String getPersonType() {
		String res = "";
		if (isRepresentative())
			res += "DELEGADO";
		else
			res += "ALUNO";
		return res;
	}

	/**
	 * @return String with disciplines
	 */
	@Override
	protected String getPersonDisciplines() {
		String res = "";
		String courseName = _course.getName();
		for (Map.Entry<String, Discipline> entry : getDisciplines().entrySet()) {
			Discipline disc = entry.getValue();
			String discName = disc.getName();
			res += "* " + courseName + " - " + discName + "\n";
		}
		return res;
	}

}