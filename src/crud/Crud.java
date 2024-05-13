package crud;

import java.util.ArrayList;

import consoleinterface.ConsoleInterface;

public abstract class Crud extends ConsoleInterface {

	protected String title;
	protected ArrayList<CrudOption> options;
	
	protected Crud() {
	}
	
	protected Crud(String title) {
		super(title);
	}
	
	protected Crud(ArrayList<CrudOption> options) {
		super();
		this.options = options;
	}
	
	protected Crud(String title, ArrayList<CrudOption> options) {
		super(title);
		this.options = options;
	}
	
	protected abstract void showDataAsTable();
	
	protected void constructCrud() {
		showTitle();
		System.out.println();
		showDataAsTable();
		System.out.println();
		showOptions();
	}
	
	protected void showOptions() {
		if (this.options == null)
			return;
		
		for (int i = 0; i<options.size(); i++)
			System.out.println(i + " - " + options.get(i).getDescription());
	}

	public static ArrayList<CrudOption> getDefaultCrudOptions() {
		ArrayList<CrudOption> options = new ArrayList<CrudOption>();
		
		options.add(new CrudOption("Criar", ECrudNextAction.CREATE));
		options.add(new CrudOption("Alterar", ECrudNextAction.UPDATE));
		options.add(new CrudOption("Excluir", ECrudNextAction.DELETE));
		
		return options;
	}
	
	protected void addDefaultCrudOptions() {
		options.addAll(getDefaultCrudOptions());
	}

	public ArrayList<CrudOption> getOptions() {
		return options;
	}

	public void setOptions(ArrayList<CrudOption> options) {
		this.options = options;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
