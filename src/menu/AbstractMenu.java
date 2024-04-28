package menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import menu.interfaces.IMenuOption;

public abstract class AbstractMenu {
	protected String title;
	protected Map<Integer, IMenuOption> options;
	
	public AbstractMenu() {
	}
	
	public AbstractMenu(ArrayList<IMenuOption> options) {
		this.options = new HashMap<Integer, IMenuOption>();
		this.addOption(options);
	}

	public AbstractMenu(String title, ArrayList<IMenuOption> options) {
		this.title = title;
		this.options = new HashMap<Integer, IMenuOption>();
		this.addOption(options);
	}
	
	public void showTitle() {
		if (this.title == null || this.title.length() == 0)
			return;
		
		int size = this.title.length();
		this.drawLine(size);
		System.out.println(this.title);
		this.drawLine(size);
	}
	
	public void showOptions() {
		if (this.options == null)
			return;
		
		for (Map.Entry<Integer, IMenuOption> option : this.options.entrySet())
			System.out.println(option.getKey() + " - " + option.getValue().getDescription());
	}
	
	public void constructMenu() {
		this.showTitle();
		System.out.println();
		this.showOptions();
	}
	
	public void execute(Scanner sc) {
		if (this.options == null || this.options.size() == 0)
			return;
		
		ENextAction nextAction;

		do {
			this.constructMenu();
			System.out.println();
			nextAction = this.waitForOptionAndExecute(sc);
			System.out.println();
		} while (nextAction != ENextAction.EXIT);
	}
	
	protected ENextAction waitForOptionAndExecute(Scanner sc) {
		Integer option = 0;
		
		try {
			System.out.print("Selecione a opção: ");
			option = sc.nextInt();
			sc.nextLine();
		}
		catch (Exception ex) {
			sc.next();
			return ENextAction.CONTINUE;
		}
		
		IMenuOption selectedOption = this.options.get(option);
		if (selectedOption == null)
			return ENextAction.CONTINUE;
		
		return selectedOption.execute(sc);
	}
	
	public void drawLine(int size) {
		for (int i=0; i<size; i++)
			System.out.print("=");
		
		System.out.println();
	}
	
	public Map<Integer, IMenuOption> getOptions() {
		return options;
	}

	public void addOption(IMenuOption option) {
		this.options.put(this.options.size()+1, option);
	}
	
	public void addOption(ArrayList<IMenuOption> options) {
		for (IMenuOption option : options)
			this.addOption(option);
	}

	public void remOption(Integer optionIndex) {
		this.options.remove(optionIndex);
	}
	
	public void clearOptions() {
		this.options.clear();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String menuTitle) {
		this.title = menuTitle;
	}
}
