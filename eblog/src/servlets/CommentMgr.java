package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CommentMgr
 */
public class CommentMgr extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommentMgr() {
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
		/*
		 * <form action="http://localhost:8080/eblog/PostMgr" method="post">
		 * <textarea cols=10 rows=5 name='comment'></textarea> <input
		 * type='hidden'
		 * value=<jsp:scriptlet>out.println(request.getParameter("id"
		 * ));</jsp:scriptlet> name='postid'></input> <input type=submit
		 * value=post></input></form>
		 * 
		 * comments, post_id, user, date_posted
		 */
		HttpSession s = request.getSession(true);
		DbManager man = new DbManager();
		String id = request.getParameter("postid");
		ArrayList<String> comment = new ArrayList<String>();
		comment.add(request.getParameter("comment"));
		comment.add(request.getParameter("postid"));
		comment.add(s.getAttribute("first").toString());
		if (man.createComment(comment) == true) {
			s.setAttribute("status", "Unable to comment");
			response.sendRedirect("comment.jsp?id="+id);
		}
	}

}
