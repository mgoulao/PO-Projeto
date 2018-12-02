package sth.core;

import java.util.Map;

import sth.core.exception.BadEntryException;

public abstract class Person implements Comparable<Person>, java.io.Serializable {

	private static final long serialVersionUID = 201811111802L;

	private String _name;
	private int _id;
	private int _phoneNumber;
	/* private ArrayList<Notification> _notifications = new ArrayList<Notification>();
	private boolean _hasNotificationsOn; */

	/**
	 * @param id
	 * @param phoneNumber
	 * @param name
	 */
	public Person(int id, int phoneNumber, String name) {
		this._name = name;
		this._id = id;
		this._phoneNumber = phoneNumber;
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

	/* public void changeNotificationsSettings() {
		if (_hasNotificationsOn) {
			_hasNotificationsOn = false;

		} else {
			_hasNotificationsOn = true;
		}
	}

	public void getNotificationCurrentState() {
		return _hasNotificationsOn;
	}

	public void addNotification(Notification n) {

		if (_hasNotificationsOn) {

			_notifications.add(n);

		}

	} */

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