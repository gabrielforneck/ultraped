package consoleinterface;

public abstract class ConsoleInterface {

	protected String title;
	
	protected ConsoleInterface() {
	}
	
	protected ConsoleInterface(String title) {
		this.title = title;
	}
	
	protected void drawLineln(int size) {
		drawLineln(size, '=');
	}
	
	protected void drawLineln(int size, char ch) {
		drawLine(size, ch);
		System.out.println();
	}
	
	protected void drawLine(int size, char ch) {
		for (int i=0; i<size; i++)
			System.out.print(ch);
	}
	
	protected void showTitle() {
		if (this.title == null || this.title.length() == 0)
			return;
		
		int size = this.title.length();
		drawLineln(size);
		System.out.println(this.title);
		drawLineln(size);
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
