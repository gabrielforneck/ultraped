package crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;

import consoleinterface.nextaction.NextAction;
import menu.Menu;
import menu.options.MethodMenuOption;
import menu.options.interfaces.IMenuOption;
import repositories.EntityWithID;
import repositories.Repository;
import result.Result;
import result.ResultWithData;

public abstract class Crud extends Menu {

	protected Crud() {
		super();
		options = new ArrayList<>();
	}

	protected Crud(String title) {
		super(title);
	}

	protected Crud(List<IMenuOption> options) {
		super(options);
	}

	protected Crud(String title, List<IMenuOption> options) {
		super(title, options);
	}

	protected abstract void showDataAsTable();

	protected abstract NextAction create(Scanner sc);

	protected abstract NextAction update(Scanner sc);

	protected abstract NextAction delete(Scanner sc);

	@Override
	protected void constructMenu() {
		showTitle();
		System.out.println();
		showDataAsTable();
		System.out.println();
		showOptions();
	}

	protected Crud addDefaultCrudOptions() {

		options.add(new MethodMenuOption("Criar", (sc) -> create(sc)));
		options.add(new MethodMenuOption("Alterar", (sc) -> update(sc)));
		options.add(new MethodMenuOption("Excluir", (sc) -> delete(sc)));

		return this;
	}

	protected <T extends EntityWithID> ResultWithData<T> waitForId(Scanner sc, Repository<T> repository) {
		return waitForId(sc, repository, "Insira o ID do registro: ");
	}
	
	protected <T extends EntityWithID> ResultWithData<T> waitForId(Scanner sc, Repository<T> repository, String description) {
		if (repository.getData().size() == 0)
			return ResultWithData.failure("Não há registros para realizar esta ação.");

		int iD;
		System.out.print(description);

		try {
			iD = sc.nextInt();
			sc.nextLine();
		} catch (Exception ex) {
			sc.next();
			return ResultWithData.failure("Entrada inválida");
		}

		T record = repository.getByID(iD);
		if (record == null)
			return ResultWithData.failure("Registro não encontrado.");

		return ResultWithData.success(record);
	}

	protected <T extends EntityWithID> NextAction validateAndSaveNew(T newRecord, Function<T, Result> validator,
			Consumer<T> repository) {
		Result validationResult = validator.apply(newRecord);

		if (validationResult.isFailure())
			return NextAction.Continue(validationResult.getMessage());

		repository.accept(newRecord);
		return NextAction.Exit();
	}
}
