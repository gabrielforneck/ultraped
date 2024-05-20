package consoleinterface.table.exceptions;

@SuppressWarnings("serial")
public class NoColumnsDefinedException extends RuntimeException {

	public NoColumnsDefinedException() {
	}

	public NoColumnsDefinedException(String message) {
		super(message);
	}

	public NoColumnsDefinedException(Throwable cause) {
		super(cause);
	}

	public NoColumnsDefinedException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoColumnsDefinedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
