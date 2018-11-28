package sth.core;

public class Submission {

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
		return "Submission placeholder";
	}
}
