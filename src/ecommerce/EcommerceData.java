package ecommerce;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import result.Result;
import result.ResultWithData;
import supplier.Supplier;
import supplier.repository.SupplierRepository;

public class EcommerceData {
	private static final String FILE_LOCATION = "data.upd";
	
	public SupplierRepository supplierRepository = new SupplierRepository();
	
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
	
	public Result saveAll(List<Supplier> suppliers) {
		Gson gson = new Gson();
		
		try (FileWriter writer = new FileWriter(FILE_LOCATION)) {			
			gson.toJson(suppliers, writer);
		} catch (Exception ex) {
			return Result.failure("Ocorreu um erro ao salvar os dados da aplicação: " + ex.getMessage());
		}
		
		return Result.success();
	}
}
