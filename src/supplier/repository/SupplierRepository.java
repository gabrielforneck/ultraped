package supplier.repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import result.Result;
import supplier.Supplier;

public class SupplierRepository {

	public static final String FILE_LOCATION = "/data/suppliers.json";
	
	public Result Insert(Supplier s) {
		
		List<Supplier> suppliers = getAll();
		if (suppliers == null) suppliers = new ArrayList<Supplier>();
		
		s.setId(getNextID(suppliers));
		
		suppliers.add(s);
		
		Result saveResult = saveToFile(suppliers);
		if (saveResult.isFailure())
			return Result.failure("Não foi possível salvar o novo registro no disco: " + saveResult.getMessage() + ".");
		
		return Result.success();
	}
	
	public Result Update(Supplier s) {
		List<Supplier> suppliers = getAll();
		
		int index = getIndexByID(s.getId(), suppliers);
		if (index == -1) return Result.failure("Registro não encontrado.");
		
		suppliers.set(index, s);
		
		Result saveResult = saveToFile(suppliers);
		if (saveResult.isFailure())
			return Result.failure("Não foi possível salvar as alterações em disco: " + saveResult.getMessage() + ".");
		
		return Result.success();
	}
	
	public Result Delete(int iD) {
		List<Supplier> suppliers = getAll();
		
		int index = getIndexByID(iD, suppliers);
		if (index == -1) return Result.failure("Registro não encontrado.");
		
		suppliers.remove(index);
		
		Result saveResult = saveToFile(suppliers);
		if (saveResult.isFailure())
			return Result.failure("Não foi possível remover o registro do disco: " + saveResult.getMessage() + ".");
		
		return Result.success();
	}
	
	public Supplier getByID(int iD) {
		return getByID(iD, getAll());
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
	
	public int getNextID() {
		return getNextID(getAll());
	}
	
	private int getNextID(List<Supplier> suppliers) {
		int maxID = 1;
		
		if (suppliers == null) return 1;
		
		for (Supplier s : suppliers)
			if (s.getId() > maxID) maxID = s.getId();
		
		return maxID+1;
	}

	public List<Supplier> getAll() {

		List<Supplier> suppliers;

		Gson gson = new Gson();

		try (FileReader reader = new FileReader(FILE_LOCATION)) {
			suppliers = gson.fromJson(reader, ArrayList.class);
		} catch (IOException ex) {
			return null;
		}

		return suppliers;
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
