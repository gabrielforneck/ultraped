package result;

public class Result {

	private boolean isSuccess;
	private String message;

	private Result(boolean isSuccess) {
		super();
		this.isSuccess = isSuccess;
	}

	private Result(boolean isSuccess, String message) {
		super();
		this.isSuccess = isSuccess;
		this.message = message;
	}

	public static Result success() {
		return new Result(true);
	}

	public static Result failure(String message) {
		return new Result(false, message);
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public boolean isFailure() {
		return !isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
