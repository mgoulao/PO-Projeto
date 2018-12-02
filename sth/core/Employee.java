package sth.core;

public class Employee extends Person implements java.io.Serializable {

	private static final long serialVersionUID = 201811111803L;

	/**
	 * @param iD
	 * @param phoneNumber
	 * @param name
	 */
	public Employee(int iD, int phoneNumber, String name) {
		super(iD, phoneNumber, name);
	}

	@Override
	Discipline getDiscipline(String disciplineName) {
		return null;
	}

	/**
	 * @return string that represents Employee
	 */
	@Override
	public String toString() {
		return ("FUNCIONÁRIO|" + super.toString());
	}

}
