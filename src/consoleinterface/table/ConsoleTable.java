package consoleinterface.table;

import java.util.ArrayList;
import java.util.List;

import consoleinterface.ConsoleInterface;
import consoleinterface.table.exceptions.NoColumnsDefinedException;

public class ConsoleTable<T> extends ConsoleInterface {

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

		buildHeader();
		buildBody();
	}

	private void buildBody() {

		for (T record : data) {
			showRecord(record);
			drawTableSeparator('-');
		}
	}

	private void showRecord(T record) {

		System.out.print(" | ");

		for (ConsoleTableColumn<T> column : columns) {
			System.out.print(column.getColumnDataWithColumnSize(record));
			System.out.print(" | ");
		}

		System.out.println();
	}

	private void buildHeader() {
		drawTableSeparator('=');
		showColumnsNames();
		drawTableSeparator('=');
	}

	private void showColumnsNames() {
		System.out.print(" | ");

		for (ConsoleTableColumn<T> column : columns) {
			System.out.print(column.getColumnNameWithColumnSize());
			System.out.print(" | ");
		}

		System.out.println();
	}

	private void drawTableSeparator(char ch) {

		System.out.print(" + ");

		for (ConsoleTableColumn<T> column : columns) {
			drawLine(column.getColumnSize(), ch);
			System.out.print(" + ");
		}

		System.out.println();

	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getColumnsQuantity() {
		return columns.size();
	}

	public ConsoleTableColumn<T> getColumn(int columnIndex) {
		return columns.get(columnIndex);
	}

	public void setColumn(int columnIndex, ConsoleTableColumn<T> newColumn) {
		this.columns.set(columnIndex, newColumn);
	}

	public void addColumn(int columnIndex, ConsoleTableColumn<T> newColumn) {
		this.columns.add(columnIndex, newColumn);
	}

}
