package sth.core;

import sth.core.exception.BadEntryException;

public abstract class Person implements Comparable<Person>, java.io.Serializable {

	private String _name;
	private int _id;
	private int _phoneNumber;
	private String _type;

	public Person(int id, int phoneNumber, String name) {
		this._name = name;
		this._id = id;
		this._phoneNumber = phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		_phoneNumber = phoneNumber;
	}

	public String getName() {
		return _name;
	}

	public int getID() {
		return _id;
	}

	public int getPhoneNumber() {
		return _phoneNumber;
	}

	public void setID(int id) {
		_id = id;
	}

	public void setName(String name) {
		_name = name;
	}

	void parseContext(String context, School school) throws BadEntryException {
		throw new BadEntryException("Should not have extra context: " + context);
	}

	abstract String printPerson();

	@Override
	public int compareTo(Person other) {
		if(_id < other.getID())
			return -1;
		else if (_id == other.getID())
			return 0;
		return 1;
	}
}