package crud.field.exceptions;

public class FiledSetterNotDefinedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FiledSetterNotDefinedException() {
	}

	public FiledSetterNotDefinedException(String message) {
		super(message);
	}

	public FiledSetterNotDefinedException(Throwable cause) {
		super(cause);
	}

	public FiledSetterNotDefinedException(String message, Throwable cause) {
		super(message, cause);
	}

	public FiledSetterNotDefinedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
