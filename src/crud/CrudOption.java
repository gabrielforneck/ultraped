package crud;

public class CrudOption {

	private String description;
	private ECrudNextAction nextAction;
	
	public CrudOption() {
	}
	
	public CrudOption(String description, ECrudNextAction nextAction) {
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

	public ECrudNextAction getNextAction() {
		return nextAction;
	}

	public void setNextAction(ECrudNextAction nextAction) {
		this.nextAction = nextAction;
	}

}
