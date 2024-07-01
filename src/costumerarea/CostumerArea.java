package costumerarea;

import costumer.Costumer;
import costumer.crud.CostumerCrud;
import menu.Menu;
import menu.options.MethodMenuOption;

public class CostumerArea extends Menu {
	
	private Costumer costumer;

	public CostumerArea(Costumer costumer) {
		super("Área do cliente " + costumer.getName() + " (" + costumer.getId() + ")");
		this.costumer = costumer;
		addDefaultOptions();
	}
	
	private void addDefaultOptions() {

		super.options.add(new MethodMenuOption("Atualizar meu cadastro", (sc) -> CostumerCrud.update(sc, costumer)));
		super.options.add(new MethodMenuOption("Excluir meu cadastro", (sc) -> CostumerCrud.delete(sc, costumer)));
	}
}
