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

	@Override
	protected String getPersonType() {
		return "FUNCIONÁRIO";
	}

	@Override
	protected String getPersonDisciplines() {
		return "";
	}

}
