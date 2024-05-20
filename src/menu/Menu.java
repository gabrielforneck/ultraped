package menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import consoleinterface.ConsoleInterface;
import consoleinterface.nextaction.NextAction;
import menu.exceptions.NoOptionsDefinedException;
import menu.options.DefaultMenuOptions;
import menu.options.interfaces.IExecutableOption;
import menu.options.interfaces.IMenuOption;

public class Menu extends ConsoleInterface implements IExecutableOption {
	protected List<IMenuOption> options;
	protected Object detailsToShow;

	public Menu() {
		this.options = new ArrayList<>();
	}

	public Menu(String title, IMenuOption option) {
		super(title);
		this.options = new ArrayList<>();
		this.addOptions(option);
	}

	public Menu(List<IMenuOption> options) {
		this.options = new ArrayList<>();
		this.addOptions(options);
	}

	public Menu(String title, List<IMenuOption> options) {
		super(title);
		this.options = new ArrayList<>();
		this.addOptions(options);
	}

	public Menu(String title, List<IMenuOption> options, String detailsToShow) {
		super(title);
		this.options = options;
		this.detailsToShow = detailsToShow;
	}

	public Menu(String title) {
		super(title);
		this.options = new ArrayList<>();
	}

	protected void showOptions() {
		if (this.options == null)
			return;

		for (int i = 0; i < options.size(); i++)
			System.out.println(i + " - " + options.get(i).getDescription());
	}

	protected void constructMenu() {
		this.showTitle();
		System.out.println();
		if (detailsToShow != null) {
			System.out.println(detailsToShow.toString());
			System.out.println();
		}
		this.showOptions();
	}

	public NextAction execute(Scanner sc) {
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
		} while (!nextAction.isExit());

		return NextAction.Continue();
	}

	protected NextAction waitForOptionAndExecute(Scanner sc) {
		int optionIndex = 0;

		try {
			System.out.print("Selecione a opção: ");
			optionIndex = sc.nextInt();
			sc.nextLine();
		} catch (Exception ex) {
			sc.next();
			return NextAction.Continue("Entrada inválida.");
		}

		if (optionIndex < 0 || optionIndex >= options.size())
			return NextAction.Continue("Opção não encontrada.");

		IMenuOption selectedOption = this.options.get(optionIndex);

		System.out.println();
		return selectedOption.execute(sc);
	}

	public Menu showCancelOption() {
		addOptions(DefaultMenuOptions.cancelOption());
		return this;
	}

	public Menu showBackOption() {
		addOptions(DefaultMenuOptions.backOption());
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

	public Menu addOptions(List<IMenuOption> options) {
		this.options.addAll(options);
		return this;
	}

	public Object getDetailsToShow() {
		return detailsToShow;
	}

	public Menu setDetailsToShow(Object detailsToShow) {
		this.detailsToShow = detailsToShow;
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
