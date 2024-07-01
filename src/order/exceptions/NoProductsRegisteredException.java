package order.exceptions;

public class NoProductsRegisteredException extends RuntimeException {

	private static final long serialVersionUID = 1875732930494952023L;

	public NoProductsRegisteredException() {
	}

	public NoProductsRegisteredException(String message) {
		super(message);
	}

	public NoProductsRegisteredException(Throwable cause) {
		super(cause);
	}

	public NoProductsRegisteredException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoProductsRegisteredException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
