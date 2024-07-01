package consoleinterface.table;

import java.util.ArrayList;
import java.util.List;

import consoleinterface.ConsoleInterface;
import consoleinterface.table.exceptions.NoColumnsDefinedException;

public class ConsoleTable<T> extends ConsoleInterface implements IConsoleTable {

	private List<T> data;
	private List<ConsoleTableColumn<T>> columns;

	public ConsoleTable() {
		data = new ArrayList<>();
		columns = new ArrayList<>();
	}

	public ConsoleTable(List<ConsoleTableColumn<T>> columns) {
		data = new ArrayList<>();
		this.columns = columns;
	}

	public ConsoleTable(List<T> data, List<ConsoleTableColumn<T>> columns) {
		this.data = data;
		this.columns = columns;
	}

	public void build() {
		if (columns == null || columns.size() == 0)
			throw new NoColumnsDefinedException();

		System.out.println(this.toString());
	}

	private String buildBody() {
		String body = "";
		String tableSeparator = getTableSeparator('-');

		for (T record : data) {
			body += getRecordLine(record);
			body += tableSeparator;
		}
		
		return body;
	}

	private String getRecordLine(T record) {
		String line = " | ";

		for (ConsoleTableColumn<T> column : columns) {
			line += column.getColumnDataWithColumnSize(record);
			line += " | ";
		}

		return line + "\n";
	}

	private String buildHeader() {
		String header = "";
		String tableSeparator = getTableSeparator('=');
		
		header += tableSeparator;
		header += getColumnsNames();
		header += tableSeparator;
		
		return header;
	}

	private String getColumnsNames() {
		String line = " | ";

		for (ConsoleTableColumn<T> column : columns) {
			line += column.getColumnNameWithColumnSize();
			line += " | ";
		}

		return line + "\n";
	}

	private String getTableSeparator(char ch) {
		String line = " + ";
		
		String chSeparator = Character.toString(ch);

		for (ConsoleTableColumn<T> column : columns) {
			line += chSeparator.repeat(column.getColumnSize());
			line += " + ";
		}

		return line + "\n";
	}

	public List<T> getData() {
		return data;
	}

	public ConsoleTable<T> setData(List<T> data) {
		this.data = data;

		return this;
	}

	public int getColumnsQuantity() {
		return columns.size();
	}

	public ConsoleTableColumn<T> getColumn(int columnIndex) {
		return columns.get(columnIndex);
	}

	public ConsoleTable<T> setColumn(int columnIndex, ConsoleTableColumn<T> newColumn) {
		this.columns.set(columnIndex, newColumn);
		
		return this;
	}

	public ConsoleTable<T> addColumn(int columnIndex, ConsoleTableColumn<T> newColumn) {
		this.columns.add(columnIndex, newColumn);
		
		return this;
	}
	
	public ConsoleTable<T> addColumn(ConsoleTableColumn<T> newColumn) {
		this.columns.add(newColumn);
		
		return this;
	}
	
	@Override
	public String toString() {
		return buildHeader() + buildBody();
	}

}
