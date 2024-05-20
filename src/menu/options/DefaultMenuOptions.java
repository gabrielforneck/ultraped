package menu.options;

import consoleinterface.nextaction.NextAction;

public final class DefaultMenuOptions {
	
	public static MethodMenuOption cancelOption() {
		return new MethodMenuOption("Cancelar", (sc) -> NextAction.Exit());
	}
	
	public static MethodMenuOption backOption() {
		return new MethodMenuOption("Voltar", (sc) -> NextAction.Exit());
	}
	
	public static MethodMenuOption acceptOption() {
		return new MethodMenuOption("Aceitar", (sc) -> NextAction.Exit());
	}
}
