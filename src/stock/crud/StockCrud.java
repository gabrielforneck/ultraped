package stock.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

import consoleinterface.nextaction.NextAction;
import crud.field.DoubleCrudField;
import crud.field.IntegerCrudField;
import menu.Menu;
import menu.options.MethodMenuOption;
import menu.options.interfaces.IMenuOption;
import result.Result;
import stock.Stock;
import stock.validation.StockValidation;

public class StockCrud {
	
	public NextAction update(Stock stock, Scanner sc) {
		Stock dummyStock = stock == null ? new Stock(0, 0.0) : stock.clone();
		List<IMenuOption> options = getDefaultFieldOptions(dummyStock);
		
		options.add(new MethodMenuOption("Aceitar", (scanner) -> validateAndSave(dummyStock, (s) -> StockValidation.validateAll(s))));
		options.add(new MethodMenuOption("Cancelar", (scanner) -> NextAction.ExecuteAndExit(scanner, (s) -> dummyStock.clear())));

		new Menu("Estoque do produto").addOptions(options).setDetailsToShow(dummyStock).execute(sc);
		
		if (dummyStock.getQuantity() == -1)
			return NextAction.Continue();
		
		dummyStock.copyTo(stock);
		return NextAction.Continue();
	}

	private List<IMenuOption> getDefaultFieldOptions(Stock stock) {
		List<IMenuOption> options = new ArrayList<>();

		options.add(new IntegerCrudField("Adicionar quantidade", "Insira a quantidade para adicionar:", (q) -> stock.addQuantity(q)));
		options.add(new IntegerCrudField("Remover quantidade", "Insira a quantidade para remover:", (q) -> stock.remQuantity(q)));
		options.add(new DoubleCrudField("Preço", "Insira o preço:", (p) -> stock.setPrice(p)));

		return options;
	}
	
	protected NextAction validateAndSave(Stock stock, Function<Stock, Result> validator) {
		Result validationResult = validator.apply(stock);

		if (validationResult.isFailure())
			return NextAction.Continue(validationResult.getMessage());

		return NextAction.Exit();
	}
}
