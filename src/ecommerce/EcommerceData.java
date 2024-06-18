package ecommerce;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import repositories.Repository;
import result.Result;
import result.ResultWithData;
import supplier.Supplier;

public class EcommerceData {
	private static final String FILE_LOCATION = "data/";
	
	public Repository<Supplier> supplierRepository = new Repository<Supplier>(new ArrayList<Supplier>());
	
	public static ResultWithData<EcommerceData> loadAll() {
		EcommerceData ec;

		Gson gson = new Gson();

		try (FileReader reader = new FileReader(FILE_LOCATION)) {
			ec = gson.fromJson(reader, EcommerceData.class);
		} catch (IOException ex) {
			return ResultWithData.failure("Não foi possível carregar os dados da aplicação.");
		}

		return ResultWithData.success(ec);
	}
	
	public Result saveAll(List<Supplier> suppliers) throws IOException {
		Gson gson = new Gson();
		
		FileWriter writer = new FileWriter(FILE_LOCATION);
		gson.toJson(suppliers, writer);

		
		return Result.success();
	}
}
