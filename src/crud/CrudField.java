package crud;

import java.util.Scanner;
import java.util.function.Function;

import consoleinterface.table.IConsoleTable;
import menu.interfaces.IExecutableOption;
import result.Result;

public class CrudField <T> implements ICrudField, IExecutableOption{

	private String fieldName;
	private String requestString;
	private Function<T, Result> fieldSetter;
	private IConsoleTable tableToShow;
	
	public String getRequestString() {
		return requestString;
	}

	public CrudField(String fieldName, String requestString, Function<T, Result> fieldSetter) {
		super();
		this.fieldName = fieldName;
		this.requestString = requestString;
		this.fieldSetter = fieldSetter;
	}

	public CrudField(String fieldName, String requestString, Function<T, Result> fieldSetter,
			IConsoleTable tableToShow) {
		super();
		this.fieldName = fieldName;
		this.requestString = requestString;
		this.fieldSetter = fieldSetter;
		this.tableToShow = tableToShow;
	}

	@SuppressWarnings("unchecked")
	public Result execute(Scanner sc) {
		T field;
		
		if (tableToShow != null)
			tableToShow.build();
		
		System.out.println(requestString);
		
		try {
			field = (T)sc.nextLine();
		}
		catch (Exception e) {
			return Result.Failure("Entrada inválida");
		}
		
		return fieldSetter.apply(field);
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Function<T, Result> getFieldSetter() {
		return fieldSetter;
	}

	public void setFieldSetter(Function<T, Result> fieldSetter) {
		this.fieldSetter = fieldSetter;
	}
}
