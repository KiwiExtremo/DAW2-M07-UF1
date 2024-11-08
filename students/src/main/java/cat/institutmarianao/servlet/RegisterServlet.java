package cat.institutmarianao.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import cat.institutmarianao.domain.Student;
import cat.institutmarianao.repository.impl.RepositoryImpl;
import jakarta.annotation.Resource;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private RepositoryImpl repo;

	@Resource
	private Validator validator;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		initializeData(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Student inputStudent = new Student();

		try {
			BeanUtils.populate(inputStudent, request.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		Map<String, String> errorMap = new HashMap<>();

		for (ConstraintViolation<Student> c : validator.validate(inputStudent)) {
			errorMap.put(c.getPropertyPath().toString(), c.getMessage());
		}

		if (!request.getParameter("dni").isEmpty()) {
			errorMap = checkDNI(errorMap, request.getParameter("dni"));
		}

		if (errorMap.isEmpty()) {
			// TODO check that the student is not already on the repository

			// Add the student to the repository
			repo.addStudent(inputStudent);

			// Go back to students.jsp
			request.setAttribute("studentDNI", request.getParameter("dni"));

			RequestDispatcher rd;

			rd = request.getRequestDispatcher("StudentsServlet");

			rd.forward(request, response);

		} else {
			// create errors and send them back to register.jsp
			request.setAttribute("errors", errorMap);
			request.setAttribute("studentData", inputStudent);

			initializeData(request, response);
		}
	}

	private void initializeData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cycle = request.getParameter("cycle");

		List<String> modules = repo.getModules(cycle);

		request.setAttribute("cycle", cycle);
		request.setAttribute("modules", modules);

		// TODO set alerts on each field that has an error, and fill all fields with
		// content

		RequestDispatcher rd;

		rd = request.getRequestDispatcher("register.jsp");

		rd.forward(request, response);
	}

	private Map<String, String> checkDNI(Map<String, String> errorMap, String dni) {
		String charsDNI = "TRWAGMYFPDXBNJZSQVHLCKE";

		String intPartDNI = dni.substring(0, 8);
		char charPartDNI = dni.charAt(8);

		int totalSumDNI = Integer.parseInt(intPartDNI) % 23;

		if (charsDNI.charAt(totalSumDNI) != charPartDNI) {
			errorMap.put("dni", "The DNI format is not valid");
		}

		return errorMap;
	}
}
