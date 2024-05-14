package crud;

import java.util.ArrayList;
import java.util.List;

import consoleinterface.ConsoleInterface;

public abstract class Crud extends ConsoleInterface {

	protected String title;
	protected List<CrudOption> options;
	
	protected Crud() {
	}
	
	protected Crud(String title) {
		super(title);
	}
	
	protected Crud(List<CrudOption> options) {
		super();
		this.options = options;
	}
	
	protected Crud(String title, List<CrudOption> options) {
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

	public static List<CrudOption> getDefaultCrudOptions() {
		List<CrudOption> options = new ArrayList<>();
		
		options.add(new CrudOption("Criar", ECrudNextAction.CREATE));
		options.add(new CrudOption("Alterar", ECrudNextAction.UPDATE));
		options.add(new CrudOption("Excluir", ECrudNextAction.DELETE));
		
		return options;
	}
	
	protected void addDefaultCrudOptions() {
		options.addAll(getDefaultCrudOptions());
	}

	public List<CrudOption> getOptions() {
		return options;
	}

	public void setOptions(List<CrudOption> options) {
		this.options = options;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
