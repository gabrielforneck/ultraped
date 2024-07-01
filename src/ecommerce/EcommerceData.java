package ecommerce;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import customer.Customer;
import customer.repository.CustomerRepository;
import order.Order;
import order.OrderProduct;
import products.Product;
import result.Result;
import result.ResultWithData;
import supplier.repository.SupplierRepository;

public class EcommerceData {
	private static final String FILE_LOCATION = "data.upd";

	@SerializedName("supplierRepository")
	public SupplierRepository supplierRepository = new SupplierRepository();

	@SerializedName("customerRepository")
	public CustomerRepository customerRepository = new CustomerRepository();

	public static ResultWithData<EcommerceData> loadAll() {
		File f = new File(FILE_LOCATION);
		if (!f.exists())
			return ResultWithData.success(new EcommerceData());

		if (!f.isFile())
			return ResultWithData.failure("O caminho do arquivo é uma pasta.");

		EcommerceData ec;
		Gson gson = new Gson();

		try (FileReader reader = new FileReader(f)) {
			ec = gson.fromJson(reader, EcommerceData.class);
		} catch (IOException ex) {
			return ResultWithData.failure(ex.getMessage());
		}

		return ResultWithData.success(ec.adjustProductReferences());
	}

	public Result saveAll() {
		Gson gson = new Gson();

		try (FileWriter writer = new FileWriter(FILE_LOCATION)) {
			gson.toJson(this, writer);
		} catch (Exception ex) {
			return Result.failure(ex.getMessage());
		}

		return Result.success();
	}

	private EcommerceData adjustProductReferences() {
		List<Product> allProducts = supplierRepository.getAllProducts();

		for (Product p : allProducts) {
			for (Customer c : customerRepository.getAll()) {
				for (Order o : c.getOrders()) {
					for (int i = 0; i < o.getOrderProductsQuantity(); i++) {
						OrderProduct op = o.getOrderProduct(i);
						if (op.getProduct().getId() == p.getId())
							op.setProduct(p);
					}
				}
			}
		}
		
		return this;
	}
}
