package products.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import application.Program;
import consoleinterface.nextaction.NextAction;
import consoleinterface.table.ConsoleTable;
import consoleinterface.table.ConsoleTableColumn;
import crud.FullCrud;
import crud.field.IntegerCrudField;
import crud.field.StringCrudField;
import menu.Menu;
import menu.options.MethodMenuOption;
import menu.options.interfaces.IMenuOption;
import products.Product;
import products.repository.ProductRepository;
import products.validation.ProductValidation;
import result.ResultWithData;
import stock.crud.StockCrud;
import supplier.Supplier;

public class ProductCrud extends FullCrud<Product> {

	private ProductRepository localRepository;

	public ProductCrud(Supplier supplier) {
		super("Produtos do fornecedor " + (supplier.getId() == 0 ? "?" : supplier.getId()));
		localRepository = new ProductRepository(supplier.getProducts());
		addDefaultCrudOptions();
		options.add(new MethodMenuOption("Buscar", this::filterByName));
	}

	public static ConsoleTable<Product> getDefaultConsoleTable() {
		List<ConsoleTableColumn<Product>> columns = new ArrayList<>();

		columns.add(new ConsoleTableColumn<>(5, "ID", (p) -> p.getId()));
		columns.add(new ConsoleTableColumn<>(30, "Nome", (p) -> p.getName()));
		columns.add(new ConsoleTableColumn<>(70, "Descrição", (p) -> p.getDescription()));
		columns.add(new ConsoleTableColumn<>(20, "Qtd. estoque", (p) -> p.getStock().getQuantity()));

		return new ConsoleTable<>(columns);
	}

	@Override
	protected void showDataAsTable() {
		getDefaultConsoleTable().setData(localRepository.getAll()).build();
	}

	@Override
	protected NextAction create(Scanner sc) {
		Product dummyProduct = new Product();

		updateRecord("Novo produto", dummyProduct, sc);
		localRepository.save(dummyProduct);

		return NextAction.Continue();
	}

	@Override
	protected NextAction update(Scanner sc) {
		ResultWithData<Product> requestResult = requestProduct(sc);
		if (requestResult.isFailure())
			return NextAction.Continue(requestResult.getMessage());

		// Não preciso atualizar no repositório pois já estou alterando na referência.
		return updateRecord("Atualizar o registro " + requestResult.getData().getId(), requestResult.getData(), sc);
	}

	@Override
	protected NextAction updateRecord(String title, Product record, Scanner sc) {
		List<IMenuOption> options = getDefaultFieldOptions(record);
		options.add(new MethodMenuOption("Concluir",
				(scanner) -> ProductValidation.validateAll(record).toExitNextActionIfSucces()));

		return new Menu(title, options).setDetailsToShow(record).execute(sc);
	}

	@Override
	protected NextAction delete(Scanner sc) {
		ResultWithData<Product> requestResult = requestProduct(sc);
		if (requestResult.isFailure())
			return NextAction.Continue(requestResult.getMessage());

		if (localRepository.isInUse(requestResult.getData()))
			return NextAction.Continue("O produto não pode ser removido pois está em um pedido.");

		localRepository.delete(requestResult.getData().getId());

		return NextAction.Continue("Produto removido.");
	}

	private List<IMenuOption> getDefaultFieldOptions(Product product) {
		List<IMenuOption> options = new ArrayList<>();

		options.add(new StringCrudField("Nome", "Insira o nome:", product::setName));
		options.add(new StringCrudField("Descrição", "Insira a descrição:", product::setDescription));
		options.add(new MethodMenuOption("Editar estoque", (sc) -> new StockCrud().update(product.getStock(), sc)));

		return options;
	}

	private NextAction filterByName(Scanner sc) {
		ResultWithData<String> requestResult = new StringCrudField("", "Insira o filtro:").requestData(sc);
		if (requestResult.isFailure())
			return NextAction.Continue(requestResult.getMessage());

		List<Product> filteredResults = localRepository.getByName(requestResult.getData());
		if (filteredResults.size() == 0)
			return NextAction.Continue("Não houveram resultados.");

		getDefaultConsoleTable().setData(filteredResults).build();
		super.waitForEnter(sc);

		return NextAction.Continue();
	}

	private ResultWithData<Product> requestProduct(Scanner sc) {
		if (Program.applicationData.supplierRepository.getCount() == 0)
			return ResultWithData.failure("Nenhum produto cadastrado.");

		ResultWithData<Integer> requestResult = new IntegerCrudField("", "Insira o ID do registro: ").requestData(sc);
		if (requestResult.isFailure())
			return ResultWithData.failure(requestResult.getMessage());

		Product selectedProduct = localRepository.getByID(requestResult.getData());
		if (selectedProduct == null)
			return ResultWithData.failure("Produto não encontrado.");

		return ResultWithData.success(selectedProduct);
	}

	private void addDefaultCrudOptions() {

		options.add(new MethodMenuOption("Criar", this::create));
		options.add(new MethodMenuOption("Alterar", this::update));
		options.add(new MethodMenuOption("Excluir", this::delete));
	}
}
