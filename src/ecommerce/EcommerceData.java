package ecommerce;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import customer.repository.CostumerRepository;
import result.Result;
import result.ResultWithData;
import supplier.repository.SupplierRepository;

public class EcommerceData {
	private static final String FILE_LOCATION = "data.upd";
	
	@SerializedName("supplierRepository")
	public SupplierRepository supplierRepository = new SupplierRepository();
	
	@SerializedName("costumerRepository")
	public CostumerRepository costumerRepository = new CostumerRepository();
	
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

		return ResultWithData.success(ec);
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
}
