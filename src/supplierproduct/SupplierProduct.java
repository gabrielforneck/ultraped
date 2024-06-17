package supplierproduct;

import repositories.EntityWithID;

@Deprecated
public class SupplierProduct extends EntityWithID {
	private int supplierID;
	private int productID;

	@Deprecated
	public SupplierProduct() {
	}

	@Deprecated
	public int getSupplierID() {
		return supplierID;
	}

	@Deprecated
	public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
	}

	@Deprecated
	public int getProductID() {
		return productID;
	}

	@Deprecated
	public void setProductID(int productID) {
		this.productID = productID;
	}
}
