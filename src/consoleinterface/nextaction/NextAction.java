package consoleinterface.nextaction;

public class NextAction {

	private String description;
	private ENextAction nextAction;
	private boolean lastActionWasCanceled;

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
	
	public boolean lastActionWasCanceled() {
		return lastActionWasCanceled;
	}

	public void setLastActionWasCanceled(boolean lastActionWasCanceled) {
		this.lastActionWasCanceled = lastActionWasCanceled;
	}
}
