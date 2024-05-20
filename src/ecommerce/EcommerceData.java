package ecommerce;

import java.util.ArrayList;

import repositories.Repository;
import supplier.Supplier;

public final class EcommerceData {
	public static final Repository<Supplier> supplierRepository = new Repository<Supplier>(new ArrayList<Supplier>());
}
