package crud.field;

import java.util.Scanner;
import java.util.function.Function;

import crud.field.exceptions.FiledSetterNotDefinedException;
import result.Result;
import result.ResultWithData;

public class DoubleCrudField extends CrudField<Double> {

	public DoubleCrudField(String description, String requestString, Function<Double, Result> fieldSetter) {
		super(description, requestString, fieldSetter);
	}
	
	public DoubleCrudField(String description, String requestString) {
		super(description, requestString);
	}

	@Override
	public Result requestField(Scanner sc) {
		if (super.getFieldSetter() == null)
			throw new FiledSetterNotDefinedException();
		
		ResultWithData<Double> requestResult = requestData(sc);
		if (requestResult.isFailure())
			return Result.failure(requestResult.getMessage());

		return super.getFieldSetter().apply(requestResult.getData());
	}

	@Override
	public ResultWithData<Double> requestData(Scanner sc) {
		Double field;

		System.out.println(super.getRequestString());

		try {
			field = Double.parseDouble(sc.nextLine());
		} catch (Exception e) {
			sc.next();
			return ResultWithData.failure("Entrada inválida");
		}
		
		return ResultWithData.success(field);
	}
	 
}
