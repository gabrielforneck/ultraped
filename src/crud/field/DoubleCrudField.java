package crud.field;

import java.util.Scanner;
import java.util.function.Function;

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
	public ResultWithData<Double> requestData(Scanner sc) {
		Double field;

		System.out.println(super.getRequestString());

		try {
			field = Double.parseDouble(sc.nextLine());
		} catch (Exception e) {
			sc.reset();
			return ResultWithData.failure("Entrada inválida.");
		}
		
		return ResultWithData.success(field);
	}
	 
}
