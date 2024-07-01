package menu.exceptions;

public class NoOptionsDefinedException extends RuntimeException {

	private static final long serialVersionUID = 6406390026641121256L;

	public static final String DESCRIPTION = "No options have been defined for this menu";

	public NoOptionsDefinedException() {
		super(DESCRIPTION);
	}

	public NoOptionsDefinedException(String message) {
		super(DESCRIPTION + ": " + message);
	}

	public NoOptionsDefinedException(Throwable cause) {
		super(DESCRIPTION, cause);
	}

	public NoOptionsDefinedException(String message, Throwable cause) {
		super(DESCRIPTION + ": " + message, cause);
	}

	public NoOptionsDefinedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(DESCRIPTION + ": " + message, cause, enableSuppression, writableStackTrace);
	}

}
