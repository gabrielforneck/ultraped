package crud.field;

import java.util.Scanner;
import java.util.function.Function;

import result.Result;

public class IntegerCrudField extends CrudField<Integer> {

	public IntegerCrudField(String description, String requestString, Function<Integer, Result> fieldSetter) {
		super(description, requestString, fieldSetter);
	}

	@Override
	public Result requestField(Scanner sc) {
		Integer field;

		System.out.println(super.getRequestString());

		try {
			field = Integer.parseInt(sc.nextLine());
		} catch (Exception e) {
			sc.next();
			return Result.Failure("Entrada inválida");
		}

		return super.getFieldSetter().apply(field);
	}
	
}
