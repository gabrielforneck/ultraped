package supplier;

import exceptions.data.EmptyDataException;

public class Supplier {
	private int id;
	private String name;
	private String description;
	private String phone;
	private String email;

	public Supplier(int id, String name, String description, String phone, String email) {
		this.id = id;
		this.setName(name);
		this.setDescription(description);
		this.setPhone(phone);
		this.setEmail(email);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null || name.length() == 0)
			throw new EmptyDataException("O nome do fornecedor não pode ser vazio.");
		
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if (description == null || description.length() == 0)
			throw new EmptyDataException("A descrição do fornecedor não pode ser vazio.");

		this.description = description;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		if (phone == null || phone.length() == 0)
			throw new EmptyDataException("O telefone do fornecedor não pode ser vazio.");
		
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (email == null || email.length() == 0)
			throw new EmptyDataException("O e-mail do fornecedor não pode ser vazio.");

		this.email = email;
	}
}
