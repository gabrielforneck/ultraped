package crud;

import java.util.Scanner;
import java.util.function.Function;

import consoleinterface.nextaction.NextAction;
import menu.options.interfaces.IMenuOption;
import result.Result;

public class CrudField<T> implements IMenuOption {

	private String description;
	private String requestString;
	private Function<T, Result> fieldSetter;

	public String getRequestString() {
		return requestString;
	}

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

	@SuppressWarnings("unchecked")
	public Result requestField(Scanner sc) {
		T field;

		System.out.println(requestString);

		try {
			field = (T) sc.nextLine();
		} catch (Exception e) {
			return Result.Failure("Entrada inválida");
		}

		return fieldSetter.apply(field);
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
