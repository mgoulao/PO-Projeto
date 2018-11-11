package sth.core;

public class Employee extends Person implements java.io.Serializable {

	private static final long serialVersionUID = 201811111803L;

	public Employee(int iD, int phoneNumber, String name) {
		super(iD, phoneNumber, name);
	}

	@Override
	String printPerson() {
		return ("FUNCIONÁRIO|" + super.getID() + "|" + super.getPhoneNumber() + "|" + super.getName() + "\n");
	}

}
