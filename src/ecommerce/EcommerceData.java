package ecommerce;

import java.util.ArrayList;

import products.Product;
import repositories.Repository;
import supplier.Supplier;
import supplierproduct.SupplierProduct;

public final class EcommerceData {
	public static final Repository<Supplier> supplierRepository = new Repository<Supplier>(new ArrayList<Supplier>());
	public static final Repository<Product> productRepository = new Repository<Product>(new ArrayList<Product>());
	public static final Repository<SupplierProduct> supplierProductRepository = new Repository<SupplierProduct>(new ArrayList<SupplierProduct>());
}
