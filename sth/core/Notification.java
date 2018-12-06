package sth.core;


public class Notification implements java.io.Serializable {

	private static final long serialVersionUID = 201812022208L;

	private String _message;

	/**
	 * @param message
	 */
	public Notification(String message) {
		_message = message;
	}

	/**
	 * @return String
	 */
	public String getMessage() {
		return _message;
	}
}