package cat.institutmarianao.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Student {
	@NotEmpty(message = "The DNI can't be empty")
	private String dni;

	@NotEmpty(message = "The name can't be empty")
	@Size(max = 200, message = "The name is too long")
	private String name;

	@NotEmpty(message = "The surname can't be empty")
	@Size(max = 200, message = "The surname is too long")
	private String surname;

	@Email(regexp = "^$|^(\\w+)@(\\w+)[.](\\w+)$", message = "The e-mail is not valid")
	private String email;

	@NotNull
	private String cycle;

	@NotEmpty(message = "You must choose at least one (1) module")
	private String[] modules;

	public Student() {
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni.toUpperCase();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public String[] getModules() {
		return modules;
	}

	public void setModules(String[] modules) {
		this.modules = modules;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Student other = (Student) obj;
		if (dni == null) {
			if (other.dni != null) {
				return false;
			}
		} else if (!dni.equalsIgnoreCase(other.dni)) {
			return false;
		}
		return true;
	}

}
