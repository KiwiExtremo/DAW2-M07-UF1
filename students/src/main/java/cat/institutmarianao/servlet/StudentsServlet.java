package cat.institutmarianao.servlet;

import java.io.IOException;
import java.util.List;

import cat.institutmarianao.domain.Student;
import cat.institutmarianao.repository.impl.RepositoryImpl;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StudentsServlet
 */
@WebServlet("/StudentsServlet")
public class StudentsServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private RepositoryImpl repo;

	public StudentsServlet() {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		initializeData(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		initializeData(request, response);
	}

	private void initializeData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		List<Student> students = repo.getStudents();
		List<String> cycles = repo.getCycles();

		request.setAttribute("students", students);
		request.setAttribute("cycles", cycles);

		RequestDispatcher rd;

		rd = request.getRequestDispatcher("students.jsp");

		rd.forward(request, response);
	}
}
