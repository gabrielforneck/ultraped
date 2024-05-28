package crud.field;

import java.util.Scanner;
import java.util.function.Function;

import consoleinterface.nextaction.NextAction;
import menu.options.interfaces.IMenuOption;
import result.Result;

public abstract class CrudField<T> implements IMenuOption {

	private String description;
	private String requestString;
	private Function<T, Result> fieldSetter;


	public CrudField(String description, String requestString, Function<T, Result> fieldSetter) {
		super();
		this.description = description;
		this.requestString = requestString;
		this.fieldSetter = fieldSetter;
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

	public abstract Result requestField(Scanner sc);

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
