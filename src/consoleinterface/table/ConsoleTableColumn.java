package consoleinterface.table;

import java.util.function.Function;

import consoleinterface.table.exceptions.TooShortColumnSizeException;

public class ConsoleTableColumn<T> {

	private int columnSize;
	private String columnName;
	private Function<T, Object> dataGetter;

	public ConsoleTableColumn(int columnSize, String columnName, Function<T, Object> dataGetter) {
		if (columnSize <= 2)
			throw new TooShortColumnSizeException();

		this.columnSize = columnSize;
		this.columnName = columnName;
		this.dataGetter = dataGetter;
	}

	public int getColumnSize() {
		return columnSize;
	}

	private String returnStringWithColumnSize(String str) {
		if (str.length() > columnSize)
			return str.substring(0, columnSize - 3) + "...";

		if (str.length() < columnSize)
			str += " ".repeat(columnSize - str.length());

		return str;
	}

	public void setColumnSize(int columnSize) {
		this.columnSize = columnSize;
	}

	public String getColumnName() {
		return columnName;
	}

	public String getColumnNameWithColumnSize() {
		return returnStringWithColumnSize(columnName);
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Function<T, Object> getDataGetter() {
		return dataGetter;
	}

	public void setDataGetter(Function<T, Object> dataGetter) {
		this.dataGetter = dataGetter;
	}

	public String getColumnDataWithColumnSize(T record) {
		return returnStringWithColumnSize(dataGetter.apply(record).toString());
	}

}
