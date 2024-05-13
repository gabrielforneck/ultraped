package consoleinterface;

import java.util.ArrayList;

public class TableColumn<T> {

	private String name;
	private int size;

	public TableColumn(String name, int size, ArrayList<T> data) {
		this.name = name;
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
