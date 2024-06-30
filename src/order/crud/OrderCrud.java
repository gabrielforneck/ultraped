//package order.crud;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//import consoleinterface.nextaction.NextAction;
//import crud.Crud;
//import menu.Menu;
//import menu.options.MethodMenuOption;
//import menu.options.interfaces.IMenuOption;
//import order.Order;
//import supplier.validation.SupplierValidations;
//
//public class OrderCrud extends Crud {
//
//	@Override
//	protected void showDataAsTable() {
//		// TODO Auto-generated method stub
//		
//	}
//	
//	private NextAction create() {
//		Order dummyOrder = new Order();
//	}
//	
//	private NextAction updateRecord(String title, Order record, Scanner sc) {
//		List<IMenuOption> options = getDefaultFieldOptions(record);
//		//options.add(new MethodMenuOption("Concluir", (scanner) -> SupplierValidations.validateAll(record).toExitNextActionIfSucces()));
//
//		return new Menu(title, options).setDetailsToShow(record).execute(sc);
//	}
//	
//	private List<IMenuOption> getDefaultFieldOptions(Order order) {
//		List<IMenuOption> options = new ArrayList<>();
//		
//	}
//
//}
