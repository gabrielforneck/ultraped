package crud.field;

import java.util.Scanner;

import crud.field.exceptions.OptionCannotBeNullException;
import result.ResultWithData;

public class BooleanCrudField extends CrudField<Boolean> {
	private String trueOption;
	private String falseOption;

	public BooleanCrudField(String description, String requestString) {
		super(description, requestString);
		trueOption = "S";
		falseOption = "N";
	}
	
	public BooleanCrudField(String description, String requestString, String trueOption, String falseOption) {
		super(description, requestString);
		
		if (trueOption == null || falseOption == null)
			throw new OptionCannotBeNullException();
		
		this.trueOption = trueOption;
		this.falseOption = falseOption;
	}

	@Override
	public ResultWithData<Boolean> requestData(Scanner sc) {
		String field;

		System.out.println(super.getRequestString() + " (" + trueOption + "/" + falseOption + ")");

		try {
			field = sc.nextLine();
		} catch (Exception e) {
			sc.reset();
			return ResultWithData.failure("Entrada inválida.");
		}
		
		if (!field.equalsIgnoreCase(trueOption) && !field.equalsIgnoreCase(falseOption))
			return ResultWithData.failure("Entrada inválida.");
		
		return ResultWithData.success(field.equals(trueOption));
	}

	public String getTrueOption() {
		return trueOption;
	}

	public void setTrueOption(String trueOption) {
		if (trueOption == null)
			throw new OptionCannotBeNullException();

		this.trueOption = trueOption;
	}

	public String getFalseOption() {
		return falseOption;
	}

	public void setFalseOption(String falseOption) {
		if (falseOption == null)
			throw new OptionCannotBeNullException();

		this.falseOption = falseOption;
	}

}
