package supplier.repository.exceptions;

public class IllegalOrderProductQuantityException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8830100750771484131L;

	public IllegalOrderProductQuantityException() {
		// TODO Auto-generated constructor stub
	}

	public IllegalOrderProductQuantityException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public IllegalOrderProductQuantityException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public IllegalOrderProductQuantityException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public IllegalOrderProductQuantityException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
