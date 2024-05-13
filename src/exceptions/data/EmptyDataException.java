package exceptions.data;

@SuppressWarnings("serial")
public class EmptyDataException extends RuntimeException {

	public EmptyDataException() {
	}

	public EmptyDataException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public EmptyDataException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public EmptyDataException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public EmptyDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
