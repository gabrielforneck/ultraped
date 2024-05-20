package consoleinterface.nextaction;

import java.util.Scanner;
import java.util.function.Consumer;

public class NextAction {

	private String description;
	private ENextAction nextAction;

	private NextAction(ENextAction nextAction) {
		super();
		this.nextAction = nextAction;
	}

	private NextAction(String description, ENextAction nextAction) {
		super();
		this.description = description;
		this.nextAction = nextAction;
	}

	public static NextAction Continue() {
		return new NextAction(ENextAction.CONTINUE);
	}

	public static NextAction Continue(String description) {
		return new NextAction(description, ENextAction.CONTINUE);
	}
	
	public static NextAction ExecuteAndExit(Scanner sc, Consumer<Scanner> action) {
		action.accept(sc);
		return new NextAction(ENextAction.EXIT);
	}

	public static NextAction Exit() {
		return new NextAction(ENextAction.EXIT);
	}

	public boolean isExit() {
		return nextAction == ENextAction.EXIT;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ENextAction getNextAction() {
		return nextAction;
	}

	public void setNextAction(ENextAction nextAction) {
		this.nextAction = nextAction;
	}
}
