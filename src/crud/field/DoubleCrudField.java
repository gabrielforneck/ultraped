package crud.field;

import java.util.Scanner;
import java.util.function.Function;

import result.Result;

public class DoubleCrudField extends CrudField<Double> {

	public DoubleCrudField(String description, String requestString, Function<Double, Result> fieldSetter) {
		super(description, requestString, fieldSetter);
	}

	@Override
	public Result requestField(Scanner sc) {
		Double field;

		System.out.println(super.getRequestString());

		try {
			field = Double.parseDouble(sc.nextLine());
		} catch (Exception e) {
			sc.next();
			return Result.Failure("Entrada inválida");
		}

		return super.getFieldSetter().apply(field);
	}
	 
}
