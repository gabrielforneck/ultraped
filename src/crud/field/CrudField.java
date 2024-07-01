package crud.field;

import java.util.Scanner;
import java.util.function.Function;

import consoleinterface.nextaction.NextAction;
import crud.field.exceptions.FiledSetterNotDefinedException;
import menu.options.interfaces.IMenuOption;
import result.Result;
import result.ResultWithData;

public abstract class CrudField<T> implements IMenuOption {

	private String description;
	private String requestString;
	private Function<T, Result> fieldSetter;


	protected CrudField(String description, String requestString, Function<T, Result> fieldSetter) {
		super();
		this.description = description;
		this.requestString = requestString;
		this.fieldSetter = fieldSetter;
	}
	
	protected CrudField(String description, String requestString) {
		super();
		this.description = description;
		this.requestString = requestString;
	}

	@Override
	public NextAction execute(Scanner sc) {
		Result result = null;

		do {
			if (result != null && result.isFailure())
				System.out.println(result.getMessage());

			result = requestField(sc);
		} while (result.isFailure());

		return NextAction.Continue();
	}

	public Result requestField(Scanner sc) {
		if (fieldSetter == null)
			throw new FiledSetterNotDefinedException();

		ResultWithData<T> requestResult = requestData(sc);
		if (requestResult.isFailure())
			return Result.failure(requestResult.getMessage());

		return getFieldSetter().apply(requestResult.getData());
	}

	public abstract ResultWithData<T> requestData(Scanner sc);

	public String getRequestString() {
		return requestString;
	}
	
	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	public Function<T, Result> getFieldSetter() {
		return fieldSetter;
	}

	public void setFieldSetter(Function<T, Result> fieldSetter) {
		this.fieldSetter = fieldSetter;
	}
}
