package crud.field;

import java.util.Scanner;
import java.util.function.Function;

import result.Result;
import result.ResultWithData;

public class IntegerCrudField extends CrudField<Integer> {

	public IntegerCrudField(String description, String requestString, Function<Integer, Result> fieldSetter) {
		super(description, requestString, fieldSetter);
	}
	
	public IntegerCrudField(String description, String requestString) {
		super(description, requestString);
	}

	@Override
	public ResultWithData<Integer> requestData(Scanner sc) {
		Integer field;

		System.out.println(super.getRequestString());

		try {
			field = Integer.parseInt(sc.nextLine());
		} catch (Exception e) {
			sc.reset();
			return ResultWithData.failure("Entrada inválida.");
		}
		
		return ResultWithData.success(field);
	}
	
}
