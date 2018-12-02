package sth.core;

public class Submission implements Comparable<Submission>, java.io.Serializable {

	private static final long serialVersionUID = 201812021600L;

	private Student _student;
	private String _answer;

	/**
	 * @param student
	 * @param submission
	 */
	public Submission(Student student, String submission) {
		_student = student;
		_answer = submission;
	}

	/**
	 * @return student id
	 */
	public int getStudentID() {
		return _student.getID();
	}

	@Override
	public String toString() {
		return _answer;
	}

	@Override
	public int compareTo(Submission subm) {
		return getStudentID() - subm.getStudentID();
	}
}
