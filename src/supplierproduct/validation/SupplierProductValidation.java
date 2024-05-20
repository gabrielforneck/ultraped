package supplierproduct.validation;

import java.util.ArrayList;
import java.util.List;

import ecommerce.EcommerceData;
import products.Product;
import result.Result;
import supplierproduct.SupplierProduct;

public final class SupplierProductValidation {

	public static Result validateSupplierID(int supplierID) {
		if (supplierID <= 0)
			return Result.Failure("Código do fornecedor inválido.");
		
		if (!EcommerceData.supplierRepository.exists(supplierID))
			return Result.Failure("O fornecedor não existe.");
		
		return Result.Success();
	}
	
	public static Result validateProductID(int productID) {
		if (productID <= 0)
			return Result.Failure("Código do produto inválido.");
		
		if (!EcommerceData.productRepository.exists(productID))
			return Result.Failure("O produto não existe.");
		
		return Result.Success();
	}
	
	public static Result validateAll(SupplierProduct product) {
		Result validationResult = validateSupplierID(product.getSupplierID());
		if (validationResult.isFailure())
			return validationResult;

		return validateProductID(product.getProductID());
	}
	
	public static boolean isASupplierProduct(SupplierProduct supplierProduct) {
		return getID(supplierProduct) > 0;
	}
	
	public static int getID(SupplierProduct supplierProduct) {
		List<SupplierProduct> supplierProducts = EcommerceData.supplierProductRepository.getData();
		
		for (SupplierProduct sp : supplierProducts)
			if (sp.getProductID() == supplierProduct.getProductID() && sp.getSupplierID() == supplierProduct.getSupplierID())
				return sp.getId();
		
		return 0;
	}
	
	public static List<Product> getAllProductsBySupplierID(int supplierID) {
		List<SupplierProduct> supplierProducts = EcommerceData.supplierProductRepository.getData();
		List<Product> products = new ArrayList<>();
		
		for (SupplierProduct sp : supplierProducts)
			if (sp.getSupplierID() == supplierID)
				products.add(EcommerceData.productRepository.getByID(sp.getProductID()));
		
		return products;
	}
}
