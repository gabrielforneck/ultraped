package crud.field;

import java.util.Scanner;
import java.util.function.Function;

import result.Result;
import result.ResultWithData;

public class StringCrudField extends CrudField<String> {

	public StringCrudField(String description, String requestString, Function<String, Result> fieldSetter) {
		super(description, requestString, fieldSetter);
	}
	
	public StringCrudField(String description, String requestString) {
		super(description, requestString);
	}

	@Override
	public ResultWithData<String> requestData(Scanner sc) {
		String field;

		System.out.println(super.getRequestString());

		try {
			field = sc.nextLine();
		} catch (Exception e) {
			sc.reset();
			return ResultWithData.failure("Entrada inválida.");
		}
		
		return ResultWithData.success(field);
	}

}
