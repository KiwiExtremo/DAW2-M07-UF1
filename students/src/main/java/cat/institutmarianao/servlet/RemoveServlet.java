package cat.institutmarianao.servlet;

import java.io.IOException;

import cat.institutmarianao.domain.Student;
import cat.institutmarianao.repository.impl.RepositoryImpl;
import jakarta.ejb.EJB;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RemoveServlet
 */
@WebServlet("/remove")
public class RemoveServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private RepositoryImpl repo;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dni = request.getParameter("dni");

		if (!dni.isBlank()) {
			Student student = new Student();
			student.setDni(request.getParameter("dni"));

			repo.removeStudent(student);
		}

		response.sendRedirect("students");
	}
}
