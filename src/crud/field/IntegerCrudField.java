package crud.field;

import java.util.Scanner;
import java.util.function.Function;

import crud.field.exceptions.FiledSetterNotDefinedException;
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
	public Result requestField(Scanner sc) {
		if (super.getFieldSetter() == null)
			throw new FiledSetterNotDefinedException();

		ResultWithData<Integer> requestResult = requestData(sc);
		if (requestResult.isFailure())
			return Result.failure(requestResult.getMessage());

		return super.getFieldSetter().apply(requestResult.getData());
	}
	
	public ResultWithData<Integer> requestData(Scanner sc) {
		Integer field;

		System.out.println(super.getRequestString());

		try {
			field = Integer.parseInt(sc.nextLine());
		} catch (Exception e) {
			sc.next();
			return ResultWithData.failure("Entrada inválida");
		}
		
		return ResultWithData.success(field);
	}
	
}
