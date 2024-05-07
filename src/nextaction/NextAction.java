package nextaction;

public class NextAction {

	private String description;
	private ENextAction nextAction;
	
	public NextAction(ENextAction nextAction) {
		super();
		this.nextAction = nextAction;
	}

	public NextAction(String description, ENextAction nextAction) {
		super();
		this.description = description;
		this.nextAction = nextAction;
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
