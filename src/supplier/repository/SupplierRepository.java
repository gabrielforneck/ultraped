package supplier.repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import result.Result;
import result.ResultWithData;
import supplier.Supplier;

public class SupplierRepository {

	public static final String FILE_LOCATION = "/data/suppliers.json";
	
	public Result Insert(Supplier s) {
		
		ResultWithData<List<Supplier>> suppliersResult = getAll();
		if (suppliersResult.isFailure()) return Result.failure(suppliersResult.getMessage());
		
		List<Supplier> suppliers = suppliersResult.getData();

		s.setId(getNextID(suppliers));
		suppliers.add(s);
		
		Result saveResult = saveToFile(suppliers);
		if (saveResult.isFailure())
			return Result.failure("Não foi possível salvar o novo registro no disco: " + saveResult.getMessage() + ".");
		
		return Result.success();
	}
	
	public Result Update(Supplier s) {
		ResultWithData<List<Supplier>> suppliersResult = getAll();
		if (suppliersResult.isFailure()) return Result.failure(suppliersResult.getMessage());
		
		List<Supplier> suppliers = suppliersResult.getData();
		
		int index = getIndexByID(s.getId(), suppliers);
		if (index == -1) return Result.failure("Registro não encontrado.");
		
		suppliers.set(index, s);
		
		Result saveResult = saveToFile(suppliers);
		if (saveResult.isFailure())
			return Result.failure("Não foi possível salvar as alterações em disco: " + saveResult.getMessage() + ".");
		
		return Result.success();
	}
	
	public Result Delete(int iD) {
		ResultWithData<List<Supplier>> suppliersResult = getAll();
		if (suppliersResult.isFailure()) return Result.failure(suppliersResult.getMessage());
		
		List<Supplier> suppliers = suppliersResult.getData();
		
		int index = getIndexByID(iD, suppliers);
		if (index == -1) return Result.failure("Registro não encontrado.");
		
		suppliers.remove(index);
		
		Result saveResult = saveToFile(suppliers);
		if (saveResult.isFailure())
			return Result.failure("Não foi possível remover o registro do disco: " + saveResult.getMessage() + ".");
		
		return Result.success();
	}
	
	public ResultWithData<Supplier> getByID(int iD) {
		ResultWithData<List<Supplier>> suppliersResult = getAll();
		if (suppliersResult.isFailure()) return ResultWithData.failure(suppliersResult.getMessage());
		
		return ResultWithData.success(getByID(iD, suppliersResult.getData()));
	}

	private Supplier getByID(int iD, List<Supplier> suppliers) {

		int index = getIndexByID(iD, suppliers);
		if (index == -1) return null;
		
		return suppliers.get(index);
	}
	
	private int getIndexByID(int iD, List<Supplier> suppliers) {

		if (suppliers == null) return -1;
		
		for (int i = 0; i < suppliers.size(); i++)
			if (suppliers.get(i).getId() == iD) return i;
		
		return -1;
	}
	
	public ResultWithData<Integer> getNextID() {
		ResultWithData<List<Supplier>> suppliersResult = getAll();
		if (suppliersResult.isFailure()) return ResultWithData.failure(suppliersResult.getMessage());
		
		return ResultWithData.success(getNextID(suppliersResult.getData()));
	}
	
	private int getNextID(List<Supplier> suppliers) {
		int maxID = 1;
		
		if (suppliers == null) return 1;
		
		for (Supplier s : suppliers)
			if (s.getId() > maxID) maxID = s.getId();
		
		return maxID+1;
	}

	public ResultWithData<List<Supplier>> getAll() {

		List<Supplier> suppliers;

		Gson gson = new Gson();

		try (FileReader reader = new FileReader(FILE_LOCATION)) {
			suppliers = gson.fromJson(reader, ArrayList.class);
		} catch (IOException ex) {
			return ResultWithData.failure("Não foi possível carregar os fornecedores do disco.");
		}

		return ResultWithData.success(suppliers);
	}
	
	public Result saveToFile(List<Supplier> suppliers) {
		Gson gson = new Gson();
		
		try (FileWriter writer = new FileWriter(FILE_LOCATION)) {
			gson.toJson(suppliers, writer);
		} catch (Exception e) {
			return Result.failure(e.getMessage());
		}
		
		return Result.success();
	}
}
