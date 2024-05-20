package ecommerce;

import java.util.ArrayList;

import products.Product;
import repositories.Repository;
import supplier.Supplier;

public final class EcommerceData {
	public static final Repository<Supplier> supplierRepository = new Repository<Supplier>(new ArrayList<Supplier>());
	public static final Repository<Product> productRepository = new Repository<Product>(new ArrayList<Product>());
}
