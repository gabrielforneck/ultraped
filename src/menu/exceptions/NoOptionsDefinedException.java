package menu.exceptions;

@SuppressWarnings("serial") // TODO: Verificar como tratar isso
public class NoOptionsDefinedException extends RuntimeException {

	public static final String DESCRIPTION = "No options have been defined for this menu";

	public NoOptionsDefinedException() {
		super(DESCRIPTION);
	}

	public NoOptionsDefinedException(String message) {
		super(DESCRIPTION + ": " + message);
		// TODO Auto-generated constructor stub
	}

	public NoOptionsDefinedException(Throwable cause) {
		super(DESCRIPTION, cause);
		// TODO Auto-generated constructor stub
	}

	public NoOptionsDefinedException(String message, Throwable cause) {
		super(DESCRIPTION + ": " + message, cause);
		// TODO Auto-generated constructor stub
	}

	public NoOptionsDefinedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(DESCRIPTION + ": " + message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
