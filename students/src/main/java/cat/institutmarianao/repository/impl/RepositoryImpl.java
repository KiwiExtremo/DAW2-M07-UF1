package cat.institutmarianao.repository.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cat.institutmarianao.domain.Student;
import cat.institutmarianao.repository.Repository;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

@Stateless
@LocalBean
public class RepositoryImpl implements Repository {
	private static final List<Student> students;

	private static final Map<String, List<String>> cyclesModules;

	private static final String[] ASIX = { "M01 - Implantació de sistemes operatius", "M02 - Gestió de bases de dades",
			"M03 - Programació bàsica", "M04 - Llenguatges de marques i sistemes de gestió d'informació",
			"M05 - Fonaments de maquinari", "M06 - Administració de sistemes operatius",
			"M07 - Planificació i administració de xarxes", "M08 - Serveis de xarxa i Internet",
			"M09 - Implantació d'aplicacions web", "M10 - Administració de sistemes gestors de bases de dades",
			"M11 - Seguretat i alta disponibilitat", "M12 - Formació i orientació laboral",
			"M13 - Empresa i iniciativa emprenedora", "M14 - Projecte d'administració de sistemes informàtics en xarxa",
			"M15 - Formació en centres de treball" };

	private static final String[] DAM = { "M01 - Sistemes informàtics", "M02 - Bases de dades", "M03A - Programació",
			"M03B - Programació orientada a objectes", "M04 - Llenguatges de marques i sistemes de gestió d'informació",
			"M05 - Entorns de desenvolupament", "M06 - Accés a dades", "M07 - Desenvolupament d'interfícies",
			"M08 - Programació multimèdia i dispositius mòbils", "M09 - Programació de serveis i processos",
			"M10 - Sistemes de gestió empresarial", "M11 - Formació i orientació laboral",
			"M12 - Empresa i iniciativa emprenedora", "M13 - Projecte de desenvolupament d'aplicacions multiplataforma",
			"M14 - Formació en centres de treball" };

	private static final String[] DAW = { "M01 - Sistemes informàtics", "M02 - Bases de dades", "M03A - Programació",
			"M03B - Programació orientada a objectes", "M04 - Llenguatges de marques i sistemes de gestió d'informació",
			"M05 - Entorns de desenvolupament", "M06 - Desenvolupament web en l'entorn client",
			"M07 - Desenvolupament web en l'entorn servidor", "M08 - Desplegament d'aplicacions web",
			"M09 - Disseny d'interfícies web", "M10 - Formació i orientació laboral",
			"M11 - Empresa i iniciativa emprenedora", "M12 - Projecte de desenvolupament d'aplicacions web",
			"M13 - Formació en centres de treball" };

	static {
		// Init repository
		cyclesModules = new HashMap<>(3);

		cyclesModules.put("ASIX", Arrays.asList(ASIX));
		cyclesModules.put("DAM", Arrays.asList(DAM));
		cyclesModules.put("DAW", Arrays.asList(DAW));

		students = new ArrayList<>();

		// Esborrar aquest usuari al acabar de fer proves
		String[] modules = { "M01 - Sistemes informàtics", "M02 - Bases de dades",
				"M04 - Llenguatges de marques i sistemes de gestió d'informació", "M05 - Entorns de desenvolupament",
				"M06 - Desenvolupament web en l'entorn client", "M07 - Desenvolupament web en l'entorn servidor",
				"M08 - Desplegament d'aplicacions web", "M09 - Disseny d'interfícies web",
				"M12 - Projecte de desenvolupament d'aplicacions web" };

		Student student = new Student();
		student.setName("Ariel");
		student.setSurname("Gómez Valiente");
		student.setDni("48164276E");
		student.setEmail("thearielcompany@gmail.com");
		student.setCycle("DAW");
		student.setModules(modules);

		students.add(student);
	}

	// Repository operations
	@Override
	public List<String> getCycles() {
		List<String> cycles = new ArrayList<>(cyclesModules.keySet());
		Collections.sort(cycles);
		return cycles;
	}

	@Override
	public List<String> getModules(String cycle) {
		return cyclesModules.get(cycle);
	}

	@Override
	public List<Student> getStudents() {
		students.sort((s, t) -> s.getName().compareTo(t.getName()));
		return students;
	}

	@Override
	public boolean studentExists(Student student) {
		return students.contains(student);
	}

	@Override
	public void addStudent(Student student) {
		students.add(student);
	}

	@Override
	public void removeStudent(Student student) {
		students.remove(student);
	}
}
