package result;

import consoleinterface.nextaction.NextAction;

public class ResultWithData<T>{
	private boolean isSuccess;
	private String message;
	private T data;
	
	private ResultWithData(boolean isSuccess, T data) {
		this.isSuccess = isSuccess;
		this.data = data;
	}
	
	private ResultWithData(boolean isSuccess, String message) {
		this.isSuccess = isSuccess;
		this.message = message;
	}
	
	public NextAction toContinueNextActionIfFailure() {
		if (this.isFailure())
			return NextAction.Continue(this.message);
		
		return NextAction.Exit();
	}
	
	public static <T> ResultWithData<T> Success(T data) {
		return new ResultWithData<T>(true, data);
	}
	
	public static <T> ResultWithData<T> Failure(String message) {
		return new ResultWithData<T>(false, message);
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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
