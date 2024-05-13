package menu;

import java.util.ArrayList;
import java.util.Scanner;

import consoleinterface.ConsoleInterface;
import menu.defaultoptions.BackOption;
import menu.defaultoptions.CancelOption;
import menu.exceptions.NoOptionsDefinedException;
import menu.interfaces.IMenuOption;
import menu.nextaction.NextAction;

public class Menu extends ConsoleInterface {
	protected ArrayList<IMenuOption> options;
	
	public Menu() {
		this.options = new ArrayList<IMenuOption>();
	}
	
	public Menu(String title, IMenuOption option) {
		super(title);
		this.options = new ArrayList<IMenuOption>();
		this.addOptions(option);
	}
	
	public Menu(ArrayList<IMenuOption> options) {
		this.options = new ArrayList<IMenuOption>();
		this.addOptions(options);
	}

	public Menu(String title, ArrayList<IMenuOption> options) {
		super(title);
		this.options = new ArrayList<IMenuOption>();
		this.addOptions(options);
	}
	
	public void showOptions() {
		if (this.options == null)
			return;
		
		for (int i = 0; i<options.size(); i++)
			System.out.println(i + " - " + options.get(i).getDescription());
	}
	
	public void constructMenu() {
		this.showTitle();
		System.out.println();
		this.showOptions();
	}
	
	public NextAction execute(Scanner sc) throws NoOptionsDefinedException {
		if (this.options == null || this.options.size() == 0)
			throw new NoOptionsDefinedException();
		
		NextAction nextAction = null;

		do {
			this.constructMenu();
			System.out.println();
			
			if (nextAction != null && nextAction.getDescription() != null)
				System.out.println(nextAction.getDescription());

			nextAction = this.waitForOptionAndExecute(sc);
			System.out.println();
		} while (nextAction.nextActionIsExit());
		
		return NextAction.Continue();
	}
	
	protected NextAction waitForOptionAndExecute(Scanner sc) {
		Integer optionIndex = 0;
		
		try {
			System.out.print("Selecione a opção: ");
			optionIndex = sc.nextInt();
			sc.nextLine();
		}
		catch (Exception ex) {
			sc.next();
			return NextAction.Continue("Entrada inválida.");
		}
		
		IMenuOption selectedOption = this.options.get(optionIndex);
		if (selectedOption == null)
			return NextAction.Continue("Opção não encontrada.");
		
		System.out.println();
		return selectedOption.execute(sc);
	}
	
	public Menu ShowCancelOption() {
		addOptions(new CancelOption());
		return this;
	}

	public Menu ShowBackOption() {
		addOptions(new BackOption());
		return this;
	}
	
	public int getOptionsQuantity(Integer optionIndex) {
		return this.options.size();
	}

	public IMenuOption getOption(Integer optionIndex) {
		return this.options.get(optionIndex);
	}

	public Menu addOptions(IMenuOption option) {
		this.options.add(option);
		return this;
	}
	
	public Menu addOptions(ArrayList<IMenuOption> options) {
		this.options.addAll(options);
		return this;
	}

	public Menu remOption(int optionIndex) {
		this.options.remove(optionIndex);
		return this;
	}
	
	public Menu clearOptions() {
		this.options.clear();

		return this;
	}
}
