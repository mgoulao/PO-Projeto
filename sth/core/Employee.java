package sth.core;

public class Employee extends Person implements java.io.Serializable {

	private static final long serialVersionUID = 201811111803L;

	public Employee(int iD, int phoneNumber, String name) {
		super(iD, phoneNumber, name);
	}

	@Override
	public String toString() {
		return ("FUNCIONÁRIO|" + super.toString());
	}

}
