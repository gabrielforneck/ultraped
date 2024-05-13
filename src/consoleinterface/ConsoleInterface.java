package consoleinterface;

import menu.interfaces.IExecutableOption;

public abstract class ConsoleInterface implements IExecutableOption {

	protected String title;
	
	protected ConsoleInterface() {
	}
	
	protected ConsoleInterface(String title) {
		this.title = title;
	}
	
	protected void showTable() {
		//TODO: Avaliar como implementar
	}
	
	protected void drawLine(int size) {
		drawLine(size, '=');
	}
	
	protected void drawLine(int size, char ch) {
		for (int i=0; i<size; i++)
			System.out.print(ch);
		
		System.out.println();
	}
	
	protected void showTitle() {
		if (this.title == null || this.title.length() == 0)
			return;
		
		int size = this.title.length();
		drawLine(size);
		System.out.println(this.title);
		drawLine(size);
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
