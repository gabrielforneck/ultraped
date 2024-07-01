package order.exceptions;

public class NotDefinedSituationException extends RuntimeException {

	private static final long serialVersionUID = 6946460052180927469L;

	public NotDefinedSituationException() {
	}

	public NotDefinedSituationException(String message) {
		super(message);
	}

	public NotDefinedSituationException(Throwable cause) {
		super(cause);
	}

	public NotDefinedSituationException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotDefinedSituationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
