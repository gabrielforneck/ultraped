package crud.field.exceptions;

public class OptionCannotBeNullException extends RuntimeException {

	private static final long serialVersionUID = -3476516065535616241L;

	public OptionCannotBeNullException() {
	}

	public OptionCannotBeNullException(String message) {
		super(message);
	}

	public OptionCannotBeNullException(Throwable cause) {
		super(cause);
	}

	public OptionCannotBeNullException(String message, Throwable cause) {
		super(message, cause);
	}

	public OptionCannotBeNullException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
