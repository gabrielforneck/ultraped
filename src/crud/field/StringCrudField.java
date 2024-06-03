package crud.field;

import java.util.Scanner;
import java.util.function.Function;

import result.Result;

public class StringCrudField extends CrudField<String> {

	public StringCrudField(String description, String requestString, Function<String, Result> fieldSetter) {
		super(description, requestString, fieldSetter);
	}

	public Result requestField(Scanner sc) {
		String field;

		System.out.println(super.getRequestString());

		try {
			field = sc.nextLine();
		} catch (Exception e) {
			sc.next();
			return Result.failure("Entrada inválida");
		}

		return super.getFieldSetter().apply(field);
	}

}
