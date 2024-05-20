package supplierproduct;

import repositories.EntityWithID;

public class SupplierProduct extends EntityWithID {
	private int supplierID;
	private int productID;

	public SupplierProduct() {
	}

	public int getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}
}
