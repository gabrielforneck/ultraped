package stock.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import consoleinterface.nextaction.NextAction;
import crud.field.DoubleCrudField;
import crud.field.IntegerCrudField;
import menu.Menu;
import menu.options.MethodMenuOption;
import menu.options.interfaces.IMenuOption;
import stock.Stock;
import stock.validation.StockValidation;

public class StockCrud {
	
	public NextAction update(Stock stock, Scanner sc) {
		List<IMenuOption> options = getDefaultFieldOptions(stock);
		options.add(new MethodMenuOption("Aceitar", (scanner) -> StockValidation.validateAll(stock).toExitNextActionIfSucces()));

		return new Menu("Estoque do produto").addOptions(options).setDetailsToShow(stock).execute(sc);
	}

	private List<IMenuOption> getDefaultFieldOptions(Stock stock) {
		List<IMenuOption> options = new ArrayList<>();

		options.add(new IntegerCrudField("Adicionar quantidade", "Insira a quantidade para adicionar:", (q) -> stock.addQuantity(q)));
		options.add(new IntegerCrudField("Remover quantidade", "Insira a quantidade para remover:", (q) -> stock.remQuantity(q)));
		options.add(new DoubleCrudField("Preço", "Insira o preço:", (p) -> stock.setPrice(p)));

		return options;
	}
}
