package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connections.User;

/**
 * Servlet implementation class Logmein
 */
public class Logmein extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Logmein() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		DbManager manage = new DbManager();
		if (request.getParameter("id").equalsIgnoreCase("login")) {
			User bean = new User(request.getParameter("username"), request
					.getParameter("password"), null);
			manage.login(bean);
			if (bean.isValid()) {
				session.setAttribute("username", bean.getUser());
				session.setAttribute("first", bean.getFname());
				session.setAttribute("last", bean.getLname());
				session.setAttribute("email", bean.getEmail());
				request.getRequestDispatcher("posts.jsp").forward(request,
						response);
			} else {
				request.setAttribute("error", "invalid credentials");
				request.getRequestDispatcher("index.jsp").forward(request,
						response);
			}

		}
	}

}
