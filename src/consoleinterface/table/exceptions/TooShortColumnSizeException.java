package consoleinterface.table.exceptions;

@SuppressWarnings("serial")
public class TooShortColumnSizeException extends RuntimeException {

	public TooShortColumnSizeException() {
	}

	public TooShortColumnSizeException(String message) {
		super(message);
	}

	public TooShortColumnSizeException(Throwable cause) {
		super(cause);
	}

	public TooShortColumnSizeException(String message, Throwable cause) {
		super(message, cause);
	}

	public TooShortColumnSizeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
