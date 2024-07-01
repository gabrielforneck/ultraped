package products.selection;

import java.util.List;
import java.util.Scanner;

import application.Program;
import consoleinterface.nextaction.NextAction;
import crud.field.IntegerCrudField;
import crud.field.StringCrudField;
import menu.Menu;
import menu.options.MethodMenuOption;
import products.Product;
import products.crud.ProductCrud;
import products.repository.ProductRepository;
import result.ResultWithData;

public class SelectProductMenu extends Menu {
	
	private ProductRepository localRepository;
	private List<Product> dataShown;
	private Product selectedProduct;

	public SelectProductMenu() {
		super("Selecionar produto");
		addDefaultOptions();
		localRepository = new ProductRepository(Program.applicationData.supplierRepository.getAllProducts());
		localRepository.setData(localRepository.getAllWithStock());
		super.setDetailsToShow(ProductCrud.getDefaultConsoleTable().setData(dataShown = localRepository.getAll()));
	}
	
	private NextAction filterProducts(Scanner sc) {
		ResultWithData<String> requestResult = new StringCrudField("", "Insira a consulta: ").requestData(sc);
		if (requestResult.isFailure())
			return NextAction.Continue(requestResult.getMessage());
		
		dataShown = localRepository.getByIDOrNameOrDescription(requestResult.getData());
		super.setDetailsToShow(ProductCrud.getDefaultConsoleTable().setData(dataShown));
		
		return NextAction.Continue();
	}
	
	private NextAction clearFilter() {
		super.setDetailsToShow(ProductCrud.getDefaultConsoleTable().setData(dataShown = localRepository.getAll()));
		return NextAction.Continue();
	}
	
	private NextAction selectProduct(Scanner sc) {
		if (dataShown.size() == 1)
		{
			selectedProduct = dataShown.get(0);
			return NextAction.Exit();
		}
		
		ResultWithData<Integer> requestResult = new IntegerCrudField("", "Insira o ID do registro:").requestData(sc);
		if (requestResult.isFailure())
			return NextAction.Continue(requestResult.getMessage());
		
		Product p = localRepository.getByID(requestResult.getData());
		if (p == null)
			NextAction.Continue("Produto não encontrado.");
		
		return NextAction.Exit();
	}
	
	private void addDefaultOptions() {
		super.options.add(new MethodMenuOption("Filtrar", this::filterProducts));
		super.options.add(new MethodMenuOption("Limpar filtros", (sc) -> clearFilter()));
		super.options.add(new MethodMenuOption("Selecionar produto", this::selectProduct));
	}
	
	public Product getSelectedProduct() {
		return selectedProduct;
	}
}
