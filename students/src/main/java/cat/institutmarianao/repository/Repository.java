package cat.institutmarianao.repository;

import java.util.List;

import cat.institutmarianao.domain.Student;
import jakarta.ejb.Local;

@Local
public interface Repository {
	public List<String> getCycles();

	public List<String> getModules(String cycle);

	public List<Student> getStudents();

	public boolean studentExists(Student student);

	public void addStudent(Student student);

	public void removeStudent(Student student);
}
