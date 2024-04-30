package menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import menu.interfaces.IExecutableOption;
import menu.interfaces.IMenuOption;

public class Menu implements IExecutableOption {
	protected String title;
	protected Map<Integer, IMenuOption> options;
	
	public Menu() {
		this.options = new HashMap<Integer, IMenuOption>();
	}
	
	public Menu(String title, IMenuOption option) {
		this.title = title;
		this.options = new HashMap<Integer, IMenuOption>();
		this.addOption(option);
	}
	
	public Menu(ArrayList<IMenuOption> options) {
		this.options = new HashMap<Integer, IMenuOption>();
		this.addOption(options);
	}

	public Menu(String title, ArrayList<IMenuOption> options) {
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
	
	public ENextAction execute(Scanner sc) {
		if (this.options == null || this.options.size() == 0)
			return ENextAction.CONTINUE;
		
		ENextAction nextAction;

		do {
			this.constructMenu();
			System.out.println();
			nextAction = this.waitForOptionAndExecute(sc);
			System.out.println();
		} while (nextAction != ENextAction.EXIT);
		
		return ENextAction.CONTINUE;
	}
	
	protected ENextAction waitForOptionAndExecute(Scanner sc) {
		Integer optionIndex = 0;
		
		try {
			System.out.print("Selecione a opção: ");
			optionIndex = sc.nextInt();
			sc.nextLine();
		}
		catch (Exception ex) {
			sc.next();
			return ENextAction.CONTINUE;
		}
		
		IMenuOption selectedOption = this.options.get(optionIndex);
		if (selectedOption == null)
			return ENextAction.CONTINUE;
		
		return selectedOption.execute(sc);
	}
	
	public void drawLine(int size) {
		for (int i=0; i<size; i++)
			System.out.print("=");
		
		System.out.println();
	}
	
	public int getOptionsQuantity(Integer optionIndex) {
		return this.options.size();
	}

	public IMenuOption getOption(Integer optionIndex) {
		return this.options.get(optionIndex);
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

	public void setTitle(String title) {
		this.title = title;
	}
}
