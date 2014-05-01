package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PostMgr
 */
public class PostMgr extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PostMgr() {
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
		// TODO Auto-generated method stub
		HttpSession s = request.getSession(true);
		PrintWriter pr = response.getWriter();
		response.setContentType("text/html");
		pr.println(request.getParameter("post") + " by "
				+ s.getAttribute("first"));
		String user = (String) s.getAttribute("first");
		DbManager man = new DbManager();
		List<String> post = new ArrayList<String>();
		// ("insert into posts(headline, body,img,user,time)
		post.add(request.getParameter("headline"));
		post.add(request.getParameter("post"));
		post.add(request.getParameter(null));
		post.add(user);
		if (man.createPost(post) == true) {
			s.setAttribute("status", "Post was successful");
			response.sendRedirect("posts.jsp");
		} else {
			s.setAttribute("status", "Unable to post ");
			response.sendRedirect("posts.jsp");
		}

	}
}
