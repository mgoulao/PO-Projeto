package sth.core;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import java.util.ArrayList;

import sth.core.exception.*;

public abstract class Person implements Comparable<Person>, java.io.Serializable {

	private static final long serialVersionUID = 201811111802L;

	private String _name;
	private int _id;
	private int _phoneNumber;
	private Collection<Notification> _notifications = new ArrayList<>();

	/**
	 * @param id
	 * @param phoneNumber
	 * @param name
	 */
	public Person(int id, int phoneNumber, String name) {
		_name = name;
		_id = id;
		_phoneNumber = phoneNumber;
	}

	/**
	 * @param phoneNumber
	 */
	public void setPhoneNumber(int phoneNumber) {
		_phoneNumber = phoneNumber;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return _name;
	}

	/**
	 * @return int - id
	 */
	public int getID() {
		return _id;
	}

	/**
	 * @return phone number
	 */
	public int getPhoneNumber() {
		return _phoneNumber;
	}

	/**
	 * @param id
	 */
	public void setID(int id) {
		_id = id;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		_name = name;
	}

	public String getNotifications() {
		String res = "";
		Iterator<Notification> iterator = _notifications.iterator();
		while (iterator.hasNext()) {
			Notification notification = iterator.next();
			res += notification.getMessage();
			iterator.remove();
		}
		return res;
	}

	public void update(Notification notification) {
		_notifications.add(notification);
	}

	protected Project getProject(String disciplineName, String projectName)
			throws NoSuchDisciplineIdException, NoSuchProjectIdException {
		Discipline discipline = getDiscipline(disciplineName);
		if (discipline == null) {
			throw new NoSuchDisciplineIdException(disciplineName);
		}

		Project project = discipline.getProjects().get(projectName);
		if (project == null) {
			throw new NoSuchProjectIdException(projectName);
		}
		return project;
	}

	String surveyResultsFormat(int numberSubmission, int numberAnswers, int min, int max, int avg) {
		return "";
	}

	abstract Discipline getDiscipline(String disciplineName);

	protected abstract String getPersonType();

	protected String getPersonBasicInfo() {
		return "|" + getID() + "|" + getPhoneNumber() + "|" + getName() + "\n";
	}

	protected abstract String getPersonDisciplines();

	@Override
	public String toString() {
		String res = "";
		res += getPersonType();
		res += getPersonBasicInfo();
		res += getPersonDisciplines();
		return res;
	}

	/**
	 * @param context
	 * @param school
	 * @throws BadEntryException
	 */
	void parseContext(String context, School school) throws BadEntryException {
		throw new BadEntryException("Should not have extra context: " + context);
	}

	@Override
	public int compareTo(Person other) {
		if (_id < other.getID())
			return -1;
		else if (_id == other.getID())
			return 0;
		return 1;
	}
}